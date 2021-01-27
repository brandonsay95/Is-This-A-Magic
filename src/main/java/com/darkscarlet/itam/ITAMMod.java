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
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ITAMMod implements ModInitializer {
    public static final String MODID="itam";
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;
    static{
        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(ITAMMod.MODID,"node_tapper_block"), BoxScreenHandler::new);

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
