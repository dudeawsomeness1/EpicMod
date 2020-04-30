package com.Invilis.entities.Elf;

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
public class ElfRenderer extends MobRenderer<Elf, ElfModel> {
	private static final ResourceLocation ELF_TEXTURES = new ResourceLocation(EpicMod.MOD_ID, "textures/entity/elf.png");
	
	public ElfRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ElfModel(), 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Elf entity) {
		return ELF_TEXTURES;
	}
	
	public static class RenderFactory implements IRenderFactory<Elf> {
		@Override
		public EntityRenderer<? super Elf> createRenderFor(EntityRendererManager manager) {
			return new ElfRenderer(manager);
		}
    }
}
