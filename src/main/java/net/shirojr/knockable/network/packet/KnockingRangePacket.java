package net.shirojr.knockable.network.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.shirojr.knockable.Knockable;
import net.shirojr.knockable.events.KnockableKeybinds;

import java.util.Collection;

public record KnockingRangePacket(double range) implements CustomPayload {
    public static final CustomPayload.Id<KnockingRangePacket> IDENTIFIER =
            new CustomPayload.Id<>(Knockable.getId("knocking_range"));

    public static final PacketCodec<RegistryByteBuf, KnockingRangePacket> CODEC = PacketCodec.tuple(
            PacketCodecs.DOUBLE, KnockingRangePacket::range,
            KnockingRangePacket::new
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return IDENTIFIER;
    }

    public void sendPacket(Collection<ServerPlayerEntity> targets) {
        for (ServerPlayerEntity target : targets) {
            ServerPlayNetworking.send(target, this);
        }
    }


    @SuppressWarnings("unused")
    public void handlePacket(ClientPlayNetworking.Context context) {
        KnockableKeybinds.setRange(this.range);
    }
}
