package com.Invilis.EpicMod;

import java.util.Random;

import com.Invilis.dimensions.EpicDimensions;
import com.Invilis.dimensions.Elven.ElvenDimension;
import com.Invilis.items.EpicItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
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
        }
    }
}
