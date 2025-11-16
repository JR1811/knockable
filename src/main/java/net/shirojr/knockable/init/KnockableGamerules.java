package net.shirojr.knockable.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import net.shirojr.knockable.network.NetworkIdentifiers;

public interface KnockableGamerules {
    GameRules.Key<GameRules.IntRule> KNOCKING_RANGE = GameRuleRegistry.register("knockingRange",
            GameRules.Category.PLAYER, GameRuleFactory.createIntRule(5, 0, (server, intRule) -> {
                for (ServerPlayerEntity target : PlayerLookup.all(server)) {
                    PacketByteBuf buf = PacketByteBufs.create();
                    buf.writeVarInt(intRule.get());
                    ServerPlayNetworking.send(target, NetworkIdentifiers.KNOCKING_RANGE, buf);
                }
            })
    );

    static void initialize() {
        // static initialisation
    }
}
