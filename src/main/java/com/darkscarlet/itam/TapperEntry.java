package com.darkscarlet.itam;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class TapperEntry {
    public Block Key;
    public ItemStack OutputPerCycle;
    public int TicksPerCycle = 1;
    public int CostPerCycle  = 1;

    public TapperEntry Input(Block block){
        this.Key = block;
        return this;
    }

    public TapperEntry Output(ItemStack stack){
        this.OutputPerCycle = stack;
        return this;
    }

    public TapperEntry Ticks(int n){
        this.TicksPerCycle = n;
        return this;
    }
    public TapperEntry Cost(int i){
        this.CostPerCycle = i;
        return this;
    }
}
