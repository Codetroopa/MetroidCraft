package net.minecraft.src;

public class BiomeGenDarkAetherPlains extends BiomeGenBase
{
    protected BiomeGenDarkAetherPlains(int par1)
    {
        super(par1);
        spawnableCreatureList.clear();
        spawnableMonsterList.clear();
        spawnableWaterCreatureList.clear();
        topBlock = (byte)mod_MetroidCraft.darkAetherGrassBlock.blockID;
        fillerBlock = (byte)Block.stone.blockID;
        minHeight = 0.1F;
        maxHeight = 0.3F;
        biomeDecorator.treesPerChunk = -999;
        biomeDecorator.flowersPerChunk = 4;
        biomeDecorator.grassPerChunk = 5;
        spawnableMonsterList.add(new SpawnListEntry(net.minecraft.src.EntityIng.class, 14, 4, 4));
    }
}
