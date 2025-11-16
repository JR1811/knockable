package net.shirojr.knockable.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.shirojr.knockable.Knockable;
import net.shirojr.knockable.events.KnockableKeybinds;
import net.shirojr.knockable.init.KnockableSounds;

import java.nio.file.Path;

public class KnockableTranslationGenerator extends FabricLanguageProvider {
    public KnockableTranslationGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder builder) {
        builder.add(KnockableKeybinds.KNOCKABLE_KEYBIND_GROUP, "Knockable");
        builder.add(KnockableKeybinds.KNOCK_KEY_BIND.getTranslationKey(), "Knock");
        builder.add("sound." + KnockableSounds.KNOCK.getId().toTranslationKey(), "Knocking");


        try {
            Path existingFilePath = dataOutput.getModContainer().findPath("assets/%s/lang/en_us.existing.json".formatted(Knockable.MOD_ID)).orElseThrow();
            builder.add(existingFilePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add existing language file!", e);
        }
    }
}
