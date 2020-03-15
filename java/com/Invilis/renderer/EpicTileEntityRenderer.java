package com.Invilis.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.model.ChestModel;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import com.Invilis.EpicMod.EpicMod;
import com.Invilis.blocks.ChestType;
import com.Invilis.blocks.CustomChestBlock;
import com.Invilis.tileentity.MithrilChestTileEntity;
import com.mojang.blaze3d.platform.GlStateManager;

@OnlyIn(Dist.CLIENT)
public class EpicTileEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {
	private final ChestModel chestModel = new ChestModel();
    //private static ItemEntity customItem;
    //private Random random = new Random();
    //private ItemRenderer itemRenderer;
    //private static float[][] shifts = { { 0.3F, 0.45F, 0.3F }, { 0.7F, 0.45F, 0.3F }, { 0.3F, 0.45F, 0.7F }, { 0.7F, 0.45F, 0.7F }, { 0.3F, 0.1F, 0.3F }, { 0.7F, 0.1F, 0.3F }, { 0.3F, 0.1F, 0.7F }, { 0.7F, 0.1F, 0.7F }, { 0.5F, 0.32F, 0.5F } };

    @Override
    public void render(T tileEntityIn, double x, double y, double z, float partialTicks, int destroyStage)
    {
        GlStateManager.enableDepthTest();
        GlStateManager.depthFunc(515);
        GlStateManager.depthMask(true);

        MithrilChestTileEntity tileEntity = (MithrilChestTileEntity) tileEntityIn;

        BlockState blockstate = tileEntity.hasWorld() ? tileEntity.getBlockState() : (BlockState) tileEntity.getBlockToUse().getDefaultState().with(CustomChestBlock.FACING, Direction.SOUTH);
        ChestType chestType = ChestType.MITHRIL;
        ChestType actualType = CustomChestBlock.getTypeFromBlock(blockstate.getBlock());

        if (actualType != null)
        {
            chestType = actualType;
        }

        if (destroyStage >= 0)
        {
            this.bindTexture(DESTROY_STAGES[destroyStage]);
            GlStateManager.matrixMode(5890);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(4.0F, 4.0F, 1.0F);
            GlStateManager.translatef(0.0625F, 0.0625F, 0.0625F);
            GlStateManager.matrixMode(5888);
        }
        else
        {
            this.bindTexture(new ResourceLocation(EpicMod.MOD_ID, "textures/block/" + chestType.modelTexture));
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        }

        GlStateManager.pushMatrix();

        GlStateManager.enableRescaleNormal();
        GlStateManager.translatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
        GlStateManager.scalef(1.0F, -1.0F, -1.0F);

        float f = blockstate.get(CustomChestBlock.FACING).getHorizontalAngle();
        if ((double) Math.abs(f) > 1.0E-5D)
        {
            GlStateManager.translatef(0.5F, 0.5F, 0.5F);
            GlStateManager.rotatef(f, 0.0F, 1.0F, 0.0F);
            GlStateManager.translatef(-0.5F, -0.5F, -0.5F);
        }

        this.rotateChestLid(tileEntityIn, partialTicks, this.chestModel);
        this.chestModel.renderAll();
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();

        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (destroyStage >= 0)
        {
            GlStateManager.matrixMode(5890);
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
        }
    }

    private void rotateChestLid(T tileEntity, float partialTicks, ChestModel chestModel)
    {
        float f = ((IChestLid) tileEntity).getLidAngle(partialTicks);
        f = 1.0F - f;
        f = 1.0F - f * f * f;
        chestModel.getLid().rotateAngleX = -(f * ((float) Math.PI / 2F));
    }
}
