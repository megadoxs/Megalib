package com.megadoxs.megalib.screen.UserInterface;

import com.megadoxs.megalib.data.UserInterfaceData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class UserInterface extends Screen {

    public UserInterface(UserInterfaceData interfaceData) {
        this(interfaceData.title(), interfaceData.height(), interfaceData.width());
    }

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
