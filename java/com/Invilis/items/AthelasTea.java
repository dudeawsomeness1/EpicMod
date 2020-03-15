package com.Invilis.items;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.events.AthelasTeaFoodEaten;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.world.World;

public class AthelasTea extends Item {

	public AthelasTea() {
		super(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).maxStackSize(1).food(EpicFoods.athelasTea));
	}
	
	@Override
	public int getUseDuration(ItemStack stack) {
		return 30;
	}

	@Override
	public UseAction getUseAction(ItemStack par1ItemStack) {
		return UseAction.DRINK;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
		ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
		//int x = (int) entity.posX;
		//int y = (int) entity.posY;
		//int z = (int) entity.posZ;
		{
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("entity", entity);
			AthelasTeaFoodEaten.executeProcedure($_dependencies);
		}
		return retval;
	}
}
