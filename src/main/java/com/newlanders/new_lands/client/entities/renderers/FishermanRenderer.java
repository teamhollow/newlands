package com.newlanders.new_lands.client.entities.renderers;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.newlanders.new_lands.client.entities.models.TropicalFishermanModel;
import com.newlanders.new_lands.entities.FishermanEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class FishermanRenderer extends EntityRenderer<FishermanEntity> {
	public FishermanRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	private static final ResourceLocation texture=new ResourceLocation("new_lands:textures/entity/tropical_fisherman.png");
	
	@Override
	public void render(FishermanEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		TropicalFishermanModel model=new TropicalFishermanModel();
		float renderYaw=(MathHelper.lerp(partialTicks,entityIn.prevRenderYawOffset,entityIn.renderYawOffset));
		model.setRotationAngles(entityIn,entityIn.limbSwing,entityIn.limbSwingAmount,entityIn.ticksExisted, renderYaw-entityYaw, entityIn.getPitch(partialTicks),partialTicks);
		matrixStackIn.push();
		matrixStackIn.rotate(new Quaternion(180,renderYaw,0,true));
		matrixStackIn.translate(0,-1.5,0);
		if (entityIn.getRidingEntity()!=null) {
			matrixStackIn.pop();
			matrixStackIn.push();
			matrixStackIn.rotate(new Quaternion(180,MathHelper.lerp(partialTicks,entityIn.getRidingEntity().prevRotationYaw,entityIn.getRidingEntity().rotationYaw),0,true));
			matrixStackIn.translate(0,-1.5,0);
			matrixStackIn.translate(0,0.35f,0);
			if (entityIn.getRidingEntity() instanceof BoatEntity &&entityIn.getRidingEntity().getPassengers().size()==1) {
				matrixStackIn.translate(0,0,0.25);
			}
		}
		if (entityIn.isSleeping()) {
			matrixStackIn.pop();
			matrixStackIn.push();
			matrixStackIn.rotate(entityIn.getBedDirection().getOpposite().getRotation());
		}
		model.render(matrixStackIn,bufferIn.getBuffer(RenderType.getEntityCutout(getEntityTexture(entityIn))),packedLightIn, OverlayTexture.NO_OVERLAY,1,1,1,1);
		matrixStackIn.pop();
	}
	
	@Override
	public ResourceLocation getEntityTexture(FishermanEntity entity) {
		return texture;
	}
}
