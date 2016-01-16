package net.minecraft.src;

import java.util.List;
import java.util.Random;

import net.minecraft.client.Minecraft;

public class EntityPowerBeam extends Entity
{
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private int inData;
    private boolean inGround;
    public boolean doesArrowBelongToPlayer;
    public int arrowShake;
    public Entity shootingEntity;
    private int ticksInGround;
    private int ticksInAir;
    private double damage;
    private int field_46027_au;
    public boolean arrowCritical;
    public boolean beaconActive;

    public EntityPowerBeam(World world)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inData = 0;
        inGround = false;
        doesArrowBelongToPlayer = false;
        arrowShake = 0;
        ticksInAir = 0;
        damage = 3.5D;
        arrowCritical = false;
        setSize(0.5F, 0.5F);
    }

    public EntityPowerBeam(World world, double d, double d1, double d2)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inData = 0;
        inGround = false;
        doesArrowBelongToPlayer = false;
        arrowShake = 0;
        ticksInAir = 0;
        damage = 3.5D;
        arrowCritical = false;
        setSize(0.5F, 0.5F);
        setPosition(d, d1, d2);
        yOffset = 0.0F;
    }

    public EntityPowerBeam(World world, EntityLiving entityliving, EntityLiving entityliving1, float f, float f1)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inData = 0;
        inGround = false;
        doesArrowBelongToPlayer = false;
        arrowShake = 0;
        ticksInAir = 0;
        damage = 3.5D;
        arrowCritical = false;
        shootingEntity = entityliving;
        doesArrowBelongToPlayer = entityliving instanceof EntityPlayer;
        posY = (entityliving.posY + (double)entityliving.getEyeHeight()) - 0.10000000149011612D;
        double d = entityliving1.posX - entityliving.posX;
        double d1 = (entityliving1.posY + (double)entityliving1.getEyeHeight()) - 0.69999998807907104D - posY;
        double d2 = entityliving1.posZ - entityliving.posZ;
        double d3 = MathHelper.sqrt_double(d * d + d2 * d2);

        if (d3 < 9.9999999999999995E-008D)
        {
            return;
        }
        else
        {
            float f2 = (float)((Math.atan2(d2, d) * 180D) / Math.PI) - 90F;
            float f3 = (float)(-((Math.atan2(d1, d3) * 180D) / Math.PI));
            double d4 = d / d3;
            double d5 = d2 / d3;
            setLocationAndAngles(entityliving.posX + d4, posY, entityliving.posZ + d5, f2, f3);
            yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            setArrowHeading(d, d1 + (double)f4, d2, f, f1);
            return;
        }
    }

    public EntityPowerBeam(World world, EntityLiving entityliving, float f)
    {
        super(world);
        xTile = -1;
        yTile = -1;
        zTile = -1;
        inTile = 0;
        inData = 0;
        inGround = false;
        doesArrowBelongToPlayer = false;
        arrowShake = 0;
        ticksInAir = 0;
        damage = 3.5D;
        arrowCritical = false;
        shootingEntity = entityliving;
        doesArrowBelongToPlayer = entityliving instanceof EntityPlayer;
        setSize(0.5F, 0.5F);
        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
        posX -= MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * 0.16F;
        setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);
        motionZ = MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);
        motionY = -MathHelper.sin((rotationPitch / 180F) * (float)Math.PI);
        setArrowHeading(motionX, motionY, motionZ, f * 1.5F, 1.0F);
    }

    protected void entityInit()
    {
    }

    public static DamageSource causePowerBeamDamage(EntityPowerBeam entitypowerbeam, Entity entity)
    {
        return (new EntityDamageSourceIndirect("fireball", entitypowerbeam, entity)).setFireDamage().setProjectile();
    }

    public void setArrowHeading(double d, double d1, double d2, float f, float f1)
    {
        float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        motionX = d;
        motionY = d1;
        motionZ = d2;
        float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / Math.PI);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / Math.PI);
        ticksInGround = 0;
    }

    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    public void setVelocity(double d, double d1, double d2)
    {
        motionX = d;
        motionY = d1;
        motionZ = d2;

        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(d * d + d2 * d2);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch;
            prevRotationYaw = rotationYaw;
            setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
            ticksInGround = 0;
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        Minecraft minecraft = ModLoader.getMinecraftInstance();
		Entity entity2 = minecraft.thePlayer;

		double x1 = entity2.posX;
		double z1 = entity2.posZ;
		int x = (int)x1;
		int z = (int)z1;


        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / Math.PI);
        }

        int i = worldObj.getBlockId(xTile, yTile, zTile);

        if (i > 0)
        {
            Block.blocksList[i].setBlockBoundsBasedOnState(worldObj, xTile, yTile - 1, zTile);
            AxisAlignedBB axisalignedbb = Block.blocksList[i].getCollisionBoundingBoxFromPool(worldObj, xTile, yTile, zTile);

            if (axisalignedbb != null && axisalignedbb.isVecInside(Vec3D.createVector(posX, posY, posZ)))
            {
                inGround = true;
            }
        }

        if (arrowShake > 0)
        {
            arrowShake--;
        }

        if (inGround)
        {
            int j = worldObj.getBlockId(xTile, yTile, zTile);
            int k = worldObj.getBlockMetadata(xTile, yTile, zTile);
            
            if (j == mod_MetroidCraft.powerDoorBlock.blockID)
            {
            	if (minecraft.theWorld.getBlockId(xTile, yTile, zTile) == mod_MetroidCraft.powerDoorBlock.blockID && minecraft.theWorld.getBlockId(xTile, yTile + 1, zTile) == mod_MetroidCraft.powerDoorBlock.blockID 
            		&& minecraft.theWorld.getBlockId(xTile, yTile - 1, zTile) == mod_MetroidCraft.powerDoorBlock.blockID && minecraft.theWorld.getBlockId(xTile, yTile, zTile + 1) == mod_MetroidCraft.powerDoorBlock.blockID
            		&& minecraft.theWorld.getBlockId(xTile, yTile, zTile - 1) == mod_MetroidCraft.powerDoorBlock.blockID)
            	{
            		Entity entitySpawn = new EntityIng(ModLoader.getMinecraftInstance().theWorld);
            		minecraft.theWorld.setBlockWithNotify(xTile, yTile, zTile, 0); 
            		minecraft.theWorld.setBlockWithNotify(xTile, yTile, zTile + 1, 0);        		
                	minecraft.theWorld.setBlockWithNotify(xTile, yTile, zTile - 1,0);        		
                	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 1, 0);        		
                	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 1, 0);        		
                	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile, 0);        		
                	minecraft.theWorld.setBlockWithNotify(xTile, yTile + 1, zTile, 0);        		
                	minecraft.theWorld.setBlockWithNotify(xTile, yTile + 1, zTile - 1, 0);        		
                	minecraft.theWorld.setBlockWithNotify(xTile, yTile + 1, zTile + 1, 0);
                	double xTile1 = (int)xTile;
                	double yTile1 = (int)yTile;
                	double zTile1 = (int)zTile;   
                	setDead();
            		entitySpawn.setPosition(xTile1, yTile1, zTile1);
                	minecraft.theWorld.spawnEntityInWorld(entitySpawn); 
            	}
            }
            if (j == mod_MetroidCraft.lightCrystalBeacon1.blockID && minecraft.thePlayer.worldObj.getBiomeGenForCoords(x, z) == BiomeGenBase.darkAetherPlains)
            {
            	//First Layer
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);     
            	
            	//Second Layer
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);
            	
            	//Third Layer
            	minecraft.theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile + 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile - 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile + 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile - 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 2, mod_MetroidCraft.energyShieldBlock.blockID);        		
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 2, mod_MetroidCraft.energyShieldBlock.blockID);  
            	
            	//Fourth Layer
            	minecraft.theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile + 2, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile + 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile - 2, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile - 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 3, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile - 2, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile - 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile + 2, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile + 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 3, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile - 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 2, yTile - 1, zTile + 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile - 3, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 2, yTile - 1, zTile + 3, mod_MetroidCraft.energyShieldBlock.blockID);
            	
            	//Fifth Layer
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile + 4, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile + 4, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile + 4, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 1, yTile - 1, zTile - 4, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 1, yTile - 1, zTile - 4, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile, yTile - 1, zTile - 4, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 4, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 4, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile - 4, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 4, yTile - 1, zTile - 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 4, yTile - 1, zTile + 1, mod_MetroidCraft.energyShieldBlock.blockID);     
            	minecraft.theWorld.setBlockWithNotify(xTile + 4, yTile - 1, zTile, mod_MetroidCraft.energyShieldBlock.blockID);     

            	setDead();
            }
            if (j == mod_MetroidCraft.lightCrystalBeacon1.blockID && minecraft.thePlayer.worldObj.getBiomeGenForCoords(x, z) != BiomeGenBase.darkAetherPlains)
            {
            	setDead();
            }
            if (j != inTile || k != inData)
            {
                inGround = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                ticksInGround = 0;
                ticksInAir = 0;
                return;
            }

            ticksInGround++;

            if (ticksInGround == 200)
            {
                setDead();
            }

            return;
        }

        ticksInAir++;
        Vec3D vec3d = Vec3D.createVector(posX, posY, posZ);
        Vec3D vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);
        MovingObjectPosition movingobjectposition = worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
        vec3d = Vec3D.createVector(posX, posY, posZ);
        vec3d1 = Vec3D.createVector(posX + motionX, posY + motionY, posZ + motionZ);

        if (movingobjectposition != null)
        {
            vec3d1 = Vec3D.createVector(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }

        Entity entity = null;
        List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
        double d = 0.0D;

        for (int l = 0; l < list.size(); l++)
        {
            Entity entity1 = (Entity)list.get(l);

            if (!entity1.canBeCollidedWith() || entity1 == shootingEntity && ticksInAir < 5)
            {
                continue;
            }

            float f5 = 0.3F;
            AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f5, f5, f5);
            MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3d, vec3d1);

            if (movingobjectposition1 == null)
            {
                continue;
            }

            double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);

            if (d1 < d || d == 0.0D)
            {
                entity = entity1;
                d = d1;
            }
        }

        if (entity != null)
        {
            movingobjectposition = new MovingObjectPosition(entity);
        }

        if (movingobjectposition != null)
        {
            if (movingobjectposition.entityHit != null)
            {
                float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                int j1 = (int)Math.ceil((double)f1 * damage);

                if (arrowCritical)
                {
                    j1 += rand.nextInt(j1 / 2 + 2);
                }

                DamageSource damagesource = null;

                if (shootingEntity == null)
                {
                    damagesource = causePowerBeamDamage(this, this);
                }
                else
                {
                    damagesource = causePowerBeamDamage(this, shootingEntity);
                }

                if (isBurning())
                {
                    movingobjectposition.entityHit.setFire(5);
                }

                if (movingobjectposition.entityHit.attackEntityFrom(damagesource, j1))
                {
                    if (movingobjectposition.entityHit instanceof EntityLiving)
                    {
                        ((EntityLiving)movingobjectposition.entityHit).arrowHitTempCounter++;

                        if (field_46027_au > 0)
                        {
                            float f7 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);

                            if (f7 > 0.0F)
                            {
                                movingobjectposition.entityHit.addVelocity((motionX * (double)field_46027_au * 0.60000002384185791D) / (double)f7, 0.10000000000000001D, (motionZ * (double)field_46027_au * 0.60000002384185791D) / (double)f7);
                            }
                        }
                    }

                    worldObj.playSoundAtEntity(this, "metroid.powerBeamHit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
                    setDead();
                }
                else
                {
                    motionX *= -0.10000000149011612D;
                    motionY *= -0.10000000149011612D;
                    motionZ *= -0.10000000149011612D;
                    rotationYaw += 180F;
                    prevRotationYaw += 180F;
                    ticksInAir = 0;
                }
            }
            else
            {
                xTile = movingobjectposition.blockX;
                yTile = movingobjectposition.blockY;
                zTile = movingobjectposition.blockZ;
                inTile = worldObj.getBlockId(xTile, yTile, zTile);
                inData = worldObj.getBlockMetadata(xTile, yTile, zTile);
                motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
                motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
                motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
                float f2 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
                posX -= (motionX / (double)f2) * 0.05000000074505806D;
                posY -= (motionY / (double)f2) * 0.05000000074505806D;
                posZ -= (motionZ / (double)f2) * 0.05000000074505806D;
                worldObj.playSoundAtEntity(this, "metroid.powerBeamHit", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
                inGround = true;
                arrowShake = 7;
                arrowCritical = false;
            }
        }

        if (arrowCritical)
        {
            for (int i1 = 0; i1 < 4; i1++)
            {
                worldObj.spawnParticle("sasdasd", posX + (motionX * (double)i1) / 4D, posY + (motionY * (double)i1) / 4D, posZ + (motionZ * (double)i1) / 4D, -motionX, -motionY + 0.20000000000000001D, -motionZ);
            }
        }

        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float f3 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / Math.PI);

        for (rotationPitch = (float)((Math.atan2(motionY, f3) * 180D) / Math.PI); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }

        for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }

        for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }

        for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }

        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f4 = 0.99F;
        float f6 = 0.05F;

        if (isInWater())
        {
            for (int k1 = 0; k1 < 4; k1++)
            {
                float f8 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * (double)f8, posY - motionY * (double)f8, posZ - motionZ * (double)f8, motionX, motionY, motionZ);
            }

            f4 = 0.8F;
        }

        motionX *= f4;
        motionY *= f4;
        motionZ *= f4;
        motionY -= f6;
        setPosition(posX, posY, posZ);
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("xTile", (short)xTile);
        nbttagcompound.setShort("yTile", (short)yTile);
        nbttagcompound.setShort("zTile", (short)zTile);
        nbttagcompound.setByte("inTile", (byte)inTile);
        nbttagcompound.setByte("inData", (byte)inData);
        nbttagcompound.setByte("shake", (byte)arrowShake);
        nbttagcompound.setByte("inGround", (byte)(inGround ? 1 : 0));
        nbttagcompound.setBoolean("player", doesArrowBelongToPlayer);
        nbttagcompound.setDouble("damage", damage);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        xTile = nbttagcompound.getShort("xTile");
        yTile = nbttagcompound.getShort("yTile");
        zTile = nbttagcompound.getShort("zTile");
        inTile = nbttagcompound.getByte("inTile") & 0xff;
        inData = nbttagcompound.getByte("inData") & 0xff;
        arrowShake = nbttagcompound.getByte("shake") & 0xff;
        inGround = nbttagcompound.getByte("inGround") == 1;
        doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");

        if (nbttagcompound.hasKey("damage"))
        {
            damage = nbttagcompound.getDouble("damage");
        }
    }

    public float getShadowSize()
    {
        return 0.0F;
    }

    public void setDamage(double d)
    {
        damage = d;
    }

    public double getDamage()
    {
        return damage;
    }

    public void func_46023_b(int i)
    {
        field_46027_au = i;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    public boolean canAttackWithItem()
    {
        return false;
    }
}
