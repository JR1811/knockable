package net.shirojr.knockable.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.shirojr.knockable.network.packet.KnockingRangePacket;

public class KnockableS2CNetworking {
    static {
        ClientPlayNetworking.registerGlobalReceiver(KnockingRangePacket.IDENTIFIER, KnockingRangePacket::handlePacket);
    }

    public static void initialize() {
        // static initialisation
    }
}
