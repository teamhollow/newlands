package net.teamhollow.newlands.init;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;

public class NLItems {
    public NLItems() {}

    public static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(NewLands.MOD_ID, id), item);
    }
    public static Item register(String id) {
        return Registry.register(Registry.ITEM, new Identifier(NewLands.MOD_ID, id), new Item(new Item.Settings().group(NewLands.ITEM_GROUP)));
    }
}
