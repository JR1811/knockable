package net.shirojr.knockable.events;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.shirojr.knockable.network.NetworkIdentifiers;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class KnockableKeybinds implements ClientTickEvents.EndTick {
    public static final String KNOCKABLE_KEYBIND_GROUP = "key.knockable.group";
    public static final KeyBinding KNOCK_KEY_BIND = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.nemuelch.entry.knocking",
                    InputUtil.Type.KEYSYM, InputUtil.GLFW_KEY_L, KNOCKABLE_KEYBIND_GROUP)
    );

    private static boolean wasKnocked = false;
    private static double range = 0;

    public static double getRange() {
        return range;
    }

    public static void setRange(double range) {
        KnockableKeybinds.range = Math.max(0, range);
    }

    @Override
    public void onEndTick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;

        handleRisingEdge(KNOCK_KEY_BIND, wasKnocked, aBoolean -> wasKnocked = aBoolean, () -> {
            HitResult hitResult = client.player.raycast(getRange(), client.getTickDelta(), false);
            if (!(hitResult instanceof BlockHitResult blockHitResult)) return;
            wasKnocked = true;
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeBlockPos(blockHitResult.getBlockPos());
            ClientPlayNetworking.send(NetworkIdentifiers.KNOCKING_RAYCAST, buf);
        });
    }

    @SuppressWarnings("SameParameterValue")
    private static void handleRisingEdge(KeyBinding key, boolean keyBuffer, Consumer<Boolean> keyBufferSetter, Runnable runnable) {
        if (!key.isPressed() && keyBuffer) {
            keyBufferSetter.accept(false);
        } else if (key.isPressed() && !keyBuffer) {
            runnable.run();
        }
    }
}
