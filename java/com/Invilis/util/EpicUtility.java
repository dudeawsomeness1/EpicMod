package com.Invilis.util;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

public class EpicUtility {
	public static IArmorMaterial constructNewArmorMaterial(int bootsDur, int leggingsDur, int chestDur, int helmetDur,
															int bootsDef, int leggingsDef, int chestDef, int helmetDef,
															int enchantability, float toughness, ItemStack repairMat,
															ResourceLocation equipSound, String name) {
		return new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{bootsDur, leggingsDur, chestDur, helmetDur}[slot.getIndex()];
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{bootsDef, leggingsDef, chestDef, helmetDef}[slot.getIndex()];
			}

			public int getEnchantability() {
				return enchantability;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(equipSound);
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(repairMat);
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return name;
			}

			public float getToughness() {
				return toughness;
			}
		};
	}
	
	/// Existing approximate armor tier nums: 1:Leather, 2:Gold, 3:Chainmail, 4:Iron, 5:Diamond, 6:Netherite, 7:Mithril
	public static IArmorMaterial constructNewArmorMaterial(float armorTierNum, int enchantability, float toughness, ItemStack repairMat, ResourceLocation equipSound, String name) {
		return constructNewArmorMaterial((int)(armorTierNum * 82.8571f - 50.7143f), (int)(armorTierNum * 94.2143f) - 55, (int)(armorTierNum * 100.25f - 58.1429f), (int)(armorTierNum * 69.7857f - 41.8571f),
				(int)(armorTierNum * 0.53571f + 0.5f), (int)(armorTierNum * 0.82143f + 1.92857f), (int)(armorTierNum * 0.96429f + 2.92857f), (int)(armorTierNum * 0.42857f + 1.21429f),
				enchantability, toughness, repairMat, equipSound, name);
	}
}
