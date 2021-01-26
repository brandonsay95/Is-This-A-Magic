package com.darkscarlet.itam.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;

public class NodeTapperEntity  extends BlockEntity {
    public NodeTapperEntity(BlockEntityType<?> type) {
        super(type);
    }
    private int number = 0;
    @Override
    public CompoundTag toTag(CompoundTag tag) {
         super.toTag(tag);

        tag.putInt("number", number);


        return tag;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);

        number   = tag.getInt("number");
    }

    public NodeTapperEntity(){
        super(ITAMEntity.NODE_TAPPER_ENTITY);
    }
}
