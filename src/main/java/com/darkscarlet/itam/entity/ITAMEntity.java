package com.darkscarlet.itam.entity;

import com.darkscarlet.itam.ITAMMod;
import com.darkscarlet.itam.block.ITAMBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class ITAMEntity {
    public static BlockEntityType<NodeTapperEntity> NODE_TAPPER_ENTITY;
    public static void InitializeEntities(){
        NODE_TAPPER_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ITAMMod.MODID + ":" + "node_tapper",
                BlockEntityType.Builder.create(NodeTapperEntity::new,
                ITAMBlocks.NODE_TAPPER_BLOCK
                ).build(null));
    }
}
