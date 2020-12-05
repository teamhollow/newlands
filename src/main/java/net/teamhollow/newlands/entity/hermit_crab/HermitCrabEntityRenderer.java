package net.teamhollow.newlands.entity.hermit_crab;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.teamhollow.newlands.init.NLEntities;

@Environment(EnvType.CLIENT)
public class HermitCrabEntityRenderer extends MobEntityRenderer<HermitCrabEntity, HermitCrabEntityModel> {
    public HermitCrabEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new HermitCrabEntityModel(), 0.2F);
    }

    @Override
    public void render(HermitCrabEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) {
            this.shadowRadius *= 0.1F;

            float scale = 0.5F;
            matrices.scale(scale, scale, scale);
            this.scale(entity, matrices, scale);
        }

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(HermitCrabEntity entity) {
        return NLEntities.texture("hermit_crab/hermit_crab");
    }
}
