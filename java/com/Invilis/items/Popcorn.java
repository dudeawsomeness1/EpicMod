package com.Invilis.items;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.events.PopcornFoodEaten;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Popcorn extends Item {
	public Popcorn() {
		super(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).food(EpicFoods.popcorn).maxStackSize(16));
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 30;
	}
	
	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
		{
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("entity", entity);
			PopcornFoodEaten.executeProcedure($_dependencies);
		}
		return super.onItemUseFinish(itemStack, world, entity);
	}
}
