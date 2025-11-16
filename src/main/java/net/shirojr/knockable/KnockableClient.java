package net.shirojr.knockable;

import net.fabricmc.api.ClientModInitializer;
import net.shirojr.knockable.init.KnockableEvents;
import net.shirojr.knockable.network.KnockableS2CNetworking;

public class KnockableClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KnockableEvents.registerClient();
        KnockableS2CNetworking.initialize();
    }
}
