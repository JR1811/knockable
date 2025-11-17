package net.shirojr.knockable.init;

import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.world.GameRules;
import net.shirojr.knockable.network.packet.KnockingRangePacket;

public interface KnockableGamerules {
    GameRules.Key<DoubleRule> KNOCKING_RANGE = GameRuleRegistry.register("knockingRange",
            GameRules.Category.PLAYER, GameRuleFactory.createDoubleRule(
                    5, 0, (server, doubleRule) ->
                            new KnockingRangePacket(doubleRule.get()).sendPacket(PlayerLookup.all(server))
            )
    );

    static void initialize() {
        // static initialisation
    }
}
