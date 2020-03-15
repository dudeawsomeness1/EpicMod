package com.Invilis.brewing;

import javax.annotation.Nonnull;

import com.Invilis.items.EpicItems;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraftforge.common.brewing.IBrewingRecipe;

public class AthelasTeaRecipe implements IBrewingRecipe {
    public AthelasTeaRecipe() {
    }

    @Override
    public boolean isInput(@Nonnull ItemStack stack) {
        //EpicMod.logger.info("IBrewingRecipe#isInput: return " + PotionUtils.getPotionFromItem(stack) + " == " + Potions.MUNDANE);
        return PotionUtils.getPotionFromItem(stack) == Potions.MUNDANE;
    }

    @Override
    public boolean isIngredient(@Nonnull ItemStack ingredient) {
        //EpicMod.logger.info("IBrewingRecipe#isIngredient: return " + ingredient.getItem() + " == " + INGREDIENT);
        return ingredient.getItem() == EpicItems.athelasLeaf;
    }

    @Nonnull
    @Override
    public ItemStack getOutput(@Nonnull ItemStack input, @Nonnull ItemStack ingredient) {
        /*EpicMod.logger.info("IBrewingRecipe#getOutput: isInput(input) = " + isInput(input)
                + " isIngredient(ingredient) = " + isIngredient(ingredient)
                + " -> return " + ((isInput(input) && isIngredient(ingredient))
                ? "OUTPUT (=" + new ItemStack(EpicItems.athelasTea) + ")" : "ItemStack.EMPTY"));
        EpicMod.logger.info("Target output: " + new ItemStack(EpicItems.athelasTea));*/
        if (isInput(input) && isIngredient(ingredient))
            return new ItemStack(EpicItems.athelasTea);
        return ItemStack.EMPTY;
    }
}
