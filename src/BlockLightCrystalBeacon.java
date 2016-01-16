package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;

public class BlockLightCrystalBeacon extends BlockContainer
{
	Minecraft minecraft = ModLoader.getMinecraftInstance();
	int z;
	int x;
	private Class TestEntityClass;
	
	
	public void onUpdate()
	{
		Minecraft minecraft = ModLoader.getMinecraftInstance();
		Entity entity = minecraft.thePlayer;

		double x1 = entity.posX;
		double z1 = entity.posZ;
		int x2 = (int)x1;
		int z2 = (int)z1;
		x = x2;
		z = z2;
	}

	protected BlockLightCrystalBeacon(int i,Class tClass)
	{
		super(i,Material.wood);
		TestEntityClass = tClass;  
		setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 1.0F, 0.7F);
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

	public int idDropped(int i, Random random, int j)
	{
		return mod_MetroidCraft.lightCrystal.shiftedIndex;
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
	
	public void onBlockRemoval(World world, int xTile, int yTile, int zTile)
    {
		if (minecraft.thePlayer.worldObj.getBiomeGenForCoords(x, z) == BiomeGenBase.darkAetherPlains && world.getBlockId(xTile, yTile - 1, zTile) != mod_MetroidCraft.darkAetherGrassBlock.blockID)
		{
			//First Layer
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	
	    	//Second Layer
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);
	    	
	    	//Third Layer
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile + 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile - 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile + 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile - 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);        		
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);  
	    	
	    	//Fourth Layer
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile + 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile + 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile - 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile - 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile - 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile - 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile + 2, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile + 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile - 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile + 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile - 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile + 3, mod_MetroidCraft.darkAetherGrassBlock.blockID);
	    	
	    	//Fifth Layer
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 4, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 4, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 4, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 4, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 4, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 4, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 4, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 4, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile - 4, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 4, yTile - 1, zTile - 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 4, yTile - 1, zTile + 1, mod_MetroidCraft.darkAetherGrassBlock.blockID);     
	    	ModLoader.getMinecraftInstance().theWorld.setBlockWithNotify(xTile + 4, yTile - 1, zTile, mod_MetroidCraft.darkAetherGrassBlock.blockID);
		}
		
    }

}
