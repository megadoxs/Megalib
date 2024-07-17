package com.megadoxs.megalib.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.megadoxs.megalib.registry.MegalibRegistries;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.screen_element.ScreenElements;
import com.megadoxs.megalib.util.DataType.Alignment;
import com.megadoxs.megalib.util.DataType.Axis;
import com.megadoxs.megalib.util.DataType.Border;
import com.megadoxs.megalib.util.DataType.Size;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.calio.ClassUtil;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import io.github.apace100.calio.util.DynamicIdentifier;
import io.github.apace100.calio.util.IdentifierAlias;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.util.List;
import java.util.function.BiFunction;

public class MegalibDataTypes {

    public static final SerializableDataType<Size.Unit> UNIT = SerializableDataType.enumValue(Size.Unit.class);
    public static final SerializableDataType<Axis> AXIS = SerializableDataType.enumValue(Axis.class);
    public static final SerializableDataType<Alignment> ALIGNMENT = SerializableDataType.enumValue(Alignment.class);

    public static final SerializableDataType<Size> SIZE = SerializableDataType.compound(
            Size.class,
            new SerializableData()
                    .add("unit", MegalibDataTypes.UNIT, Size.Unit.PERCENTS)
                    .add("width", ApoliDataTypes.NON_NEGATIVE_INT, 0)
                    .add("height", ApoliDataTypes.NON_NEGATIVE_INT, 0),
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

    public static final SerializableDataType<Integer> RGB = SerializableDataType.boundNumber(
            SerializableDataTypes.INT, 0, 255,
            value -> (min, max) -> {

                if (value < min) {
                    throw new IllegalArgumentException("Expected value to be equal or greater than " + min + "! (current value: " + value + ")");
                }

                if (value > max) {
                    throw new IllegalArgumentException("Expected value to be equal or smaller than " + max + "! (current value: " + value + ")");
                }

                return value;

            }
    );

    public static final SerializableDataType<Float> ALPHA = SerializableDataType.boundNumber(
            SerializableDataTypes.FLOAT, 0f, 1f,
            value -> (min, max) -> {

                if (value < min) {
                    throw new IllegalArgumentException("Expected value to be equal or greater than " + min + "! (current value: " + value + ")");
                }

                if (value > max) {
                    throw new IllegalArgumentException("Expected value to be equal or smaller than " + max + "! (current value: " + value + ")");
                }

                return value;

            }
    );

    public static final SerializableDataType<Border> SINGLE_BORDER = SerializableDataType.compound(
            Border.class,
            new SerializableData()
                    .add("should_render", SerializableDataTypes.BOOLEAN, true)
                    .add("width", ApoliDataTypes.NON_NEGATIVE_INT, 1)
                    .add("alpha", MegalibDataTypes.ALPHA, 1f)
                    .add("red", MegalibDataTypes.RGB, 0)
                    .add("green", MegalibDataTypes.RGB, 0)
                    .add("blue", MegalibDataTypes.RGB, 0),
            (data) -> new Border(
                    data.get("should_render"),
                    data.get("width"),
                    (int) (255 * (float) data.get("alpha")),
                    data.get("red"),
                    data.get("green"),
                    data.get("blue")
            ),
            (serializableData, border) -> {
                SerializableData.Instance data = serializableData.new Instance();

                data.set("should_render", border.should_render());
                data.set("width", border.width());
                data.set("alpha", border.alpha() == 0 ? 0 : (float) (255 / border.alpha()));
                data.set("red", border.red());
                data.set("green", border.green());
                data.set("blue", border.blue());

                return data;
            }
    );

    // this is also shit, but it works
    public static final SerializableDataType<JsonObject> MULTIPLE_BORDERS = SerializableDataType.compound(
            JsonObject.class,
            new SerializableData()
                    .add("border_bottom", SINGLE_BORDER, null)
                    .add("border_top", SINGLE_BORDER, null)
                    .add("border_left", SINGLE_BORDER, null)
                    .add("border_right", SINGLE_BORDER, null),
            data -> {
                JsonObject jsonObject = new JsonObject();
                jsonObject.add("border_bottom", SINGLE_BORDER.write(data.get("border_bottom")));
                jsonObject.add("border_top", SINGLE_BORDER.write(data.get("border_top")));
                jsonObject.add("border_left", SINGLE_BORDER.write(data.get("border_left")));
                jsonObject.add("border_right", SINGLE_BORDER.write(data.get("border_right")));
                return jsonObject;
            },
            (serializableData, jsonObject) -> {
                SerializableData.Instance data = serializableData.new Instance();
                data.set("border_bottom", SINGLE_BORDER.read(jsonObject.get("border_bottom")));
                data.set("border_top", SINGLE_BORDER.read(jsonObject.get("border_top")));
                data.set("border_left", SINGLE_BORDER.read(jsonObject.get("border_left")));
                data.set("border_right", SINGLE_BORDER.read(jsonObject.get("border_right")));
                return data;
            }
    );

    // this is shit, but it works
    public static final SerializableDataType<Object> BORDER = new SerializableDataType<>(
            Object.class,
            (buf, obj) -> {
                if (obj instanceof Border) {
                    buf.writeBoolean(false);
                    SINGLE_BORDER.send(buf, obj);
                } else if (obj instanceof Border[] borders) {
                    buf.writeBoolean(true);
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("border_top", SINGLE_BORDER.write(borders[0]));
                    jsonObject.add("border_bottom", SINGLE_BORDER.write(borders[1]));
                    jsonObject.add("border_left", SINGLE_BORDER.write(borders[2]));
                    jsonObject.add("border_right", SINGLE_BORDER.write(borders[3]));
                    MULTIPLE_BORDERS.send(buf, jsonObject);
                } else {
                    throw new IllegalArgumentException("Unsupported object type");
                }
            },
            buf -> {
                if (buf.readBoolean()) {
                    JsonObject jsonObject = MULTIPLE_BORDERS.receive(buf);
                    Border border_top = SINGLE_BORDER.read(jsonObject.get("border_top"));
                    Border border_bottom = SINGLE_BORDER.read(jsonObject.get("border_bottom"));
                    Border border_left = SINGLE_BORDER.read(jsonObject.get("border_left"));
                    Border border_right = SINGLE_BORDER.read(jsonObject.get("border_right"));
                    return new Border[]{border_top, border_bottom, border_left, border_right};
                } else {
                    return SINGLE_BORDER.receive(buf);
                }
            },
            jsonElement -> {
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();

                    Border border_top = jsonObject.has("border_top") ? SINGLE_BORDER.read(jsonObject.get("border_top")) : new Border(false, 0, 0, 0, 0, 0);
                    Border border_bottom = jsonObject.has("border_bottom") ? SINGLE_BORDER.read(jsonObject.get("border_bottom")) : new Border(false, 0, 0, 0, 0, 0);
                    Border border_left = jsonObject.has("border_left") ? SINGLE_BORDER.read(jsonObject.get("border_left")) : new Border(false, 0, 0, 0, 0, 0);
                    Border border_right = jsonObject.has("border_right") ? SINGLE_BORDER.read(jsonObject.get("border_right")) : new Border(false, 0, 0, 0, 0, 0);

                    return new Border[]{border_top, border_bottom, border_left, border_right};
                } else {
                    Border border = SINGLE_BORDER.read(jsonElement);
                    return new Border[]{border, border, border, border};
                }
            },
            obj -> {
                if (obj instanceof Border) {
                    return SINGLE_BORDER.write((Border) obj);
                } else if (obj instanceof Border[] borders) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.add("border_top", SINGLE_BORDER.write(borders[0]));
                    jsonObject.add("border_bottom", SINGLE_BORDER.write(borders[1]));
                    jsonObject.add("border_left", SINGLE_BORDER.write(borders[2]));
                    jsonObject.add("border_right", SINGLE_BORDER.write(borders[3]));
                    return MULTIPLE_BORDERS.write(jsonObject);
                } else {
                    throw new IllegalArgumentException("Unsupported object type");
                }
            }
    );

    public static final SerializableDataType<ScreenElementFactory.Instance> SCREEN_ELEMENT = element(MegalibRegistries.SCREEN_ELEMENT_FACTORY, ScreenElements.ALIASES, "Screen Element");
    public static final SerializableDataType<List<ScreenElementFactory.Instance>> SCREEN_ELEMENTS = SerializableDataType.list(SCREEN_ELEMENT);

    public static SerializableDataType<ScreenElementFactory.Instance> element(Registry<ScreenElementFactory> registry, String name) {
        return element(registry, IdentifierAlias.GLOBAL, name);
    }

    public static SerializableDataType<ScreenElementFactory.Instance> element(Registry<ScreenElementFactory> registry, IdentifierAlias aliases, String name) {
        return element(registry, aliases, (conditionFactories, id) -> new IllegalArgumentException(name + " \"" + id + "\" is not registered"));
    }

    public static SerializableDataType<ScreenElementFactory.Instance> element(Registry<ScreenElementFactory> registry, IdentifierAlias aliases, BiFunction<Registry<ScreenElementFactory>, Identifier, RuntimeException> errorHandler) {
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
