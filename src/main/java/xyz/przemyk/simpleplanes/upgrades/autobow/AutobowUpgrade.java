package xyz.przemyk.simpleplanes.upgrades.autobow;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SpectralArrow;
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
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.przemyk.simpleplanes.client.gui.PlaneInventoryScreen;
import xyz.przemyk.simpleplanes.entities.PlaneEntity;
import xyz.przemyk.simpleplanes.setup.SimplePlanesUpgrades;
import xyz.przemyk.simpleplanes.upgrades.Upgrade;
import java.util.function.Function;

import static net.minecraft.Util.getRandom;

public class AutobowUpgrade extends Upgrade {

    public final ItemStackHandler itemStackHandler = new ItemStackHandler();
    public final LazyOptional<ItemStackHandler> itemStackHandlerLazyOptional = LazyOptional.of(() -> itemStackHandler);

    public AutobowUpgrade(PlaneEntity planeEntity) {
        super(SimplePlanesUpgrades.AUTOBOW.get(), planeEntity);
    }

    public void use(Player player, double offx, double offz, double offy) {
        Vector3f motion1 = planeEntity.transformPos(new Vector3f(0, -0.25f, (float) (1 + planeEntity.getDeltaMovement().length())));
        Vec3 motion = new Vec3(motion1);
        Level level = player.level;

        updateClient();

        ItemStack itemStack = itemStackHandler.getStackInSlot(0);
        Item item = itemStack.getItem();

        //rotating offset position around the planeEntity
        Vector3f transpos = planeEntity.transformPos(new Vector3f((float) offx, (float) offy,(float) offz));

        if (item == Items.ARROW) {
            Arrow arrowEntity = new Arrow(level,
                    planeEntity.getX() + transpos.x(), planeEntity.getY() + transpos.y(), planeEntity.getZ() + transpos.z());
            arrowEntity.setOwner(player);
            arrowEntity.setDeltaMovement(motion.scale(Math.max(motion.length() * 1.5, 3) / motion.length()));
            if (!player.isCreative()) {
                itemStackHandler.extractItem(0, 1, false);
                arrowEntity.pickup = AbstractArrow.Pickup.ALLOWED;
            }
            level.addFreshEntity(arrowEntity);
        } else if (item == Items.TIPPED_ARROW) {
            Arrow arrowEntity = new Arrow(level,
                    planeEntity.getX() + transpos.x(), planeEntity.getY() + transpos.y(), planeEntity.getZ() + transpos.z());
            arrowEntity.setOwner(player);
            arrowEntity.setEffectsFromItem(itemStack);
            arrowEntity.setDeltaMovement(motion.scale(Math.max(motion.length() * 1.5, 3) / motion.length()));
            if (!player.isCreative()) {
                itemStackHandler.extractItem(0, 1, false);
                arrowEntity.pickup = AbstractArrow.Pickup.ALLOWED;
            }
            level.addFreshEntity(arrowEntity);
        } else if (item == Items.SPECTRAL_ARROW) {
            SpectralArrow arrowEntity = new SpectralArrow(level,
                    planeEntity.getX() + transpos.x(), planeEntity.getY() + transpos.y(), planeEntity.getZ() + transpos.z());
            arrowEntity.setOwner(player);
            arrowEntity.setDeltaMovement(motion.scale(Math.max(motion.length() * 1.5, 3) / motion.length()));
            if (!player.isCreative()) {
                itemStackHandler.extractItem(0, 1, false);
                arrowEntity.pickup = AbstractArrow.Pickup.ALLOWED;
            }
            level.addFreshEntity(arrowEntity);


            level.playSound(player, planeEntity.getX() + transpos.x(), planeEntity.getY() + transpos.y(), planeEntity.getZ() + transpos.z(), SoundEvents.CROSSBOW_SHOOT,SoundSource.PLAYERS, 1.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F);
            level.playLocalSound(planeEntity.getX() + transpos.x(), planeEntity.getY() + transpos.y(), planeEntity.getZ() + transpos.z(), SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 4.0F, (1.0F + (level.random.nextFloat() - level.random.nextFloat()) * 0.2F) * 0.7F, false);
            //planeEntity.playSound(SoundEvents.GENERIC_EXPLODE, 1f, 1.0F);
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
