package net.minecraft.src;

import java.util.Random;

public class WorldProviderDarkAether extends WorldProviderBase {


	public WorldProviderDarkAether()
	{

	}

	public boolean renderClouds()
	{
		return true;
	}
	
	public boolean renderVoidFog()
	{
		return true;
	}

	public int getDimensionID() 
	{
		return 35;
	}
	
	public float setSunSize()
	{
		return 1.0F;
	}
	
	public float setMoonSize()
	{
		return 1F;
	}
	
	
	public String getMoonTexture()
	{
		//Change this later to the aether
		return "/metroid/aetherMoon.png";
	}
	
	public boolean renderStars()
	{
		return true;
	}
	
	public boolean darkenSkyDuringRain()
	{
		return true;
	}
	
	public String getRespawnMessage()
	{
		return "Leaving the Dark Dimension";
	}
	

	public void registerWorldChunkManager()
    {
            worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.darkAetherPlains, 1.0F, 0.0F);
    }
	
	public IChunkProvider getChunkProvider()
	{
		return new ChunkProviderDarkAether1(worldObj, worldObj.getSeed(), true);
	}
	
	public boolean canRespawnHere()
	{
		return false;
	}
	
	public boolean getWorldHasNoSky()
    {
        return true;
    }
	
    
	
}
