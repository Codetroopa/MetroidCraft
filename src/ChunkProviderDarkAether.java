package net.minecraft.src;

import java.util.Random;
import java.util.List;

public class ChunkProviderDarkAether extends ChunkProviderGenerate 
implements IChunkProvider
{
	private Random rand;
	private World worldObj;
	private MapGenBase caveGenerator;
	private MapGenBase ravineGenerator;
	private BiomeGenBase biomesForGeneration[];
	
	
	public ChunkProviderDarkAether(World world, long l)
	{
		super (world, l, true);
		worldObj = world;
		rand = new Random(l);
		caveGenerator = new MapGenCaves();
		ravineGenerator = new MapGenRavine();
		
	}
	
	public Chunk provideChunk(int par1, int par2)
	{
		rand.setSeed((long)par1 * 0x4f9939f508L + (long)par2 * 0x1ef1565bd5L);
		byte abyte0[] = new byte[32768];
		generateTerrain (par1, par2, abyte0);
		biomesForGeneration = worldObj.getWorldChunkManager().loadBlockGeneratorData(biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
		replaceBlocksForBiome(par1, par2, abyte0, biomesForGeneration);
		caveGenerator.generate(this, worldObj, par1, par2, abyte0);
		ravineGenerator.generate(this, worldObj, par1, par2, abyte0);
		Chunk chunk = new Chunk(worldObj, abyte0, par1, par2);
		chunk.generateSkylightMap();
		return chunk;
		
	}
	
	public void populate(IChunkProvider ichunkprovider, int i, int j)
	{
		BlockSand.fallInstantly = true;
		int k = i * 16;
		int l = j * 16;
		BiomeGenBase biomegenbase = worldObj.getWorldChunkManager().getBiomeGenAt(k + 16, l + 16);
		rand.setSeed(worldObj.getSeed());
		long l1 = (rand.nextLong()/ 2L) * 2L + 1L;
		long l2 = (rand.nextLong()/ 2L) * 2L + 1L;
		rand.setSeed((long) i * l1 + (long)j * 12 ^ worldObj.getSeed());
		boolean flag = false;
		if (!flag && rand.nextInt(4) == 0)
		{
			int i1 = k + rand.nextInt(16) + 8;
			int j2 = rand.nextInt(128);
			int k3 = l + rand.nextInt(16) + 8;
			(new WorldGenLakes(Block.lavaStill.blockID)).generate(worldObj, rand, i1, j2, k3);
			
		}
		
		if (!flag && rand.nextInt(8) == 0)
        {
			int j1 = k + rand.nextInt(16) + 8;
			int k2 = rand.nextInt(rand.nextInt(128 - 8) + 8);
			int l3 = l + rand.nextInt(16) + 8;
			
		if (k2 < 63 || rand.nextInt(10) == 0)
        {
				(new WorldGenLakes(Block.ice.blockID)).generate(worldObj, rand, j1, k2, l3);
        }
       
       
        }
        /*
         * for (int k1 = 0; k1 < 8; k1++)
        {
        int i3 = k + rand.nextInt(16) + 8;
        int i4 = rand.nextInt(128);
        int k4 = l + rand.nextInt(16) + 8;
        if (!(new WorldGenDungeons()).generate(worldObj, rand, i3, i4, k4));
        }
        */

        biomegenbase.decorate(worldObj, rand, k, l);
        k += 8;
        l += 8;
        BlockSand.fallInstantly = false;
        }

        //This has to do with strongholds, which I removed, so it just returns null.
        public ChunkPosition func_40376_a(World world, String s, int i, int j, int k)
        {
        return null;
        }
		
	
	
}
