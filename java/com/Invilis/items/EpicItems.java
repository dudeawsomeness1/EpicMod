package com.Invilis.items;

import net.minecraft.block.Blocks;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.IItemTier;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

//import java.util.function.Supplier;

import com.Invilis.EpicMod.*;
import com.Invilis.blocks.EpicBlocks;
import com.Invilis.entities.EpicEntities;
import com.Invilis.renderer.EpicItemStackTileEntityRenderer;

@Mod.EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicItems {
	public static Item Ruby = null;
	
	public static Item BalrogSword = null;
	
	public static Item MithrilIngot = null;
    public static Item mithrilSword = null;
    public static Item MithrilPickaxe = null;
    public static Item MithrilAxe = null;
    public static Item MithrilHoe = null;
    public static Item MithrilShovel = null;
    public static Item MithrilHelmet = null;
    public static Item MithrilChestplate = null;
    public static Item MithrilLeggings = null;
    public static Item MithrilBoots = null;
    public static Item mithrilShield = null;
    
    public static Item scanner = null;
    public static Item scanner2 = null;
    public static Item scanner3 = null;
    
    public static Item athelasLeaf = null;
    public static Item mallornNut = null;
    public static Item thinIronWire = null, ironMail = null;
    public static Item shardOfTheInterplane = null;
    
    /// Food Items
    @ObjectHolder(EpicMod.MOD_ID + ":jelly_beans")
    public static Item JellyBeans = null;
    public static Item Lembas = null;
    public static Item corn = null;
    public static Item cornKernals = null;
    
    public static Item athelasTea = null;
    
    /// BlockItems
    public static Item MithrilChest = null;
    public static Item MithrilOre = null;
    public static Item MithrilBlock = null;
    public static Item MithrilPortal = null;
    
    public static Item RubyBlock = null;
    public static Item RubyOre = null;
    
    public static Item athelas = null;
    public static Item blessedCorn = null;
    
    public static Item mallornLeaves = null;
    public static Item mallornLeavesAutumnal = null;
    public static Item mallornLog = null;
    public static Item strippedMallornLog = null;
    public static Item mallornWood = null;
    public static Item strippedMallornWood = null;
    public static Item mallornPlanks = null;
    @ObjectHolder(EpicMod.MOD_ID + ":mallorn_sapling")
    public static Item mallornSapling = null;
    @ObjectHolder(EpicMod.MOD_ID + ":mallorn_sapling_autumnal")
    public static Item mallornSaplingAutumnal = null;
    
    /// Spawn Eggs
    public static Item barrowWight_SE = null;
    
    


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
    	//In here you pass in all item instances you want to register.
        //Make sure you always set the registry name.
        event.getRegistry().registerAll(
        	Ruby =			new Item(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "ruby"),
        	athelasLeaf =	new AthelasLeaf(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).maxStackSize(16)).setRegistryName(EpicMod.MOD_ID, "athelas_leaf"),
        	mallornNut =	new MallornNut().setRegistryName(EpicMod.MOD_ID, "mallorn_nut"),
			cornKernals =	new CornKernals().setRegistryName(EpicMod.MOD_ID, "corn_kernals"),
			thinIronWire =	new Item(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "thin_iron_wire"),
			ironMail =		new Item(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "iron_mail"),
        	shardOfTheInterplane = new ShardOfTheInterplane().setRegistryName(EpicMod.MOD_ID, "shard_of_the_interplane"),
			
            BalrogSword =	new BalrogSword(ShadowFlameMaterial, 3, -2.5f, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "balrog_sword"),
            
            /// Mithril
            MithrilIngot =		new Item(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_ingot"),
            
            mithrilSword =		new MithrilSword(MithrilTier, 4,	-2.3f,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_sword"),
            MithrilPickaxe =	new PickaxeItem(MithrilTier, 2,		-2.3f,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_pickaxe"),
            MithrilAxe =		new AxeItem(MithrilTier, 5.0f,		-2.3f,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_axe"),
            MithrilHoe = 		new HoeItem(MithrilTier,			1.0f,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_hoe"),
            MithrilShovel =		new ShovelItem(MithrilTier, 2.5f,	-2.3f,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_shovel"),
            
            MithrilHelmet =		new MithrilArmorHelmet(MithrilArmorMaterial, EquipmentSlotType.HEAD, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_helmet"),
            MithrilChestplate =	new ArmorItem(MithrilArmorMaterial, EquipmentSlotType.CHEST, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_chestplate"),
            MithrilLeggings =	new ArmorItem(MithrilArmorMaterial, EquipmentSlotType.LEGS, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_leggings"),
            MithrilBoots =		new ArmorItem(MithrilArmorMaterial, EquipmentSlotType.FEET, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_boots"),
            
            mithrilShield =		new ShieldItem(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).maxDamage(557)).setRegistryName(EpicMod.MOD_ID, "mithril_shield"),
            
            scanner =	new PowerLevelScanner(PowerLevelScanner.Tier.MKI).setRegistryName(EpicMod.MOD_ID, "scanner"),
    		scanner2 =	new PowerLevelScanner(PowerLevelScanner.Tier.MKII).setRegistryName(EpicMod.MOD_ID, "scanner2"),
			scanner3 =	new PowerLevelScanner(PowerLevelScanner.Tier.MKIII).setRegistryName(EpicMod.MOD_ID, "scanner3"),
            
            /// Food Items
            JellyBeans =	new Item(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).food(EpicFoods.JellyBeans)).setRegistryName(EpicMod.MOD_ID, "jelly_beans"),
            Lembas =		new Item(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).food(EpicFoods.Lembas)).setRegistryName(EpicMod.MOD_ID, "lembas"),
            corn =			new Item(new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).food(EpicFoods.corn)).setRegistryName(EpicMod.MOD_ID, "corn"),
            
            athelasTea =	new AthelasTea().setRegistryName(EpicMod.MOD_ID, "athelas_tea"),
            
            /// BlockItems
            MithrilChest =	new BlockItem(EpicBlocks.mithrilChest,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP).setTEISR(() -> EpicItemStackTileEntityRenderer::new)).setRegistryName(EpicMod.MOD_ID, "mithril_chest"),
            MithrilOre =	new BlockItem(EpicBlocks.MithrilOre,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_ore"),
            MithrilBlock =	new BlockItem(EpicBlocks.MithrilBlock,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_block"),
            MithrilPortal =	new BlockItem(EpicBlocks.mithrilPortal,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mithril_portal"),
            
            RubyBlock =		new BlockItem(EpicBlocks.RubyBlock,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "ruby_block"),
            RubyOre =		new BlockItem(EpicBlocks.RubyOre,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "ruby_ore"),
            
            mallornLeaves =	new BlockItem(EpicBlocks.mallornLeaves,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mallorn_leaves"),
            mallornLeavesAutumnal = new BlockItem(EpicBlocks.mallornLeavesAutumnal,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mallorn_leaves_autumnal"),
            mallornLog =			new BlockItem(EpicBlocks.mallornLog,			new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mallorn_log"),
            strippedMallornLog =	new BlockItem(EpicBlocks.strippedMallornLog,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "stripped_mallorn_log"),
    		mallornWood =			new BlockItem(EpicBlocks.mallornWood,			new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mallorn_wood"),
            strippedMallornWood =	new BlockItem(EpicBlocks.strippedMallornWood,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "stripped_mallorn_wood"),
            mallornPlanks =		new BlockItem(EpicBlocks.mallornPlanks, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mallorn_planks"),
            new BlockItem(EpicBlocks.mallornSapling, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling"),
            new BlockItem(EpicBlocks.mallornSaplingAutumnal, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "mallorn_sapling_autumnal"),
            
            athelas =		new BlockItem(EpicBlocks.AthelasBlock,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "athelas"),
            blessedCorn =	new BlockItem(EpicBlocks.blessedCorn,	new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName(EpicMod.MOD_ID, "blessed_corn"),
            
            /// Spawn Eggs
            barrowWight_SE = new SpawnEggItem(EpicEntities.BARROW_WIGHT, -13421773, -13434880, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP)).setRegistryName("barrow_wight_spawn_egg")
        );
    }
    
    static IItemTier MithrilTier = new IItemTier() {
		public int getMaxUses() {
			return 3777;
		}

		public float getEfficiency() {
			return 9f;
		}

		public float getAttackDamage() {
			return 4f;
		}

		public int getHarvestLevel() {
			return 4;
		}

		public int getEnchantability() {
			return 20;
		}

		public Ingredient getRepairMaterial() {
			return Ingredient.fromStacks(new ItemStack(MithrilIngot, (int) (1)));
		}
	};

	static IItemTier ShadowFlameMaterial = new IItemTier() {
		public int getMaxUses() {
			return 77777;
		}

		public float getEfficiency() {
			return 10.5f;
		}

		public float getAttackDamage() {
			return 773f;
		}

		public int getHarvestLevel() {
			return 7;
		}

		public int getEnchantability() {
			return 77;
		}

		public Ingredient getRepairMaterial() {
			return Ingredient.fromStacks(new ItemStack(Blocks.FIRE, (int) (1)));
		}
	};
	
	static IArmorMaterial MithrilArmorMaterial = new IArmorMaterial() {
		public int getDurability(EquipmentSlotType slot) {
			return new int[]{416, 600, 564, 490}[slot.getIndex()];
		}

		public int getDamageReductionAmount(EquipmentSlotType slot) {
			return new int[]{4, 7, 9, 4}[slot.getIndex()]; // boots, leggings, chestplate, helmet
		}

		public int getEnchantability() {
			return 20;
		}

		public net.minecraft.util.SoundEvent getSoundEvent() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron"));
		}

		public Ingredient getRepairMaterial() {
			return Ingredient.fromStacks(new ItemStack(MithrilIngot, (int) (1)));
		}

		@OnlyIn(Dist.CLIENT)
		public String getName() {
			return EpicMod.MOD_ID + ":mithril";
		}

		public float getToughness() {
			return 3f;
		}
	};
}
