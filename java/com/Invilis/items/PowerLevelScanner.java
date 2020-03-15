package com.Invilis.items;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class PowerLevelScanner extends ArmorItem {
	enum Tier { MKI, MKII, MKIII };
	Tier tier;
	public PowerLevelScanner(Tier tierIn) {
		super(scannerMaterial, EquipmentSlotType.HEAD, new Item.Properties().group(EpicMod.EPIC_ITEM_GROUP));
		tier = tierIn;
	}
	
	public Tier getTier() {
		return tier;
	}
	
	public int overloadsAt() {
		switch (tier) {
		case MKI:
			return 200;
		case MKII:
			return 1000;
		case MKIII:
			return Integer.MAX_VALUE;
		}
		return Integer.MAX_VALUE;
	}
	
	public void explodeScanner(ItemStack s, LivingEntity entity, World world) {
		s.setDamage(s.getMaxDamage());
		s.shrink(1);
		world.playSound((PlayerEntity)null, entity.posX, entity.posY, entity.posZ,
				SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 1.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
	}
	
	public String getTexturePath() {
		switch (tier) {
		case MKI:
			return "textures/overlays/scanner.png";
		case MKII:
			return "textures/overlays/scanner2.png";
		case MKIII:
			return "textures/overlays/scanner3.png";
		}
		return "textures/overlays/scanner.png";
	}
	
	private static final IArmorMaterial scannerMaterial = new IArmorMaterial() {
		public int getDurability(EquipmentSlotType slot) {
			return new int[]{11, 16, 15, 13}[slot.getIndex()] * 15;
		}

		public int getDamageReductionAmount(EquipmentSlotType slot) {
			return new int[]{1, 2, 4, 1}[slot.getIndex()]; // boots, leggings, chestplate, helmet
		}

		public int getEnchantability() {
			return 12;
		}

		public net.minecraft.util.SoundEvent getSoundEvent() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron"));
		}

		public Ingredient getRepairMaterial() {
			return Ingredient.fromStacks(new ItemStack(Items.IRON_INGOT, (int) (1)));
		}

		@OnlyIn(Dist.CLIENT)
		public String getName() {
			return EpicMod.MOD_ID + ":scanner";
		}

		public float getToughness() {
			return 0f;
		}
	};
}
