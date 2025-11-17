package net.shirojr.knockable.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.shirojr.knockable.network.packet.KnockingRaycastPacket;

public class KnockableC2SNetworking {
    static {
        ServerPlayNetworking.registerGlobalReceiver(KnockingRaycastPacket.IDENTIFIER, KnockingRaycastPacket::handlePacket);
    }


    public static void initialize() {
        // static initialisation
    }
}
