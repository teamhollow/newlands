package net.teamhollow.newlands.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.entity.hermit_crab.HermitCrabEntity;

public class NLEntities {
    public static final EntityType<HermitCrabEntity> HERMIT_CRAB = register(
        HermitCrabEntity.id,
        FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HermitCrabEntity::new)
            .dimensions(EntityDimensions.fixed(0.4F, 0.4F)),
        new int[]{ 15690005, 3552306 }
    );

    public NLEntities() {
        registerDefaultAttributes(HERMIT_CRAB, HermitCrabEntity.createHermitCrabAttributes());
    }

    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType, int[] spawnEggColors) {
        if (spawnEggColors != null)
            NLItems.register(id + "_spawn_egg", new SpawnEggItem(entityType, spawnEggColors[0], spawnEggColors[1], new Item.Settings().maxCount(64).group(NewLands.ITEM_GROUP)));

        return Registry.register(Registry.ENTITY_TYPE, new Identifier(NewLands.MOD_ID, id), entityType);
    }
    // private static <T extends Entity> EntityType<T> register(String id, EntityType.Builder<T> entityTypeBuilder, int[] spawnEggColors) {
    //     return register(id, entityTypeBuilder.build(id), spawnEggColors);
    // }
    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityTypeBuilder, int[] spawnEggColors) {
        return register(id, entityTypeBuilder.build(), spawnEggColors);
    }

    public static void registerDefaultAttributes(EntityType<? extends LivingEntity> type, DefaultAttributeContainer.Builder builder) {
        FabricDefaultAttributeRegistry.register(type, builder);
    }

    public static Identifier texture(String path) {
        return NewLands.texture("entity/" + path);
    }
}
