package com.darkscarlet.itam.oregen;

import com.darkscarlet.itam.ITAMMod;
import com.darkscarlet.itam.block.ITAMBlocks;
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

public class ITAMOreGen {

    private static ConfiguredFeature<?,?> ORE_ANIMA_OVERWORLD = Feature.ORE
            .configure(
                    new OreFeatureConfig(
                            //new BlockMatchRuleTest(Blocks.IRON_ORE),
                           OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                            ITAMBlocks.ANIMA_ORE.getDefaultState(),
                            10//vein Size
                    ))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                            0,0,64
                    ))
                            .spreadHorizontally().repeat(20)
            ) ;




    public static void InitializeOreGen() {
        RegistryKey<ConfiguredFeature<?,?>> ore_anima_overworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(ITAMMod.MODID,"ore_anima_overworld")
        );
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,ore_anima_overworld.getValue(),ORE_ANIMA_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ore_anima_overworld);


    }
}
