package com.newlanders.new_lands.entities;

import com.newlanders.new_lands.client.entities.renderers.FishermanRenderer;
import net.minecraft.block.BedBlock;
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
	
	boolean rotating = false;
	boolean rotatingDirection = false;
	boolean driving = false;
	int reeledIn = 0;
	
	private static final List<Activity> boatActivities = Arrays.asList(
			Activity.PLAY,
			Activity.IDLE,
			Activity.WORK,
			Activity.MEET
	);
	
	@Override
	public void tick() {
		super.tick();
		if (!this.world.isRemote) {
			List<BoatEntity> nearestBoat = this.world.getEntitiesWithinAABB(BoatEntity.class, this.getBoundingBox().expand(1, 1, 1).offset(-0.5, -0.5, -0.5));
			if (this.world.getBlockState(this.getPosition()).getBlock().getTags().contains(new ResourceLocation("minecraft:beds"))) {
				BlockPos home = this.getBedPosition().orElse(null);
				boolean hasHome = home != null;
				if (!hasHome) {
					if (getActivity(this).equals(Activity.REST) && !checkBoat(this)) {
						if (!this.world.getBlockState(this.getPosition()).get(BedBlock.OCCUPIED)) {
							if (!this.isSleeping()) {
								this.setBedPosition(this.getPosition());
							}
							this.startSleeping(this.getPosition());
							this.setHomePosAndDistance(this.getPosition(), 64);
							this.getBrain().setMemory(MemoryModuleType.NEAREST_BED, this.getPosition());
						}
					}
				}
			} else {
				if (this.isSleeping()) {
					this.startSleeping(this.getPosition());
				}
			}
			if (this.getRidingEntity() != null) {
				GlobalPos home = getHome();
				if (home.getPos().equals(this.getPosition())) {
					this.stopRiding();
				}
			}
			if (this.getRidingEntity() != null) {
				if (this.getRidingEntity() instanceof BoatEntity) {
					if (this.getRidingEntity().getPassengers().get(0).equals(this)) {
						if (!getActivity(this).equals(Activity.WORK) || !checkBoat(this)) {
							GlobalPos home = getHome();
//							boolean hasHome=home!=null;
							boolean negativeX = false;
							boolean negativeZ = false;
							if ((this.isWithinHomeDistanceCurrentPosition()) && checkBoat(this)) {
								this.getRidingEntity().setMotion(this.getRidingEntity().getLookVec().scale(0.1f).mul((negativeX ? -1 : 1), 0, (negativeZ ? -1 : 1)));
							} else {
								this.getRidingEntity().rotationPitch = 0;
								this.getRidingEntity().rotationYaw = (float) (Math.atan2(this.getPositionVec().getX() - home.getPos().getX(), this.getPositionVec().getZ()) - home.getPos().getZ()) * 360 + 180;
								Vec3d motionThis = this.getRidingEntity().getPositionVec().subtract(new Vec3d(home.getPos())).mul(1, 0, 1).normalize();
								Vec3d motionBoat = this.getRidingEntity().getMotion();
								motionThis = new Vec3d(motionThis.getX(), 0, motionThis.getZ());
								this.getRidingEntity().setMotion(motionThis.scale(-0.1f).add(motionBoat).normalize().scale(0.1f));
							}
							
							((BoatEntity) this.getRidingEntity()).setPaddleState(true, true);
						} else {
							if (this.rotating && !getActivity(this).equals(Activity.WORK)) {
								((BoatEntity) this.getRidingEntity()).setPaddleState(!rotatingDirection, rotatingDirection);
							} else {
								((BoatEntity) this.getRidingEntity()).setPaddleState(false, false);
							}
							if ((this.ticksExisted % 200 <= 40)) {
								reeledIn += 1;
								if ((reeledIn / 40) >= 32) {
									if (this.offers != null) {
										this.offers.forEach(MerchantOffer::resetUses);
									}
									reeledIn = 0;
								}
							}
						}
						
						this.getRidingEntity().rotationPitch = 0;
						
						if (checkBoat(this)) {
							if (this.rotating) {
								if (!getActivity(this).equals(Activity.WORK)) {
									this.getRidingEntity().rotationYaw += rotatingDirection ? -1 : 1;
								}
							}
							
							if (rand.nextInt(128) >= 127) {
								rotating = !rotating;
							}
							
							if (rand.nextInt(360) >= 359) {
								rotatingDirection = !rotatingDirection;
							}
							
							if (rand.nextInt(780) >= 779) {
								driving = !driving;
							}
						}
					} else {
						if ((this.getRidingEntity().getPassengers().get(0) instanceof FishermanEntity) && !this.getRidingEntity().getPassengers().get(0).equals(this)) {
							this.stopRiding();
						}
					}
					if (this.getRidingEntity() != null) {
						this.rotationYaw = this.getRidingEntity().rotationYaw;
						this.prevRotationYaw = this.getRidingEntity().rotationYaw;
						this.rotationYawHead = this.getRidingEntity().getRotationYawHead();
						this.prevRotationYawHead = this.getRidingEntity().getRotationYawHead();
					}
				}
			} else if (!nearestBoat.isEmpty() && checkBoat(this)) {
				GlobalPos home = getHome();
				if (!home.getPos().equals(this.getPosition())) {
					this.startRiding(nearestBoat.get(0));
				}
			}
			if (!checkBoat(this) && this.getRidingEntity() != null) {
				if (this.world.getBlockState(new BlockPos(this.getPositionVec().add((this.getRidingEntity().getLookVec().scale(2))))).isSolid()) {
					this.stopRiding();
				}
				if (this.getRidingEntity() != null) {
					if (this.world.getBlockState(new BlockPos(this.getPositionVec().add((this.getRidingEntity().getLookVec().scale(-2))))).isSolid()) {
						this.stopRiding();
					}
				}
			}
			List<BoatEntity> nearestBoats = this.world.getEntitiesWithinAABB(BoatEntity.class, this.getBoundingBox().expand(64, 64, 64).offset(-32, -32, -32));
			if (!nearestBoats.isEmpty()) {
				if (checkBoat(this)) {
					BoatEntity boat = getNearestUnoccupiedBoat(nearestBoats, this);
					if (boat != null) {
						GlobalPos home = getHome();
						if (home.getPos().equals(this.getPosition())) {
							this.moveController.setMoveTo(boat.getPosX(), boat.getPosY(), boat.getPosZ(), 0.5f);
						}
					}
				}
			}
		}
//		System.out.println(getActivity(this));
	}
	
	private GlobalPos getHome() {
		GlobalPos home = this.getBrain().getMemory(MemoryModuleType.JOB_SITE).orElse(GlobalPos.of(this.dimension, this.getHomePosition()));
		if (home.getPos().equals(new BlockPos(0, 0, 0))) {
			home = GlobalPos.of(this.dimension, this.getBedPosition().orElse(this.getOnPosition()));
		}
		if (home.getPos().equals(new BlockPos(0, 0, 0))) {
			home = GlobalPos.of(this.dimension, this.getOnPosition());
		}
		return home;
	}
	
	public BoatEntity getNearestUnoccupiedBoat(List<BoatEntity> boats, FishermanEntity target) {
		double distanceBest = 99999;
		BoatEntity returnValue = null;
		for (int i = 0; i < boats.size(); i++) {
			BoatEntity boat = boats.get(i);
			double distance = boat.getPositionVec().distanceTo(target.getPositionVec());
			if (distanceBest > distance) {
				distanceBest = Math.min(distanceBest, distance);
				returnValue = boats.get(i);
			}
		}
		return returnValue;
	}
	
	public boolean isDoingAnything() {
		boolean deadOrAsleep = (this.isAlive() || this.isSleeping());
		if (!deadOrAsleep) {
			return true;
		}
		boolean ridingBoat = (this.getRidingEntity() != null && this.getRidingEntity() instanceof BoatEntity);
		return ridingBoat;
	}
	
	public static boolean checkBoat(FishermanEntity entity) {
		if (entity.world.isRaining()) {
			return false;
		}
		return boatActivities.contains(entity.getBrain().getSchedule().getScheduledActivity((int) entity.world.getDayTime()));
	}
	
	private static Activity getActivity(FishermanEntity entity) {
		return entity.getBrain().getSchedule().getScheduledActivity((int) entity.world.getDayTime());
	}
}
