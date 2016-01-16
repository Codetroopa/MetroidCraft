package net.minecraft.src;

import java.util.Random;

public class BlockLight extends Block
{
    protected BlockLight(int i, Material material)
    {
        super(i, Material.rock);
        setTickRandomly(true);
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    public int idDropped(int i, Random random, int j)
    {
        return this.blockID;
    }

    /**
     * Called right before the block is destroyed by a player.  Args: world, x, y, z, metaData
	 */
    
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        if ((par1World.worldProvider.worldType == 0 ||  par1World.worldProvider.worldType == 35)&& par1World.getBlockId(par2, par3 - 1, par4) == mod_MetroidCraft.lightBlock.blockID && mod_MetroidCraft.darkAetherPortal.tryToCreatePortal(par1World, par2, par3, par4))
        {
            return;
        }
    }
}