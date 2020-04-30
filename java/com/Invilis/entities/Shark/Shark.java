package com.Invilis.entities.Shark;

import java.util.function.Predicate;

import com.Invilis.entities.EpicEntities;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

public class Shark extends WaterMobEntity {
	public static final Predicate<LivingEntity> isPrey = (entity) -> {
	      EntityType<?> entitytype = entity.getType();
	      return entitytype == EntityType.COD || entitytype == EntityType.SALMON || entitytype == EntityType.TROPICAL_FISH || entitytype == EntityType.SQUID;
	   };
	
	public Shark(FMLPlayMessages.SpawnEntity packet, World world) {
		super(EpicEntities.SHARK, world);
	}
	
	public Shark(EntityType<? extends WaterMobEntity> type, World world) {
		super(type, world);
		experienceValue = 7;
		setNoAI(false);
	}
	
	public static void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			ResourceLocation biome_RL = ForgeRegistries.BIOMES.getKey(biome);
			if (biome_RL.equals(Biomes.OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_LUKEWARM_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.COLD_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_COLD_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.DEEP_WARM_OCEAN.getRegistryName()) ||
					biome_RL.equals(Biomes.WARM_OCEAN.getRegistryName()))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.WATER_CREATURE).add(new Biome.SpawnListEntry(EpicEntities.SHARK, 8, 1, 6));
		}
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(70.0);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FindWaterGoal(this));
		this.goalSelector.addGoal(2, new MoveTowardsTargetGoal((CreatureEntity)this, 1.0, 0.5f));
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterMobEntity.class, 10, false, false, isPrey));
		this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
	    this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.2D, true));
	}
}
