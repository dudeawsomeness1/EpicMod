package com.Invilis.events;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

public class BlackBreathOnPotionActiveTick {
	public static void executeProcedure(java.util.HashMap<String, Object> dependencies, int amplifier) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BlackBreathOnPotionActiveTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.getPersistentData().putInt("black_breath_counter", ((entity.getPersistentData().getInt("black_breath_counter")) + 1));
		if ((((entity.getPersistentData().getInt("black_breath_counter")) % (39 / ((2 * amplifier == 0) ? 1 : 2 * amplifier))) == 0)) {
			entity.attackEntityFrom(DamageSource.MAGIC, 1f); // 1 damage (or half a heart) every ( 39/(2*amplifier) ) ticks
			/// an amplifier of 0 will be every 39 ticks, 1 will be every 19 ticks, etc.
			/// this is 1 tick faster than wither
		}
	}
}
