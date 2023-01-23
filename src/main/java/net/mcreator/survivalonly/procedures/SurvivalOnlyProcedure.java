package net.mcreator.survivalonly.procedures;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;

import net.mcreator.survivalonly.SurvivalonlyMod;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

public class SurvivalOnlyProcedure {
	public SurvivalOnlyProcedure() {
		ServerTickEvents.END_WORLD_TICK.register((level) -> {
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("world", level);
			execute(dependencies);
		});
	}

	public static void execute(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				SurvivalonlyMod.LOGGER.warn("Failed to load dependency world for procedure SurvivalOnly!");
			return;
		}
		LevelAccessor world = (LevelAccessor) dependencies.get("world");
		{
			List<? extends Player> _players = new ArrayList<>(world.players());
			for (Entity entityiterator : _players) {
				if (entityiterator instanceof ServerPlayer _player)
					_player.setGameMode(GameType.SURVIVAL);
			}
		}
	}
}
