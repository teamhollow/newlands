package com.newlanders.new_lands.registry;

import com.newlanders.new_lands.NewLands;
import com.newlanders.new_lands.entities.FishermanEntity;
import com.newlanders.new_lands.entities.NLBoatEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Entities {
	public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, NewLands.ModID);
	
	public static RegistryObject<EntityType<NLBoatEntity>> BOAT = ENTITIES.register("nlboat",()->EntityType.Builder.<NLBoatEntity>create(NLBoatEntity::new, EntityClassification.MISC).size(1.375F, 0.5625F).build("nlboat"));
	public static RegistryObject<EntityType<FishermanEntity>> FISHERMAN = ENTITIES.register("fisherman",()->EntityType.Builder.<FishermanEntity>create(FishermanEntity::new, EntityClassification.MISC).size(0.6F, 1.95F).build("fisherman"));
}
