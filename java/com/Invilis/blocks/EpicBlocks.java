package com.Invilis.blocks;

import java.util.function.Supplier;

import com.Invilis.EpicMod.*;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicBlocks {
	public static Block amethystOre = null, aquamarineOre = null, garnetOre = null;
	public static Block opalOre = null, peridotOre = null;
	public static Block sapphireOre = null, tanzaniteOre = null, topazOre = null;
	public static Block RubyOre;
	
	public static Block alexandriteBlock = null;
	public static Block amethystBlock = null, aquamarineBlock = null, garnetBlock = null;
	public static Block opalBlock = null, pearlBlock = null, peridotBlock = null;
	public static Block sapphireBlock = null, tanzaniteBlock = null, topazBlock = null;
    public static Block RubyBlock;
    
    public static Block cornKernelBlock = null;
    
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
    public static Block mallornPlanks, mallornSlab, mallornStairs, mallornPressurePlate, mallornDoor,
    	/*mallornSign,*/ mallornButton, mallornTrapdoor, mallornFence, mallornFenceGate;
    @ObjectHolder(EpicMod.MOD_ID + ":mallorn_sapling")
    public static Block mallornSapling;
    @ObjectHolder(EpicMod.MOD_ID + ":mallorn_sapling_autumnal")
    public static Block mallornSaplingAutumnal;
    
    /// Plants
    public static Block AthelasBlock;
    public static Block blessedCorn;
    
    private static final Supplier<BlockState> F_stairsBlockState = () -> Blocks.OAK_STAIRS.getDefaultState();
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
    	event.getRegistry().registerAll(
			amethystOre = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f, 16f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "amethyst_ore"),
    		RubyOre = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.5f, 16f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "ruby_ore"),
    		
    		RubyBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(6f, 32.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "ruby_block"),
			alexandriteBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.5f, 31.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "alexandrite_block"),
			amethystBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(4.75f, 29.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "amethyst_block"),
			aquamarineBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5f, 30.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "aquamarine_block"),
			garnetBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5f, 30.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "garnet_block"),
			opalBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(4.5f, 28.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "opal_block"),
			pearlBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.25f, 20.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "pearl_block"),
			peridotBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5f, 30.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "peridot_block"),
			sapphireBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(6f, 32.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "sapphire_block"),
			tanzaniteBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(4.75f, 29.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "tanzanite_block"),
			topazBlock = new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5.5f, 31.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE)).setRegistryName(EpicMod.MOD_ID, "topaz_block"),
			
			cornKernelBlock =	new CornKernelBlock().setRegistryName(EpicMod.MOD_ID, "corn_kernel_block"),
			
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
    		mallornPlanks = new Block(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 8f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_planks"),
    		mallornSlab =	new SlabBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 8f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_slab"),
    		mallornStairs =	new StairsBlock(F_stairsBlockState, Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 8f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_stairs"),
			mallornPressurePlate =	new CustomPressurePlate(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.5f, 0.5f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_pressure_plate"),
			mallornDoor =	new CustomDoorBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3f, 10f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_door"),
			mallornButton =	new CustomWoodButtonBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.5f, 0.5f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_button"),
			mallornTrapdoor =	new CustomTrapDoorBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(3f, 10f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_trapdoor"),
			mallornFence =		new FenceBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 8f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_fence"),
			mallornFenceGate =	new FenceGateBlock(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(2f, 8f).harvestLevel(0).harvestTool(ToolType.AXE)).setRegistryName(EpicMod.MOD_ID, "mallorn_fence_gate"),
    		new MallornSapling(false).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling"),
    		new MallornSapling(true).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling_autumnal"),
    		
    		AthelasBlock = new Athelas().setRegistryName(EpicMod.MOD_ID, "athelas"),
    		blessedCorn = new BlessedCorn().setRegistryName(EpicMod.MOD_ID, "blessed_corn")
    	);
    }
}
