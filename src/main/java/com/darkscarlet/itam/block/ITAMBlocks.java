package com.darkscarlet.itam.block;

import com.darkscarlet.itam.ITAMMod;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ITAMBlocks{
    public static final Block IRON_ORE_NODE = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f));
    public static void InitializeBlocks(){
        Registry.register(Registry.BLOCK,new Identifier(ITAMMod.MODID,"iron_ore_node"),IRON_ORE_NODE);
        Registry.register(Registry.ITEM,new Identifier(ITAMMod.MODID,"iron_ore_node"),new BlockItem(IRON_ORE_NODE,new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));


    }
}
