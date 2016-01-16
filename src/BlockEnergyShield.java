package net.minecraft.src;

import java.util.Random;

public class BlockEnergyShield extends Block
{
    protected BlockEnergyShield(int i, Material material)
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
    
    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
    	if (entity.getEntityString() == "Warrior Ing")
    	{
    		entity.attackEntityFrom(DamageSource.magic, 10);
    	}
    }
}
