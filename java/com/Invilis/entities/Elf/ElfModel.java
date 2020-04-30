package com.Invilis.entities.Elf;

//import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;

public class ElfModel extends /*BipedModel*/PlayerModel<Elf> {
	public ElfModel() {
		this(0.0F, false);
	}
	
	public ElfModel(float modelSize, boolean smallArmsIn) {
		super(modelSize, smallArmsIn);
	}
}
