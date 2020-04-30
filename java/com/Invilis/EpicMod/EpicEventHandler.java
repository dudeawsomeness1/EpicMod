package com.Invilis.EpicMod;

import java.util.Random;

import com.Invilis.entities.BarrowWight.BarrowWight;
import com.Invilis.items.EpicItems;
import com.Invilis.mob_effects.EpicMobEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EpicEventHandler {
	/// Mithril Shield damaged
	@SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event){
        Entity entity = event != null ? event.getEntity() : null;
        int damageInt = (int) event.getAmount();
        if (entity != null && entity instanceof PlayerEntity) {
            ItemStack activeItem = ((PlayerEntity)entity).getActiveItemStack();
            
            if (((PlayerEntity)entity).isActiveItemStackBlocking() && ((activeItem).getItem() == EpicItems.mithrilShield)) {
                if (activeItem.attemptDamageItem(damageInt, new Random(), null)) {
                	activeItem.shrink(1);
                	activeItem.setDamage(0);
				}
            }
            else if (event.getSource().getImmediateSource() instanceof BarrowWight) {
            	((PlayerEntity) entity).addPotionEffect(new EffectInstance(EpicMobEffects.black_breath, (int) 3*30, (int) 0, false, true));
            }
        }
    }
}
