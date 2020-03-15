package com.Invilis.blocks;

import com.Invilis.tileentity.MithrilChestTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class MithrilChest extends CustomChestBlock {
	public MithrilChest(Block.Properties properties)
    {
        super(properties, ChestType.MITHRIL);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new MithrilChestTileEntity();
    }
}
