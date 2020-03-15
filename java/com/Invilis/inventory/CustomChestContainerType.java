package com.Invilis.inventory;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

public class CustomChestContainerType {
	@ObjectHolder(EpicMod.MOD_ID + ":mithril_chest")
    public static ContainerType<CustomChestContainer> MITHRIL_CHEST;
	
	@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration
    {
        @SubscribeEvent
        public static void onContainerTypeRegistry(final RegistryEvent.Register<ContainerType<?>> e)
        {
            e.getRegistry().registerAll(
                    new ContainerType<>(CustomChestContainer::createMithrilContainer).setRegistryName(EpicMod.MOD_ID + ":mithril_chest")
            );
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerScreenFactories()
    {
        ScreenManager.registerFactory(MITHRIL_CHEST, CustomChestScreen::new);
    }
}
