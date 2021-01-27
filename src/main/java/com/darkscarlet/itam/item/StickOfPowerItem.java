package com.darkscarlet.itam.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class StickOfPowerItem extends Item {

    public StickOfPowerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        BlockState originalBlock = context.getWorld().getBlockState(context.getBlockPos());
        Block block =  originalBlock.getBlock();
        int cSize = 64;
        int chunkX = (int) Math.floor(context.getHitPos().getX() / cSize);
        int chunkZ = (int) Math.floor(context.getHitPos().getZ() / cSize);
        for(int x = chunkX * cSize; x < chunkX*cSize+cSize ;x++){
            for(int y = 0 ; y< 256;y++){
                for(int z = chunkZ * cSize; z<chunkZ *cSize+cSize;z++){
                if(context.getWorld().getBlockState(new BlockPos(x,y,z)).getBlock() == block)

                    context .getWorld().setBlockState(new BlockPos(x,y,z), Blocks.AIR.getDefaultState());

                }
            }
        }

        return super.useOnBlock(context);
    }
}
