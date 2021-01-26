package com.darkscarlet.itam;

import com.darkscarlet.itam.block.ITAMBlocks;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ITAMMod implements ModInitializer {
    public static final String MODID="itam";

private static ConfiguredFeature<?,?> ORE_IRON_NODE_OVERWORLD = Feature.ORE
.configure(
    new OreFeatureConfig(
    new BlockMatchRuleTest(Blocks.IRON_ORE),
    //  OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
    ITAMBlocks.IRON_ORE_NODE.getDefaultState(),
    1//vein Size
    ))
    .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
    0,0,64
    ))
    .spreadHorizontally().repeat(20)
    ) ;

    @Override
    public void onInitialize() {
        System.out.println("Is This a Magic? ");
        ITAMBlocks.InitializeBlocks();
        RegistryKey<ConfiguredFeature<?,?>> ore_iron_node_overworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MODID,"ore_iron_node_overworld")
                );
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,ore_iron_node_overworld.getValue(),ORE_IRON_NODE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ore_iron_node_overworld);



        System.out.println("Who Knows");

    }
}
