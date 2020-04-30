package com.Invilis.entities.Oyster;

import javax.annotation.ParametersAreNonnullByDefault;

import com.Invilis.EpicMod.EpicMod;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
@ParametersAreNonnullByDefault
public class OysterRenderer extends MobRenderer<Oyster, OysterModel> {
	private static final ResourceLocation OYSTER_TEXTURE = new ResourceLocation(EpicMod.MOD_ID, "textures/entity/oyster.png");
	
	public OysterRenderer(EntityRendererManager renderManager) {
		super(renderManager, new OysterModel(), 0.0f);
	}

	@Override
	protected ResourceLocation getEntityTexture(Oyster entity) {
		return OYSTER_TEXTURE;
	}
	
	public static class RenderFactory implements IRenderFactory<Oyster> {
		@Override
		public EntityRenderer<? super Oyster> createRenderFor(EntityRendererManager manager) {
			return new OysterRenderer(manager);
		}
    }
}
