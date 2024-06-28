package com.megadoxs.megalib.networking.packet.s2c;

import com.megadoxs.megalib.data.UserInterfaceData;
import io.github.apace100.apoli.Apoli;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record ShowInterfaceS2CPacket(UserInterfaceData interfaceData) implements FabricPacket {

    public static final PacketType<ShowInterfaceS2CPacket> TYPE = PacketType.create(
            Apoli.identifier("s2c/show_interface"), ShowInterfaceS2CPacket::read
    );

    public static ShowInterfaceS2CPacket read(PacketByteBuf buf) {
        return new ShowInterfaceS2CPacket(UserInterfaceData.DATA_TYPE.receive(buf));
    }
    @Override
    public void write(PacketByteBuf buf) {
        UserInterfaceData.DATA_TYPE.send(buf, interfaceData);
    }

    @Override
    public PacketType<?> getType() {
        return null;
    }
}
