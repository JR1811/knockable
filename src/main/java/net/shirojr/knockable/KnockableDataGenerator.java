package net.shirojr.knockable;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.shirojr.knockable.datagen.KnockableTagGenerators;
import net.shirojr.knockable.datagen.KnockableTranslationGenerator;

public class KnockableDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(KnockableTranslationGenerator::new);

		KnockableTagGenerators.registerAll(pack);
	}
}
