package com.Invilis.features;

import java.util.Random;
import java.util.Set;

import com.Invilis.blocks.EpicBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MallornTree extends AbstractTreeFeature<NoFeatureConfig> {
	private final boolean IS_AUTUMNAL;
	
	public MallornTree(boolean _IS_AUTUMNAL) {
		super(NoFeatureConfig::deserialize, false);
		IS_AUTUMNAL = _IS_AUTUMNAL;
	}

	@Override
	protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader worldIn, Random rand, BlockPos position, MutableBoundingBox bbox) {
		if (!(worldIn instanceof IWorld))
			return false;
		IWorld world = (IWorld) worldIn;
		int height = rand.nextInt(5) + 18; // height: 18-22
		boolean spawnTree = true;
		if (position.getY() >= 1 && position.getY() + height + 1 <= world.getHeight()) {
			for (int j = position.getY(); j <= position.getY() + 1 + height; j++) {
				int k = 1;
				if (j == position.getY())
					k = 0;
				if (j >= position.getY() + height - 1)
					k = 2;
				for (int px = position.getX() - k; px <= position.getX() + k && spawnTree; px++) {
					for (int pz = position.getZ() - k; pz <= position.getZ() + k && spawnTree; pz++) {
						if (j >= 0 && j < world.getHeight()) {
							if (!this.isReplaceable(world, new BlockPos(px, j, pz))) {
								spawnTree = false;
							}
						} else {
							spawnTree = false;
						}
					}
				}
			}
			if (!spawnTree) {
				return false;
			} else {
				Block ground = world.getBlockState(position.add(0, -1, 0)).getBlock();
				Block ground2 = world.getBlockState(position.add(0, -2, 0)).getBlock();
				if (!((ground == Blocks.GRASS_BLOCK.getDefaultState().getBlock() || ground == Blocks.DIRT.getDefaultState().getBlock()) && (ground2 == Blocks.GRASS_BLOCK
						.getDefaultState().getBlock() || ground2 == Blocks.DIRT.getDefaultState().getBlock())))
					return false;
				BlockState state = world.getBlockState(position.down());
				if (position.getY() < world.getHeight() - height - 1) {
					setTreeBlockState(changedBlocks, world, position.down(), Blocks.DIRT.getDefaultState(), bbox);
					for (int genh = position.getY() - 3 + height; genh <= position.getY() + height; genh++) {
						int i4 = genh - (position.getY() + height);
						int j1 = (int) (1 - i4 * 0.5);
						for (int k1 = position.getX() - j1; k1 <= position.getX() + j1; ++k1) {
							for (int i2 = position.getZ() - j1; i2 <= position.getZ() + j1; ++i2) {
								int j2 = i2 - position.getZ();
								if (Math.abs(position.getX()) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0 && i4 != 0) {
									BlockPos blockpos = new BlockPos(k1, genh, i2);
									state = world.getBlockState(blockpos);
									if (state.getBlock().isAir(state, world, blockpos) || state.getMaterial().blocksMovement()
											|| state.isIn(BlockTags.LEAVES) || state.getBlock() == Blocks.AIR.getDefaultState().getBlock()
											|| state.getBlock() == (IS_AUTUMNAL ? EpicBlocks.mallornLeavesAutumnal : EpicBlocks.mallornLeaves).getDefaultState().getBlock()) {
										setTreeBlockState(changedBlocks, world, blockpos, (IS_AUTUMNAL ? EpicBlocks.mallornLeavesAutumnal : EpicBlocks.mallornLeaves).getDefaultState(), bbox);
									}
								}
							}
						}
					}
					for (int genh = 0; genh < height; genh++) {
						BlockPos genhPos = position.up(genh);
						state = world.getBlockState(genhPos);
						setTreeBlockState(changedBlocks, world, genhPos, EpicBlocks.mallornLog.getDefaultState(), bbox);
						if (state.getBlock().isAir(state, world, genhPos) || state.getMaterial().blocksMovement() || state.isIn(BlockTags.LEAVES)
								|| state.getBlock() == Blocks.AIR.getDefaultState().getBlock()
								|| state.getBlock() == (IS_AUTUMNAL ? EpicBlocks.mallornLeavesAutumnal : EpicBlocks.mallornLeaves).getDefaultState().getBlock()) {
						}
					}
					if (rand.nextInt(4) == 0 && height > 5) {
						for (int hlevel = 0; hlevel < 2; hlevel++) {
							for (Direction Direction : Direction.Plane.HORIZONTAL) {
								if (rand.nextInt(4 - hlevel) == 0) {
									Direction dir = Direction.getOpposite();
									setTreeBlockState(changedBlocks, world,
											position.add(dir.getXOffset(), height - 5 + hlevel, dir.getZOffset()), Blocks.AIR.getDefaultState(),
											bbox);
								}
							}
						}
					}
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	}
	
	private boolean canGrowInto(Block blockType) {
		return blockType.getDefaultState().getMaterial() == Material.AIR || blockType == EpicBlocks.mallornLog.getDefaultState().getBlock()
				|| blockType == EpicBlocks.mallornLeaves.getDefaultState().getBlock() || blockType == EpicBlocks.mallornLeavesAutumnal.getDefaultState().getBlock()
				|| blockType == Blocks.GRASS_BLOCK.getDefaultState().getBlock() || blockType == Blocks.DIRT.getDefaultState().getBlock();
	}

	private boolean isReplaceable(IWorld world, BlockPos pos) {
		BlockState state = world.getBlockState(pos);
		return state.getBlock().isAir(state, world, pos) || canGrowInto(state.getBlock()) || !state.getMaterial().blocksMovement();
	}

	private void setTreeBlockState(Set<BlockPos> changedBlocks, IWorldWriter world, BlockPos pos, BlockState state, MutableBoundingBox mbb) {
		super.setLogState(changedBlocks, world, pos, state, mbb);
		changedBlocks.add(pos.toImmutable());
	}
}
