package com.Invilis.entities;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.entities.BarrowWight.BarrowWight;
import com.Invilis.entities.BarrowWight.BarrowWightRenderer;
import com.Invilis.entities.Elf.Elf;
import com.Invilis.entities.Elf.ElfRenderer;
import com.Invilis.entities.Oyster.Oyster;
import com.Invilis.entities.Oyster.OysterRenderer;
import com.Invilis.entities.Shark.Shark;
import com.Invilis.entities.Shark.SharkRenderer;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicEntities {
	// Hostile
	public final static EntityType<BarrowWight> BARROW_WIGHT = (EntityType.Builder.<BarrowWight>create(BarrowWight::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(BarrowWight::new).size(0.6f, 1.95f)).build("barrow_wight");
	
	// Neutral
	public final static EntityType<Shark> SHARK = (EntityType.Builder.<Shark>create(Shark::new, EntityClassification.WATER_CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(70).setUpdateInterval(1).setCustomClientFactory(Shark::new).size(0.6f, 1.95f)).build("shark");
	public final static EntityType<Oyster> OYSTER = (EntityType.Builder.<Oyster>create(Oyster::new, EntityClassification.WATER_CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(Oyster::new).size(0.6f, 1.95f)).build("oyster");
	
	// Friendly
	public final static EntityType<Elf> ELF = (EntityType.Builder.<Elf>create(Elf::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(Elf::new).size(0.6f, 1.95f)).build("elf");
	
	@SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		BARROW_WIGHT.setRegistryName(EpicMod.MOD_ID, "barrow_wight");
		SHARK.setRegistryName(EpicMod.MOD_ID, "shark");
		OYSTER.setRegistryName(EpicMod.MOD_ID, "oyster");
		ELF.setRegistryName(EpicMod.MOD_ID, "elf");
		
		event.getRegistry().registerAll(
			BARROW_WIGHT,
			SHARK,
			OYSTER,
			ELF
		);
		
		EntitySpawnPlacementRegistry.register(BARROW_WIGHT, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
		EntitySpawnPlacementRegistry.register(SHARK, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR,
				WaterMobEntity::func_223315_a);
		EntitySpawnPlacementRegistry.register(OYSTER, EntitySpawnPlacementRegistry.PlacementType.IN_WATER, Heightmap.Type.OCEAN_FLOOR,
				WaterMobEntity::func_223315_a);
	}
	
	@OnlyIn(Dist.CLIENT)
    public static void registerRenderingHandlers() {
        RenderingRegistry.registerEntityRenderingHandler(BarrowWight.class, BarrowWightRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Elf.class, ElfRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Shark.class, SharkRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(Oyster.class, OysterRenderer::new);
    }
}
