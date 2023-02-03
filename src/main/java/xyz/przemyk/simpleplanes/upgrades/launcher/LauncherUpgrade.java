package xyz.przemyk.simpleplanes.upgrades.launcher;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.przemyk.simpleplanes.client.gui.PlaneInventoryScreen;
import xyz.przemyk.simpleplanes.compat.MrCrayfishGunCompat;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;
import xyz.przemyk.simpleplanes.setup.SimplePlanesUpgrades;
import xyz.przemyk.simpleplanes.upgrades.Upgrade;

import java.util.function.Function;

public class LauncherUpgrade extends Upgrade {

    public final ItemStackHandler itemStackHandler = new ItemStackHandler();
    public final LazyOptional<ItemStackHandler> itemStackHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);

    public LauncherUpgrade(PlaneEntity planeEntity) {
        super(SimplePlanesUpgrades.LAUNCHER.get(), planeEntity);
    }

    public void use(Player player, double offx, double offz, double offy, boolean left) {
        Vector3f motion1 = planeEntity.transformPos(new Vector3f(0, -0.25f, (float) (1 + planeEntity.getDeltaMovement().length())));
        Vec3 motion = new Vec3(motion1);
        Level level = player.level;

        double aim = Math.toRadians(planeEntity.getRotationVector().y * -1);
        Vec3 p = planeEntity.position();
        double fx = Math.sin(aim);
        double fz = Math.cos(aim);
        double sz = fx * -1;
        Vec3 lp = new Vec3(p.x + (fz * offx) + (fx * offz), 0.0D, p.z + (sz * offx) + (fz * offz));
        Vec3 rp = new Vec3(p.x - (fz * offx) + (fx * offz), 0.0D, p.z - (sz * offx) + (fz * offz));

        updateClient();

        ItemStack itemStack = itemStackHandler.getStackInSlot(0);
        Item item = itemStack.getItem();

        if(left) {
            if (item == Items.FIREWORK_ROCKET) {
                FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(level, itemStack, lp.x, p.y + offy, lp.z, true);
                fireworkrocketentity.shoot(-motion.x, -motion.y, -motion.z, -(float) Math.max(0.5F, motion.length() * 1.5), 1.0F);
                level.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, lp.x, p.y + offy, lp.z, 0 - (fx * 0.05), 0, 0 - (fz * 0.05));
                level.addFreshEntity(fireworkrocketentity);

                if (!player.isCreative()) {
                    itemStackHandler.extractItem(0, 1, false);
                }
            } else {
                ModList.get().getModContainerById("cgm").ifPresent(cgm -> MrCrayfishGunCompat.shooterBehaviour("launcher", item, itemStackHandler, level, player, motion, lp.x, p.y + offy, lp.z));
            }
        } else {
            if (item == Items.FIREWORK_ROCKET) {
                FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(level, itemStack, rp.x, p.y + offy, rp.z, true);
                fireworkrocketentity.shoot(-motion.x, -motion.y, -motion.z, -(float) Math.max(0.5F, motion.length() * 1.5), 1.0F);
                level.addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, rp.x, p.y + offy, rp.z, 0 - (fx * 0.05), 0, 0 - (fz * 0.05));
                level.addFreshEntity(fireworkrocketentity);
            } else {
                ModList.get().getModContainerById("cgm").ifPresent(cgm -> MrCrayfishGunCompat.shooterBehaviour("launcher", item, itemStackHandler, level, player, motion, rp.x, p.y + offy, rp.z));
            }
        }

    }
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        itemStackHandlerLazyOptional.invalidate();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundTag = new CompoundTag();
        compoundTag.put("item", itemStackHandler.serializeNBT());
        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag compoundTag) {
        itemStackHandler.deserializeNBT(compoundTag.getCompound("item"));
    }

    @Override
    public void writePacket(FriendlyByteBuf buffer) {
        buffer.writeItem(itemStackHandler.getStackInSlot(0));
    }

    @Override
    public void readPacket(FriendlyByteBuf buffer) {
        itemStackHandler.setStackInSlot(0, buffer.readItem());
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return itemStackHandlerLazyOptional.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onRemoved() {
        planeEntity.spawnAtLocation(Items.DISPENSER);
        planeEntity.spawnAtLocation(itemStackHandler.getStackInSlot(0));
    }
    //tu mieszka amunicja
    @Override
    public void addContainerData(Function<Slot, Slot> addSlot, Function<DataSlot, DataSlot> addDataSlot) {
        addSlot.apply(new SlotItemHandler(itemStackHandler, 0, 134, 62));
    }

    @Override
    public void renderScreenBg(PoseStack poseStack, int x, int y, float partialTicks, PlaneInventoryScreen screen) {
        screen.blit(poseStack, screen.getGuiLeft() + 133, screen.getGuiTop() + 61, 226, 0, 18, 18);
    }

}
