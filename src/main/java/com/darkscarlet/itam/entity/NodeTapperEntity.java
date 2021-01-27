package com.darkscarlet.itam.entity;

import com.darkscarlet.itam.ITAMMod;
import com.darkscarlet.itam.block.ITAMBlocks;
import com.darkscarlet.itam.screenhandlers.BoxScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
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
                for(int x = pos.getX()-1; x <= pos.getX()+1 ;x++){
                    for(int y = pos.getY()-1; y <= pos.getY()+1 ;y++){
                        for(int z = pos.getZ()-1; z <= pos.getZ()+1 ;z++){
                            if(x == pos.getX() && y == pos.getY() && z == pos.getZ())
                                continue;
                            BlockEntity e = world.getBlockEntity(new BlockPos(x,y,z));
                            BlockState state = world.getBlockState(new BlockPos(x,y,z));
                            if(state.getBlock() == ITAMBlocks.IRON_ORE_NODE){
                                for(int i = 0 ; i < items.size();i++){

                                    if(canStack && getStack(i).getItem() == Items.IRON_NUGGET && getStack(i).getCount() < getStack(i).getMaxCount())
                                    {
                                        getStack(i).setCount(getStack(i).getCount() + 1);
                                        break;

                                    }

                                    if(getStack(i).isEmpty()){
                                        setStack(i, new ItemStack(Items.IRON_NUGGET,1));
                                        break;

                                    }
                                }
                            }
                        }
                    }
                }
            }else{
            _currentTick ++;


            }

        }
    }


    private boolean canUse() {
        return _currentTick>=_maxTick;

    }
}
