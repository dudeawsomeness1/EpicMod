package com.Invilis.blocks;

import com.Invilis.events.MallornLogOnBlockRightClicked;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class MallornLog extends LogBlock {
	//public static final DirectionProperty FACING = DirectionalBlock.FACING;
	
	MallornLog() {
		super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 10f).harvestLevel(0).harvestTool(ToolType.AXE));
		//this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.SOUTH));
	}
	
	/*@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING);
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		if (rot == Rotation.CLOCKWISE_90 || rot == Rotation.COUNTERCLOCKWISE_90) {
			if ((Direction) state.get(FACING) == Direction.WEST || (Direction) state.get(FACING) == Direction.EAST) {
				return state.with(FACING, Direction.UP);
			} else if ((Direction) state.get(FACING) == Direction.UP || (Direction) state.get(FACING) == Direction.DOWN) {
				return state.with(FACING, Direction.WEST);
			}
		}
		return state;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		Direction facing = context.getFace();
		if (facing == Direction.WEST || facing == Direction.EAST)
			facing = Direction.UP;
		else if (facing == Direction.NORTH || facing == Direction.SOUTH)
			facing = Direction.EAST;
		else
			facing = Direction.SOUTH;
		return this.getDefaultState().with(FACING, facing);
	}*/
	
	@Override
	public boolean onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		{
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("entity", player);
			$_dependencies.put("x", x);
			$_dependencies.put("y", y);
			$_dependencies.put("z", z);
			$_dependencies.put("world", world);
			MallornLogOnBlockRightClicked.executeProcedure($_dependencies);
		}
		return false;
	}
}
