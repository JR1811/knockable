package net.shirojr.knockable.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.shirojr.knockable.events.KnockableKeybinds;

public class KnockableS2CNetworking {
    static {
        ClientPlayNetworking.registerGlobalReceiver(NetworkIdentifiers.KNOCKING_RANGE, KnockableS2CNetworking::handleKnockingRangeChange);
    }

    private static void handleKnockingRangeChange(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender sender) {
        int range = buf.readVarInt();

        client.execute(() -> KnockableKeybinds.setRange(range));
    }

    public static void initialize() {
        // static initialisation
    }
}
