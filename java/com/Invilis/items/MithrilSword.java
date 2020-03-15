package com.Invilis.items;

import com.Invilis.events.MithrilSwordToolInHandTick;

import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.world.World;

public class MithrilSword extends SwordItem {
	public MithrilSword(final IItemTier t, final int damage, final float speed, final Item.Properties p){
		super(t, damage, speed, p);
	}
	
	@Override
	public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		//int x = (int) entity.posX;
		//int y = (int) entity.posY;
		//int z = (int) entity.posZ;
		if (selected) {
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("entity", entity);
			MithrilSwordToolInHandTick.executeProcedure($_dependencies);
		}
	}
}
