package com.darkscarlet.itam;

import com.darkscarlet.itam.block.ITAMBlocks;
import com.darkscarlet.itam.entity.ITAMEntity;
import com.darkscarlet.itam.item.ITAMItem;
import com.darkscarlet.itam.oregen.ITAMOreGen;
import net.fabricmc.api.ModInitializer;

public class ITAMMod implements ModInitializer {
    public static final String MODID="itam";


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
