package com.Invilis.entities.Shark;

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
public class SharkRenderer extends MobRenderer<Shark, SharkModel> {
	private static final ResourceLocation SHARK_TEXTURES = new ResourceLocation(EpicMod.MOD_ID, "textures/entity/shark.png");
	
	public SharkRenderer(EntityRendererManager renderManager) {
		super(renderManager, new SharkModel(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Shark entity) {
		return SHARK_TEXTURES;
	}
    
    public static class RenderFactory implements IRenderFactory<Shark> {
		@Override
		public EntityRenderer<? super Shark> createRenderFor(EntityRendererManager manager) {
			return new SharkRenderer(manager);
		}
    }
}
