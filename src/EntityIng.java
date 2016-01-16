package net.minecraft.src;

import java.util.Random;

public class EntityIng extends EntityMob
{
	public Entity aetherInvulnerable = this;
    public EntityIng(World world)
    {
        super(world);
        texture = "/metroid/ingDone.png";
        setSize(1.1F, 2.1F);
        moveSpeed = 0.8F;
    }

    protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(16, new Byte((byte)0));
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();

        if (!worldObj.isRemote)
        {
            func_40148_a(isCollidedHorizontally);
        }
    }

    public int getMaxHealth()
    {
        return 24;
    }

    /**
     * Returns the Y offset from the entity's position for any entity riding this one.
     */
    public double getMountedYOffset()
    {
        return (double)height * 0.75D - 0.5D;
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        return true;
    }

    /**
     * Finds the closest player within 16 blocks to attack, or null if this Entity isn't interested in attacking
     * (Animals, Spiders at day, peaceful PigZombies).
     */
    protected Entity findPlayerToAttack()
    {
        double d = 24D;
        return worldObj.getClosestVulnerablePlayerToEntity(this, d);
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound()
    {
        return "mob.spider";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound()
    {
        return "mob.spider";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound()
    {
        return "mob.spiderdeath";
    }

    /**
     * Basic mob attack. Default to touch of death in EntityCreature. Overridden by each mob to define their attack.
     */
    protected void attackEntity(Entity entity, float f)
    {
        float f1 = getBrightness(1.0F);

        if (rand.nextInt(10) == 0)
        {
            if (onGround)
            {
                double d = entity.posX - posX;
                double d1 = entity.posZ - posZ;
                float f2 = MathHelper.sqrt_double(d * d + d1 * d1);
                motionX = (d / (double)f2) * 0.5D * 0.80000001192092896D + motionX * 0.20000000298023224D;
                motionZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + motionZ * 0.20000000298023224D;
                motionY = 0.40000000596046448D;
            }
        }
        else
        {
            super.attackEntity(entity, f);
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected int getDropItemId()
    {
        return mod_MetroidCraft.phazon.shiftedIndex;
    }

    /**
     * Drop 0-2 items of this living's type
     */
    protected void dropFewItems(boolean flag, int i)
    {
        super.dropFewItems(flag, i);

        if (flag && (rand.nextInt(3) == 0 || rand.nextInt(1 + i) > 0))
        {
            dropItem(mod_MetroidCraft.darkEnergy.shiftedIndex, 1);
        }
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder()
    {
        return func_40149_l_();
    }

    /**
     * Sets the Entity inside a web block.
     */
    public void setInWeb()
    {
    }

    public float spiderScaleAmount()
    {
        return 1.0F;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    public boolean isPotionApplicable(PotionEffect potioneffect)
    {
        if (potioneffect.getPotionID() == Potion.poison.id)
        {
            return false;
        }
        else
        {
            return super.isPotionApplicable(potioneffect);
        }
    }

    public boolean func_40149_l_()
    {
        return (dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }

    public void func_40148_a(boolean flag)
    {
        byte byte0 = dataWatcher.getWatchableObjectByte(16);

        if (flag)
        {
            byte0 |= 1;
        }
        else
        {
            byte0 &= 0xfe;
        }

        dataWatcher.updateObject(16, Byte.valueOf(byte0));
    }
}
