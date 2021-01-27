package com.darkscarlet.itam.client;

import com.darkscarlet.itam.ITAMMod;
import com.darkscarlet.itam.screenhandlers.BoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class ITAMClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ITAMMod.BOX_SCREEN_HANDLER, BoxScreen::new);
    }
}
