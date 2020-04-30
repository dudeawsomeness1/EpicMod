package com.Invilis.events;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class PopcornFoodEaten {
	public static void executeProcedure(HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency, entity, for procedure PopcornFoodEaten!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1));
	}
}
