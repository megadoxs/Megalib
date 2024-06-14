package com.megadoxs.megalib.networking.packet.s2c;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.data.UserInterfaceData;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.minecraft.network.PacketByteBuf;

public record UserInterfaceS2CPacket(UserInterfaceData InterfaceData) implements FabricPacket {

    public static final PacketType<UserInterfaceS2CPacket> TYPE = PacketType.create(
            Megalib.identifier("s2c/user_interface"), UserInterfaceS2CPacket::read
    );

    public static UserInterfaceS2CPacket read(PacketByteBuf buf) {
        return new UserInterfaceS2CPacket(UserInterfaceData.DATA_TYPE.receive(buf));
    }

    @Override
    public void write(PacketByteBuf buf) {
        UserInterfaceData.DATA_TYPE.send(buf, InterfaceData);
    }

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

}
