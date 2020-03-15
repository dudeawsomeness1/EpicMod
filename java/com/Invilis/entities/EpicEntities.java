package com.Invilis.entities;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.entities.BarrowWight.BarrowWight;
import com.Invilis.entities.BarrowWight.BarrowWightRenderer;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicEntities {
	public final static EntityType<BarrowWight> BARROW_WIGHT = (EntityType.Builder.<BarrowWight>create(BarrowWight::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
			.setTrackingRange(64).setUpdateInterval(1).setCustomClientFactory(BarrowWight::new).size(0.6f, 1.95f)).build("barrow_wight");
	
	@SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
		BARROW_WIGHT.setRegistryName(EpicMod.MOD_ID, "barrow_wight");
		
		event.getRegistry().registerAll(
			//barrowWight
			BARROW_WIGHT
		);
		
		EntitySpawnPlacementRegistry.register(BARROW_WIGHT, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				MonsterEntity::func_223315_a);
	}
	
	@OnlyIn(Dist.CLIENT)
    public static void registerRenderingHandlers() {
		//BarrowWightRenderer.register(BarrowWight.class, BarrowWightRenderer.class);
        //RenderingRegistry.registerEntityRenderingHandler(BarrowWight.class, new BarrowWightRenderer.RenderFactory());
        RenderingRegistry.registerEntityRenderingHandler(BarrowWight.class, BarrowWightRenderer::new);
    }
}
