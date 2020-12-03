package net.teamhollow.newlands.block.helper;

import static net.teamhollow.newlands.init.NLBlocks.register;
import static net.teamhollow.newlands.init.NLItems.register;

import java.util.function.Supplier;

import com.google.common.collect.ImmutableMap;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SignItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.teamhollow.newlands.NewLands;
import net.teamhollow.newlands.block.*;
import net.teamhollow.newlands.block.vanilla.*;
import net.teamhollow.newlands.entity.NLBoatEntity;
import net.teamhollow.newlands.entity.NLBoatInfo;
import net.teamhollow.newlands.item.NLBoatItem;

public class WoodBlocks {
    private final String id;
    private final boolean flammable;
    private final Identifier signTextureIdentifier;

    public final Block PLANKS;
    public final Block SAPLING;
    public final Block LOG;
    public final Block STRIPPED_LOG;
    public final Block STRIPPED_WOOD;
    public final Block WOOD;
    public final Block LEAVES;
    public final Block SLAB;
    public final Block PRESSURE_PLATE;
    public final Block FENCE;
    public final Block TRAPDOOR;
    public final Block FENCE_GATE;
    public final Block STAIRS;
    public final Block BUTTON;
    public final Block DOOR;
    public final Block SIGN;
    public final Block WALL_SIGN;

    public final Item SIGN_ITEM;
    public final Item BOAT_ITEM;

    public final EntityType<NLBoatEntity> BOAT_ENTITY;

    private WoodBlocks(String id, boolean flammable, SaplingGenerator saplingGenerator, BoatEntity.Type boatType, PressurePlateBlock.ActivationRule pressurePlateActivationRule) {
        this.id = id;
        this.flammable = flammable;
        this.signTextureIdentifier = new Identifier(NewLands.MOD_ID, "entity/sign/" + id);;

        this.PLANKS = register(id + "_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)));
        this.SAPLING = register(id + "_sapling", new PublicSaplingBlock(saplingGenerator, FabricBlockSettings.copy(Blocks.OAK_SAPLING)));
        this.LOG = register(id + "_log", new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)));
        this.STRIPPED_LOG = register("stripped_" + id + "_log", new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG)));
        this.STRIPPED_WOOD = register("stripped_" + id + "_wood", new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)));
        this.WOOD = register(id + "_wood", new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)));
        this.LEAVES = register(id + "_leaves", new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES)));
        this.SLAB = register(id + "_slab", new SlabBlock(FabricBlockSettings.copy(Blocks.OAK_SLAB)));
        this.PRESSURE_PLATE = register(id + "_pressure_plate", new PublicPressurePlateBlock(pressurePlateActivationRule, FabricBlockSettings.copy(Blocks.OAK_PRESSURE_PLATE)));
        this.FENCE = register(id + "_fence", new FenceBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE)));
        this.TRAPDOOR = register(id + "_trapdoor", new PublicTrapdoorBlock(FabricBlockSettings.copy(Blocks.OAK_TRAPDOOR)));
        this.FENCE_GATE = register(id + "_fence_gate", new FenceGateBlock(FabricBlockSettings.copy(Blocks.OAK_FENCE_GATE)));
        this.STAIRS = register(id + "_stairs", new PublicStairsBlock(this.PLANKS.getDefaultState(), FabricBlockSettings.copy(Blocks.OAK_STAIRS)));
        this.BUTTON = register(id + "_button", new PublicWoodenButtonBlock(FabricBlockSettings.copy(Blocks.OAK_BUTTON)));
        this.DOOR = register(id + "_door", new PublicDoorBlock(FabricBlockSettings.copy(Blocks.OAK_DOOR)));

        this.SIGN = register(id + "_sign", new NLSignBlock(this.getSignTextureIdentifier(), FabricBlockSettings.copy(Blocks.OAK_SIGN)), false);
        this.WALL_SIGN = register(id + "_wall_sign", new NLWallSignBlock(this.getSignTextureIdentifier(), FabricBlockSettings.copy(Blocks.OAK_WALL_SIGN)), false);
        SIGN_ITEM = register(id + "_sign", new SignItem(new Item.Settings().maxCount(16).group(NewLands.ITEM_GROUP), this.SIGN, this.WALL_SIGN));

        BOAT_ITEM = register(id + "_boat", new NLBoatItem(new Supplier<EntityType<NLBoatEntity>>(){
            @Override
            public EntityType<NLBoatEntity> get() {
                return BOAT_ENTITY;
            }
        }, new Item.Settings().maxCount(1).group(NewLands.ITEM_GROUP)));;
        BOAT_ENTITY = Registry.register(Registry.ENTITY_TYPE, new Identifier(NewLands.MOD_ID, id + "_boat"), FabricEntityTypeBuilder.<NLBoatEntity>create(SpawnGroup.MISC, (entity, world) -> new NLBoatEntity(entity, world, new NLBoatInfo(this.BOAT_ITEM, this.PLANKS.asItem(), new Identifier(NewLands.MOD_ID, "textures/entity/boat/" + id + ".png"), boatType))).dimensions(EntityDimensions.fixed(1.375F, 0.5625F)).build());

        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(this.LEAVES.asItem(), 0.3F);
        if (this.isFlammable()) {
            // flammable blocks
            int baseBurnChance = 5;
            int largeBurnChance = baseBurnChance * 6;

            int baseSpreadChance = 20;
            int smallSpreadChance = baseSpreadChance / 4;
            int largeSpreadChance = baseSpreadChance * 3;

            FlammableBlockRegistry fbrInstance = FlammableBlockRegistry.getDefaultInstance();
            fbrInstance.add(this.PLANKS, baseBurnChance, baseSpreadChance);
            fbrInstance.add(this.SLAB, baseBurnChance, baseSpreadChance);
            fbrInstance.add(this.FENCE_GATE, baseBurnChance, baseSpreadChance);
            fbrInstance.add(this.FENCE, baseBurnChance, baseSpreadChance);
            fbrInstance.add(this.STAIRS, baseBurnChance, baseSpreadChance);
            fbrInstance.add(this.LOG, baseBurnChance, smallSpreadChance);
            fbrInstance.add(this.STRIPPED_LOG, baseBurnChance, smallSpreadChance);
            fbrInstance.add(this.STRIPPED_WOOD, baseBurnChance, smallSpreadChance);
            fbrInstance.add(this.WOOD, baseBurnChance, smallSpreadChance);
            fbrInstance.add(this.LEAVES, largeBurnChance, largeSpreadChance);

            // fuel registering
            int fenceFuelTime = 300;

            FuelRegistry frInstance = FuelRegistry.INSTANCE;
            frInstance.add(this.FENCE, fenceFuelTime);
            frInstance.add(this.FENCE_GATE, fenceFuelTime);
        }

        new ImmutableMap.Builder<Block, Block>()
            .put(this.LOG, this.STRIPPED_LOG)
            .put(this.WOOD, this.STRIPPED_WOOD)
            .build().forEach((base, result) -> {
                UseBlockCallback.EVENT.register((player, world, hand, hit) -> {
                    if (player.getStackInHand(hand).getItem().isIn(FabricToolTags.AXES) && world.getBlockState(hit.getBlockPos()).getBlock() == base) {
                        BlockPos blockPos = hit.getBlockPos();
                        BlockState blockState = world.getBlockState(blockPos);

                        world.playSound(player, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        if (!world.isClient) {
                            world.setBlockState(blockPos, result.getDefaultState().with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS)), 11);
                            if (!player.isCreative()) {
                                ItemStack stack = player.getStackInHand(hand);
                                stack.damage(1, player, ((p) -> p.sendToolBreakStatus(hand)));
                            }
                        }

                        return ActionResult.SUCCESS;
                    }

                    return ActionResult.PASS;
                });
            });
    }

    public String getId() {
        return this.id;
    }
    public boolean isFlammable() {
        return this.flammable;
    }
    public Identifier getSignTextureIdentifier() {
        return this.signTextureIdentifier;
    }

    public static class Builder {
        private boolean flammable = true;
        private SaplingGenerator saplingGenerator = new OakSaplingGenerator();
        private BoatEntity.Type boatType = BoatEntity.Type.OAK;
        private PressurePlateBlock.ActivationRule pressurePlateActivationRule = PressurePlateBlock.ActivationRule.EVERYTHING;

        public WoodBlocks.Builder nonFlammable() {
            this.flammable = false;
            return this;
        }
        public WoodBlocks.Builder saplingGenerator(SaplingGenerator saplingGenerator) {
            this.saplingGenerator = saplingGenerator;
            return this;
        }
        public WoodBlocks.Builder boatType(BoatEntity.Type boatType) {
            this.boatType = boatType;
            return this;
        }
        public WoodBlocks.Builder pressurePlateActivationRule(PressurePlateBlock.ActivationRule pressurePlateActivationRule) {
            this.pressurePlateActivationRule = pressurePlateActivationRule;
            return this;
        }

        public WoodBlocks build(String id) {
            return new WoodBlocks(id, this.flammable, this.saplingGenerator, this.boatType, this.pressurePlateActivationRule);
        }
    }
}