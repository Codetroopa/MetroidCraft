package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BlockFlower, World, WorldGenTaiga2, WorldGenForest, 
//            WorldGenTrees, WorldGenBigTree, WorldGenerator

public class DarkAetherSapling extends BlockFlower
{

    protected DarkAetherSapling(int i, int j)
    {
        super(i, j);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
    }
    
    protected boolean canThisPlantGrowOnThisBlockID(int i)
    {
        return i == mod_MetroidCraft.darkAetherGrassBlock.blockID;
    }
    
    
    public void updateTick(World world, int i, int j, int k, Random random)
    {
        if(world.isRemote)
        {
            return;
        }
        super.updateTick(world, i, j, k, random);
        
        if(world.getBlockLightValue(i, j + 1, k) >= 9 && random.nextInt(7) == 0)
        {
            int l = world.getBlockMetadata(i, j, k);
            if((l & 8) == 0)
            {
                world.setBlockMetadataWithNotify(i, j, k, l | 8);
            } else
            {
                growTree(world, i, j, k, random);
            }
        }
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        j &= 3;
        if(j == 1)
        {
            return blockIndexInTexture;  //63
        }
        if(j == 2)
        {
            return blockIndexInTexture; //79
        } else
        {
            //return super.getBlockTextureFromSideAndMetadata(i, j);
        	return blockIndexInTexture;
        }
    }

    public void growTree(World world, int i, int j, int k, Random random)
    {
        int l = world.getBlockMetadata(i, j, k) & 3;
        world.setBlock(i, j, k, 0);
        Object obj = null;
        obj = new WorldGenDarkAetherTree();
        /*if(l == 1)
        {
            obj = new WorldGenTaiga2(true);
        } else
        if(l == 2)
        {
            obj = new WorldGenForest(true);
        } else
        {
            obj = new WorldGenTrees(true);
            if(random.nextInt(10) == 0)
            {
                obj = new WorldGenBigTree(true);
            }
        }*/
        if(!((WorldGenerator) (obj)).generate(world, random, i, j, k))
        {
            world.setBlockAndMetadata(i, j, k, blockID, l);
        }
    }

    protected int damageDropped(int i)
    {
        return 0;
    }
}
