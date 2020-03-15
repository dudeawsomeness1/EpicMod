package com.Invilis.renderer;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.items.PowerLevelScanner;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.RayTraceResult.Type;

public class GuiOverlay extends IngameGui {
	private void calculatePowerLevel(final int[] pl, LivingEntity entity) {
		pl[0] += entity.getHealth();
        if (pl[0] != 0) {
        	if (entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
            	pl[0] += entity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue() * 8; // movement speed in m/s
            if (entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null && entity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED) != null)
            	pl[0] += entity.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue() * entity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).getValue(); // dps
            if (entity.getAttribute(SharedMonsterAttributes.ARMOR) != null)
            	pl[0] += entity.getAttribute(SharedMonsterAttributes.ARMOR).getValue();
            //boolean hasDamageItem = false;
            entity.getEquipmentAndArmor().forEach((s) -> {
            	if (s.getItem() instanceof SwordItem) {
            		//hasDamageItem = true;
            		if (entity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED) != null)
            			pl[0] += ((SwordItem)s.getItem()).getAttackDamage() * entity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).getValue();
            	}
            });
            entity.getActivePotionEffects().forEach((effect) -> {
            	if (effect.getPotion().isBeneficial())
            		pl[0] += effect.getAmplifier() + 1;
            	else
            		pl[0] -= effect.getAmplifier() + 1;
            });
         // if pl is WAY too high then trigger scanner explosion else continue drawing stuff
        }
	}
	
	private void renderScanner(Minecraft mc) {
		int scaled_width = mc.mainWindow.getScaledWidth();
		int scaled_height = mc.mainWindow.getScaledHeight();
		
		//mc = Minecraft.getInstance();
		if(mc.player == null || mc.currentScreen != null)
			return;

		ItemStack helmet = mc.player.inventory.armorItemInSlot(3);
		if(mc.gameSettings.thirdPersonView == 0 && helmet != null && helmet.getItem() instanceof PowerLevelScanner) {
			PowerLevelScanner scannerItem = (PowerLevelScanner)helmet.getItem();
			final int width = 256;
			final int height = 256;
			mc.getTextureManager().bindTexture(new ResourceLocation(EpicMod.MOD_ID, scannerItem.getTexturePath()));
			blit(scaled_width - width, (scaled_height - height) / 2, 0, 0, width, height);
			
			final int[] pl = {0}; // hp + movement_speed + dps + good_effect_levels - bad_effect_levels
			if (mc.objectMouseOver.getType() == Type.ENTITY) {
				// calculate power level
				// dps = dmg*atk_spd
				//LivingEntity entity = (LivingEntity)((EntityRayTraceResult)mc.objectMouseOver).getEntity();
				LivingEntity entity = null;
				if (mc.pointedEntity instanceof LivingEntity) {
					entity = (LivingEntity) mc.pointedEntity;
				}
				if (entity != null) {
					calculatePowerLevel(pl, entity);
					if (pl[0] > scannerItem.overloadsAt()) {
						scannerItem.explodeScanner(helmet, entity, mc.world.getWorld());
						return;
					}
					else {
						drawString(mc.fontRenderer, Integer.toString(pl[0]), (scaled_width - width) + 125, ((scaled_height - height) / 2) + 105, Integer.parseInt((pl[0] >= 0.85 * scannerItem.overloadsAt()) ? "FFF521" : "FFE521", 16));
					}
				}
				else {
					drawString(mc.fontRenderer, "Err", (scaled_width - width) + 126, ((scaled_height - height) / 2) + 107, Integer.parseInt("FFE521", 16));
				}
			}
			else {
				drawString(mc.fontRenderer, "---", (scaled_width - width) + 126, ((scaled_height - height) / 2) + 107, Integer.parseInt("FFE521", 16));
			}
			
			pl[0] = 0;
			calculatePowerLevel(pl, (LivingEntity)mc.player);
			drawString(mc.fontRenderer, (pl[0] > scannerItem.overloadsAt()) ? "###" : Integer.toString(pl[0]), (scaled_width - width) + 126, ((scaled_height - height) / 2) + 120, Integer.parseInt((pl[0] >= 0.85 * scannerItem.overloadsAt()) ? "FFF521" : "FFE521", 16));
	    }
	}
	
	public GuiOverlay(Minecraft mc) {
		super(mc);
		renderScanner(mc);
	}

	/*@SubscribeEvent
	public void onPostGuiRender(RenderGameOverlayEvent.Post event) {
		EpicMod.logger.debug(event.getType().toString());
		if (event.isCanceled()) return;//|| event.getType() != ElementType.HELMET
		
		final Minecraft mc = Minecraft.getInstance();
		if(mc.player == null || mc.currentScreen != null)
			return;

		ItemStack helmet = mc.player.inventory.armorItemInSlot(3);
		if(mc.gameSettings.thirdPersonView == 0 && helmet != null && helmet.getItem() == EpicItems.MithrilHelmet) {
			final int width = 452;
			final int height = 256;
			mc.getTextureManager().bindTexture(new ResourceLocation(EpicMod.MOD_ID, "textures/overlays/scanner.png"));
			blit(0, 0, 0, 0, width, height);
	    }
	}*/
	
	/*@Override
	public void render(int ticks, float partialTicks, ClientWorld world, Minecraft mc) {
		if(mc.player == null || mc.currentScreen != null)
			return;

		ItemStack helmet = mc.player.inventory.armorItemInSlot(3);
		if(mc.gameSettings.thirdPersonView == 0 && helmet != null && helmet.getItem() == EpicItems.MithrilHelmet) {
			final int width = 452;
			final int height = 256;
			mc.getTextureManager().bindTexture(new ResourceLocation(EpicMod.MOD_ID, "textures/overlays/scanner.png"));
			blit(0, 0, 0, 0, width, height);
	    }
	}*/
	
}

/*@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuiOverlay extends AbstractGui {
	/// Overlay render
	@SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
			final int width = 452;
			final int height = 256;
			
			Minecraft mc = Minecraft.getInstance();
			
			if (mc.player.inventory.armorInventory.get(3).getItem() == EpicItems.MithrilHelmet) {
				mc.getTextureManager().bindTexture(new ResourceLocation(EpicMod.MOD_ID, "textures/overlays/scanner.png"));
				blit(0, 0, 0, 0, width, height);
			}
		}
	}
}
*/