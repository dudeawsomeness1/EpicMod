package com.Invilis.entities.BarrowWight;

import net.minecraft.client.renderer.entity.model.AbstractZombieModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BarrowWightModel extends AbstractZombieModel<BarrowWight> /*implements IHasArm, IHasHead*/ {
	/*public BarrowWightModel(final float scale) {
		super(scale, scale, 64, 64);
	}*/

	/*@Override
	public boolean func_212850_a_(BarrowWight p_212850_1_) {
		return false;
	}*/
	
	public BarrowWightModel() {
		this(0.0F, false);
	}

	public BarrowWightModel(float modelSize, boolean p_i1168_2_) {
	   super(modelSize, 0.0F, 64, p_i1168_2_ ? 32 : 64);
	}

	protected BarrowWightModel(float p_i48914_1_, float p_i48914_2_, int p_i48914_3_, int p_i48914_4_) {
	   super(p_i48914_1_, p_i48914_2_, p_i48914_3_, p_i48914_4_);
	}

	public boolean func_212850_a_(BarrowWight p_212850_1_) {
	   return p_212850_1_.isAggressive();
	}
}
