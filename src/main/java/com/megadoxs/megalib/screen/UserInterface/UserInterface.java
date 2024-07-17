package com.megadoxs.megalib.screen.UserInterface;

import com.megadoxs.megalib.MegalibClient;
import com.megadoxs.megalib.data.UserInterfaceData;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.screen_element.element.ButtonScreenElement;
import com.megadoxs.megalib.screen_element.element.DisplayScreenElement;
import com.megadoxs.megalib.util.DataType.Alignment;
import com.megadoxs.megalib.util.DataType.Size;
import com.megadoxs.megalib.util.Screen.MegalibButtonWidget;
import com.megadoxs.megalib.util.Screen.MegalibDisplayWidget;
import com.megadoxs.megalib.util.Screen.MegalibTextWidget;
import com.megadoxs.megalib.util.Screen.MegalibWidgetVisibility;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ElementListWidget;
import net.minecraft.client.gui.widget.ScrollableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;

import java.util.ArrayList;

@Environment(EnvType.CLIENT)
public class UserInterface extends Screen {

    // will be removed in later update
    private int x;
    private int y;

    private final Alignment alignment;

    private final Text title;
    private final Size size;

    private final ScreenElementFactory.Instance screenElements;

    // can't I just make a function for widget to add themselves here so when creating them? could make display and button set their value when creating.
    private final ArrayList<Widget> widgets = new ArrayList<>();
    //private MegalibWidgetVisibility widget; // has planned used in future

    public UserInterface(UserInterfaceData interfaceData) {
        this(interfaceData.title(), interfaceData.size(), interfaceData.alignment() ,interfaceData.screenElements());
    }

    private UserInterface(String title, Size size, Alignment alignment, ScreenElementFactory.Instance screenElements) {
        super(Text.of(title));
        this.title = Text.of(title);
        this.size = size;
        this.alignment = alignment;
        this.screenElements = screenElements;
    }

    @Override
    protected void init() {
        super.init();
        widgets.clear();

        assert client != null;
        if (size.unit().equals(Size.Unit.PERCENTS)){
            width = client.getWindow().getScaledWidth() * size.width()/100;
            height = client.getWindow().getScaledHeight() * size.height()/100;
        }
        else {
            width = size.width();
            height = size.height();
        }

        x = alignment.getX(0, client.getWindow().getScaledWidth(), width);
        y = alignment.getY(0, client.getWindow().getScaledHeight(), height);



        //Will make a title Bar that will display the screen title at the top of the screen (toggleable)
        MegalibTextWidget titleBar = new MegalibTextWidget(x, y, width, 9, title, textRenderer);
        titleBar.setAlignment(Alignment.CENTER_LEFT);
        //titleBar.setTextColor() // will set the text to a slightly less gray
        // need the background options to be added to make it also more dark
        addDrawableChild(titleBar);


        //adding Widget to screen
        screenElements.getWidgets(widgets, x, y + 9, width, height);

        // test for next widget to be added
//        widgets.add(new SliderWidget(0, 0, 50, 10, Text.of("test"), 20) {
//            @Override
//            protected void updateMessage() {
//
//            }
//
//            @Override
//            protected void applyValue() {
//
//            }
//        });

        widgets.forEach(widget -> {
            if (((MegalibWidgetVisibility) widget).getVisibility())
                widget.forEachChild(this::addDrawableChild);
        });
    }

    public void update(){
        for (int i = 0; i < widgets.size(); i++){
            //MegalibClient.
            if(widgets.get(i) instanceof MegalibButtonWidget megalibButtonWidget && megalibButtonWidget.getCondition() != null)
                ButtonScreenElement.IsButtonConditionFulfilled(i);
            if(widgets.get(i) instanceof MegalibDisplayWidget)
                DisplayScreenElement.setDisplayValue(i);
        }
    }

//    possibly will be used in the future
//    public ArrayList<Widget> getWidgets() {
//        return this.widgets;
//    }
//
//    public void setElement(MegalibWidgetVisibility element){
//        this.widget = element;
//    }

    // override for background image and shit. (to be done in the near future)
    @Override
    public void renderBackgroundTexture(DrawContext context) {
        // will need to override this for it to work: renderBackground()
        //context.drawTexture(OPTIONS_BACKGROUND_TEXTURE, 0, 0, 0, 0.0F, 0.0F, this.width, this.height, 32, 32);
    }

    @Override
    public boolean shouldPause() {
        return false; // default value is true
    }

    @Override
    public void renderInGameBackground(DrawContext context) {
        // potentially could add a border parameter.
        assert client != null;
        context.fillGradient( x, y, alignment.getEndX(0, client.getWindow().getScaledWidth(), width), alignment.getEndY(0, client.getWindow().getScaledHeight(), height), -1072689136, -804253680);
    }

    // might add security, but really not sure if it is needed, as it should be impossible to ask for an outOfBonds index
    public Widget getWidget(int index){
        return widgets.get(index);
    }
}
