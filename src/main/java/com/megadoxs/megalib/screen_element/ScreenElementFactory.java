package com.megadoxs.megalib.screen_element;

import com.google.gson.JsonObject;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import io.github.apace100.apoli.power.factory.Factory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ScreenElementFactory implements Factory {

    private final Identifier identifier;
    protected final SerializableData data;
    protected final ScreenElement widget;

    public ScreenElementFactory(Identifier identifier, SerializableData data, ScreenElement widget){
        this.identifier = identifier;
        this.data = data.copy();
        this.widget = widget;
    }

    public interface ScreenElement {
        void getWidgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height);
    }

    public class Instance{

        protected final SerializableData.Instance dataInstance;
        protected Instance(SerializableData.Instance data) {
            this.dataInstance = data;
        }

        public void getWidgets(ArrayList<Widget> widgets, int x, int y, int width, int height) {
            widget.getWidgets(dataInstance, widgets, x, y, width, height);
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
