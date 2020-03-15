package com.Invilis.EpicMod;

import com.Invilis.dimensions.EpicDimensions;
import com.Invilis.dimensions.Elven.ElvenDimension;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DimensionRegistryEventHandler {
	/// Dimension registered
	@SubscribeEvent
	public void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
		EpicMod.logger.debug("Yay!");
		//ElvenDimension.type = DimensionManager.registerDimension(new ResourceLocation(EpicMod.MOD_ID, "elven_realm"), EpicDimensions.elvenRealm, null, true);
		ElvenDimension.type = DimensionManager.registerOrGetDimension(new ResourceLocation(EpicMod.MOD_ID, "elven_realm"), EpicDimensions.elvenRealm, null, true);
	}
}
