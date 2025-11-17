package net.shirojr.knockable.events;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.shirojr.knockable.init.KnockableGamerules;
import net.shirojr.knockable.network.packet.KnockingRangePacket;

import java.util.List;

public class KnockableServerEvents implements ServerPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        new KnockingRangePacket(server.getGameRules().get(KnockableGamerules.KNOCKING_RANGE).get()).sendPacket(List.of(handler.player));
    }
}
