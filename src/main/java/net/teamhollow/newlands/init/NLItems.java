package net.teamhollow.newlands.init;

import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.item.*;

public class NLItems {
    public static final Item HERMIT_CRAB_BUCKET = register("hermit_crab_bucket", new EntityBucketItem(NLEntities.HERMIT_CRAB, Fluids.WATER, new Item.Settings().maxCount(1).group(NewLands.ITEM_GROUP)));

    public NLItems() {}

    public static Item register(String id, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(NewLands.MOD_ID, id), item);
    }
    public static Item register(String id) {
        return Registry.register(Registry.ITEM, new Identifier(NewLands.MOD_ID, id), new Item(new Item.Settings().group(NewLands.ITEM_GROUP)));
    }
}
