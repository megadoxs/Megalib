package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.MegalibClient;
import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.Screen.ScreenElement;
import com.megadoxs.megalib.util.Screen.Size;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

public class ButtonScreenElement implements ScreenElement {

    public static ArrayList<Widget> widgets(SerializableData.Instance data, int width, int height, PowerType<?> powerType) {
        ArrayList<Widget> widget = new ArrayList<>();

        // Sets the action that will be executed when the user presses the button
        ActionFactory<Entity>.Instance action = data.get("action_on_press");
        ButtonWidget button = ButtonWidget.builder(data.get("text"), a -> MegalibClient.performButtonActions(powerType.getIdentifier()) /*action.accept(MinecraftClient.getInstance().player)*/).build();

        // will make it so that the button cannot be pressed unless the condition(s) are true



        // lets the user chose the width and height of the button
        Size size = data.get("size");
        if (size.width() != 0)
            button.setWidth(size.getWidth(width));
        else
            button.setWidth(MinecraftClient.getInstance().textRenderer.getWidth(button.getMessage()) + 10);
        if (size.height() != 0)
            button.setHeight(size.getHeight(height));

        // set the text that will be showed when the user hovers the button, if defined
        if (data.get("description") != null)
            button.setTooltip(Tooltip.of(data.get("description")));


        widget.add(button);
        return widget;
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory(
                Megalib.identifier("button"),
                new SerializableData()
                        .add("text", SerializableDataTypes.TEXT)
                        .add("size", MegalibDataTypes.SIZE, new Size("", 0, 0))
                        .add("description", SerializableDataTypes.TEXT, null)
                        .add("action_on_press", ApoliDataTypes.ENTITY_ACTION)
                        .add("press_condition", ApoliDataTypes.ENTITY_CONDITION, null),
                ButtonScreenElement.class
        );
    }
}
