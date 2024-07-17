package com.megadoxs.megalib.networking.packet.c2s;

import com.megadoxs.megalib.Megalib;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record SetDisplayValueC2SPacket (int index) implements FabricPacket {
    public static final PacketType<SetDisplayValueC2SPacket> TYPE = PacketType.create(
            Megalib.identifier("c2s/set_display_value"), SetDisplayValueC2SPacket::read
    );

    private static SetDisplayValueC2SPacket read(PacketByteBuf buffer) {
        return new SetDisplayValueC2SPacket(buffer.readInt());
    }

    public void write(PacketByteBuf buffer) {
        buffer.writeInt(index);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }
}
