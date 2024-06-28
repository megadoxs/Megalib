package com.megadoxs.megalib.networking.packet.c2s;

import com.megadoxs.megalib.Megalib;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record IsButtonConditionFulfilledC2SPacket(int index) implements FabricPacket {
    public static final PacketType<IsButtonConditionFulfilledC2SPacket> TYPE = PacketType.create(
            Megalib.identifier("c2s/is_button_condition_fulfilled"), IsButtonConditionFulfilledC2SPacket::read
    );

    private static IsButtonConditionFulfilledC2SPacket read(PacketByteBuf buffer) {
        return new IsButtonConditionFulfilledC2SPacket(buffer.readInt());
    }
    @Override
    public void write(PacketByteBuf buffer) {
        buffer.writeInt(index);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
