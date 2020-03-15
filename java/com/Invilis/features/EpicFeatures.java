package com.Invilis.features;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicFeatures {
	@ObjectHolder(EpicMod.MOD_ID + ":mallorn_tree")
	public static AbstractTreeFeature<NoFeatureConfig> mallornTree = (AbstractTreeFeature<NoFeatureConfig>) new MallornTree(false).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling");
	@ObjectHolder(EpicMod.MOD_ID + ":mallorn_tree_autumnal")
	public static AbstractTreeFeature<NoFeatureConfig> mallornTreeAutumnal = (AbstractTreeFeature<NoFeatureConfig>) new MallornTree(true).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling_autumnal");
	
    /*@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<> event) {
    	event.getRegistry().registerAll(
    		mallornTree = new MallornTree()
    	);
    }*/
}
