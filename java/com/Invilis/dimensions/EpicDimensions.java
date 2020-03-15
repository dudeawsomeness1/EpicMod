package com.Invilis.dimensions;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.dimensions.Elven.ElvenDimension;
import com.Invilis.dimensions.Elven.ElvenDimension.CustomModDimension;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicDimensions {
	@ObjectHolder(EpicMod.MOD_ID + "elven_realm")
	public static final CustomModDimension elvenRealm = null;
	
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
