package com.Invilis.EpicMod;

import com.Invilis.biomes.BarrowDowns;
import com.Invilis.biomes.ValinorianForest;
import com.Invilis.blocks.Athelas;
import com.Invilis.blocks.BlessedCorn;
import com.Invilis.blocks.EpicBlocks;
import com.Invilis.brewing.AthelasTeaRecipe;
import com.Invilis.dimensions.Elven.ElvenDimension;
import com.Invilis.entities.EpicEntities;
import com.Invilis.entities.BarrowWight.BarrowWight;
import com.Invilis.entities.Elf.Elf;
import com.Invilis.entities.Shark.Shark;
import com.Invilis.inventory.CustomChestContainerType;
import com.Invilis.renderer.GuiOverlay;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import net.minecraftforge.registries.ForgeRegistries;

@Mod("epic")
public class EpicMod {
	public static EpicMod instance;
	public static final String MOD_ID = "epic";
	public static final Logger logger = LogManager.getLogger(MOD_ID); // was private
	
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("epic", "epic"),
			() -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	
	public static ServerProxy proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> ServerProxy::new);
	
	public static final ItemGroup EPIC_ITEM_GROUP = new EpicTab(EpicMod.MOD_ID, () -> new ItemStack(Item.BLOCK_TO_ITEM.get(EpicBlocks.MithrilOre)));
	
	public EpicMod() {
		instance = this;
		
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public void init_Mithril_Ore_spawn(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator<?> generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;
					if (dimensionType == DimensionType.OVERWORLD)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.place(world, generator, rand, pos, config);
				}
			}, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("mithril_ore", "mithril_ore", blockAt -> {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}), EpicBlocks.MithrilOre.getDefaultState(), 7), Placement.COUNT_RANGE, new CountRangeConfig(1, 0, 0, 15)));
		}
	}
	
	public void init_Ruby_Ore_spawn(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(new OreFeature(OreFeatureConfig::deserialize) {
				@Override
				public boolean place(IWorld world, ChunkGenerator<?> generator, Random rand, BlockPos pos, OreFeatureConfig config) {
					DimensionType dimensionType = world.getDimension().getType();
					boolean dimensionCriteria = false;
					if (dimensionType == DimensionType.OVERWORLD)
						dimensionCriteria = true;
					if (!dimensionCriteria)
						return false;
					return super.place(world, generator, rand, pos, config);
				}
			}, new OreFeatureConfig(OreFeatureConfig.FillerBlockType.create("ruby_ore", "ruby_ore", blockAt -> {
				boolean blockCriteria = false;
				if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
					blockCriteria = true;
				return blockCriteria;
			}), EpicBlocks.RubyOre.getDefaultState(), 6), Placement.COUNT_RANGE, new CountRangeConfig(3, 0, 0, 29)));
		}
	}
	
	private void init(FMLCommonSetupEvent event) {
		proxy.preInit();
		MinecraftForge.EVENT_BUS.register(GuiOverlay.class);
		
		Athelas.init(event);
		BlessedCorn.init(event);
		init_Mithril_Ore_spawn(event);
		init_Ruby_Ore_spawn(event);
		
		ValinorianForest.init(event);
		BarrowDowns.init(event);
		
		BarrowWight.init(event);
		Shark.init(event);
		//Oyster.init(event);
		Elf.init(event);
		
		ElvenDimension.init(event);
		
		DistExecutor.runWhenOn(Dist.CLIENT, () -> CustomChestContainerType::registerScreenFactories);
	}
	
	private void registerPotions() {
		BrewingRecipeRegistry.addRecipe(new AthelasTeaRecipe());
	}
	
	private void setup(final FMLCommonSetupEvent event) {
		DeferredWorkQueue.runLater(this::registerPotions);
		logger.info("setup method registered");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event) {
		EpicEntities.registerRenderingHandlers();
		logger.info("clientRegistries method registered");
	}
}
