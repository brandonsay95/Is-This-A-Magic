package com.darkscarlet.itam;

import com.darkscarlet.itam.block.ITAMBlocks;
import com.darkscarlet.itam.entity.ITAMEntity;
import com.darkscarlet.itam.item.ITAMItem;
import com.darkscarlet.itam.oregen.ITAMOreGen;
import com.darkscarlet.itam.screenhandlers.BoxScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class ITAMMod implements ModInitializer {
    public static final Map<Block, ItemStack> TAPPER_ITEMS = new HashMap<Block,ItemStack>(){


    };
    public static final String MODID="itam";
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;
    static{
        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(ITAMMod.MODID,"node_tapper_block"), BoxScreenHandler::new);

        TAPPER_ITEMS.put(Blocks.IRON_ORE,new ItemStack(Items.IRON_INGOT,1));
        TAPPER_ITEMS.put(Blocks.GOLD_ORE,new ItemStack(Items.GOLD_INGOT,1));
        TAPPER_ITEMS.put(Blocks.COAL_ORE,new ItemStack(Items.COAL,1));
        TAPPER_ITEMS.put(Blocks.DIAMOND_ORE,new ItemStack(Items.DIAMOND,1));
        TAPPER_ITEMS.put(Blocks.EMERALD_ORE,new ItemStack(Items.EMERALD,1));
        TAPPER_ITEMS.put(Blocks.STONE,new ItemStack(Items.STONE,1));
    }

    @Override
    public void onInitialize() {
        System.out.println("Is This a Magic? ");

        ITAMBlocks.InitializeBlocks();
        ITAMOreGen.InitializeOreGen();
        ITAMItem.InitializeItems();
        ITAMEntity.InitializeEntities();


        System.out.println("Who Knows");

    }
}
