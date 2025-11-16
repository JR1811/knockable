package net.shirojr.knockable.init;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.shirojr.knockable.events.KnockableKeybinds;
import net.shirojr.knockable.events.KnockableServerEvents;

public interface KnockableEvents {
    static void registerCommon() {
        ServerPlayConnectionEvents.JOIN.register(new KnockableServerEvents());
    }

    static void registerClient() {
        ClientTickEvents.END_CLIENT_TICK.register(new KnockableKeybinds());
    }
}
