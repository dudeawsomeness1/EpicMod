package com.Invilis.merchant;

import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.Util;

public class EpicTrades {
	public static final Map<VillagerProfession, Int2ObjectMap<VillagerTrades.ITrade[]>> professionTrades = Util.make(Maps.newHashMap(), (p_221237_0_) -> {
		p_221237_0_.put(EpicProfessions.ELF_FARMER, func_221238_a(ImmutableMap.of(1, new VillagerTrades.ITrade[]{new EmeraldForItemsTrade(Items.WHEAT, 20, 16, 2), new EmeraldForItemsTrade(Items.POTATO, 26, 16, 2), new EmeraldForItemsTrade(Items.CARROT, 22, 16, 2), new EmeraldForItemsTrade(Items.BEETROOT, 15, 16, 2), new ItemsForEmeraldsTrade(Items.BREAD, 1, 6, 16, 1)}, 2, new VillagerTrades.ITrade[]{new EmeraldForItemsTrade(Blocks.PUMPKIN, 6, 12, 10), new ItemsForEmeraldsTrade(Items.PUMPKIN_PIE, 1, 4, 5), new ItemsForEmeraldsTrade(Items.APPLE, 1, 4, 16, 5)}, 3, new VillagerTrades.ITrade[]{new ItemsForEmeraldsTrade(Items.COOKIE, 3, 18, 10), new EmeraldForItemsTrade(Blocks.MELON, 4, 12, 20)}, 4, new VillagerTrades.ITrade[]{new ItemsForEmeraldsTrade(Blocks.CAKE, 1, 1, 12, 15)}, 5, new VillagerTrades.ITrade[]{new ItemsForEmeraldsTrade(Items.GOLDEN_CARROT, 3, 3, 30), new ItemsForEmeraldsTrade(Items.GLISTERING_MELON_SLICE, 4, 3, 30)})));
	});
	
	private static Int2ObjectMap<VillagerTrades.ITrade[]> func_221238_a(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
		return new Int2ObjectOpenHashMap<>(p_221238_0_);
	}
	
	static class EmeraldForItemsTrade implements VillagerTrades.ITrade {
		private final Item tradeItem;
		private final int count;
		private final int field_221185_c;
		private final int field_221186_d;
		private final float field_221187_e;
	
		public EmeraldForItemsTrade(IItemProvider tradeItemProvider, int p_i50539_2_, int p_i50539_3_, int p_i50539_4_) {
			this.tradeItem = tradeItemProvider.asItem();
			this.count = p_i50539_2_;
			this.field_221185_c = p_i50539_3_;
			this.field_221186_d = p_i50539_4_;
			this.field_221187_e = 0.05F;
		}
	
		public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
			ItemStack itemstack = new ItemStack(this.tradeItem, this.count);
			return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.field_221185_c, this.field_221186_d, this.field_221187_e);
		}
	}
	
	static class ItemsForEmeraldsTrade implements VillagerTrades.ITrade {
	      private final ItemStack field_221208_a;
	      private final int field_221209_b;
	      private final int field_221210_c;
	      private final int field_221211_d;
	      private final int field_221212_e;
	      private final float field_221213_f;

	      public ItemsForEmeraldsTrade(Block p_i50528_1_, int p_i50528_2_, int p_i50528_3_, int p_i50528_4_, int p_i50528_5_) {
	         this(new ItemStack(p_i50528_1_), p_i50528_2_, p_i50528_3_, p_i50528_4_, p_i50528_5_);
	      }

	      public ItemsForEmeraldsTrade(Item p_i50529_1_, int p_i50529_2_, int p_i50529_3_, int p_i50529_4_) {
	         this(new ItemStack(p_i50529_1_), p_i50529_2_, p_i50529_3_, 12, p_i50529_4_);
	      }

	      public ItemsForEmeraldsTrade(Item p_i50530_1_, int p_i50530_2_, int p_i50530_3_, int p_i50530_4_, int p_i50530_5_) {
	         this(new ItemStack(p_i50530_1_), p_i50530_2_, p_i50530_3_, p_i50530_4_, p_i50530_5_);
	      }

	      public ItemsForEmeraldsTrade(ItemStack p_i50531_1_, int p_i50531_2_, int p_i50531_3_, int p_i50531_4_, int p_i50531_5_) {
	         this(p_i50531_1_, p_i50531_2_, p_i50531_3_, p_i50531_4_, p_i50531_5_, 0.05F);
	      }

	      public ItemsForEmeraldsTrade(ItemStack p_i50532_1_, int p_i50532_2_, int p_i50532_3_, int p_i50532_4_, int p_i50532_5_, float p_i50532_6_) {
	         this.field_221208_a = p_i50532_1_;
	         this.field_221209_b = p_i50532_2_;
	         this.field_221210_c = p_i50532_3_;
	         this.field_221211_d = p_i50532_4_;
	         this.field_221212_e = p_i50532_5_;
	         this.field_221213_f = p_i50532_6_;
	      }

	      public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	         return new MerchantOffer(new ItemStack(Items.EMERALD, this.field_221209_b), new ItemStack(this.field_221208_a.getItem(), this.field_221210_c), this.field_221211_d, this.field_221212_e, this.field_221213_f);
	      }
	}
	
	static class EnchantedItemForEmeraldsTrade implements VillagerTrades.ITrade {
	      private final ItemStack field_221195_a;
	      private final int field_221196_b;
	      private final int field_221197_c;
	      private final int field_221198_d;
	      private final float field_221199_e;

	      public EnchantedItemForEmeraldsTrade(Item p_i50535_1_, int p_i50535_2_, int p_i50535_3_, int p_i50535_4_) {
	         this(p_i50535_1_, p_i50535_2_, p_i50535_3_, p_i50535_4_, 0.05F);
	      }

	      public EnchantedItemForEmeraldsTrade(Item p_i50536_1_, int p_i50536_2_, int p_i50536_3_, int p_i50536_4_, float p_i50536_5_) {
	         this.field_221195_a = new ItemStack(p_i50536_1_);
	         this.field_221196_b = p_i50536_2_;
	         this.field_221197_c = p_i50536_3_;
	         this.field_221198_d = p_i50536_4_;
	         this.field_221199_e = p_i50536_5_;
	      }

	      public MerchantOffer getOffer(Entity p_221182_1_, Random p_221182_2_) {
	         int i = 5 + p_221182_2_.nextInt(15);
	         ItemStack itemstack = EnchantmentHelper.addRandomEnchantment(p_221182_2_, new ItemStack(this.field_221195_a.getItem()), i, false);
	         int j = Math.min(this.field_221196_b + i, 64);
	         ItemStack itemstack1 = new ItemStack(Items.EMERALD, j);
	         return new MerchantOffer(itemstack1, itemstack, this.field_221197_c, this.field_221198_d, this.field_221199_e);
	      }
	}
}
