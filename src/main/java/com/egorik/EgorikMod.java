package com.egorik;

import com.egorik.config.EgorikConfig;
import com.egorik.keybind.Keybinds;
import com.egorik.fps.FpsBooster;
import com.egorik.sky.SkyRenderer;
import com.egorik.sound.HitSoundHandler;
import net.fabricmc.api.ClientModInitializer;

public class EgorikMod implements ClientModInitializer {
    public static final String MOD_ID = "egorik";

    @Override
    public void onInitializeClient() {
        EgorikConfig.load();
        Keybinds.register();
        FpsBooster.init();
        SkyRenderer.init();
        HitSoundHandler.init();
    }
}
