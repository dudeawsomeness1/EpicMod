package com.Invilis.entities.Elf;

import javax.annotation.Nullable;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.entities.EpicEntities;
import com.Invilis.merchant.EpicTrades;
import com.mojang.datafixers.Dynamic;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerData;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.villager.IVillagerType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.item.MerchantOffers;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTDynamicOps;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.GossipManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

public class Elf extends AbstractVillagerEntity {
	private static final DataParameter<VillagerData> VILLAGER_DATA = EntityDataManager.createKey(Elf.class, DataSerializers.VILLAGER_DATA);
	private final GossipManager gossip = new GossipManager();
	
	public Elf(FMLPlayMessages.SpawnEntity packet, World world) {
		this(EpicEntities.ELF, world);
	}
	
	public Elf(EntityType<? extends AbstractVillagerEntity> type, World world) {
		super(type, world);
		experienceValue = 8;
		setNoAI(false);
	}
	
	public Elf(EntityType<? extends AbstractVillagerEntity> type, World world, IVillagerType villagerType) {
		super(type, world);
		((GroundPathNavigator)this.getNavigator()).setBreakDoors(true);
		this.getNavigator().setCanSwim(true);
		this.setCanPickUpLoot(true);
		this.setVillagerData(this.getVillagerData().withType(villagerType).withProfession(VillagerProfession.NONE));
		this.brain = this.createBrain(new Dynamic<>(NBTDynamicOps.INSTANCE, new CompoundNBT()));
	}
	
	public static void init(FMLCommonSetupEvent event) {
		// add Elf spawn to biomes
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			/// only spawns in Valinorian Forest if in Overworld
			boolean biomeCriteria = false;
			if ((ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation(EpicMod.MOD_ID + ":valinorian_forest"))))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MISC).add(new Biome.SpawnListEntry(EpicEntities.ELF, 5, 1, 7));
		}
	}
	
	protected void registerAttributes() {
		super.registerAttributes();
		this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(48.0D);
	}
	
	public void setCustomer(@Nullable PlayerEntity player) {
		boolean flag = this.getCustomer() != null && player == null;
		super.setCustomer(player);
		if (flag) {
			this.func_213750_eg();
		}
	}
	
	protected void func_213750_eg() {
		super.func_213750_eg();
		this.func_213748_et();
	}

	private void func_213748_et() {
		for(MerchantOffer merchantoffer : this.getOffers()) {
			merchantoffer.func_222220_k();
		}
	}
	
	public boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		boolean flag = itemstack.getItem() == Items.NAME_TAG;
		if (flag) {
			itemstack.interactWithEntity(player, this, hand);
			return true;
		} else if (itemstack.getItem() != Items.VILLAGER_SPAWN_EGG && this.isAlive() && !this.func_213716_dX() && !this.isSleeping() && !player.isSneaking()) {
			if (this.isChild()) {
				//this.shakeHead();
				return super.processInteract(player, hand);
			} else {
				boolean flag1 = this.getOffers().isEmpty();
				if (hand == Hand.MAIN_HAND) {
					if (flag1 && !this.world.isRemote) {
						//this.shakeHead();
					}

					player.addStat(Stats.TALKED_TO_VILLAGER);
				}

				if (flag1) {
					return super.processInteract(player, hand);
				} else {
					if (!this.world.isRemote && !this.offers.isEmpty()) {
						this.initiateTradeing(player);
					}

					return true;
				}
			}
		} else {
			return super.processInteract(player, hand);
		}
	}
	
	private void initiateTradeing(PlayerEntity player) {
		this.updatePrices(player);
		this.setCustomer(player);
		this.func_213707_a(player, this.getDisplayName(), this.getVillagerData().getLevel());
	}
	
	private void updatePrices(PlayerEntity player) {
		int i = this.getGossipValue(player);
		if (i != 0) {
			// change prices based on gossip
			for(MerchantOffer merchantoffer : this.getOffers()) {
				merchantoffer.func_222207_a(-MathHelper.floor((float)i * merchantoffer.func_222211_m()));
			}
		}

		if (player.isPotionActive(Effects.HERO_OF_THE_VILLAGE)) {
			EffectInstance effectinstance = player.getActivePotionEffect(Effects.HERO_OF_THE_VILLAGE);
			int k = effectinstance.getAmplifier();

			for(MerchantOffer merchantoffer1 : this.getOffers()) {
				double d0 = 0.3D + 0.0625D * (double)k;
				int j = (int)Math.floor(d0 * (double)merchantoffer1.func_222218_a().getCount());
				merchantoffer1.func_222207_a(-Math.max(j, 1));
			}
		}
	}
	
	public int getGossipValue(PlayerEntity player) {
		return this.gossip.func_220921_a(player.getUniqueID(), (gossipType) -> {
			return true;
		});
	}
	
	@Nullable
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	      if (reason == SpawnReason.BREEDING) {
	         this.setVillagerData(this.getVillagerData().withProfession(VillagerProfession.NONE));
	      }

	      if (reason == SpawnReason.COMMAND || reason == SpawnReason.SPAWN_EGG || reason == SpawnReason.SPAWNER) {
	         this.setVillagerData(this.getVillagerData().withType(IVillagerType.byBiome(worldIn.getBiome(new BlockPos(this)))));
	      }

	      return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void func_213713_b(MerchantOffer p_213713_1_) {
		// called when trade happens
		
	}
	
	protected void registerData() {
		super.registerData();
		this.dataManager.register(VILLAGER_DATA, new VillagerData(IVillagerType.PLAINS, VillagerProfession.NONE, 1));
	}
	
	public void setVillagerData(VillagerData data) {
		VillagerData villagerdata = this.getVillagerData();
		if (villagerdata.getProfession() != data.getProfession()) {
			this.offers = null;
		}

		this.dataManager.set(VILLAGER_DATA, data);
	}
	
	public VillagerData getVillagerData() {
		return this.dataManager.get(VILLAGER_DATA);
	}

	@Override
	protected void populateTradeData() {
		VillagerData villagerdata = this.getVillagerData();
		Int2ObjectMap<VillagerTrades.ITrade[]> int2objectmap = EpicTrades.professionTrades.get(villagerdata.getProfession());
		if (int2objectmap != null && !int2objectmap.isEmpty()) {
			VillagerTrades.ITrade[] avillagertrades$itrade = int2objectmap.get(villagerdata.getLevel());
			if (avillagertrades$itrade != null) {
				MerchantOffers merchantoffers = this.getOffers();
				this.addTrades(merchantoffers, avillagertrades$itrade, 2);
			}
		}
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		double d0 = this.rand.nextDouble();
	    IVillagerType ivillagertype;
	    if (d0 < 0.5D) {
	    	ivillagertype = IVillagerType.byBiome(this.world.getBiome(new BlockPos(this)));
	    } else if (d0 < 0.75D) {
	    	ivillagertype = this.getVillagerData().getType();
	    } else {
	    	ivillagertype = ((VillagerEntity)ageable).getVillagerData().getType();
	    }
	
	    Elf villagerentity = new Elf(EntityType.VILLAGER, this.world, ivillagertype);
	    villagerentity.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(villagerentity)), SpawnReason.BREEDING, (ILivingEntityData)null, (CompoundNBT)null);
	    return villagerentity;
	}
}
