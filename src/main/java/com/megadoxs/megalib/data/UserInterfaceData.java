package com.megadoxs.megalib.data;

import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.DataType.Size;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;

public record UserInterfaceData(String title, Size size, String location, ScreenElementFactory.Instance screenElements) {

    public static final SerializableData DATA = new SerializableData()
            .add("title", SerializableDataTypes.STRING, "UserInterface.Title")
            .add("size", MegalibDataTypes.SIZE, new Size(Size.Unit.PERCENTS, 100, 100))
            .add("location", SerializableDataTypes.STRING, "centered")
            .add("element", MegalibDataTypes.SCREEN_ELEMENT, null);

    public static final SerializableDataType<UserInterfaceData> DATA_TYPE = SerializableDataType.compound(
            UserInterfaceData.class,
            DATA,
            UserInterfaceData::fromData,
            (serializableData, interfaceData) -> interfaceData.toData()
    );

    public static UserInterfaceData fromData(SerializableData.Instance data) {
        return new UserInterfaceData(
                data.get("title"),
                data.get("size"),
                data.get("location"),
                data.get("element")
        );
    }

    public SerializableData.Instance toData() {

        SerializableData.Instance data = DATA.new Instance();

        data.set("title", this.title());
        data.set("size", this.size());
        data.set("location", this.location());
        data.set("element", this.screenElements());

        return data;

    }

}
