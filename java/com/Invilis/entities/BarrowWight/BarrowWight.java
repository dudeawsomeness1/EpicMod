package com.Invilis.entities.BarrowWight;

import java.util.Random;
import java.util.function.Predicate;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.entities.EpicEntities;
import com.Invilis.sounds.EpicSounds;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.entity.ai.goal.FleeSunGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.MoveTowardsTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

public class BarrowWight extends MonsterEntity {
	private final BreakDoorGoal breakDoor;
    private boolean isBreakDoorsTaskSet;
    protected static final IAttribute SPAWN_REINFORCEMENTS_CHANCE = (new RangedAttribute((IAttribute)null, "zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).setDescription("Spawn Reinforcements Chance");
	
	public BarrowWight(FMLPlayMessages.SpawnEntity packet, World world) {
		this(EpicEntities.BARROW_WIGHT, world);
	}

	public BarrowWight(EntityType<? extends MonsterEntity> type, World world) {
		super(type, world);
		experienceValue = 8;
		setNoAI(false);
		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD, (int) (1)));
		this.breakDoor = new BreakDoorGoal((MobEntity)this, (Predicate<Difficulty>)p_lambda$static$0_0_ -> p_lambda$static$0_0_ == Difficulty.HARD);
	}
	
	public static void init(FMLCommonSetupEvent event) {
		// add Barrow Wight spawn to biomes (not valinorian forest)
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			/// cannot spawn in Valinorian Forest
			boolean biomeCriteria = false;
			/*if (!(ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation(EpicMod.MOD_ID + ":valinorian_forest"))))
				biomeCriteria = true;*/
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation(EpicMod.MOD_ID + ":barrow_downs")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(EpicEntities.BARROW_WIGHT, 6, 1, 7));
		}
	}
	
	@Override
	public boolean isAggressive() {
		return true;
	}
	
	@Override
	protected void registerAttributes() {
		super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0);
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23002);
        this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0);
        this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.0);
        this.getAttributes().registerAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.rand.nextDouble() * (double)ForgeConfig.SERVER.zombieBaseSummonChance.get());
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new RestrictSunGoal(this));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 1));
		this.goalSelector.addGoal(5, new SwimGoal(this));
        this.goalSelector.addGoal(8, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.addGoal(8, (Goal)new LookRandomlyGoal(this));
        this.applyEntityAI();
	}
	
	protected void applyEntityAI() {
		this.goalSelector.addGoal(1, new FleeSunGoal((CreatureEntity)this, 0.4));
        this.goalSelector.addGoal(2, (Goal)new MeleeAttackGoal((CreatureEntity)this, 1.0, canPickUpLoot()));
        this.goalSelector.addGoal(2, (Goal)new MoveTowardsTargetGoal((CreatureEntity)this, 1.0, 0.5f));
        this.goalSelector.addGoal(6, (Goal)new MoveThroughVillageGoal((CreatureEntity)this, 1.0, true, 4, this::isBreakDoorsTaskSet));
        this.goalSelector.addGoal(7, (Goal)new WaterAvoidingRandomWalkingGoal((CreatureEntity)this, 1.0));
        this.targetSelector.addGoal(1, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]).setCallsForHelp(new Class[] { this.getClass() }));
        this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal<PlayerEntity>((MobEntity)this, (Class)PlayerEntity.class, true));
        this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal<AbstractVillagerEntity>((MobEntity)this, (Class)AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal<IronGolemEntity>((MobEntity)this, (Class)IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, (Goal)new NearestAttackableTargetGoal<TurtleEntity>((MobEntity)this, (Class)TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
    }
	
	public boolean isBreakDoorsTaskSet() {
        return this.isBreakDoorsTaskSet;
    }
	
	public void setBreakDoorsAItask(final boolean p_146070_1_) {
        if (this.canBreakDoors()) {
            if (this.isBreakDoorsTaskSet != p_146070_1_) {
                this.isBreakDoorsTaskSet = p_146070_1_;
                ((GroundPathNavigator)this.getNavigator()).setBreakDoors(p_146070_1_);
                if (p_146070_1_) {
                    this.goalSelector.addGoal(1, (Goal)this.breakDoor);
                }
                else {
                    this.goalSelector.removeGoal((Goal)this.breakDoor);
                }
            }
        }
        else if (this.isBreakDoorsTaskSet) {
            this.goalSelector.removeGoal((Goal)this.breakDoor);
            this.isBreakDoorsTaskSet = false;
        }
    }
    
    protected boolean canBreakDoors() {
        return true;
    }

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEAD;
	}

	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropSpecialItems(source, looting, recentlyHitIn);
	}

	@Override
	public SoundEvent getAmbientSound() {
		return EpicSounds.BARROW_WIGHT_AMBIENT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_DEATH;
	}

	@Override
	protected float getSoundVolume() {
		return 1.0F;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source == DamageSource.CACTUS)
			return false;
		if (source == DamageSource.DROWN)
			return false;
		return super.attackEntityFrom(source, amount);
	}

	public void livingTick() {
		super.livingTick();
		int i = (int) this.posX;
		int j = (int) this.posY;
		int k = (int) this.posZ;
		Random random = this.rand;
		if (true)
			for (int l = 0; l < 4; ++l) {
				double d0 = (i + random.nextFloat());
				double d1 = (j + random.nextFloat());
				double d2 = (k + random.nextFloat());
				//int i1 = random.nextInt(2) * 2 - 1;
				double d3 = (random.nextFloat() - 0.5D) * 0.6000000014901161D;
				double d4 = (random.nextFloat() - 0.5D) * 0.6000000014901161D;
				double d5 = (random.nextFloat() - 0.5D) * 0.6000000014901161D;
				world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, d3, d4, d5);
			}
	}
}
