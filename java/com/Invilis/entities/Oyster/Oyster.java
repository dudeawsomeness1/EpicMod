package com.Invilis.entities.Oyster;

import com.Invilis.entities.EpicEntities;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ShulkerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

public class Oyster extends ShulkerEntity {
	public Oyster(FMLPlayMessages.SpawnEntity packet, World world) {
		super(EpicEntities.OYSTER, world);
	}
	
	public Oyster(EntityType<? extends ShulkerEntity> type, World world) {
		super(type, world);
		experienceValue = 6;
		setNoAI(false);
	}
	
	public static void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			ResourceLocation biome_RL = ForgeRegistries.BIOMES.getKey(biome);
			if (biome_RL.equals(Biomes.OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_LUKEWARM_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.COLD_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_COLD_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_WARM_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.WARM_OCEAN.getRegistryName()))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(EpicEntities.OYSTER, 7, 1, 1));
		}
	}
	
	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}
}
