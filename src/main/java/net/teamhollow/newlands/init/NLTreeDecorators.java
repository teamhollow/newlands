package net.teamhollow.newlands.init;

import com.mojang.serialization.Codec;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.tree.TreeDecorator;
import net.minecraft.world.gen.tree.TreeDecoratorType;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.mixin.TreeDecoratorTypeMixin;
import net.teamhollow.newlands.world.gen.tree.LeaveMagnoliaVineTreeDecorator;

public class NLTreeDecorators {
    public static TreeDecoratorType<LeaveMagnoliaVineTreeDecorator> LEAVE_MAGNOLIA_VINE = register("leave_magnolia_vine", LeaveMagnoliaVineTreeDecorator.CODEC);

    public NLTreeDecorators() {}

    private static <P extends TreeDecorator> TreeDecoratorType<P> register(String id, Codec<P> codec) {
        return Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier(NewLands.MOD_ID, id), TreeDecoratorTypeMixin.createTreeDecoratorType(codec));
    }
}
