package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.MegalibClient;
import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.DataType.Size;
import com.megadoxs.megalib.util.Screen.MegalibButtonWidget;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

public class ButtonScreenElement{
    public static void widgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height) {
        int index = widgets.size();

        // Sets the action that will be executed when the user presses the button
        ActionFactory<Entity>.Instance action = data.get("action_on_press");
        MegalibButtonWidget megalibButtonWidget = MegalibButtonWidget.betterBuilder(data.get("text"), b -> { // Will probably completely redo the buttonWidgetClass
            MegalibClient.performWidgetActions(index);
            action.accept(MinecraftClient.getInstance().player);
        }).build();
        megalibButtonWidget.setAction(b -> action);

        // add the widget to the list to be loaded on the interface (before checking for the condition)
        widgets.add(megalibButtonWidget);

        //probably only checks the condition when the screen is open, need confirmation/fix
        if(data.get("press_condition") != null){
            ConditionFactory<Entity>.Instance condition = data.get("press_condition");
            megalibButtonWidget.setCondition(condition);
            ButtonScreenElement.IsButtonConditionFulfilled(index);
            //maybe make it wait for a response?
        }
        // might add a display condition as well?


        // lets the user chose the width and height of the button
        Size size = data.get("size");
        if (size.width() != 0)
            megalibButtonWidget.setWidth(size.getWidth(width));
        else
            megalibButtonWidget.setWidth(MinecraftClient.getInstance().textRenderer.getWidth(megalibButtonWidget.getMessage()) + 10);
        if (size.height() != 0)
            megalibButtonWidget.setHeight(size.getHeight(height));

        // going to have an alignment field
        //button.setX();
        //button.setY();

        // only for test/demo purposes
        megalibButtonWidget.setX(MinecraftClient.getInstance().getWindow().getScaledWidth()/2- megalibButtonWidget.getWidth()/2);
        megalibButtonWidget.setY(MinecraftClient.getInstance().getWindow().getScaledHeight()/2- megalibButtonWidget.getHeight()/2);

        // will maybe add something to play with the alpha value (transparency)
        //button.setAlpha(0.1F);

        // set the text that will be showed when the user hovers the button, if defined
        if (data.get("description") != null)
            megalibButtonWidget.setTooltip(Tooltip.of(data.get("description")));
    }

    public static void IsButtonConditionFulfilled(int index){
        // will need to find a way to include this when I build the button.
        MegalibClient.isButtonConditionFulfilled(index);
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory(
                Megalib.identifier("button"),
                new SerializableData()
                        .add("text", SerializableDataTypes.TEXT)
                        .add("size", MegalibDataTypes.SIZE, new Size(Size.Unit.PERCENTS, 0, 0))
                        .add("description", SerializableDataTypes.TEXT, null)
                        .add("alignment", MegalibDataTypes.ALIGNMENT, null) // might give it a default value
                        .add("action_on_press", ApoliDataTypes.ENTITY_ACTION)
                        .add("press_condition", ApoliDataTypes.ENTITY_CONDITION, null),
                ButtonScreenElement::widgets
        );
    }
}
