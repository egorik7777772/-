package com.egorik.sky;

import com.egorik.config.EgorikConfig;
import net.minecraft.client.MinecraftClient;

public class SkyRenderer {
    public static void init() {
        // Инициализация для будущих версий
    }

    public static int computeColor(int mode, long time) {
        return switch (mode) {
            case 1 -> 0xFF4444;
            case 2 -> 0x4488FF;
            case 3 -> 0xAA44FF;
            case 4 -> 0xFF8844;
            case 5 -> rainbow(time);
            case 6 -> 0x88CCFF;
            default -> 0xFFFFFF;
        };
    }

    private static int rainbow(long time) {
        float hue = (time % 6000) / 6000f;
        return java.awt.Color.HSBtoRGB(hue, 0.7f, 1.0f) & 0xFFFFFF;
    }

    public static int currentSkyColor() {
        if (EgorikConfig.skyMode == 0) return 0xFFFFFF;
        var mc = MinecraftClient.getInstance();
        long t = mc.world != null ? mc.world.getTime() : 0;
        return computeColor(EgorikConfig.skyMode, t);
    }
}
