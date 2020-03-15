package com.Invilis.blocks;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.tileentity.MithrilChestTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public enum ChestType implements IStringSerializable {
	MITHRIL(108, 12, "mithril_chest.png", MithrilChestTileEntity.class, EpicMod.MOD_ID + ":mithril_chest", 238, 276, new ResourceLocation(EpicMod.MOD_ID, "textures/gui/mithril_chest_gui.png"), 256, 276),
	WOOD(0, 0, "", null, null, 0, 0, null, 0, 0);
	
	//public static final ChestType VALUES[] = values();
    public final String name;
    public final int size;
    public final int rowLength;
    public final String modelTexture;
    public final Class<? extends TileEntity> clazz;
    public final String itemName;
    public final int xSize;
    public final int ySize;
    public final ResourceLocation guiTexture;
    public final int textureXSize;
    public final int textureYSize;

    ChestType(int size, int rowLength, String modelTexture, Class<? extends MithrilChestTileEntity> clazz, String itemName, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize)
    {
        this.name = this.name().toLowerCase();
        this.size = size;
        this.rowLength = rowLength;
        this.modelTexture = modelTexture;
        this.clazz = clazz;
        this.itemName = itemName;
        this.xSize = xSize;
        this.ySize = ySize;
        this.guiTexture = guiTexture;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    public int getRowCount()
    {
        return this.size / this.rowLength;
    }

    public static ChestType get(ResourceLocation resourceLocation)
    {
        switch (resourceLocation.toString())
        {
            case (EpicMod.MOD_ID + ":mithril_chest"):
                return MITHRIL;
            default:
                return WOOD;
        }
    }

    public static BlockState get(ChestType type)
    {
        switch (type)
        {
            case MITHRIL:
                return EpicBlocks.mithrilChest.getDefaultState();
            default:
                return null;
        }
    }

    public MithrilChestTileEntity makeEntity()
    {
        switch (this)
        {
            case MITHRIL:
                return new MithrilChestTileEntity();
            default:
                return null;
        }
    }
}
