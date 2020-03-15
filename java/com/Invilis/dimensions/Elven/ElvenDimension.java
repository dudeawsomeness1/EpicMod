package com.Invilis.dimensions.Elven;

import java.util.function.BiFunction;

import javax.annotation.Nullable;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.Dist;

public class ElvenDimension extends Dimension {
	static Biome[] dimensionBiomes;
	public static DimensionType type = null;
	
	public ElvenDimension(World worldIn, DimensionType typeIn) {
		super(worldIn, typeIn);
		this.nether = false;
		//type = DimensionType.byName(new ResourceLocation(EpicMod.MOD_ID, "elven_realm"));
	}
	
	/*public static DimensionType type()
	{
		return DimensionType.byName(new ResourceLocation(EpicMod.MOD_ID + ":valinorian_forest"));
	}*/
	
	public static class CustomModDimension extends ModDimension {
		@Override
		public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
			return ElvenDimension::new;
		}
	}
	
	public static void init(FMLCommonSetupEvent event) {
		dimensionBiomes = new Biome[]{ForgeRegistries.BIOMES.getValue(new ResourceLocation(EpicMod.MOD_ID + ":valinorian_forest")),
				ForgeRegistries.BIOMES.getValue(new ResourceLocation("ocean")), ForgeRegistries.BIOMES.getValue(new ResourceLocation("plains")),
				ForgeRegistries.BIOMES.getValue(new ResourceLocation("mountains")), ForgeRegistries.BIOMES.getValue(new ResourceLocation("forest")),
				ForgeRegistries.BIOMES.getValue(new ResourceLocation("river")),};
	}

	@Override
	public ChunkGenerator<?> createChunkGenerator() {
		return new ElvenChunkProvider(this.world, new ElvenBiomeProvider(this.world));
	}

	@Override
	@Nullable
	public BlockPos findSpawn(ChunkPos chunkPosIn, boolean checkValid) {
		return null;
	}

	@Override
	@Nullable
	public BlockPos findSpawn(int posX, int posZ, boolean checkValid) {
		return null;
	}
	
	@Override
	protected void generateLightBrightnessTable() {
		float f = 0.5f;
		for (int i = 0; i <= 15; ++i) {
			float f1 = 1 - (float) i / 15f;
			this.lightBrightnessTable[i] = (1 - f1) / (f1 * 3 + 1) * (1 - f) + f;
		}
	}
	
	@Override
	public boolean doesWaterVaporize() {
		return false;
	}

	@Override
	public boolean isSurfaceWorld() {
		return true;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public Vec3d getFogColor(float celestialAngle, float partialTicks) {
		return new Vec3d(0.752941176471, 0.847058823529, 1.0);
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public SleepResult canSleepAt(PlayerEntity player, BlockPos pos) {
		return SleepResult.ALLOW;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean doesXZShowFog(int x, int z) {
		return false;
	}
	
	@Override
	/** 
	 * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
	 */
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		double d0 = MathHelper.frac((double) worldTime / 24000.0D - 0.25D);
		double d1 = 0.5D - Math.cos(d0 * Math.PI) / 2.0D;
		return (float) (d0 * 2.0D + d1) / 3.0F;
	}
}
