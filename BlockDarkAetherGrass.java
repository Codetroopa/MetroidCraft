package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;

public class BlockDarkAetherGrass extends Block
{
	Minecraft minecraft = ModLoader.getMinecraftInstance();
	protected BlockDarkAetherGrass(int i, Material material)
	{
		super(i, Material.grass);
		setTickRandomly(true);

	}

	/**
	 * Returns the ID of the items to drop on destruction.
	 */
	public int idDropped(int i, Random random, int j)
	{
		return mod_MetroidCraft.darkAetherGrassBlock.blockID;
	}

	public int getBlockTextureFromSideAndMetadata (int i, int j)
	{

		if (i == 0)

			return mod_MetroidCraft.grassBottom;
		if (i == 1)

			return mod_MetroidCraft.grassTop;

		if (i == 2)

			return mod_MetroidCraft.grassSide;
		if (i == 3)

			return mod_MetroidCraft.grassSide;
		if (i == 4)

			return mod_MetroidCraft.grassSide;
		if (i == 5)

			return mod_MetroidCraft.grassSide;

		if (j ==1)
		{
			return 145;
		}

		return j != 2 ? 20 : 145;
	}
	
	public int tickRate()
    {
        return 40000;
    }
	
}