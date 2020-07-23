package com.newlanders.new_lands.utils;

import net.minecraftforge.fml.RegistryObject;

//GiantLuigi4:More stuff copied from another one of my mods.
public class BiRegistry<T extends RegistryObject<?>,H extends RegistryObject<?>> {
	private final T object1;
	private final H object2;
	
	public BiRegistry(T t, H h) {
		object1 = t;
		object2 = h;
	}
	
	public T getObject1() {
		return object1;
	}
	
	public H getObject2() {
		return object2;
	}
}
