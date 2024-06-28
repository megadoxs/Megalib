package com.megadoxs.megalib.networking.packet.c2s;

import com.megadoxs.megalib.Megalib;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public record PerformButtonActionsC2SPacket(int index) implements FabricPacket {

    public static final PacketType<PerformButtonActionsC2SPacket> TYPE = PacketType.create(
            Megalib.identifier("c2s/perform_button_action"), PerformButtonActionsC2SPacket::read
    );

    private static PerformButtonActionsC2SPacket read(PacketByteBuf buffer) {
        return new PerformButtonActionsC2SPacket(buffer.readInt());
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
