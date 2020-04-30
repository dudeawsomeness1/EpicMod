package com.Invilis.biomes;

import com.Invilis.entities.EpicEntities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.DoublePlantConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class BarrowDowns extends Biome {
	protected BarrowDowns() {
		super(new Biome.Builder()
				.downfall(0.45f)
				.depth(0.15f)
				.scale(0.4f)
				.temperature(0.4f)
				.precipitation(Biome.RainType.RAIN)
				.category(Biome.Category.NONE)
				.waterColor(-14527804)
				.waterFogColor(-14527804)
				.surfaceBuilder(
						SurfaceBuilder.DEFAULT,
						new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.DIRT.getDefaultState())));
		
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addGrass(this);
		DefaultBiomeFeatures.addInfestedStone(this);
		DefaultBiomeFeatures.addTaigaRocks(this);
		addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DOUBLE_PLANT, new DoublePlantConfig(Blocks.TALL_GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_32, new FrequencyConfig(2)));
		
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 10, 1, 3));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 15, 1, 5));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 15, 3, 5));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 12, 1, 3));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 15, 1, 5));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.STRAY, 12, 3, 4));
		this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EpicEntities.BARROW_WIGHT, 11, 1, 7));
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public int getGrassColor(BlockPos pos) {
		return 0xff2d8a57;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public int getFoliageColor(BlockPos pos) {
		return 0xff2d8a57;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public int getSkyColorByTemp(float currentTemperature) {
		return 0xff6e8a85;
	}
	
	public static void init(FMLCommonSetupEvent event) {
		BiomeDictionary.addTypes(EpicBiomes.valinorianForest, BiomeDictionary.Type.FOREST);
		BiomeManager.addSpawnBiome(EpicBiomes.valinorianForest);
		BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(EpicBiomes.valinorianForest, 10));
	}
}
