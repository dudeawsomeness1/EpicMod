package com.Invilis.blocks;

import java.util.Random;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class BlessedCorn extends SugarCaneBlock {
	public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
	
	protected BlessedCorn() {
		super(Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.PLANT).hardnessAndResistance(0f, 0f).lightValue(0));
		this.setDefaultState(this.getDefaultState().with(STAGE, Integer.valueOf(0)));
	}
	
	public static void init(FMLCommonSetupEvent event) {
		Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::deserialize) { // error
			@Override
			public boolean place(IWorld world, ChunkGenerator<?> generator, Random random, BlockPos pos, NoFeatureConfig config) {
				DimensionType dimensionType = world.getDimension().getType();
				boolean dimensionCriteria = false;
				if (dimensionType == DimensionType.OVERWORLD)
					dimensionCriteria = true;
				if (!dimensionCriteria)
					return false;
				
				if (random.nextFloat() > 0.95f) // 95% chance to try place
					return false;
				
				int generated = 0;
				for (int j = 0; j < 1; ++j) {
					BlockPos blockpos = pos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
					if (world.isAirBlock(blockpos)) {
						//BlockPos blockpos1 = blockpos.down();
						int k = 1 + random.nextInt(random.nextInt(3) + 1);
						k = Math.min(3, k);
						for (int l = 0; l < k; ++l) {
							if (EpicBlocks.blessedCorn.getDefaultState().isValidPosition(world, blockpos)) { // error
								world.setBlockState(blockpos.up(l), EpicBlocks.blessedCorn.getDefaultState(), 2);
								generated++;
							}
						}
					}
				}
				return generated > 0;
			}
		};
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("plains")))
				biomeCriteria = true;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("sunflower_plains")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
					Biome.createDecoratedFeature(feature, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
		}
	}
	
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return true;
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.Plains;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
		builder.add(STAGE);
	}

	@Override
	public void tick(BlockState state, World world, BlockPos pos, Random random) {
		if (!state.isValidPosition(world, pos)) {
			world.destroyBlock(pos, true);
		}
		else if (world.isAirBlock(pos.up())) {
			int i = 1;
			for (; world.getBlockState(pos.down(i)).getBlock() == this; ++i);
			int stage = state.get(STAGE);
			int newStage = stage;
			int age = state.get(AGE);
			if (i < 3) {
				if (age == 15) {
					world.setBlockState(pos.up(), getDefaultState());
					//world.setBlockState(pos, state.with(AGE, 0), 4);
				} else {
					if ((age == 3 && stage == 0) || (age == 7 && stage == 1) || (age == 10 && stage == 2)) newStage++;
					world.setBlockState(pos, state.with(AGE, age + 1).with(STAGE, newStage));
				}
			}
			else if (age < 15) {
				if ((age == 3 && stage == 0) || (age == 7 && stage == 1) || (age == 10 && stage == 2)) newStage++;
				world.setBlockState(pos, state.with(AGE, age + 1).with(STAGE, newStage));
			}
		}
	}
}
