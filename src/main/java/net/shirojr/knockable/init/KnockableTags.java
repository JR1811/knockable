package net.shirojr.knockable.init;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.shirojr.knockable.Knockable;

import java.util.ArrayList;
import java.util.List;

public class KnockableTags {
    public static final List<TagKey<?>> ALL_TAGS = new ArrayList<>();

    public static void initialize() {
        // static initialisation
        Blocks.initialize();
    }

    public interface Blocks {
        List<TagKey<Block>> ALL_BLOCK_TAGS = new ArrayList<>();

        TagKey<Block> KNOCKABLE_BLOCKS = createTag("knockable");

        @SuppressWarnings("SameParameterValue")
        private static TagKey<Block> createTag(String name) {
            TagKey<Block> tagKey = TagKey.of(RegistryKeys.BLOCK, Knockable.getId(name));
            ALL_BLOCK_TAGS.add(tagKey);
            ALL_TAGS.add(tagKey);
            return tagKey;
        }

        static void initialize() {
            // static initialisation
        }
    }
}
