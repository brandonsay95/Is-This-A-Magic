package com.darkscarlet.itam.item;

import com.darkscarlet.itam.ITAMMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ITAMItem {
    public static final Item STICK_OF_POWER = new StickOfPowerItem(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item Anima_Essence = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item ANIMA_SPEED_UP = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item ANIMA_EFFICIENCY_UP = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static void InitializeItems(){
        Registry.register(Registry.ITEM,new Identifier(ITAMMod.MODID,"stick_of_power"),STICK_OF_POWER);
        Registry.register(Registry.ITEM,new Identifier(ITAMMod.MODID,"anima_essence"),Anima_Essence);


        Registry.register(Registry.ITEM,new Identifier(ITAMMod.MODID,"anima_speed_up"),ANIMA_SPEED_UP);
        Registry.register(Registry.ITEM,new Identifier(ITAMMod.MODID,"anima_efficiency_up"),ANIMA_EFFICIENCY_UP);

    }
}
