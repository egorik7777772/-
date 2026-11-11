package com.egorik.keybind;

import com.egorik.gui.EgorikMenuScreen;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class Keybinds {
    public static KeyBinding OPEN_MENU;

    public static void register() {
        OPEN_MENU = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.egorik.open_menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_B,
            "category.egorik"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (OPEN_MENU.wasPressed()) {
                client.setScreen(new EgorikMenuScreen());
            }
        });
    }
}
