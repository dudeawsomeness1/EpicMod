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
	public static ResourceLocation BARROW_WIGHT_AMBIENT = new ResourceLocation(EpicMod.MOD_ID, "sound/barrow_wight.ogg");
	
	@SubscribeEvent
	public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
		event.getRegistry().register(new SoundEvent(BARROW_WIGHT_AMBIENT).setRegistryName(EpicMod.MOD_ID, "barrow_wight_ambient"));
	}
}
