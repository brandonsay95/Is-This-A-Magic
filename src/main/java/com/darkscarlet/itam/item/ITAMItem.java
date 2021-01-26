package com.darkscarlet.itam.item;

import com.darkscarlet.itam.ITAMMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ITAMItem {
    public static final Item STICK_OF_POWER = new StickOfPowerItem(new FabricItemSettings().group(ItemGroup.MISC));
    public static void InitializeItems(){
        Registry.register(Registry.ITEM,new Identifier(ITAMMod.MODID,"stick_of_power"),STICK_OF_POWER);

    }
}
