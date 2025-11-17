package net.shirojr.knockable.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.shirojr.knockable.network.packet.KnockingRangePacket;
import net.shirojr.knockable.network.packet.KnockingRaycastPacket;

@SuppressWarnings("SameParameterValue")
public class KnockablePayloads {
    static {
        registerC2S(KnockingRaycastPacket.IDENTIFIER, KnockingRaycastPacket.CODEC);
        registerS2C(KnockingRangePacket.IDENTIFIER, KnockingRangePacket.CODEC);
    }


    private static <T extends CustomPayload> void registerS2C(CustomPayload.Id<T> packetIdentifier, PacketCodec<RegistryByteBuf, T> codec) {
        PayloadTypeRegistry.playS2C().register(packetIdentifier, codec);
    }

    private static <T extends CustomPayload> void registerC2S(CustomPayload.Id<T> packetIdentifier, PacketCodec<RegistryByteBuf, T> codec) {
        PayloadTypeRegistry.playC2S().register(packetIdentifier, codec);
    }

    public static void initialize() {
        // static initialisation
    }
}
