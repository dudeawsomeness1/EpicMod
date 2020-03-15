package com.Invilis.dimensions.Elven;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.OverworldChunkGenerator;
import net.minecraft.world.gen.OverworldGenSettings;

public class ElvenChunkProvider extends OverworldChunkGenerator {
	private static final int SEALEVEL = 63;

	public ElvenChunkProvider(IWorld world, BiomeProvider provider) {
		super(world, provider, new OverworldGenSettings() {
			public BlockState getDefaultBlock() {
				return Blocks.STONE.getDefaultState();
			}

			public BlockState getDefaultFluid() {
				return Blocks.WATER.getDefaultState();
			}
		});
		this.randomSeed.skip(5349);
	}

	@Override
	public int getSeaLevel() {
		return SEALEVEL;
	}
}
