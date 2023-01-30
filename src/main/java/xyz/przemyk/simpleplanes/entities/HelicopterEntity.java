package xyz.przemyk.simpleplanes.entities;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import xyz.przemyk.simpleplanes.misc.MathUtil;
import xyz.przemyk.simpleplanes.setup.SimplePlanesConfig;
import xyz.przemyk.simpleplanes.setup.SimplePlanesItems;
import xyz.przemyk.simpleplanes.setup.SimplePlanesUpgrades;
import xyz.przemyk.simpleplanes.upgrades.UpgradeType;

import static xyz.przemyk.simpleplanes.misc.MathUtil.lerpAngle;

//numero tres in the grand pecking order
public class HelicopterEntity extends LargePlaneEntity {

    public static final EntityDataAccessor<Boolean> MOVE_UP = SynchedEntityData.defineId(HelicopterEntity.class, EntityDataSerializers.BOOLEAN);
    public HelicopterEntity(EntityType<? extends HelicopterEntity> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(MOVE_UP, false);
    }

    @Override
    protected TempMotionVars getMotionVars() {
        TempMotionVars motionTempMotionVars = super.getMotionVars();
        motionTempMotionVars.passiveEnginePush = 0.1f;
        motionTempMotionVars.push = 0.015f;
        motionTempMotionVars.dragQuad *= 10;
        motionTempMotionVars.dragMul *= 3;
        motionTempMotionVars.gravity = -0.1;
        motionTempMotionVars.maxSpeed = 1;
        motionTempMotionVars.maxLift = 2;
        motionTempMotionVars.liftFactor = 10;
        return motionTempMotionVars;
    }
/*
    @Override
    protected Vector3f getTickPush(TempMotionVars tempMotionVars) {
        if (tempMotionVars.moveForward < 0 && isPowered() && !entityData.get(MOVE_UP)) {
            tempMotionVars.push *= 0.2;
        }
        if (tempMotionVars.moveForward > 0 && isPowered() && !entityData.get(MOVE_UP)) {
            tempMotionVars.push *= 1.5;
        }

        if (isPowered() && entityData.get(MOVE_UP) && tempMotionVars.moveForward >= 0) {
            tempMotionVars.push += 0.01 * getThrottle();
        }
        return transformPos(new Vector3f(0, tempMotionVars.push, 0));
    }

*/
    @Override
    protected Vector3f getTickPush(TempMotionVars tempMotionVars) {
        tempMotionVars.push *= getThrottle();
        return transformPos(new Vector3f(0, tempMotionVars.push, 0));
    }

    @Override
    protected void tickRoll(TempMotionVars tempMotionVars) {
        if (getHealth() <= 0) {
            setXRot(-2);
            setDeltaMovement(getDeltaMovement().add(0, -0.04, 0));
        }

        double turn = 0;

        if (getOnGround() || isOnWater()) {
            turn = tempMotionVars.moveStrafing > 0 ? 3 : tempMotionVars.moveStrafing == 0 ? 0 : -3;
            rotationRoll = lerpAngle(0.1f, rotationRoll, 0);

        } else {
            if (tempMotionVars.moveStrafing > 0.0f) {
                rollSpeed += 0.25f;
            } else if (tempMotionVars.moveStrafing < 0.0f) {
                rollSpeed -= 0.25f;
            } else {
                if (rollSpeed < 0) {
                    rollSpeed += 0.2f;
                } else if (rollSpeed > 0) {
                    rollSpeed -= 0.2f;
                }
            }

            rollSpeed = Mth.clamp(rollSpeed, -5.0f, 5.0f);
            rotationRoll += rollSpeed;
        }

        setYRot((float) (getYRot() - turn));
    }

    /*
    @Override
    protected void tickYaw() {}
*/
    @Override
    protected boolean tickOnGround(TempMotionVars tempMotionVars) {
        float push = tempMotionVars.push;
        super.tickOnGround(tempMotionVars);
        /*
        if (entityData.get(MOVE_UP)) {

            tempMotionVars.push = push;
        } else {
            tempMotionVars.push = 0;
        }
        */
        return false;
    }

    @Override
    protected float getGroundPitch() {
        return 0;
    }
    @Override
    public int getFuelCost() {
        return SimplePlanesConfig.HELICOPTER_FUEL_COST.get();
    }

    @Override
    protected Quaternion tickRotateMotion(TempMotionVars tempMotionVars, Quaternion q, Vec3 motion) {
        return q;
    }
/*
    @Override
    protected void tickRoll(TempMotionVars tempMotionVars) {
        if (getHealth() <= 0) {
            setYRot(getYRot() + (getId() % 2 == 0 ? 16.0f : -16.0f));
            return;
        }

        if (!entityData.get(MOVE_UP)) {
            setYRot(getYRot() - tempMotionVars.moveStrafing * 2);
            if (tempMotionVars.moveForward > 0 && !onGround) {
                rotationRoll = MathUtil.lerpAngle(0.1f, rotationRoll, tempMotionVars.moveStrafing * 30);
            } else {
                rotationRoll = MathUtil.lerpAngle(0.1f, rotationRoll, 0);
            }
        } else if (!onGround) {
            rotationRoll = MathUtil.lerpAngle(0.1f, rotationRoll, tempMotionVars.moveStrafing * 50);
        }
    }
*/
    @Override
    public void positionRider(Entity passenger) {
        positionRiderGeneric(passenger);
        int index = getPassengers().indexOf(passenger);

        if (index == 0) {
            Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset()), 0.5f));
            passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
        } else {
            if (hasLargeUpgrade) {
                index++;
            }
            switch (index) {
                case 1 -> {
                    Vector3f pos = transformPos(new Vector3f(0, (float) (getPassengersRidingOffset() + getEntityYOffset(passenger)), -0.5f));
                    passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
                }
                case 2 -> {
                    Vector3f pos = transformPos(new Vector3f(-1, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset() - 0.4f), 0));
                    passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
                }
                case 3 -> {
                    Vector3f pos = transformPos(new Vector3f(1, (float) (getPassengersRidingOffset() + passenger.getMyRidingOffset()) - 0.4f, 0));
                    passenger.setPos(getX() + pos.x(), getY() + pos.y(), getZ() + pos.z());
                }
            }
        }
    }

    @Override
    public double getEntityYOffset(Entity passenger) {
        if (passenger instanceof Villager) {
            return ((Villager) passenger).isBaby() ? -0.1 : -0.35D;
        }
        return passenger.getMyRidingOffset();
    }

    @Override
    protected Item getItem() {
        return SimplePlanesItems.HELICOPTER_ITEM.get();
    }

    @Override
    public boolean canAddUpgrade(UpgradeType upgradeType) {
        if (upgradeType == SimplePlanesUpgrades.SOLAR_PANEL.get()) {
            return false;
        }
        return super.canAddUpgrade(upgradeType);
    }

    @Override
    public double getCameraDistanceMultiplayer() {
        return SimplePlanesConfig.HELI_CAMERA_DISTANCE_MULTIPLIER.get();
    }

    public void setMoveUp(boolean up) {
        entityData.set(MOVE_UP, up);
    }

}
