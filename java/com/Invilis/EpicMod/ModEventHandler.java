package com.Invilis.EpicMod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventHandler {
	/*@SubscribeEvent
	public static void onInterModEnqueue(InterModEnqueueEvent event) {
		InterModComms.sendTo("curios", String, Supplier)
	}*/
}
