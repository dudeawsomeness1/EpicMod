package com.Invilis.biomes;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicBiomes {
	public static Biome valinorianForest = null;
	
	@SubscribeEvent
    public static void registerBiomes(RegistryEvent.Register<Biome> event) {
		event.getRegistry().registerAll(
			valinorianForest = new ValinorianForest().setRegistryName(EpicMod.MOD_ID, "valinorian_forest")
		);
	}
}
