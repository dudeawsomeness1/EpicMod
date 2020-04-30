package com.Invilis.blocks;

import com.Invilis.events.KernalBlockOnBlockRightClickedProcedure;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class CornKernelBlock extends FallingBlock {
	public CornKernelBlock() {
		super(Block.Properties.create(Material.MISCELLANEOUS).sound(SoundType.SAND).hardnessAndResistance(0.8f, 1.96875f).harvestLevel(0).harvestTool(ToolType.SHOVEL));
	}
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity entity, Hand hand, BlockRayTraceResult hit) {
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
			return KernalBlockOnBlockRightClickedProcedure.executeProcedure($_dependencies);
		}
	}
}
