package com.Invilis.EpicMod;

import java.util.function.Supplier;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class EpicTab extends ItemGroup {
	private final Supplier<ItemStack> iconSupplier;

	public EpicTab(final String name, final Supplier<ItemStack> iconSupplier) {
		super(name);
		this.iconSupplier = iconSupplier;
	}

	@Override
	public ItemStack createIcon() {
		return iconSupplier.get();
	}
}
