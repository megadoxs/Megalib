package com.megadoxs.megalib.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.megadoxs.megalib.registry.MegalibRegistries;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.screen_element.ScreenElements;
import com.megadoxs.megalib.util.Screen.Size;
import io.github.apace100.calio.util.IdentifierAlias;
import io.github.apace100.calio.ClassUtil;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import io.github.apace100.calio.util.DynamicIdentifier;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.List;
import java.util.function.BiFunction;

public class MegalibDataTypes {
    public static final SerializableDataType<Size> SIZE = SerializableDataType.compound(
            Size.class,
            new SerializableData()
                    .add("unit", SerializableDataTypes.STRING, "percents")
                    .add("width", SerializableDataTypes.INT, 0)
                    .add("height", SerializableDataTypes.INT, 0),
            (data) -> new Size(
                    data.get("unit"),
                    data.get("width"),
                    data.get("height")
            ),
            (serializableData, Size) -> {
                SerializableData.Instance data = serializableData.new Instance();

                data.set("unit", Size.unit());
                data.set("width", Size.width());
                data.set("height", Size.height());

                return data;
            }
    );

    public static final SerializableDataType<ScreenElementFactory.Instance> SCREEN_ELEMENT = element(MegalibRegistries.SCREEN_ELEMENT_FACTORY, ScreenElements.ALIASES, "Screen Element");
    public static final SerializableDataType<List<ScreenElementFactory.Instance>> SCREEN_ELEMENTS = SerializableDataType.list(SCREEN_ELEMENT);

    public static <T> SerializableDataType<ScreenElementFactory.Instance> element(Registry<ScreenElementFactory> registry, String name) {
        return element(registry, IdentifierAlias.GLOBAL, name);
    }

    public static <T> SerializableDataType<ScreenElementFactory.Instance> element(Registry<ScreenElementFactory> registry, IdentifierAlias aliases, String name) {
        return element(registry, aliases, (conditionFactories, id) -> new IllegalArgumentException(name + " \"" + id + "\" is not registered"));
    }

    public static <T> SerializableDataType<ScreenElementFactory.Instance> element(Registry<ScreenElementFactory> registry, IdentifierAlias aliases, BiFunction<Registry<ScreenElementFactory>, Identifier, RuntimeException> errorHandler) {
        return new SerializableDataType<>(
                ClassUtil.castClass(ScreenElementFactory.Instance.class),
                (buf, instance) -> instance.write(buf),
                buf -> {
                    Identifier factoryId = buf.readIdentifier();
                    return registry
                            .getOrEmpty(aliases.resolveAlias(factoryId, registry::containsId))
                            .orElseThrow(() -> errorHandler.apply(registry, factoryId))
                            .read(buf);
                },
                jsonElement -> {

                    if (!(jsonElement instanceof JsonObject jsonObject)) {
                        throw new JsonSyntaxException("Expected a JSON object.");
                    }

                    Identifier factoryId = DynamicIdentifier.of(JsonHelper.getString(jsonObject, "type"));
                    return registry
                            .getOrEmpty(aliases.resolveAlias(factoryId, registry::containsId))
                            .orElseThrow(() -> errorHandler.apply(registry, factoryId))
                            .read(jsonObject);

                },
                ScreenElementFactory.Instance::toJson
        );
    }
}
