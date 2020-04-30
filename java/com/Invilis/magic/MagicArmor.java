package com.Invilis.magic;

import java.util.List;
import java.util.Random;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class MagicArmor extends ArmorItem {
	protected boolean hasRadiant = false;
	protected boolean hasGarnet = false;
	
	protected int numSockets = 0;
	
	public MagicArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
		
		numSockets = (new Random()).nextInt(2) + 1;
	}
	
	@Override
	public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(itemstack, world, list, flag);
		list.add(new StringTextComponent("Sockets: " + numSockets));
	}
}
