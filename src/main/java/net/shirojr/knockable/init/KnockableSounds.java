package net.shirojr.knockable.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.shirojr.knockable.Knockable;

public interface KnockableSounds {
    SoundEvent KNOCK = register("knock");

    @SuppressWarnings("SameParameterValue")
    private static SoundEvent register(String name) {
        Identifier identifier = Knockable.getId(name);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    static void initialize() {
        // static initialisation
    }
}
