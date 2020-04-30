package com.Invilis.EpicMod;

import com.Invilis.dimensions.EpicDimensions;
import com.Invilis.dimensions.Elven.ElvenDimension;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DimensionRegistryEventHandler {
	/// Dimension registered
	@SubscribeEvent
	public static void onRegisterDimensionsEvent(RegisterDimensionsEvent event) {
		//ElvenDimension.type = DimensionManager.registerDimension(new ResourceLocation(EpicMod.MOD_ID, "elven_realm"), EpicDimensions.elvenRealm, null, true);
		if (DimensionType.byName(new ResourceLocation(EpicMod.MOD_ID, "elven_realm")) == null) {
			ElvenDimension.type = DimensionManager.registerOrGetDimension(new ResourceLocation(EpicMod.MOD_ID, "elven_realm"), EpicDimensions.ELVEN_REALM, null, true);
		}
	}
}
