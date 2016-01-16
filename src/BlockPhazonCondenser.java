package net.minecraft.src;

import java.util.Random;

public class BlockPhazonCondenser extends BlockContainer
{
    private Random CondenserRand;
    private final boolean isActive;
    private static boolean keepCondenserInventory = false;

    protected BlockPhazonCondenser(int i, boolean flag)
    {
        super(i, Material.ground);
        CondenserRand = new Random();
        isActive = flag;
    }

    public int idDropped(int i, Random random)
    {
        return mod_PhazonCondenser.CondenserIdle.blockID;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);
        setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(World world, int i, int j, int k)
    {
        if (world.isRemote)
        {
            return;
        }

        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;

        if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
        {
            byte0 = 3;
        }

        if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
        {
            byte0 = 2;
        }

        if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
        {
            byte0 = 5;
        }

        if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
        {
            byte0 = 4;
        }

        world.setBlockMetadataWithNotify(i, j, k, byte0);
    }

    public static void updateCondenserBlockState(boolean flag, World world, int i, int j, int k)
    {
        int l = world.getBlockMetadata(i, j, k);
        TileEntity tileentity = world.getBlockTileEntity(i, j, k);
        keepCondenserInventory = true;

        if (flag)
        {
            world.setBlockWithNotify(i, j, k, mod_PhazonCondenser.CondenserActive.blockID);
        }
        else
        {
            world.setBlockWithNotify(i, j, k, mod_PhazonCondenser.CondenserIdle.blockID);
        }

        keepCondenserInventory = false;
        world.setBlockMetadataWithNotify(i, j, k, l);

        if (tileentity != null)
        {
            tileentity.validate();
            world.setBlockTileEntity(i, j, k, tileentity);
        }
    }

    /**
     * Retrieves the block texture to use based on the display side. Args: iBlockAccess, x, y, z, side
     */
    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if (l == 1)
        {
            return mod_PhazonCondenser.CondenserTop;
        }

        if (l == 0)
        {
            return mod_PhazonCondenser.CondenserBottom;
        }

        int i1 = iblockaccess.getBlockMetadata(i, j, k);

        if (l != i1)
        {
            return mod_PhazonCondenser.CondenserSide;
        }

        if (isActive)
        {
            return mod_PhazonCondenser.CondenserActive2;
        }
        else
        {
            return mod_PhazonCondenser.CondenserFront;
        }
    }

    /**
     * Returns the block texture based on the side being looked at.  Args: side
     */
    public int getBlockTextureFromSide(int i)
    {
        if (i == 3)
        {
            return mod_PhazonCondenser.CondenserTop;
        }
        else
        {
            return mod_PhazonCondenser.CondenserTop;
        }
    }

    /**
     * Called upon block activation (left or right click on the block.). The three integers represent x,y,z of the
     * block.
     */
    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            TileEntityCondenser tileentitycondenser = (TileEntityCondenser)world.getBlockTileEntity(i, j, k);
            ModLoader.openGUI(entityplayer, new GuiCondenser(entityplayer.inventory, tileentitycondenser));
            return true;
        }
    }

    /**
     * Returns the TileEntity used by this block.
     */
    public TileEntity getBlockEntity()
    {
        return new TileEntityCondenser();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
        int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(i, j, k, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(i, j, k, 5);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(i, j, k, 3);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(i, j, k, 4);
        }
    }

    /**
     * Called whenever the block is removed.
     */
    public void onBlockRemoval(World world, int i, int j, int k)
    {
        if (!keepCondenserInventory)
        {
            TileEntityCondenser tileentitycondenser = (TileEntityCondenser)world.getBlockTileEntity(i, j, k);
            label0:

            for (int l = 0; l < tileentitycondenser.getSizeInventory(); l++)
            {
                ItemStack itemstack = tileentitycondenser.getStackInSlot(l);

                if (itemstack == null)
                {
                    continue;
                }

                float f = CondenserRand.nextFloat() * 0.8F + 0.1F;
                float f1 = CondenserRand.nextFloat() * 0.8F + 0.1F;
                float f2 = CondenserRand.nextFloat() * 0.8F + 0.1F;

                do
                {
                    if (itemstack.stackSize <= 0)
                    {
                        continue label0;
                    }

                    int i1 = CondenserRand.nextInt(21) + 10;

                    if (i1 > itemstack.stackSize)
                    {
                        i1 = itemstack.stackSize;
                    }

                    itemstack.stackSize -= i1;
                    EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
                    float f3 = 0.05F;
                    entityitem.motionX = (float)CondenserRand.nextGaussian() * f3;
                    entityitem.motionY = (float)CondenserRand.nextGaussian() * f3 + 0.2F;
                    entityitem.motionZ = (float)CondenserRand.nextGaussian() * f3;
                    world.spawnEntityInWorld(entityitem);
                }
                while (true);
            }
        }

        super.onBlockRemoval(world, i, j, k);
    }
}
