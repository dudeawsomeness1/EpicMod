package com.Invilis.merchant;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.Invilis.EpicMod.EpicMod;
import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.item.Item;
import net.minecraft.village.PointOfInterestType;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicProfessions {
	public static final VillagerProfession ELF_FARMER = null;
    @SubscribeEvent
    public static void registerVillagerProfessions(Register<VillagerProfession> event)
    {
        IForgeRegistry<VillagerProfession> registry = event.getRegistry();
        
        registry.register(villagerProfession("elf_farmer", PointOfInterestType.FARMER, ImmutableSet.of(), ImmutableSet.of()).setRegistryName(EpicMod.MOD_ID, "elf_farmer"));
    }
    
    public static VillagerProfession villagerProfession(String p1, PointOfInterestType p2, ImmutableSet<Item> p3, ImmutableSet<Block> p4)
    {
        try
        {
            Constructor<VillagerProfession> c = VillagerProfession.class.getDeclaredConstructor(String.class, PointOfInterestType.class, ImmutableSet.class, ImmutableSet.class);
            c.setAccessible(true);
            return c.newInstance(p1, p2, p3, p4);
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
}

/*
@EventBusSubscriber(modid = EpicMod.MOD_ID, bus = Bus.MOD)
@ObjectHolder(EpicMod.MOD_ID)
public class EpicPointOfInterestTypes
{
    public static final PointOfInterestType ELF_FARMER = null;
    
    @SubscribeEvent
    public static void registerPointOfInterestTypes(Register<PointOfInterestType> event)
    {
        IForgeRegistry<PointOfInterestType> registry = event.getRegistry();
        
        registry.register(pointOfInterestType("elf_farmer", EpicUtility.getAllStates(EpicBlocks.elfFarmerBlock), 1, 1).setRegistryName(EpicMod.MOD_ID, "elf_farmer"));
    }

	public static PointOfInterestType pointOfInterestType(String p1, Set<BlockState> p2, int p3, int p4)
    {
        try
        {
            //          Constructor<PointOfInterestType> c = (Constructor<PointOfInterestType>)PointOfInterestType.class.getDeclaredConstructors()[1];
            Constructor<PointOfInterestType> c = PointOfInterestType.class.getDeclaredConstructor(String.class, Set.class, Integer.TYPE, Integer.TYPE);
            c.setAccessible(true);
            return c.newInstance(p1, p2, p3, p4);
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (SecurityException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        
        return null;
    }
}

public static Set<BlockState> getAllStates(Block block)
{
    return ImmutableSet.copyOf(block.getStateContainer().getValidStates());
}
 * */
