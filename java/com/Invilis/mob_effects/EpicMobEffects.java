package com.Invilis.mob_effects;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.potion.Effect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicMobEffects {
	public static Effect black_breath = null;
	
	@SubscribeEvent
    public static void registerEffects(RegistryEvent.Register<Effect> event) {
		event.getRegistry().registerAll(
			black_breath = new BlackBreath().setRegistryName(EpicMod.MOD_ID, "black_breath")
		);
	}
}
