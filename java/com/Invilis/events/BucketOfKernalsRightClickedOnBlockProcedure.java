package com.Invilis.events;

import java.util.HashMap;
import java.util.Random;

import com.Invilis.blocks.EpicBlocks;
import com.Invilis.items.EpicItems;
import com.Invilis.sounds.EpicSounds;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class BucketOfKernalsRightClickedOnBlockProcedure {
	public static void executeProcedure(HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency, entity, for procedure BucketOfKernalsRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency, x, for procedure BucketOfKernalsRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency, y, for procedure BucketOfKernalsRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency, z, for procedure BucketOfKernalsRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency, world, for procedure BucketOfKernalsRightClickedOnBlock!");
			return;
		}
		if (dependencies.get("direction") == null) {
			System.err.println("Failed to load dependency, direction, for procedure BucketOfKernalsRightClickedOnBlock!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		Direction direction = (Direction) dependencies.get("direction");
		
		// place corn kernel block
		BlockPos pos = new BlockPos((int) x, (int) y, (int) z);
		if (world.getBlockState(pos).isFoliage(world, pos))
			world.setBlockState(pos, EpicBlocks.cornKernelBlock.getDefaultState(), 3);
		else
			world.setBlockState(pos.add(direction.getDirectionVec()), EpicBlocks.cornKernelBlock.getDefaultState(), 3);
		
		// play sound
		world.playSound((PlayerEntity) null, x, y, z, EpicSounds.KERNELS_PLACED,
				SoundCategory.NEUTRAL, (float) 1, (float) 1);
		/*world.playSound((PlayerEntity) null, x, y, z,
				(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.sand.place")),
				SoundCategory.NEUTRAL, (float) 1, (float) 1);*/
		
		if (entity instanceof PlayerEntity) {
			// remove bucket of corn kernel item
			((PlayerEntity) entity).inventory.clearMatchingItems(p -> new ItemStack(EpicItems.bucketOfCornKernels, (int) (1)).getItem() == p.getItem(), (int) 1);
			// give bucket
			((PlayerEntity) entity).inventory.addItemStackToInventory(new ItemStack(Items.BUCKET, 1));
		}
	}
}
