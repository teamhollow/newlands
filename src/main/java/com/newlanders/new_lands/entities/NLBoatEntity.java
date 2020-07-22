package com.newlanders.new_lands.entities;

import com.newlanders.new_lands.registry.Entities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class NLBoatEntity extends BoatEntity {
	public NLBoatEntity(EntityType<? extends BoatEntity> p_i50129_1_, World p_i50129_2_) {
		super(p_i50129_1_, p_i50129_2_);
	}
	
	public NLBoatEntity(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}
	
	@Override
	public EntityType<?> getType() {
		return Entities.BOAT.get();
	}
	
	@Override
	public Item getItemBoat() {
		switch(this.getBoatType()) {
			case OAK:
			default:
				return com.newlanders.new_lands.registry.Items.TROPICAL_BOAT.get();
			case SPRUCE:
				return Items.SPRUCE_BOAT;
			case BIRCH:
				return Items.BIRCH_BOAT;
			case JUNGLE:
				return Items.JUNGLE_BOAT;
			case ACACIA:
				return Items.ACACIA_BOAT;
			case DARK_OAK:
				return Items.DARK_OAK_BOAT;
		}
	}
}
