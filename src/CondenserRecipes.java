package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class CondenserRecipes
{
    private static final CondenserRecipes solidifyingBase = new CondenserRecipes();
    private Map solidifyingList;

    public static final CondenserRecipes solidifying()
    {
        return solidifyingBase;
    }

    private CondenserRecipes()
    {
        solidifyingList = new HashMap();
        addSolidifying(mod_MetroidCraft.phazon.shiftedIndex, new ItemStack(mod_MetroidCraft.phazonCondensed, 1));
        addSolidifying(Item.coal.shiftedIndex, new ItemStack(mod_MetroidCraft.infusedCoal, 1));
    }

    public void addSolidifying(int i, ItemStack itemstack)
    {
        solidifyingList.put(Integer.valueOf(i), itemstack);
    }

    public ItemStack getSolidifyingResult(int i)
    {
        return (ItemStack)solidifyingList.get(Integer.valueOf(i));
    }

    public Map getSolidifyingList()
    {
        return solidifyingList;
    }
}
