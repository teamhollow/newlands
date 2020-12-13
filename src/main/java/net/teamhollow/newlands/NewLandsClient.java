package net.teamhollow.newlands;

import static net.teamhollow.newlands.NewLands.log;

import me.andante.chord.util.CClientUtils;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.teamhollow.newlands.client.particle.WhiteAlliumParticle;
import net.teamhollow.newlands.entity.hermit_crab.HermitCrabEntityRenderer;
import net.teamhollow.newlands.init.NLBlocks;
import net.teamhollow.newlands.init.NLEntities;
import net.teamhollow.newlands.init.NLParticles;

public class NewLandsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        log("Initializing client");

        // render layers
        BlockRenderLayerMap brlmInstance = BlockRenderLayerMap.INSTANCE;
        brlmInstance.putBlocks(RenderLayer.getCutout(), NLBlocks.MAGNOLIA_VINE);
        brlmInstance.putBlocks(RenderLayer.getCutoutMipped(), NLBlocks.FLOWERING_MAGNOLIA_LEAVES, NLBlocks.FLOWERING_MAGNOLIA_LEAF_CARPET, NLBlocks.WHITE_ALLIUM);

        // color providers
        ColorProviderRegistry.BLOCK.register(
                (state, world, pos, tintIndex) -> world != null && pos != null
                        ? BiomeColors.getGrassColor(world, pos)
                        : GrassColors.getColor(0.5D, 1.0D),
                NLBlocks.FLOWERING_MAGNOLIA_LEAVES, NLBlocks.FLOWERING_MAGNOLIA_LEAF_CARPET
        );
        ColorProviderRegistry.ITEM.register(
                (stack, tintIndex) -> NLBlocks.MAGNOLIA.getLeafItemColor(),
                NLBlocks.FLOWERING_MAGNOLIA_LEAVES, NLBlocks.FLOWERING_MAGNOLIA_LEAF_CARPET
        );

        // renderers
        CClientUtils.registerWoodBlocks(NLBlocks.MAGNOLIA, NLBlocks.TROPICAL_PALM);

        EntityRendererRegistry INSTANCE = EntityRendererRegistry.INSTANCE;
        INSTANCE.register(NLEntities.HERMIT_CRAB, (entityRenderDispatcher, context) -> new HermitCrabEntityRenderer(entityRenderDispatcher));

        // particles
        ParticleFactoryRegistry pfrInstance = ParticleFactoryRegistry.getInstance();
        pfrInstance.register(NLParticles.WHITE_ALLIUM, WhiteAlliumParticle.Factory::new);

        log("Initialized client");
    }
}
