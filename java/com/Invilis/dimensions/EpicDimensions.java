package com.Invilis.dimensions;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.dimensions.Elven.ElvenDimension;
import com.Invilis.dimensions.Elven.ElvenDimension.CustomModDimension;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = EpicMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class EpicDimensions {
	@ObjectHolder(EpicMod.MOD_ID + ":elven_realm")
	public static final CustomModDimension ELVEN_REALM = null;
	
	EpicDimensions () {
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}
	
	@SubscribeEvent
	public static void registerDimension(RegistryEvent.Register<ModDimension> event) {
		event.getRegistry().registerAll(
			new ElvenDimension.CustomModDimension().setRegistryName(EpicMod.MOD_ID, "elven_realm")
		);
	}
}
