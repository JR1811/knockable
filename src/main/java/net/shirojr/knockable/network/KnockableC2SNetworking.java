package net.shirojr.knockable.network;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.shirojr.knockable.init.KnockableGamerules;
import net.shirojr.knockable.init.KnockableSounds;
import net.shirojr.knockable.init.KnockableTags;

public class KnockableC2SNetworking {
    static {
        ServerPlayNetworking.registerGlobalReceiver(NetworkIdentifiers.KNOCKING_RAYCAST, KnockableC2SNetworking::handleKnockingRaycast);
    }

    private static void handleKnockingRaycast(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        BlockPos hitPos = buf.readBlockPos();

        server.execute(() -> {
            ServerWorld world = player.getServerWorld();
            BlockState hitState = world.getBlockState(hitPos);
            if (!hitState.isIn(KnockableTags.Blocks.KNOCKABLE_BLOCKS)) return;
            double maxRange = world.getGameRules().get(KnockableGamerules.KNOCKING_RANGE).get();
            double sqMaxRange = maxRange * maxRange;
            double sqDistanceToBlock = player.squaredDistanceTo(hitPos.toCenterPos());
            if (sqDistanceToBlock > sqMaxRange) return;

            float minPitch = 0.85f, maxPitch = 1.2f;
            float pitch;

            if (world.getBlockEntity(hitPos) instanceof Inventory inventory) {
                int occupiedSlots = 0;
                for (int i = 0; i < inventory.size(); i++) {
                    if (!inventory.getStack(i).isEmpty()) {
                        occupiedSlots++;
                    }
                }
                pitch = MathHelper.lerp(1f - ((float) occupiedSlots / inventory.size()), minPitch, maxPitch);
            } else {
                float normalizedDistance = (float) MathHelper.clamp(sqDistanceToBlock / sqMaxRange, 0, 1);
                // pitch = (float) MathHelper.lerp(Math.pow(normalizedDistance, 2), minPitch, maxPitch);
                pitch = MathHelper.lerp(normalizedDistance, minPitch, maxPitch);
            }

            world.playSound(null, hitPos, KnockableSounds.KNOCK, SoundCategory.BLOCKS, 2f, pitch);
        });
    }

    public static void initialize() {
        // static initialisation
    }
}
