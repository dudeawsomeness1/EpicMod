package com.Invilis.blocks;

import java.util.Random;

import com.Invilis.features.EpicFeatures;
import com.Invilis.items.EpicItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.PlantType;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MallornSapling extends SaplingBlock /*implements IGrowable*/ {
	public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 1);
	
	public MallornSapling(boolean isAutumnal) {
		super(new Tree() {
			@Override
			protected AbstractTreeFeature<NoFeatureConfig> getTreeFeature(Random random) {
				if (isAutumnal) return EpicFeatures.mallornTreeAutumnal;
				return EpicFeatures.mallornTree;
			}},
				Block.Properties.create(Material.PLANTS).tickRandomly().doesNotBlockMovement().sound(SoundType.PLANT).hardnessAndResistance(0f, 0f));
	}
	
	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == EpicItems.mallornSapling || event.getItemStack().getItem() == EpicItems.mallornSaplingAutumnal)//new ItemStack(EpicBlocks.mallornSapling, (int) (1)).getItem()
			event.setBurnTime(100);
	}
	
	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return true;
	}

	@Override
	public PlantType getPlantType(IBlockReader world, BlockPos pos) {
		return PlantType.Plains;
	}
}
