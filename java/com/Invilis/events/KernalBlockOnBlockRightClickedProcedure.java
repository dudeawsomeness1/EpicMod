package com.Invilis.events;

import java.util.HashMap;

import com.Invilis.items.EpicItems;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemHandlerHelper;

public class KernalBlockOnBlockRightClickedProcedure {
	public static boolean executeProcedure(HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency, entity, for procedure KernalBlockOnBlockRightClicked!");
			return false;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency, x, for procedure KernalBlockOnBlockRightClicked!");
			return false;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency, y, for procedure KernalBlockOnBlockRightClicked!");
			return false;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency, z, for procedure KernalBlockOnBlockRightClicked!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency, world, for procedure KernalBlockOnBlockRightClicked!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.BUCKET, (int) (1)).getItem())) {
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(Items.BUCKET, (int) (1)).getItem() == p.getItem(), (int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), new ItemStack(EpicItems.bucketOfCornKernels, (int) (1)));
				//((PlayerEntity) entity).replaceItemInInventory(EquipmentSlotType.MAINHAND.getSlotIndex(), new ItemStack(EpicItems.bucketOfCornKernels, (int) (1)));
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			return true;
		}
		
		return false;
	}
}
