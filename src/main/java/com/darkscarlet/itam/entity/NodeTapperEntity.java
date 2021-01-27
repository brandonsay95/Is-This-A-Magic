package com.darkscarlet.itam.entity;

import com.darkscarlet.itam.ITAMMod;
import com.darkscarlet.itam.block.ITAMBlocks;
import com.darkscarlet.itam.item.ITAMItem;
import com.darkscarlet.itam.screenhandlers.BoxScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeTapperEntity  extends BlockEntity implements NamedScreenHandlerFactory ,  ImplementedInventory, Tickable {

    private int _currentTick = 0 ;
    private int _maxTick = 20;
    private boolean canStack = true;


    private long lastTickTimer;

    public NodeTapperEntity(BlockEntityType<?> type) {
        super(type);
    }

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,items);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }

    public NodeTapperEntity(){
        super(ITAMEntity.NODE_TAPPER_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
       return new BoxScreenHandler(syncId,inv,this);
    }

    @Override
    public void tick() {

        if(this.world != null && !this.world.isClient ){
            if(canUse()){
            _currentTick = 0;

            ArrayList<ItemStack> blockCounts = calculateBLockMap(1);
            int manaPool = getManaPool();
            int deduct = 0;
            if(manaPool >= blockCounts.size())
            {
                for(ItemStack itemStack : blockCounts){
                    boolean canAdd = addSlot(itemStack);
                    if(canAdd)
                        deduct ++;

                }
                if(deduct >0){
                    markDirty();
                    deductManaPool(deduct);
                }

            }


            }else{
            _currentTick ++;


            }

        }
    }

    private boolean addSlot(ItemStack itemStack) {
        for(int i = 0 ; i < size();i++){
            ItemStack cStack = getStack(i);
            if(cStack.isEmpty())
            {
                setStack(i,itemStack);
                return true;
            }else{
                if(cStack.getItem() == itemStack.getItem()){
                    if(cStack.getCount() + itemStack.getCount() <= cStack.getMaxCount()){
                        cStack.setCount(cStack.getCount() + itemStack.getCount());
                        return true;
                    }else{
                        continue;
                    }
                }
            }

        }
        return false;

    }

    private int deductManaPool(int deduct){
        //int mxSize = 0;
        for(int i = 0 ; i < size();i++){
            Item itm = getStack(i).getItem();
            if(itm == ITAMItem.Anima_Essence){
               getStack(i).setCount(getStack(i).getCount() - deduct); // im not doing any negative protection here
                return deduct;
                //im so very lazy
            }
        }
        // return mxSize;
        return 0;
    }
    private int getManaPool() { // im so fucking lazy // were just not going to allow them to use more than one slot , unless they are good at math
        //int mxSize = 0;
        for(int i = 0 ; i < size();i++){
            Item itm = getStack(i).getItem();
            if(itm == ITAMItem.Anima_Essence){
                return getStack(i).getCount();
                //mxSize += getStack(i).getCount();
            }
        }
       // return mxSize;
        return 0;
    }

    private ArrayList<ItemStack> calculateBLockMap(int radius) {
        /// possibly only needs calculated when blocks around me are changed
        Map<Block,Integer> mp = new HashMap<Block,Integer>();
        int xpos = pos.getX();
        int ypos = pos.getY();
        int zpos = pos.getZ();
        for(int x = xpos-radius; x<= xpos+radius ; x++)
            for(int y = ypos-radius; y<= ypos+radius ; y++)
                for(int z = zpos-radius; z<= zpos+radius ; z++){
                    Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
                    if(ITAMMod.TAPPER_ITEMS.containsKey(block)){
                        ItemStack s = ITAMMod.TAPPER_ITEMS.get(block);
                        int cnt = mp.containsKey(block)?mp.get(block) +1:1;
                        mp.put(block,cnt);
                    }
                }
        ArrayList<ItemStack> lst = new ArrayList<ItemStack>();
        for(Block key: mp.keySet()){
            ItemStack stack = ITAMMod.TAPPER_ITEMS.get(key).copy();
            stack.setCount(stack.getCount() * mp.get(key));
            lst.add(stack);
        }
        return lst;

    }


    private boolean canUse() {
        return _currentTick>=_maxTick;

    }
}
