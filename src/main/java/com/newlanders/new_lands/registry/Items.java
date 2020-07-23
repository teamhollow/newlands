package com.newlanders.new_lands.registry;

import com.newlanders.new_lands.CreativeTabs;
import com.newlanders.new_lands.NewLands;
import com.newlanders.new_lands.items.NLBoatItem;
import net.minecraft.entity.item.BoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

//GiantLuigi4:MORE COPYING FROM MYSELF!!!
public class Items {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NewLands.ModID);
	
	//NAVIGATION
	public static RegistryObject<Item> TROPICAL_BOAT = ITEMS.register("tropical_palm_boat", () -> new NLBoatItem(BoatEntity.Type.OAK, new Item.Properties().group(CreativeTabs.NAVIGATION)));
}
