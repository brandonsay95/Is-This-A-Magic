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

import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class ITAMMod implements ModInitializer {

    public static final ArrayList<TapperEntry> TAPPER_ENTRIES = new ArrayList<TapperEntry>();

    public static final String MODID="itam";
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;
    static{
        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(ITAMMod.MODID,"node_tapper_block"), BoxScreenHandler::new);


        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.DIRT).Output(new ItemStack(Items.DIRT,1)).Cost(1).Ticks(20) );


        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.SAND).Output(new ItemStack(Items.SAND,1)).Cost(2).Ticks(40) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.GRAVEL).Output(new ItemStack(Items.GRAVEL,1)).Cost(2).Ticks(40) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.STONE).Output(new ItemStack(Items.STONE,1)).Cost(1).Ticks(40) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.COBBLESTONE).Output(new ItemStack(Items.COBBLESTONE,1)).Cost(1).Ticks(40) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.COAL_ORE).Output(new ItemStack(Items.COAL,1)).Cost(2).Ticks(80) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.IRON_ORE).Output(new ItemStack(Items.IRON_NUGGET,1)).Cost(4).Ticks(80) );


        TAPPER_ENTRIES.add(
                new TapperEntry().Input(ITAMBlocks.ANIMA_ORE).Output(new ItemStack(ITAMItem.Anima_Essence,1)).Cost(0).Ticks(100) );


        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.GOLD_ORE).Output(new ItemStack(Items.GOLD_NUGGET,1)).Cost(8).Ticks(160) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.ACACIA_LOG).Output(new ItemStack(Items.ACACIA_LOG,1)).Cost(4).Ticks(80) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.BIRCH_LOG).Output(new ItemStack(Items.BIRCH_LOG,1)).Cost(4).Ticks(80) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.DARK_OAK_LOG).Output(new ItemStack(Items.DARK_OAK_LOG,1)).Cost(4).Ticks(80) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.JUNGLE_LOG).Output(new ItemStack(Items.JUNGLE_LOG,1)).Cost(4).Ticks(80) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.OAK_LOG).Output(new ItemStack(Items.OAK_LOG,1)).Cost(4).Ticks(80) );
        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.SPRUCE_LOG).Output(new ItemStack(Items.SPRUCE_LOG,1)).Cost(4).Ticks(80) );


        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.DIAMOND_ORE).Output(new ItemStack(Items.DIAMOND,1)).Cost(64).Ticks(400) );

        TAPPER_ENTRIES.add(
                new TapperEntry().Input(Blocks.EMERALD_ORE).Output(new ItemStack(Items.EMERALD,1)).Cost(64).Ticks(400) );

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
