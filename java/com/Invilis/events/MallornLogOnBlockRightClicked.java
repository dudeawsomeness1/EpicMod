package com.Invilis.events;

import java.util.Random;

import com.Invilis.blocks.EpicBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;

public class MallornLogOnBlockRightClicked {
	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MallornLogOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MallornLogOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MallornLogOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MallornLogOnBlockRightClicked!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MallornLogOnBlockRightClicked!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		int x = (int) dependencies.get("x");
		int y = (int) dependencies.get("y");
		int z = (int) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() instanceof AxeItem)) {
			BlockState _bs = world.getBlockState(new BlockPos(x, y, z));
			
			boolean isLog = EpicBlocks.mallornLog.getRegistryName() == _bs.getBlock().getRegistryName();
			
			if (isLog || EpicBlocks.mallornWood.getRegistryName() == _bs.getBlock().getRegistryName()) {
				Comparable d;
				// get direction of mallorn log/wood
				try {
					d = _bs.get(_bs.getBlock().getStateContainer().getProperty("axis"));
				} catch (Exception e) {
					d = (Comparable)Direction.Axis.Y;
				}
				
				if (isLog)
					world.setBlockState(new BlockPos(x, y, z), EpicBlocks.strippedMallornLog.getDefaultState(), 3);
				else
					world.setBlockState(new BlockPos(x, y, z), EpicBlocks.strippedMallornWood.getDefaultState(), 3);
				_bs = world.getBlockState(new BlockPos(x, y, z));
				// set direction of stripped mallorn log/wood
				try {
					world.setBlockState(new BlockPos(x, y, z),
							_bs.with((IProperty) _bs.getBlock().getStateContainer().getProperty("axis"), d), 3);
				} catch (Exception e) {
				}
				
				world.playSound((PlayerEntity) null, x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.axe.strip")),
						SoundCategory.NEUTRAL, 1.0f, 1.0f);
				// damage tool by 1
				if (((LivingEntity) entity).getHeldItemMainhand().attemptDamageItem(1, new Random(), null)) {
					((LivingEntity) entity).getHeldItemMainhand().shrink(1);
					((LivingEntity) entity).getHeldItemMainhand().setDamage(0);
				}
			}
		}
	}
}
