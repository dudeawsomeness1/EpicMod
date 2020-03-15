package com.Invilis.inventory;

import com.Invilis.blocks.ChestType;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CustomChestContainer extends Container {
	private final IInventory inventory;

    private final ChestType chestType;

    private CustomChestContainer(ContainerType<?> containerType, int windowId, PlayerInventory playerInventory)
    {
        this(containerType, windowId, playerInventory, new Inventory(ChestType.WOOD.size), ChestType.WOOD);
    }

    public static CustomChestContainer createMithrilContainer(int windowId, PlayerInventory playerInventory)
    {
        return new CustomChestContainer(CustomChestContainerType.MITHRIL_CHEST, windowId, playerInventory, new Inventory(ChestType.MITHRIL.size), ChestType.MITHRIL);
    }

    public static CustomChestContainer createMithrilContainer(int windowId, PlayerInventory playerInventory, IInventory inventory)
    {
        return new CustomChestContainer(CustomChestContainerType.MITHRIL_CHEST, windowId, playerInventory, inventory, ChestType.MITHRIL);
    }
    
    public CustomChestContainer(ContainerType<?> containerType, int windowId, PlayerInventory playerInventory, IInventory inventory, ChestType chestType)
    {
        super(containerType, windowId);
        assertInventorySize(inventory, chestType.size);

        this.inventory = inventory;
        this.chestType = chestType;

        inventory.openInventory(playerInventory.player);

        for (int chestRow = 0; chestRow < chestType.getRowCount(); chestRow++)
        {
            for (int chestCol = 0; chestCol < chestType.rowLength; chestCol++)
            {
                this.addSlot(new Slot(inventory, chestCol + chestRow * chestType.rowLength, 12 + chestCol * 18, 18 + chestRow * 18));
            }
        }

        int leftCol = (chestType.xSize - 162) / 2 + 1;

        for (int playerInvRow = 0; playerInvRow < 3; playerInvRow++)
        {
            for (int playerInvCol = 0; playerInvCol < 9; playerInvCol++)
            {
                this.addSlot(new Slot(playerInventory, playerInvCol + playerInvRow * 9 + 9, leftCol + playerInvCol * 18, chestType.ySize - (4 - playerInvRow) * 18 - 10));
            }

        }

        for (int hotbarSlot = 0; hotbarSlot < 9; hotbarSlot++)
        {
            this.addSlot(new Slot(playerInventory, hotbarSlot, leftCol + hotbarSlot * 18, chestType.ySize - 24));
        }
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn)
    {
        return this.inventory.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.chestType.size)
            {
                if (!this.mergeItemStack(itemstack1, this.chestType.size, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.chestType.size, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn)
    {
        super.onContainerClosed(playerIn);
        this.inventory.closeInventory(playerIn);
    }

    @OnlyIn(Dist.CLIENT)
    public ChestType getChestType()
    {
        return this.chestType;
    }
}
