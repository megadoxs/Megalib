package com.megadoxs.megalib.screen_element;

import com.google.gson.JsonObject;
import com.megadoxs.megalib.util.Screen.ScreenElement;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.Factory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class ScreenElementFactory implements Factory {

    private final Identifier identifier;
    protected final SerializableData data;
    protected final Class<ScreenElement> clazz;

    public ScreenElementFactory(Identifier identifier, SerializableData data, Class<? extends ScreenElement> clazz){
        this.identifier = identifier;
        this.data = data.copy();
        this.clazz = (Class<ScreenElement>) clazz;
    }


    public class Instance {

        protected final SerializableData.Instance dataInstance;
        protected Instance(SerializableData.Instance data) {
            this.dataInstance = data;
        }

        // this is such a cursed way to get the widgets
        public ArrayList<Widget> getWidgets(int width, int height, PowerType<?> powerType) {
            try {
                Method method = clazz.getMethod("widgets", SerializableData.Instance.class, int.class, int.class, PowerType.class);
                return (ArrayList<Widget>) method.invoke(null, dataInstance, width, height, powerType);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        public void write(PacketByteBuf buf) {
            buf.writeIdentifier(identifier);
            data.write(buf, dataInstance);
        }

        public JsonObject toJson() {

            JsonObject jsonObject = data.write(dataInstance);
            jsonObject.addProperty("type", identifier.toString());

            return jsonObject;

        }

    }

    @Override
    public Identifier getSerializerId() {
        return identifier;
    }

    @Override
    public SerializableData getSerializableData() {
        return data;
    }

    public Instance read(JsonObject json) {
        return new Instance(data.read(json));
    }

    public Instance read(PacketByteBuf buffer) {
        return new Instance(data.read(buffer));
    }
}
