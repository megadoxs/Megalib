package com.megadoxs.megalib.networking.packet.c2s;

import com.megadoxs.megalib.Megalib;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record PerformWidgetActionC2SPacket(int index) implements FabricPacket {

    public static final PacketType<PerformWidgetActionC2SPacket> TYPE = PacketType.create(
            Megalib.identifier("c2s/perform_button_action"), PerformWidgetActionC2SPacket::read
    );

    private static PerformWidgetActionC2SPacket read(PacketByteBuf buffer) {
        return new PerformWidgetActionC2SPacket(buffer.readInt());
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
