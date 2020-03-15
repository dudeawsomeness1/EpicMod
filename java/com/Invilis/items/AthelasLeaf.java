package com.Invilis.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class AthelasLeaf extends Item {

	public AthelasLeaf(Properties properties) {
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
		super.addInformation(itemstack, world, list, flag);
		list.add(new StringTextComponent("Kingsfoil"));
		list.add(new StringTextComponent("a sweet-smelling herb with healing powers"));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(final World worldIn, final PlayerEntity player, final Hand hand) {
		ItemStack heldItem = player.getHeldItem(hand);
		if(player.isPotionActive(Effects.POISON)) {
			player.removePotionEffect(Effects.POISON);
			player.inventory.clearMatchingItems(p -> new ItemStack(EpicItems.athelasLeaf, (int) (1)).getItem() == p.getItem(), (int) 1);
		}
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, (ItemStack)heldItem);
	}
}
