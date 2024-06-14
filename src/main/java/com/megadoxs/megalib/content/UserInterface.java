package com.megadoxs.megalib.content;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class UserInterface extends Screen {

    public UserInterface(String title, int height, int width) {
        super(Text.of(title));
        super.height = height;
        super.width = width;
    }

    @Override
    protected void init() {
        super.init();
    }
}
