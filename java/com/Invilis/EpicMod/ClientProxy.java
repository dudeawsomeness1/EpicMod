package com.Invilis.EpicMod;

import com.Invilis.blocks.ChestType;
import com.Invilis.renderer.EpicTileEntityRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends ServerProxy {
	public ClientProxy()
    {

    }

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void preInit()
    {
        super.preInit();

        for (ChestType type : ChestType.values())
        {
            if (type.clazz != null)
            {
                ClientRegistry.bindTileEntitySpecialRenderer(type.clazz, new EpicTileEntityRenderer());
            }
        }
    }

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }
}
