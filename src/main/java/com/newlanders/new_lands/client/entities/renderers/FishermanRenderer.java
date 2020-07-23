package com.newlanders.new_lands.client.entities.renderers;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.newlanders.new_lands.client.entities.models.TropicalFishermanModel;
import com.newlanders.new_lands.entities.FishermanEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.FishRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;

public class FishermanRenderer extends EntityRenderer<FishermanEntity> {
	public FishermanRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}
	
	private static final ResourceLocation texture = new ResourceLocation("new_lands:textures/entity/tropical_fisherman.png");
	private static final ResourceLocation texture_bobber = new ResourceLocation("textures/entity/fishing_hook.png");
	
	@Override
	public void render(FishermanEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		TropicalFishermanModel model = new TropicalFishermanModel();
		float renderYaw = (MathHelper.lerp(partialTicks, entityIn.prevRenderYawOffset, entityIn.renderYawOffset));
		model.setRotationAngles(entityIn, entityIn.limbSwing, entityIn.limbSwingAmount, entityIn.ticksExisted, renderYaw - entityYaw, entityIn.getPitch(partialTicks), partialTicks);
		matrixStackIn.push();
		matrixStackIn.rotate(new Quaternion(180, renderYaw, 0, true));
		matrixStackIn.translate(0, -1.5, 0);
		if (entityIn.getRidingEntity() != null) {
			matrixStackIn.pop();
			matrixStackIn.push();
			matrixStackIn.rotate(new Quaternion(180, MathHelper.lerp(partialTicks, entityIn.getRidingEntity().prevRotationYaw, entityIn.getRidingEntity().rotationYaw), 0, true));
			matrixStackIn.translate(0, -1.5, 0);
			matrixStackIn.translate(0, 0.35f, 0);
			if (entityIn.getRidingEntity() instanceof BoatEntity && entityIn.getRidingEntity().getPassengers().size() == 1) {
				matrixStackIn.translate(0, 0, 0.25);
				if (entityIn.getBrain().getSchedule().getScheduledActivity((int) entityIn.world.getDayTime()).equals(Activity.WORK) && FishermanEntity.checkBoat(entityIn)) {
					if (!(entityIn.ticksExisted % 200 <= 40)) {
						IVertexBuilder fishing = bufferIn.getBuffer(RenderType.getLines());
						double yOff = 0;
						double yOff2 = 0;
						for (int i = 0; i < 16; i++) {
							yOff += (i - 1) / 50f;
							yOff2 = ((yOff + ((i - 1) / 50f)) - yOff) + 0.02f;
							
							Vec3d lineStart = new Vec3d(0.25, yOff, -0.5 - (i / 2f));
							Vec3d path = new Vec3d(0, yOff2, -0.5f);
							
							
							Vec3d start = lineStart.add(entityIn.getPositionVec());
							Vec3d end = start.add(path);
							if (!entityIn.getWorld().getBlockState(new BlockPos(end)).getFluidState().getFluid().equals(Fluids.WATER)) {
								drawFishingLineSegment(fishing, lineStart, new Vec3d(0.05, 0, 0), matrixStackIn);
								break;
							}
							drawFishingLineSegment(fishing, lineStart, path, matrixStackIn);
						}
					}
				}
			}
		}
		if (entityIn.isSleeping()) {
			matrixStackIn.pop();
			matrixStackIn.push();
			matrixStackIn.rotate(entityIn.getBedDirection().getOpposite().getRotation());
		}
		if (!entityIn.isAlive()) {
			float f = ((float) entityIn.deathTime + partialTicks - 1.0F) / 20.0F * 1.6F;
			f = MathHelper.sqrt(f);
			if (f > 1.0F) {
				f = 1.0F;
			}
			
			matrixStackIn.translate(0, f * 1.25f, 0);
			matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(f * this.getDeathMaxRotation(entityIn)));
		}
		model.render(matrixStackIn, bufferIn.getBuffer(RenderType.getEntityCutout(getEntityTexture(entityIn))), packedLightIn, getPackedOverlay(entityIn, 0), 1, 1, 1, 1, entityIn.isDoingAnything());
		matrixStackIn.pop();
	}
	
	protected float getDeathMaxRotation(Entity entityLivingBaseIn) {
		return 90.0F;
	}
	
	public static void drawFishingLineSegment(IVertexBuilder fishing, Vec3d start, Vec3d path, MatrixStack matrixStackIn) {
		fishing.addQuad(matrixStackIn.getLast(), createQuad(start, start.add(path), start, start.add(path), Minecraft.getInstance().getAtlasSpriteGetter(new ResourceLocation("textures/atlas/blocks.png")).apply(new ResourceLocation("minecraft:white_concrete")), Direction.NORTH), 0, 0, 0, 0, OverlayTexture.NO_OVERLAY);
	}
	
	public static int getPackedOverlay(LivingEntity livingEntityIn, float uIn) {
		return OverlayTexture.getPackedUV(OverlayTexture.getU(uIn), OverlayTexture.getV(livingEntityIn.hurtTime > 0 || livingEntityIn.deathTime > 0));
	}
	
	@Override
	public ResourceLocation getEntityTexture(FishermanEntity entity) {
		return texture;
	}
	
	public static BakedQuad createQuad(
			Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4,
			TextureAtlasSprite sprite, Direction dir
	) {
		return createQuad(v1, v2, v3, v4, sprite, dir, 1);
	}
	
	public static BakedQuad createQuad(
			Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4,
			TextureAtlasSprite sprite, Direction dir, int tint
	) {
		Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();
		
		BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
		builder.setQuadOrientation(dir);
		putVertex(builder, normal, v1.x, v1.y, v1.z, 0, 0, sprite, 1.0f, 1.0f, 1.0f, 1);
		putVertex(builder, normal, v2.x, v2.y, v2.z, 0, 9, sprite, 1.0f, 1.0f, 1.0f, 1);
		putVertex(builder, normal, v3.x, v3.y, v3.z, 0, 0, sprite, 1.0f, 1.0f, 1.0f, 1);
		putVertex(builder, normal, v4.x, v4.y, v4.z, 0, 0, sprite, 1.0f, 1.0f, 1.0f, 1);
		builder.setQuadTint(tint);
		return builder.build();
	}
	
	//MCJTY:https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/blocks/FancyBakedModel.java
	private static void putVertex(BakedQuadBuilder builder, Vec3d normal,
								  double x, double y, double z, double u, double v, TextureAtlasSprite sprite, double r, double g, double b, float a) {
		
		ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
		for (int j = 0; j < elements.size(); j++) {
			VertexFormatElement e = elements.get(j);
			switch (e.getUsage()) {
				case POSITION:
					builder.put(j, (float) x, (float) y, (float) z, 1.0f);
					break;
				case COLOR:
					builder.put(j, (float) r, (float) g, (float) b, a);
					break;
				case UV:
					switch (e.getIndex()) {
						case 0:
							float iu = sprite.getInterpolatedU(u);
							float iv = sprite.getInterpolatedV(v);
							builder.put(j, iu, iv);
							break;
						case 2:
							builder.put(j, 0f, 1f);
							break;
						default:
							builder.put(j);
							break;
					}
					break;
				case NORMAL:
					builder.put(j, (float) normal.x, (float) normal.y, (float) normal.z);
					break;
				default:
					builder.put(j);
					break;
			}
		}
	}
}
