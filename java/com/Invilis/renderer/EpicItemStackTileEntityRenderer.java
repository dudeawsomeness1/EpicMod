package com.Invilis.renderer;

import com.Invilis.blocks.ChestType;
import com.Invilis.blocks.CustomChestBlock;
import com.Invilis.tileentity.MithrilChestTileEntity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EpicItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
	private static final MithrilChestTileEntity MITHRIL_CHEST = new MithrilChestTileEntity();
	
	private static final MithrilChestTileEntity[] CHESTS = { MITHRIL_CHEST };
	
	public static EpicItemStackTileEntityRenderer instance = new EpicItemStackTileEntityRenderer();

    @Override
    public void renderByItem(ItemStack itemStackIn)
    {
        Item item = itemStackIn.getItem();

        if (Block.getBlockFromItem(item) instanceof CustomChestBlock)
        {
            ChestType typeOut = CustomChestBlock.getTypeFromItem(item);
            if (typeOut == null)
            {
                TileEntityRendererDispatcher.instance.renderAsItem(MITHRIL_CHEST);
            }
            else
            {
                TileEntityRendererDispatcher.instance.renderAsItem(CHESTS[typeOut.ordinal()]);
            }
        }
        else
        {
            super.renderByItem(itemStackIn);
        }
    }
}
