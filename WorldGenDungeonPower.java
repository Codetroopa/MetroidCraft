package net.minecraft.src;

import java.util.Random;
import java.io.PrintStream;

public class WorldGenDungeonPower 
{
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		//The block it spawns on
		if(world.getBlockId(x, y, z) != mod_MetroidCraft.darkAetherGrassBlock.blockID || world.getBlockId(x, y + 1, z) != 0)
		{
			return false;
		}
		
		int wall = Block.blockGold.blockID;
		int door = mod_MetroidCraft.powerDoorBlock.blockID;
		
		for (int y1 = 0; y1 < 10; y1++)
		{
			//door
			if (y1 > 1 && y1 <5)
			{
				world.setBlockWithNotify(x + 3, y + y1, z + 1, door);     
				world.setBlockWithNotify(x + 3, y + y1, z - 1, door);     
				world.setBlockWithNotify(x + 3, y + y1, z, door); 
			}
			//around the door
			if (y1 == 0 || y1 == 1 || y1 == 5 || y1 == 6)
			{
				world.setBlockWithNotify(x + 3, y + y1, z + 1, wall);     
				world.setBlockWithNotify(x + 3, y + y1, z - 1, wall);     
				world.setBlockWithNotify(x + 3, y + y1, z, wall); 
			}
			//outer walls
			if (y1 < 7)
			{
				world.setBlockWithNotify(x + 3, y + y1, z + 2, wall);     
				world.setBlockWithNotify(x + 3, y + y1, z - 2, wall); 		
				world.setBlockWithNotify(x + 3, y + y1, z + 3, wall);     
				world.setBlockWithNotify(x + 3, y + y1, z - 3, wall);  
				world.setBlockWithNotify(x - 3, y + y1, z - 1, wall);     
				world.setBlockWithNotify(x - 3, y + y1, z - 2, wall);     
				world.setBlockWithNotify(x - 3, y + y1, z - 3, wall);     
				world.setBlockWithNotify(x - 3, y + y1, z + 1, wall);     
				world.setBlockWithNotify(x - 3, y + y1, z + 2, wall);     
				world.setBlockWithNotify(x - 3, y + y1, z + 3, wall);     
				world.setBlockWithNotify(x - 3, y + y1, z, wall);     
				world.setBlockWithNotify(x, y + y1, z - 3, wall);     
				world.setBlockWithNotify(x, y + y1, z + 3, wall);     
				world.setBlockWithNotify(x + 1, y + y1, z - 3, wall);     
				world.setBlockWithNotify(x + 1, y + y1, z + 3, wall);     
				world.setBlockWithNotify(x - 1, y + y1, z - 3, wall);     
				world.setBlockWithNotify(x - 1, y + y1, z + 3, wall);     
				world.setBlockWithNotify(x + 2, y + y1, z - 3, wall);     
				world.setBlockWithNotify(x + 2, y + y1, z + 3, wall);     
				world.setBlockWithNotify(x - 2, y + y1, z - 3, wall);     
				world.setBlockWithNotify(x - 2, y + y1, z + 3, wall); 
			}
			//roof and floor
			if (y1 == 7 || y1 == 0)
			{
				world.setBlockWithNotify(x, y + y1, z, wall);     
		    	world.setBlockWithNotify(x + 1, y + y1, z, wall);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z + 1, wall);        		
		    	world.setBlockWithNotify(x, y + y1, z + 1, wall);        		
		    	world.setBlockWithNotify(x, y + y1, z - 1, wall);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z, wall);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z - 1, wall);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z - 1, wall);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z + 1, wall);
		    	world.setBlockWithNotify(x + 2, y + y1, z + 1, wall);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z + 2, wall);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z - 1, wall);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z - 2, wall);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z, wall);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z + 1, wall);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z + 2, wall);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z - 1, wall);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z - 2, wall);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z, wall);        		
		    	world.setBlockWithNotify(x, y + y1, z + 2, wall);        		
		    	world.setBlockWithNotify(x, y + y1, z - 2, wall);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z + 2, wall);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z - 2, wall);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z + 2, wall);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z - 2, wall);
			}
			//the empty air
			if (y1 > 0 && y1 < 7)
			{
				world.setBlockWithNotify(x, y + y1, z, 0);     
		    	world.setBlockWithNotify(x + 1, y + y1, z, 0);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z + 1, 0);        		
		    	world.setBlockWithNotify(x, y + y1, z + 1, 0);        		
		    	world.setBlockWithNotify(x, y + y1, z - 1, 0);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z, 0);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z - 1, 0);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z - 1, 0);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z + 1, 0);
		    	world.setBlockWithNotify(x + 2, y + y1, z + 1, 0);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z + 2, 0);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z - 1, 0);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z - 2, 0);        		
		    	world.setBlockWithNotify(x + 2, y + y1, z, 0);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z + 1, 0);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z + 2, 0);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z - 1, 0);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z - 2, 0);        		
		    	world.setBlockWithNotify(x - 2, y + y1, z, 0);        		
		    	world.setBlockWithNotify(x, y + y1, z + 2, 0);        		
		    	world.setBlockWithNotify(x, y + y1, z - 2, 0);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z + 2, 0);        		
		    	world.setBlockWithNotify(x + 1, y + y1, z - 2, 0);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z + 2, 0);        		
		    	world.setBlockWithNotify(x - 1, y + y1, z - 2, 0);
			}
		}

		world.setBlockWithNotify(x + 1, y, z + 4, wall);     
		world.setBlockWithNotify(x - 1, y, z + 4, wall);     
		world.setBlockWithNotify(x, y, z + 4, wall);     
		world.setBlockWithNotify(x - 1, y, z - 4, wall);     
		world.setBlockWithNotify(x + 1, y, z - 4, wall);     
		world.setBlockWithNotify(x, y, z - 4, wall);     
		world.setBlockWithNotify(x - 4, y, z - 1, wall);     
		world.setBlockWithNotify(x - 4, y, z + 1, wall);     
		world.setBlockWithNotify(x - 4, y, z, wall);     
		world.setBlockWithNotify(x + 4, y, z - 1, wall);     
		world.setBlockWithNotify(x + 4, y, z + 1, wall);     
		world.setBlockWithNotify(x + 4, y, z, wall);

		return true;

	}  
}
