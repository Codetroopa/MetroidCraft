package net.minecraft.src;

import java.util.Random;

import net.minecraft.client.Minecraft;

public class BlockPowerDoor extends BlockContainer
{
	
	int z;
	int x;
	private Class TestEntityClass;
	
    protected BlockPowerDoor(int i, Class tClass)
    {
        super(i, Material.rock);
        setTickRandomly(true);
		TestEntityClass = tClass;  
		setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 1.0F);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int i, Random random, int j)
    {
        return 0;
    }

	public TileEntity getBlockEntity()
	{
		try
		{
			return (TileEntity)TestEntityClass.newInstance();
		}
		catch(Exception exception)
		{
			throw new RuntimeException(exception);
		}
	}

	public int quanityDropped(Random random){
		return 1;
	}

	public int getRenderType()
	{
		return -1;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

}
