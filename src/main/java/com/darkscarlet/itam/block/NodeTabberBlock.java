package com.darkscarlet.itam.block;

import com.darkscarlet.itam.entity.NodeTapperEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class NodeTabberBlock  extends Block implements BlockEntityProvider {
    public NodeTabberBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new NodeTapperEntity();
    }
}
