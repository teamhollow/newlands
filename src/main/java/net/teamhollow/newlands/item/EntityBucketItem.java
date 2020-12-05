package net.teamhollow.newlands.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class EntityBucketItem extends BucketItem {
    private final EntityType<?> entityType;

    public EntityBucketItem(EntityType<?> type, Fluid fluid, Item.Settings settings) {
        super(fluid, settings);
        this.entityType = type;
    }

    @Override
    public void onEmptied(World world, ItemStack stack, BlockPos pos) {
        if (world instanceof ServerWorld) this.entityType.spawnFromItemStack((ServerWorld)world, stack, (PlayerEntity) null, pos, SpawnReason.BUCKET, true, false);
    }

    @Override
    protected void playEmptyingSound(PlayerEntity player, WorldAccess world, BlockPos pos) {
        world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY_FISH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
    }
}
