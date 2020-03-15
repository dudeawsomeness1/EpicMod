package com.Invilis.items;

import com.Invilis.events.MithrilArmorHelmetTickEvent;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class MithrilArmorHelmet extends ArmorItem {
	public MithrilArmorHelmet(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}

	@Override
	public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
		super.onArmorTick(itemstack, world, entity);
		{
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("entity", entity);
			MithrilArmorHelmetTickEvent.executeProcedure($_dependencies);
		}
	}
}
