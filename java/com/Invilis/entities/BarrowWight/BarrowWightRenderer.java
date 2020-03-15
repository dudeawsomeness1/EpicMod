package com.Invilis.entities.BarrowWight;

import javax.annotation.ParametersAreNonnullByDefault;

import com.Invilis.EpicMod.EpicMod;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;

@OnlyIn(Dist.CLIENT)
@ParametersAreNonnullByDefault
public class BarrowWightRenderer extends MobRenderer<BarrowWight, BarrowWightModel> {
	private static final ResourceLocation BARROW_WIGHT_TEXTURES = new ResourceLocation(EpicMod.MOD_ID, "textures/entity/barrow_wight.png");
	
	public BarrowWightRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BarrowWightModel(), 0.0F);
	}

    @Override
    protected ResourceLocation getEntityTexture(BarrowWight entity) {
        return BARROW_WIGHT_TEXTURES;
    }
    
    public static class RenderFactory implements IRenderFactory<BarrowWight> {
		@Override
		public EntityRenderer<? super BarrowWight> createRenderFor(EntityRendererManager manager) {
			return new BarrowWightRenderer(manager);
		}
    }
}
