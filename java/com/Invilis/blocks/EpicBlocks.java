package com.Invilis.blocks;

import com.Invilis.EpicMod.*;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicBlocks {
	public static Block RubyOre;
    public static Block RubyBlock;
    
    /// Mithril
    public static Block MithrilOre;
    public static Block MithrilBlock;
    @ObjectHolder(EpicMod.MOD_ID + ":mithril_chest")
    public static CustomChestBlock mithrilChest;
    @ObjectHolder(EpicMod.MOD_ID + ":mithril_portal")
    public static MithrilPortal mithrilPortal;
    
    /// Mallorn Tree
    public static Block mallornLeaves;
    public static Block mallornLeavesAutumnal;
    public static Block mallornLog;
    public static Block strippedMallornLog;
    public static Block mallornWood;
    public static Block strippedMallornWood;
    public static Block mallornPlanks;
    @ObjectHolder(EpicMod.MOD_ID + ":mallorn_sapling")
    public static Block mallornSapling;
    @ObjectHolder(EpicMod.MOD_ID + ":mallorn_sapling_autumnal")
    public static Block mallornSaplingAutumnal;
    
    /// Plants
    public static Block AthelasBlock;
    public static Block blessedCorn;
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	event.getRegistry().registerAll(
    		RubyOre = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f, 16f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "ruby_ore"),
    		RubyBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(6f, 32.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "ruby_block"),
    		
    		MithrilOre = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.2f, 16f).harvestLevel(3).harvestTool(ToolType.PICKAXE).lightValue(8)).setRegistryName(EpicMod.MOD_ID, "mithril_ore"),
    		MithrilBlock = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(6.25f, 40.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE).lightValue(9)).setRegistryName(EpicMod.MOD_ID, "mithril_block"),
    		new MithrilChest(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3.5f, 16f).lightValue(2).harvestLevel(0).harvestTool(ToolType.AXE)),
    		new MithrilPortal().setRegistryName(EpicMod.MOD_ID, "mithril_portal"),
    		
    		mallornLeaves = new MallornLeaves().setRegistryName(EpicMod.MOD_ID, "mallorn_leaves"),
    		mallornLeavesAutumnal = new MallornLeaves().setRegistryName(EpicMod.MOD_ID, "mallorn_leaves_autumnal"),
    		mallornLog = new MallornLog().setRegistryName(EpicMod.MOD_ID, "mallorn_log"),
    		strippedMallornLog = new MallornLog().setRegistryName(EpicMod.MOD_ID, "stripped_mallorn_log"),
			mallornWood = new MallornLog().setRegistryName(EpicMod.MOD_ID, "mallorn_wood"),
    		strippedMallornWood = new MallornLog().setRegistryName(EpicMod.MOD_ID, "stripped_mallorn_wood"),
    		mallornPlanks = new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 15f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_planks"),
    		new MallornSapling(false).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling"),
    		new MallornSapling(true).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling_autumnal"),
    		
    		AthelasBlock = new Athelas().setRegistryName(EpicMod.MOD_ID, "athelas"),
    		blessedCorn = new BlessedCorn().setRegistryName(EpicMod.MOD_ID, "blessed_corn")
    	);
    }
}
