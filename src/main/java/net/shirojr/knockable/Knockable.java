package net.shirojr.knockable;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.shirojr.knockable.init.KnockableEvents;
import net.shirojr.knockable.init.KnockableGamerules;
import net.shirojr.knockable.init.KnockableSounds;
import net.shirojr.knockable.init.KnockableTags;
import net.shirojr.knockable.network.KnockableC2SNetworking;
import net.shirojr.knockable.network.KnockablePayloads;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Knockable implements ModInitializer {
	public static final String MOD_ID = "knockable";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		KnockableEvents.registerCommon();
		KnockablePayloads.initialize();
		KnockableC2SNetworking.initialize();
		KnockableGamerules.initialize();
		KnockableTags.initialize();
		KnockableSounds.initialize();

		LOGGER.info("Knock knock?");
	}

	public static Identifier getId(String path) {
		return Identifier.of(MOD_ID, path);
	}
}