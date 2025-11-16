package net.shirojr.knockable.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v1.ConventionalBlockTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.shirojr.knockable.init.KnockableTags;

import java.util.concurrent.CompletableFuture;

public class KnockableTagGenerators {
    public static class BlockTagProvider extends FabricTagProvider.BlockTagProvider {
        public BlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
            super(output, registriesFuture);
        }

        @Override
        protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
            getOrCreateTagBuilder(KnockableTags.Blocks.KNOCKABLE_BLOCKS)
                    .addOptionalTag(BlockTags.DOORS)
                    .addOptionalTag(BlockTags.TRAPDOORS)
                    .addOptionalTag(ConventionalBlockTags.CHESTS)
                    .addOptionalTag(ConventionalBlockTags.WOODEN_BARRELS)
                    .addOptionalTag(BlockTags.SHULKER_BOXES);
        }
    }

    public static void registerAll(FabricDataGenerator.Pack generator) {
        generator.addProvider(BlockTagProvider::new);
    }
}
