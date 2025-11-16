package net.shirojr.knockable.network;

import net.minecraft.util.Identifier;
import net.shirojr.knockable.Knockable;

public interface NetworkIdentifiers {
    Identifier KNOCKING_RAYCAST = Knockable.getId("knocking_raycast");
    Identifier KNOCKING_RANGE = Knockable.getId("knocking_range");
}
