package com.megadoxs.megalib.data;

import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;

public record UserInterfaceData(String title) {

    public static final SerializableData DATA = new SerializableData()
            .add("title", SerializableDataTypes.STRING);

    public static final SerializableDataType<UserInterfaceData> DATA_TYPE = SerializableDataType.compound(
            UserInterfaceData.class,
            DATA,
            UserInterfaceData::fromData,
            (serializableData, interfaceData) -> interfaceData.toData()
    );

    public static UserInterfaceData fromData(SerializableData.Instance data) {
        return new UserInterfaceData(
                data.get("title")
        );
    }

    public SerializableData.Instance toData() {

        SerializableData.Instance data = DATA.new Instance();

        data.set("title", this.title());

        return data;

    }

}
