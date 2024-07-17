package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.MegalibClient;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.Screen.MegalibCheckboxWidget;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

public class CheckboxScreenElement {
    public static void widgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height) {
        int index = widgets.size();

        ActionFactory<Entity>.Instance checkedAction = data.get("action_on_check");
        ActionFactory<Entity>.Instance uncheckedAction = data.get("action_on_uncheck");

        MegalibCheckboxWidget checkbox = MegalibCheckboxWidget.builder(data.get("description"), MinecraftClient.getInstance().textRenderer, (cb, checked) -> {
            if (checked){
                checkedAction.accept(MinecraftClient.getInstance().player);
            }
            else {
                uncheckedAction.accept(MinecraftClient.getInstance().player);
            }
            MegalibClient.performWidgetActions(index);
        }).build();

        checkbox.setAction(cb -> {
            if (((MegalibCheckboxWidget) cb).isChecked())
                return checkedAction;
            else
                return uncheckedAction;
        });

//        if (data.get("border") != null)
//            display.setBorder(data.get("border"));

        widgets.add(checkbox);
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory(
                Megalib.identifier("checkbox"),
                new SerializableData()
                        .add("description", SerializableDataTypes.TEXT)
                        .add("checked", SerializableDataTypes.BOOLEAN, false)
                        .add("action_on_check", ApoliDataTypes.ENTITY_ACTION)
                        .add("action_on_uncheck", ApoliDataTypes.ENTITY_ACTION),
                CheckboxScreenElement::widgets
        );
    }
}
