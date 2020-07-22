package com.newlanders.new_lands.entities;

import com.newlanders.new_lands.client.entities.renderers.FishermanRenderer;
import net.minecraft.block.Block;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.item.MerchantOffer;
import net.minecraft.tags.Tag;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FishermanEntity extends VillagerEntity {
	public FishermanEntity(EntityType<? extends VillagerEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	@Override
	protected void onVillagerTrade(MerchantOffer offer) {
		super.onVillagerTrade(offer);
	}
	
	@Override
	protected void populateTradeData() {
		super.populateTradeData();
	}
	
	boolean rotating=false;
	boolean rotatingDirection=false;
	boolean driving=false;
	
	private static final List<Activity> boatActivities=Arrays.asList(
			Activity.PLAY,
			Activity.IDLE,
			Activity.WORK
	);
	
	@Override
	public void tick() {
		super.tick();
		if (!this.world.isRemote) {
			List<BoatEntity> nearestBoat=this.world.getEntitiesWithinAABB(BoatEntity.class, this.getBoundingBox().expand(1,1,1).offset(-0.5,-0.5,-0.5));
			if (this.world.getBlockState(this.getPosition()).getBlock().getTags().contains(new ResourceLocation("minecraft:beds"))) {
				BlockPos home=this.getBedPosition().orElse(null);
				boolean hasHome=home!=null;
				if (!hasHome) {
					if (getActivity(this).equals(Activity.REST)) {
						this.setBedPosition(this.getPosition());
						this.startSleeping(this.getPosition());
						this.setHomePosAndDistance(this.getPosition(),64);
						this.getBrain().setMemory(MemoryModuleType.NEAREST_BED,this.getPosition());
					}
				}
			} else {
				if (this.isSleeping()) {
					this.startSleeping(this.getPosition());
				}
			}
			if (this.getRidingEntity()!=null) {
				if (this.getRidingEntity() instanceof BoatEntity) {
					if (this.getRidingEntity().getPassengers().get(0).equals(this)) {
						if (!getActivity(this).equals(Activity.WORK)) {
							GlobalPos home=this.getBrain().getMemory(MemoryModuleType.JOB_SITE).orElse(GlobalPos.of(this.dimension,this.getHomePosition()));
							if (home.getPos().equals(new BlockPos(0,0,0))) {
								home=GlobalPos.of(this.dimension,this.getBedPosition().orElse(this.getOnPosition()));
							}
							if (home.getPos().equals(new BlockPos(0,0,0))) {
								home=GlobalPos.of(this.dimension,this.getOnPosition());
							}
//							boolean hasHome=home!=null;
							if ((this.isWithinHomeDistanceCurrentPosition())&&checkBoat(this)) {
							} else {
								this.getRidingEntity().lookAt(EntityAnchorArgument.Type.FEET,new Vec3d(home.getPos()));
								this.lookAt(EntityAnchorArgument.Type.FEET,new Vec3d(home.getPos()));
							}
							this.getRidingEntity().rotationPitch=0;
							this.getRidingEntity().setMotion(this.getRidingEntity().getLookVec().scale(0.1f).mul(1,0,1));
							((BoatEntity) this.getRidingEntity()).setPaddleState(true,true);
						} else {
							if (this.rotating&&!getActivity(this).equals(Activity.WORK)) {
								((BoatEntity) this.getRidingEntity()).setPaddleState(!rotatingDirection,rotatingDirection);
							} else {
								((BoatEntity) this.getRidingEntity()).setPaddleState(false,false);
							}
						}
						
						this.getRidingEntity().rotationPitch=0;
						
						if (this.rotating) {
							if (!getActivity(this).equals(Activity.WORK)) {
								this.getRidingEntity().rotationYaw+=rotatingDirection?-1:1;
							}
						}
						
						if (rand.nextInt(128)>=127) {
							rotating=!rotating;
						}
						
						if (rand.nextInt(360)>=359) {
							rotatingDirection=!rotatingDirection;
						}
						
						if (rand.nextInt(780)>=779) {
							driving=!driving;
						}
					}
					this.rotationYaw=this.getRidingEntity().rotationYaw;
					this.prevRotationYaw=this.getRidingEntity().rotationYaw;
					this.rotationYawHead=this.getRidingEntity().getRotationYawHead();
					this.prevRotationYawHead=this.getRidingEntity().getRotationYawHead();
				}
			} else if (!nearestBoat.isEmpty()&&checkBoat(this)) {
				this.startRiding(nearestBoat.get(0));
			}
			if (!checkBoat(this)&&this.getRidingEntity()!=null) {
				if (this.world.getBlockState(new BlockPos(this.getPositionVec().add((this.getRidingEntity().getLookVec().scale(2))))).isSolid()) {
					this.stopRiding();
				}
			}
			List<BoatEntity> nearestBoats=this.world.getEntitiesWithinAABB(BoatEntity.class, this.getBoundingBox().expand(64,64,64).offset(-32,-32,-32));
			if (!nearestBoats.isEmpty()) {
				if (checkBoat(this)) {
					this.moveController.setMoveTo(nearestBoats.get(0).getPosX(),nearestBoats.get(0).getPosY(),nearestBoats.get(0).getPosZ(),0.5f);
				}
			}
		} else {
			if (this.getRidingEntity()!=null) {
//				GlobalPos home=this.getBrain().getMemory(MemoryModuleType.JOB_SITE).orElse(GlobalPos.of(this.dimension,this.getHomePosition()));
//				if (home.getPos().equals(new BlockPos(0,0,0))) {
//					home=GlobalPos.of(this.dimension,this.getBedPosition().orElse(this.getOnPosition()));
//				}
//				if (home.getPos().equals(new BlockPos(0,0,0))) {
//					home=GlobalPos.of(this.dimension,this.getOnPosition());
//				}
//				if ((this.isWithinHomeDistanceCurrentPosition())&&checkBoat(this)) {
//				} else {
//					this.getRidingEntity().lookAt(EntityAnchorArgument.Type.FEET,new Vec3d(home.getPos()));
//					this.lookAt(EntityAnchorArgument.Type.FEET,new Vec3d(home.getPos()));
//				}
			}
		}
//		System.out.println(getActivity(this).getKey());
	}
	
	private static boolean checkBoat(FishermanEntity entity) {
		return boatActivities.contains(entity.getBrain().getSchedule().getScheduledActivity((int)entity.world.getDayTime()));
	}
	
	private static Activity getActivity(FishermanEntity entity) {
		return entity.getBrain().getSchedule().getScheduledActivity((int)entity.world.getDayTime());
	}
}
