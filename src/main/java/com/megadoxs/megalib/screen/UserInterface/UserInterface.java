package com.megadoxs.megalib.screen.UserInterface;

import com.megadoxs.megalib.data.UserInterfaceData;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.screen_element.element.ButtonScreenElement;
import com.megadoxs.megalib.util.Screen.Button;
import com.megadoxs.megalib.util.DataType.Size;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class UserInterface extends Screen {

    private int x;
    private int y;

    private final Text title;
    private final Size size;

    private final ScreenElementFactory.Instance screenElements;

    private final ArrayList<Widget> widgets = new ArrayList<>();

    private int index;

    public UserInterface(UserInterfaceData interfaceData) {
        this(interfaceData.title(), interfaceData.size(), interfaceData.screenElements());
    }

    private UserInterface(String title, Size size, ScreenElementFactory.Instance screenElements) {
        super(Text.of(title));
        this.title = Text.of(title);
        this.size = size;
        this.screenElements = screenElements;
    }

    @Override
    protected void init() {
        super.init();
        index = -1; // so that when you ask for an index you get 0 as the first value

        if (size.unit().equals(Size.Unit.PERCENTS)){
            assert client != null;
            width = client.getWindow().getScaledWidth() * size.width()/100;
            height = client.getWindow().getScaledHeight() * size.height()/100;
        }
        else {
            width = size.width();
            height = size.height();
        };

        //Will make a title Bar that will display the screen title at the top of the screen (toggleable)


        //adding Widget to screen
        widgets.addAll(screenElements.getWidgets(this, width, height));
        for (int i = 0; i < widgets.size(); i++){
            // updates button active field because it needs the widgets arraylist to be defined to get the condition.
            if(widgets.get(i) instanceof Button button && button.getCondition() != null)
                ButtonScreenElement.IsButtonConditionFulfilled(i);
            widgets.get(i).forEachChild(this::addDrawableChild);
        }
    }

    @Override
    public void renderInGameBackground(DrawContext context) {
        assert client != null;
        //currently has a hardcoded color and centers the screen
        context.fillGradient( this.width/-2+client.getWindow().getScaledWidth()/2, this.height/-2+client.getWindow().getScaledHeight()/2, this.width/2+client.getWindow().getScaledWidth()/2, this.height/2+client.getWindow().getScaledHeight()/2, -1072689136, -804253680);
    }

    // might add security, but really not sure if it is needed, as it should be impossible to ask for an outOfBonds index
    public Widget getWidget(int index){
        return widgets.get(index);
    }

    public int getIndex() {
        return index += 1;
    }
}
