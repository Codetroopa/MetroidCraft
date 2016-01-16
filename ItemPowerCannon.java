package net.minecraft.src;

public class ItemPowerCannon extends Item
{
    public ItemPowerCannon(int i)
    {
        super(i);
        maxStackSize = 1;
        setMaxDamage(-1);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int i)
    {
        boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, itemstack) > 0;

        if (flag || entityplayer.inventory.hasItem(mod_MetroidCraft.powerBeamCell.shiftedIndex))
        {
            int j = getMaxItemUseDuration(itemstack) - i;
            float f = (float)j / 20F;
            f = (f * f + f * 2.0F) / 3F;

            if ((double)f < 0.10000000000000001D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityPowerBeam entitypowerbeam = new EntityPowerBeam(world, entityplayer, f * 2.0F);

            if (f == 1.0F)
            {
                entitypowerbeam.arrowCritical = true;
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, itemstack);

            if (k > 0)
            {
                entitypowerbeam.setDamage(entitypowerbeam.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, itemstack);

            if (l > 0)
            {
                entitypowerbeam.func_46023_b(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, itemstack) > 0)
            {
                entitypowerbeam.setFire(100);
            }

            itemstack.damageItem(1, entityplayer);
            world.playSoundAtEntity(entitypowerbeam, "metroid.powerBeamFire", 1.0F, 1.0F);

            if (!flag)
            {
                entityplayer.inventory.consumeInventoryItem(mod_MetroidCraft.powerBeamCell.shiftedIndex);
            }
            else
            {
                entitypowerbeam.doesArrowBelongToPlayer = false;
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entitypowerbeam);
            }
        }
    }

    public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        return itemstack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack itemstack)
    {
        return 0x11940;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        if (entityplayer.capabilities.isCreativeMode || entityplayer.inventory.hasItem(mod_MetroidCraft.powerBeamCell.shiftedIndex))
        {
            entityplayer.setItemInUse(itemstack, getMaxItemUseDuration(itemstack));
            EntityPowerBeam entitypowerbeam = new EntityPowerBeam(world, entityplayer, 1.0F);
            world.playSoundAtEntity(entitypowerbeam, "metroid.powerBeamCharge", 1.0F, 1.0F);
        }
        else
        {
            EntityPowerBeam entitypowerbeam1 = new EntityPowerBeam(world, entityplayer, 1.0F);
            world.playSoundAtEntity(entitypowerbeam1, "metroid.noAmmo", 1.0F, 1.0F);
        }

        return itemstack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    public void onUpdate(ItemStack itemstack, World world, Entity entity, int i, boolean flag)
    {
        EntityPlayer entityplayer = (EntityPlayer)entity;
        ItemStack itemstack1 = entityplayer.inventory.getCurrentItem();

        if (entityplayer.isUsingItem() && itemstack1.itemID == mod_MetroidCraft.powerCannon.shiftedIndex)
        {
            int j = itemstack.getMaxItemUseDuration() - entityplayer.getItemInUseCount();

            if (j >= 18)
            {
                iconIndex = mod_MetroidCraft.powerCannon4;
            }
            else if (j > 13)
            {
                iconIndex = mod_MetroidCraft.powerCannon3;
            }
            else if (j > 0)
            {
                iconIndex = mod_MetroidCraft.powerCannon2;
            }
        }
        else
        {
            iconIndex = mod_MetroidCraft.powerCannon1;
        }
    }
}
