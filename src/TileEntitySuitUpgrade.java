package net.minecraft.src;

public class TileEntitySuitUpgrade extends TileEntity implements IInventory 
{
    private ItemStack suitItemStacks[];
	
    public TileEntitySuitUpgrade()
    {
        suitItemStacks = new ItemStack[3];
    }
    
	@Override
	public int getSizeInventory() 
	{
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int i) 
	{
		return suitItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) 
	{
		if (suitItemStacks[i] != null)
        {
            if (suitItemStacks[i].stackSize <= j)
            {
                ItemStack itemstack = suitItemStacks[i];
                suitItemStacks[i] = null;
                return itemstack;
            }

            ItemStack itemstack1 = suitItemStacks[i].splitStack(j);

            if (suitItemStacks[i].stackSize == 0)
            {
                suitItemStacks[i] = null;
            }

            return itemstack1;
        }
        else
        {
            return null;
        }	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) 
	{
		if (suitItemStacks[i] != null)
        {
            ItemStack itemstack = suitItemStacks[i];
            suitItemStacks[i] = null;
            return itemstack;
        }
        else
        {
            return null;
        }	
		
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack)
	{
		suitItemStacks[i] = itemstack;

        if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
	}

	@Override
	public String getInvName() 
	{
		return "suit inventory";
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer)
	{
		return true;
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
        suitItemStacks = new ItemStack[getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound.getByte("Slot");

            if (byte0 >= 0 && byte0 < suitItemStacks.length)
            {
                suitItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < suitItemStacks.length; i++)
        {
            if (suitItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte)i);
                suitItemStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }

        par1NBTTagCompound.setTag("Items", nbttaglist);
    }
	
	@Override
	public void openChest() 
	{
		
	}

	@Override
	public void closeChest() 
	{
		
	}

}
