package com.Invilis.events;

import net.minecraft.entity.Entity;

public class BalrogSwordMobIsHitWithTool {
	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BalrogSwordMobIsHitWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setFire((int) 4);
	}
}
