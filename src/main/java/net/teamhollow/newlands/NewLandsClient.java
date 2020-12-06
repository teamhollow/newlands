package net.teamhollow.newlands;

import static net.teamhollow.newlands.NewLands.log;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.block.helper.WoodBlocks;
import net.teamhollow.newlands.client.particle.WhiteAlliumParticle;
import net.teamhollow.newlands.entity.hermit_crab.HermitCrabEntityRenderer;
import net.teamhollow.newlands.init.NLBlocks;
import net.teamhollow.newlands.init.NLEntities;
import net.teamhollow.newlands.init.NLParticles;
import net.teamhollow.newlands.registry.SpriteIdentifierRegistry;

public class NewLandsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        log("Initializing client");

        // render layers
        BlockRenderLayerMap brlmInstance = BlockRenderLayerMap.INSTANCE;
        brlmInstance.putBlocks(RenderLayer.getCutout(), NLBlocks.MAGNOLIA.DOOR, NLBlocks.MAGNOLIA.TRAPDOOR, NLBlocks.MAGNOLIA_VINE, NLBlocks.TROPICAL_PALM.SAPLING, NLBlocks.MAGNOLIA.SAPLING, NLBlocks.TROPICAL_PALM.DOOR, NLBlocks.TROPICAL_PALM.TRAPDOOR);
        brlmInstance.putBlocks(RenderLayer.getCutoutMipped(), NLBlocks.MAGNOLIA.LEAVES, NLBlocks.FLOWERING_MAGNOLIA_LEAVES, NLBlocks.FLOWERING_MAGNOLIA_LEAF_CARPET, NLBlocks.WHITE_ALLIUM, NLBlocks.TROPICAL_PALM.LEAVES);

        // color providers
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            return world != null && pos != null
                ? BiomeColors.getGrassColor(world, pos)
                : GrassColors.getColor(0.5D, 1.0D);
        }, NLBlocks.TROPICAL_PALM.LEAVES);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x8eb04a, NLBlocks.TROPICAL_PALM.LEAVES);

        // renderers
        registerSignRenderers(NLBlocks.MAGNOLIA, NLBlocks.TROPICAL_PALM);
        registerBoatRenderers(NLBlocks.MAGNOLIA.BOAT_ENTITY, NLBlocks.TROPICAL_PALM.BOAT_ENTITY);

        EntityRendererRegistry INSTANCE = EntityRendererRegistry.INSTANCE;
        INSTANCE.register(NLEntities.HERMIT_CRAB, (entityRenderDispatcher, context) -> new HermitCrabEntityRenderer(entityRenderDispatcher));

        // particles
        ParticleFactoryRegistry pfrInstance = ParticleFactoryRegistry.getInstance();
        pfrInstance.register(NLParticles.WHITE_ALLIUM, WhiteAlliumParticle.Factory::new);

        log("Initialized client");
    }

    private void registerSignRenderers(WoodBlocks... woodBlocks) {
        for (WoodBlocks set : woodBlocks) {
            String id = set.getId();

            SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, set.getSignTextureIdentifier()));

            id += "_sign";
            Registry.register(Registry.BLOCK_ENTITY_TYPE, id, BlockEntityType.Builder.create(SignBlockEntity::new, set.SIGN, set.WALL_SIGN).build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id)));
        }
    }
    private void registerBoatRenderers(EntityType<?>... boats) {
        for (EntityType<?> boat : boats) {
            EntityRendererRegistry.INSTANCE.register(boat, (entityRenderDispatcher, context) -> new BoatEntityRenderer(entityRenderDispatcher));
        }
    }
}
