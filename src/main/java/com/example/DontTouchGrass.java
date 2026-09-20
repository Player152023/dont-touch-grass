package com.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DontTouchGrass implements ModInitializer {

	public static final String MOD_ID = "grass";

	public static final Logger LOGGER =
			LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Don't Touch Grass loaded!");

		ServerTickEvents.START_SERVER_TICK.register(server -> {

			for (ServerPlayer player : server.getPlayerList().getPlayers()) {

				BlockPos posBelow = player.blockPosition().below();

				if (player.level().getBlockState(posBelow).is(Blocks.GRASS_BLOCK)
						|| player.level().getBlockState(posBelow).is(Blocks.MYCELIUM)
						|| player.level().getBlockState(posBelow).is(Blocks.PODZOL)) {
					player.kill(player.level());
				}
			}
		});
	}
}
