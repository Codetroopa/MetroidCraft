package net.minecraft.src;
import java.util.Random;

public class DarkAetherLog extends Block
{

    protected DarkAetherLog(int i)
    {
        super(i, Material.wood);
        //blockIndexInTexture = 20;   - Not needed
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public int idDropped(int i, Random random, int j)
    {
        return mod_MetroidCraft.darkAetherLog.blockID;
    }

    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
    {
        super.harvestBlock(world, entityplayer, i, j, k, l);
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
        byte byte0 = 4;
        int l = byte0 + 1;
        if(world.checkChunksExist(i - l, j - l, k - l, i + l, j + l, k + l))
        {
            for(int i1 = -byte0; i1 <= byte0; i1++)
            {
                for(int j1 = -byte0; j1 <= byte0; j1++)
                {
                    for(int k1 = -byte0; k1 <= byte0; k1++)
                    {
                        int l1 = world.getBlockId(i + i1, j + j1, k + k1);
                        if(l1 != mod_MetroidCraft.darkAetherLeaf.blockID)  ///Leaf//////////////
                        {
                            continue;
                        }
                        int i2 = world.getBlockMetadata(i + i1, j + j1, k + k1);
                        if((i2 & 8) == 0)
                        {
                            world.setBlockMetadata(i + i1, j + j1, k + k1, i2 | 8);
                        }
                    }

                }

            }

        }
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)  ////////////////
    {
    	if(i == 0)
			return mod_MetroidCraft.logBottom;
		if(i == 1)
			return mod_MetroidCraft.logBottom;
		if(i == 2)
			return mod_MetroidCraft.logSide;
		if(i == 3)
			return mod_MetroidCraft.logSide;
		if(i == 4)
			return mod_MetroidCraft.logSide;
		if(i == 5)
			return mod_MetroidCraft.logSide;
		
        if(j == 1)
        {
            return 116;
        }
        return j != 2 ? 20 : 117;
    }

    protected int damageDropped(int i)
    {
        return i;
    }
}

