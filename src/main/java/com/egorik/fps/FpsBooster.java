package com.egorik.fps;

import com.egorik.config.EgorikConfig;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;

public class FpsBooster {
    private static int tick;

    public static void init() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (++tick % 200 == 0) apply(client);
        });
    }

    public static void apply(MinecraftClient mc) {
        if (mc.options == null) return;

        if (EgorikConfig.fpsBooster) {
            var o = mc.options;
            o.getViewDistance().setValue(8);
            o.getSimulationDistance().setValue(6);
            o.getEntityShadows().setValue(false);
            o.getBobView().setValue(false);
            o.getScreenEffectScale().setValue(0.0);
            o.getFovEffectScale().setValue(0.0);
            o.getAo().setValue(false);
        }

        mc.options.getParticles().setValue(
            EgorikConfig.particles ? ParticlesMode.ALL : ParticlesMode.MINIMAL);

        mc.options.getGamma().setValue(EgorikConfig.fullbright ? 10.0 : 1.0);
    }
}
