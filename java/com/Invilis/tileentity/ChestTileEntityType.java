package com.Invilis.tileentity;

import java.util.function.Supplier;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.blocks.EpicBlocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

public class ChestTileEntityType {
	@ObjectHolder(EpicMod.MOD_ID + ":mithril_chest")
    public static TileEntityType<?> MITHRIL_CHEST;

    public ChestTileEntityType()
    {
    	
    }

    @Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class Registration
    {
        @SubscribeEvent
        public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> e)
        {
            e.getRegistry().registerAll(
                    TileEntityType.Builder.create((Supplier<TileEntity>) MithrilChestTileEntity::new, EpicBlocks.mithrilChest).build(null).setRegistryName(EpicMod.MOD_ID, "mithril_chest")
            );
        }
    }
}
