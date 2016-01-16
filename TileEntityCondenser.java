package net.minecraft.src;

public class TileEntityCondenser extends TileEntity implements IInventory
{
    private ItemStack CondenserItemStacks[];
    public int CondenserBurnTime;
    public int currentItemBurnTime;
    public int CondenserCookTime;

    public TileEntityCondenser()
    {
        CondenserItemStacks = new ItemStack[3];
        CondenserBurnTime = 0;
        currentItemBurnTime = 0;
        CondenserCookTime = 0;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return CondenserItemStacks.length;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int i)
    {
        return CondenserItemStacks[i];
    }

    /**
     * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new
     * stack.
     */
    public ItemStack decrStackSize(int i, int j)
    {
        if (CondenserItemStacks[i] != null)
        {
            if (CondenserItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = CondenserItemStacks[i];
                CondenserItemStacks[i] = null;
                return itemstack;
            }

            ItemStack itemstack1 = CondenserItemStacks[i].splitStack(j);

            if (CondenserItemStacks[i].stackSize == 0)
            {
                CondenserItemStacks[i] = null;
            }

            return itemstack1;
        }
        else
        {
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int i)
    {
        if (CondenserItemStacks[i] != null)
        {
            ItemStack itemstack = CondenserItemStacks[i];
            CondenserItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        CondenserItemStacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName()
    {
        return "container.Condenser";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        CondenserItemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");

            if (byte0 >= 0 && byte0 < CondenserItemStacks.length)
            {
                CondenserItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        CondenserBurnTime = nbttagcompound.getShort("BurnTime");
        CondenserCookTime = nbttagcompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(CondenserItemStacks[1]);
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setShort("BurnTime", (short)CondenserBurnTime);
        nbttagcompound.setShort("CookTime", (short)CondenserCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < CondenserItemStacks.length; i++)
        {
            if (CondenserItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                CondenserItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit()
    {
        return 64;
    }

    public int getCookProgressScaled(int i)
    {
        return (CondenserCookTime * i) / 200;
    }

    public int getBurnTimeRemainingScaled(int i)
    {
        if (currentItemBurnTime == 0)
        {
            currentItemBurnTime = 200;
        }

        return (CondenserBurnTime * i) / currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return CondenserBurnTime > 0;
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    public void updateEntity()
    {
        boolean flag = CondenserBurnTime > 0;
        boolean flag1 = false;

        if (CondenserBurnTime > 0)
        {
            CondenserBurnTime--;
        }

        if (!worldObj.isRemote)
        {
            if (CondenserBurnTime == 0 && canSmelt())
            {
                currentItemBurnTime = CondenserBurnTime = getItemBurnTime(CondenserItemStacks[1]);

                if (CondenserBurnTime > 0)
                {
                    flag1 = true;

                    if (CondenserItemStacks[1] != null)
                    {
                        if (CondenserItemStacks[1].getItem().func_46056_k())
                        {
                            CondenserItemStacks[1] = new ItemStack(CondenserItemStacks[1].getItem().setFull3D());
                        }
                        else
                        {
                            CondenserItemStacks[1].stackSize--;
                        }

                        if (CondenserItemStacks[1].stackSize == 0)
                        {
                            CondenserItemStacks[1] = null;
                        }
                    }
                }
            }

            if (isBurning() && canSmelt())
            {
                CondenserCookTime++;

                if (CondenserCookTime == 200)
                {
                    CondenserCookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            }
            else
            {
                CondenserCookTime = 0;
            }

            if (flag != (CondenserBurnTime > 0))
            {
                flag1 = true;
                BlockPhazonCondenser.updateCondenserBlockState(CondenserBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
            }
        }

        if (flag1)
        {
            onInventoryChanged();
        }
    }

    private boolean canSmelt()
    {
        if (CondenserItemStacks[0] == null)
        {
            return false;
        }

        ItemStack itemstack = CondenserRecipes.solidifying().getSolidifyingResult(CondenserItemStacks[0].getItem().shiftedIndex);

        if (itemstack == null)
        {
            return false;
        }

        if (CondenserItemStacks[2] == null)
        {
            return true;
        }

        if (!CondenserItemStacks[2].isItemEqual(itemstack))
        {
            return false;
        }

        if (CondenserItemStacks[2].stackSize < getInventoryStackLimit() && CondenserItemStacks[2].stackSize < CondenserItemStacks[2].getMaxStackSize())
        {
            return true;
        }
        else
        {
            return CondenserItemStacks[2].stackSize < itemstack.getMaxStackSize();
        }
    }

    public void smeltItem()
    {
        if (!canSmelt())
        {
            return;
        }

        ItemStack itemstack = CondenserRecipes.solidifying().getSolidifyingResult(CondenserItemStacks[0].getItem().shiftedIndex);

        if (CondenserItemStacks[2] == null)
        {
            CondenserItemStacks[2] = itemstack.copy();
        }
        else if (CondenserItemStacks[2].itemID == itemstack.itemID)
        {
            CondenserItemStacks[2].stackSize += itemstack.stackSize;
        }

        if (CondenserItemStacks[0].getItem().func_46056_k())
        {
            CondenserItemStacks[0] = new ItemStack(CondenserItemStacks[0].getItem().setFull3D());
        }
        else
        {
            CondenserItemStacks[0].stackSize--;
        }

        if (CondenserItemStacks[0].stackSize <= 0)
        {
            CondenserItemStacks[0] = null;
        }
    }

    private int getItemBurnTime(ItemStack itemstack)
    {
        if (itemstack == null)
        {
            return 0;
        }

        int i = itemstack.getItem().shiftedIndex;

        if (i == mod_MetroidCraft.phazon.shiftedIndex)
        {
            return 200;
        }
        else
        {
            return ModLoader.addAllFuel(itemstack.itemID, itemstack.getItemDamage());
        }
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
        {
            return false;
        }
        else
        {
            return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
        }
    }

    public void openChest()
    {
    }

    public void closeChest()
    {
    }
}
