package com.Invilis.sounds;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicSounds {
	//public static ResourceLocation BARROW_WIGHT_AMBIENT = new ResourceLocation(EpicMod.MOD_ID, "barrow_wight_ambient");
	//public static ResourceLocation KERNELS_PLACED = new ResourceLocation(EpicMod.MOD_ID, "kernels_placed");
	public static final SoundEvent BARROW_WIGHT_AMBIENT = new SoundEvent(new ResourceLocation(EpicMod.MOD_ID, "barrow_wight_ambient"));
	public static final SoundEvent KERNELS_PLACED = new SoundEvent(new ResourceLocation(EpicMod.MOD_ID, "kernels_placed"));
	
	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
		event.getRegistry().register(BARROW_WIGHT_AMBIENT.setRegistryName(EpicMod.MOD_ID, "barrow_wight_ambient"));
		event.getRegistry().register(KERNELS_PLACED.setRegistryName(EpicMod.MOD_ID, "kernels_placed"));
	}
}
