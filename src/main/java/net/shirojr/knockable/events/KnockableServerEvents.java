package net.shirojr.knockable.events;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.shirojr.knockable.init.KnockableGamerules;
import net.shirojr.knockable.network.NetworkIdentifiers;

public class KnockableServerEvents implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeVarInt(server.getGameRules().getInt(KnockableGamerules.KNOCKING_RANGE));
        ServerPlayNetworking.send(handler.player, NetworkIdentifiers.KNOCKING_RANGE, buf);
    }
}
