package com.Invilis.items;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.events.MallornNutRightClickedOnBlock;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MallornNut extends Item {

	public MallornNut() {
		super(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP));
	}
	
	@Override
	public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
		ActionResultType retval = super.onItemUse(context);
		World world = context.getWorld();
		BlockPos pos = context.getPos();
		PlayerEntity entity = context.getPlayer();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		{
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			MallornNutRightClickedOnBlock.executeProcedure($_dependencies);
		}
		return retval;
	}
}
