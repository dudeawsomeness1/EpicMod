package com.Invilis.items;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class EpicFoods {
	public static final Food JellyBeans;
	public static final Food Lembas;
	public static final Food corn;
	public static final Food popcorn;
	public static final Food athelasTea;
	
	static {
		EffectInstance spd = new EffectInstance(Effects.SPEED, (int) 60, (int) 0, true, true);
		JellyBeans =	(new Food.Builder()).setAlwaysEdible().fastToEat().effect(spd, 0.9f).hunger(2).saturation(0.2f).build();
		Lembas = 		(new Food.Builder()).effect(new EffectInstance(Effects.SATURATION), 1.0f).effect(new EffectInstance(Effects.STRENGTH), 0.2f).hunger(20).saturation(1.6f).build();
		corn = 			(new Food.Builder()).hunger(1).saturation(0.5f).build();
		popcorn =		(new Food.Builder()).hunger(6).saturation(0.75f).build();
		athelasTea =	(new Food.Builder()).hunger(1).saturation(0.1f).setAlwaysEdible().build();
	}
}
