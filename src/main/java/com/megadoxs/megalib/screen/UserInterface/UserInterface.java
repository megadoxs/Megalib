package com.megadoxs.megalib.screen.UserInterface;

import com.megadoxs.megalib.data.UserInterfaceData;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.Screen.Size;
import io.github.apace100.apoli.power.PowerType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class UserInterface extends Screen {
    private final Size size;

    private final ScreenElementFactory.Instance screenElements;

    private final PowerType<?> powerType;

    public UserInterface(UserInterfaceData interfaceData, PowerType<?> powerType) {
        this(interfaceData.title(), interfaceData.size(), interfaceData.screenElements(), powerType);
    }

    private UserInterface(String title, Size size, ScreenElementFactory.Instance screenElements, PowerType<?> powerType) {
        super(Text.of(title));
        this.size = size;
        this.screenElements = screenElements;
        this.powerType = powerType;
    }

    @Override
    protected void init() {
        super.init();
        if (size.unit().equals("percents")){
            assert client != null;
            width = client.getWindow().getScaledWidth() * size.width()/100;
            height = client.getWindow().getScaledHeight() * size.height()/100;
        }
        else {
            width = size.width();
            height = size.height();
        }
        screenElements.getWidgets(width, height, powerType).forEach(widget -> widget.forEachChild(this::addDrawableChild));
        //ButtonWidget button = ButtonWidget.builder(Text.literal("test"), action -> {}).build();
        //button.setWidth(width);
        //addDrawableChild(button);
    }
}
