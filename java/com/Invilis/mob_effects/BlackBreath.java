package com.Invilis.mob_effects;

import java.util.ArrayList;
import java.util.List;

import com.Invilis.events.BlackBreathOnPotionActiveTick;
import com.Invilis.items.EpicItems;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class BlackBreath extends Effect {
	private final ResourceLocation potionIcon;

	protected BlackBreath() {
		super(EffectType.HARMFUL, -16183029);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		potionIcon = new ResourceLocation("epic:textures/black_breath.png");
	}
	
	@Override
	public String getName() {
		return "effect.blackbreath";
	}

	@Override
	public boolean isBeneficial() {
		return false;
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		List<ItemStack> ret = new ArrayList<>();
		ret.add(new ItemStack(EpicItems.athelasTea, (int) (1)));
		return ret;
	}

	@Override
	public boolean shouldRenderInvText(EffectInstance effect) {
		return true;
	}

	@Override
	public boolean shouldRenderHUD(EffectInstance effect) {
		return true;
	}

	@Override
	public void performEffect(LivingEntity entity, int amplifier) {
		//World world = entity.world;
		//int x = (int) entity.posX;
		//int y = (int) entity.posY;
		//int z = (int) entity.posZ;
		{
			java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
			$_dependencies.put("entity", entity);
			BlackBreathOnPotionActiveTick.executeProcedure($_dependencies, amplifier);
		}
	}

	@Override
	public boolean isReady(int duration, int amplifier) {
		return true;
	}
}
