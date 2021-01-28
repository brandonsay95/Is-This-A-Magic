package com.darkscarlet.itam.entity;

import com.darkscarlet.itam.ITAMMod;
import com.darkscarlet.itam.TapperEntry;
import com.darkscarlet.itam.block.ITAMBlocks;
import com.darkscarlet.itam.block.NodeTapperBlock;
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

    private  int speed = 1;
    private  double efficiency = 1;
    private int _currentTick = 0 ;
    private int _maxTick = 20;
    private boolean canStack = true;


    private long lastTickTimer;

    private int lastTickTime;
    private int lastBlockCost;

    public NodeTapperEntity(BlockEntityType<?> type) {
        super(type);
    }

    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);

    public NodeTapperEntity(int speed, double efficiency) {
        super(ITAMEntity.NODE_TAPPER_ENTITY);
        this.speed = speed;
        this.efficiency=efficiency;

    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag,items);

        this.speed = tag.getInt("speed");
        this.efficiency = tag.getDouble("efficiency");

    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        tag.putInt("speed",this.speed);
        tag.putDouble("efficiency",this.efficiency);
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
        return new TranslatableText("E:" + this.efficiency + "|S:" + this.speed + "TC:" + this.lastTickTime + "LC:" +this.lastBlockCost);
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
       return new BoxScreenHandler(syncId,inv,this);
    }
    int _cTick2 =0;
    @Override
    public void tick() {

        if(this.world != null && !this.world.isClient ){
            //if(canUse()){

            //_cTick2 += _currentTick;
                // block works in units of 20 ticks
                //anything lower is not calculated
                //anything higher needs to wait another cycle.
                // Im so sorry :-(

            //_currentTick = 0;
            _cTick2+=1; // tick iterator
            ArrayList<tapperStruct> blockCounts = calculateBlockMap(1);
            int blockCountCost = 0;
            int tickTime = 0;

            for(tapperStruct stack :blockCounts){
                blockCountCost += stack.tapperEntry.CostPerCycle;// * stack.multiplier;
                tickTime += stack.tapperEntry.TicksPerCycle ;//* stack.multiplier;
            }

            calcSpeedAndEfficiency();
            this.lastTickTime = tickTime;
            this.lastBlockCost = blockCountCost;

            tickTime /= this.speed;
            blockCountCost /=this.efficiency;


            if(tickTime <=0)
                tickTime = 1;
            if(_cTick2 <tickTime)
                return;
            _cTick2 -=tickTime;

            int manaPool = getManaPool();
            int deduct = 0;

            if(manaPool >= blockCountCost)
            {
                for(tapperStruct stack : blockCounts){
                    ItemStack stk = stack.tapperEntry.OutputPerCycle.copy();
                    stk.setCount(stk.getCount() * stack.multiplier);

                    boolean canAdd = addSlot(stk.copy());
                    if(canAdd)
                        deduct += (stack.tapperEntry.CostPerCycle
                                //* stack.multiplier

                        )/ this.efficiency;

                }
                if(deduct >0){
                    markDirty();
                    deductManaPool(deduct);
                }

            }


            }else{
            _currentTick ++;


            }

       // }
    }

    private void calcSpeedAndEfficiency() {
        int spd = 1;
        int eff = 1;
        for(int i = 0 ; i < size();i++) {
            if(! getStack(i).isEmpty() && getStack(i).getItem() == ITAMItem.ANIMA_SPEED_UP)
                spd++;
            if(! getStack(i).isEmpty() && getStack(i).getItem() == ITAMItem.ANIMA_EFFICIENCY_UP)
                eff++;
        }
        this.speed = spd;
        this.efficiency = eff;

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

            if(deduct<=0)
                return 0 ;

            Item itm = getStack(i).getItem();
            if(itm == ITAMItem.Anima_Essence){
                if(getStack(i).getCount() - deduct<0){
                    deduct -= getStack(i).getCount();
                    getStack(i).setCount(0);
                }else{
                    getStack(i).setCount(getStack(i).getCount() - deduct); // im not doing any negative protection here
                    return 0;
                }
               // return deduct;
                //im so very lazy
            }
        }
        // return mxSize;
        return 0;
    }
    private int getManaPool() { // im so fucking lazy // were just not going to allow them to use more than one slot , unless they are good at math
        int mxSize = 0;
        for(int i = 0 ; i < size();i++){
            Item itm = getStack(i).getItem();
            if(itm == ITAMItem.Anima_Essence){
                //return getStack(i).getCount();
                mxSize += getStack(i).getCount();
            }
        }
        return mxSize;
       // return 0;
    }
    private class tapperStruct{
        public TapperEntry tapperEntry;
        public int multiplier = 0;
        public tapperStruct Entry(TapperEntry entry)
        {
            this.tapperEntry = entry;
            return this;
        }

        public tapperStruct Multiplier(int m)
        {
            this.multiplier = m;
            return this;
        }
    }
    private ArrayList<tapperStruct> calculateBlockMap(int radius) {
        /// possibly only needs calculated when blocks around me are changed
        ArrayList<tapperStruct> lst = new ArrayList<tapperStruct>();

        int xpos = pos.getX();
        int ypos = pos.getY();
        int zpos = pos.getZ();
        for(int x = xpos-radius; x<= xpos+radius ; x++)
            for(int y = ypos-radius; y<= ypos+radius ; y++)
                for(int z = zpos-radius; z<= zpos+radius ; z++){
                    Block block = world.getBlockState(new BlockPos(x,y,z)).getBlock();
                    if(ITAMMod.TAPPER_ENTRIES.stream().anyMatch(n->n.Key == block)){
                        TapperEntry s = ITAMMod.TAPPER_ENTRIES.stream().filter(n->n.Key == block).findFirst().orElse(null);
                        if(lst.stream().anyMatch(n->n.tapperEntry == s)){
                            tapperStruct str =  lst.stream().filter(n->n.tapperEntry == s).findFirst().orElse(null);
                            str.multiplier++;
                        }else{
                            lst.add(new tapperStruct().Entry(s).Multiplier(1));
                        }
                    }
                }

        return lst;

    }


    private boolean canUse() {
        return _currentTick>=_maxTick;

    }
}
