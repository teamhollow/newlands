package com.newlanders.new_lands.client.entities.models;// Made with Blockbench 3.6.3
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.util.math.MathHelper;

public class TropicalFishermanModel extends EntityModel<Entity> {
	private final ModelRenderer Hat;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer body;
	private final ModelRenderer arms;
	private final ModelRenderer mirrored;
	private final ModelRenderer right_leg;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_arm;
	private final ModelRenderer left_arm;

	public TropicalFishermanModel() {
		textureWidth = 112;
		textureHeight = 64;

		Hat = new ModelRenderer(this);
		Hat.setRotationPoint(0.0F, -6.25F, 0.0F);
		Hat.setTextureOffset(48, 16).addBox(-4.0F, -3.75F, -4.0F, 8.0F, 10.0F, 8.0F, 0.3F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(24, 0).addBox(-1.0F, -3.0F, -6.0F, 2.0F, 4.0F, 2.0F, 0.0F, false);
		head.addChild(Hat);
		
		nose = new ModelRenderer(this);
		nose.setRotationPoint(0.0F, -2.0F, 0.0F);
		

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureOffset(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(0, 40).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, 0.25F, false);

		arms = new ModelRenderer(this);
		arms.setRotationPoint(0.0F, 2.0F, 0.0F);
		setRotationAngle(arms, -0.8727F, 0.0F, 0.0F);
		arms.setTextureOffset(40, 0).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, 0.0F, false);
		arms.setTextureOffset(64, 0).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

		mirrored = new ModelRenderer(this);
		mirrored.setRotationPoint(0.0F, 22.0F, 0.0F);
		arms.addChild(mirrored);
		mirrored.setTextureOffset(64, 0).addBox(4.0F, -24.0F, -2.0F, 4.0F, 8.0F, 4.0F, 0.0F, true);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(2.0F, 12.0F, 0.0F);
		right_leg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		left_leg.setTextureOffset(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(5.0F, 2.0F, 0.0F);
		right_arm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		left_arm.setTextureOffset(32, 48).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		
		body.addChild(left_arm);
		body.addChild(right_arm);
		body.addChild(left_leg);
		body.addChild(right_leg);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		//previously the render function, render code was moved to a method below
		float limbSwingVal=MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount;
		setRotationAngle(right_arm, limbSwingVal * -0.5F,0,0);
		setRotationAngle(left_arm,limbSwingVal * 0.5F,0,0);
		setRotationAngle(right_leg,limbSwingVal * 0.5F,0,0);
		setRotationAngle(left_leg,limbSwingVal * -0.5F,0,0);
		
		setRotationAngle(head,entity.rotationPitch/64f,(netHeadYaw)/256f,0);
	}
	
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float partialTicks) {
		//previously the render function, render code was moved to a method below
		float limbSwingVal=MathHelper.cos((limbSwing+partialTicks) * 0.6662F) * 2.0F * limbSwingAmount;
		
		if (entity.getRidingEntity()!=null) {
			//SIT
			setRotationAngle(right_leg, (float) Math.toRadians(-90), (float) Math.toRadians(-15), 0);
			setRotationAngle(left_leg, (float) Math.toRadians(-90), (float) Math.toRadians(15), 0);
			
			if (entity.getRidingEntity() instanceof BoatEntity&&entity.getRidingEntity().getPassengers().size()==1) {
				//DRIVER
				BoatEntity boat=(BoatEntity)entity.getRidingEntity();
				float rowTime=boat.getRowingTime(0,limbSwing);
				setRotationAngle(right_arm, (float) Math.toRadians(Math.cos(rowTime)*12-75), (float) Math.sin(-rowTime)/12f, 0);
				rowTime=boat.getRowingTime(1,limbSwing);
				setRotationAngle(left_arm, (float) Math.toRadians(Math.cos(rowTime)*12-75), (float) Math.sin(rowTime)/12f, 0);
				
				//FISHING
				if (((VillagerEntity)entity).getBrain().getSchedule().getScheduledActivity((int)entity.world.getDayTime()).equals(Activity.WORK)) {
					setRotationAngle(left_arm, (float) Math.toRadians(-22.5f), (float) Math.toRadians(-20), 0);
					setRotationAngle(right_arm,(float)Math.toRadians(-80),(float)Math.toRadians(3),0);
				}
			} else {
				//PASSENGER
				setRotationAngle(right_arm, (float) Math.toRadians(-22.5f), (float) Math.toRadians(20), 0);
				setRotationAngle(left_arm, (float) Math.toRadians(-22.5f), (float) Math.toRadians(-20), 0);
			}
		} else {
			//WALKING AND STANDING
			setRotationAngle(right_arm, limbSwingVal * -0.5F, 0, 0);
			setRotationAngle(left_arm, limbSwingVal * 0.5f, 0, 0);
			setRotationAngle(right_leg, limbSwingVal * 0.5F, 0, 0);
			setRotationAngle(left_leg, limbSwingVal * -0.5F, 0, 0);
			
			setRotationAngle(head, entity.rotationPitch / 64f, (netHeadYaw) / 256f, 0);
		}
		
		//SHAKE HEAD
		if (((VillagerEntity)entity).getShakeHeadTicks()>0) {
			head.rotateAngleX=(float)Math.toRadians(22.5f);
			head.rotateAngleY+=Math.sin(((VillagerEntity)entity).getShakeHeadTicks()/2f);
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		nose.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//		arms.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}