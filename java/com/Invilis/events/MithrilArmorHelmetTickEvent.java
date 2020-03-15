package com.Invilis.events;

import com.Invilis.items.EpicItems;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class MithrilArmorHelmetTickEvent {
	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MithrilArmorHelmetTickEvent!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		// If player is wearing full set of Mithril armor, give them regeneration
		/*@SubscribeEvent
		public void onClientTick(TickEvent.PlayerTickEvent event)
		{
			final PlayerEntity player = event.player;
			
			if( player.inventory.armorItemInSlot(3).getItem().equals(ModItems.snorkle) )
			{
			   player.setAir(100);
			}
		}*/
		
		if ((((((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(2) : ItemStack.EMPTY).getItem() == new ItemStack(
				EpicItems.MithrilChestplate, (int) (1)).getItem()) && (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(1)
				: ItemStack.EMPTY).getItem() == new ItemStack(EpicItems.MithrilLeggings, (int) (1)).getItem())) && (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.armorInventory.get(0)
				: ItemStack.EMPTY).getItem() == new ItemStack(EpicItems.MithrilBoots, (int) (1)).getItem()))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.REGENERATION, (int) -1, (int) 0, (true), (false)));
		}
	}
}
