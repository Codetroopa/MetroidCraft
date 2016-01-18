package net.minecraft.src;

public class mod_PhazonCondenser extends BaseMod
{
    public static Block CondenserIdle;
    public static Block CondenserActive;
    public static int CondenserActive2 = ModLoader.addOverride("/terrain.png", "/metroid/phazonCondenserActive.png");
    public static int CondenserSide = ModLoader.addOverride("/terrain.png", "/metroid/phazonCondenserSide.png");
    public static int CondenserFront = ModLoader.addOverride("/terrain.png", "/metroid/phazonCondenserFront.png");
    public static int CondenserTop = ModLoader.addOverride("/terrain.png", "/metroid/phazonCondenserTop.png");
    public static int CondenserBottom = ModLoader.addOverride("/terrain.png", "/metroid/phazonCondenserBottom.png");

    public mod_PhazonCondenser()
    {
        CondenserIdle = (new BlockPhazonCondenser(141, false)).setHardness(2.0F).setStepSound(Block.soundStoneFootstep).setBlockName("Condenser").setRequiresSelfNotify();
        CondenserActive = (new BlockPhazonCondenser(142, true)).setHardness(2.0F).setStepSound(Block.soundStoneFootstep).setLightValue(0.875F).setBlockName("Condenser").setRequiresSelfNotify();
        ModLoader.registerBlock(CondenserActive);
        ModLoader.registerBlock(CondenserIdle);
        ModLoader.registerTileEntity(net.minecraft.src.TileEntityCondenser.class, "Condenser");
        ModLoader.addName(CondenserIdle, "Phazon Infuser");
        ModLoader.addRecipe(new ItemStack(CondenserIdle, 1), new Object[]
                {
                    "XXX", "XOX", "XXX", 'X', mod_MetroidCraft.phazon, 'O', Block.stoneOvenIdle
                });
    }

    public String getVersion()
    {
        return "1.2.5";
    }

    public void load()
    {
    }
}
