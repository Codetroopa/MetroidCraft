package net.minecraft.src;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.util.Map;
import java.util.Random;
import net.minecraft.client.Minecraft;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import paulscode.sound.SoundSystem;

public class mod_MetroidCraft extends BaseMod
{
    Minecraft minecraft = ModLoader.getMinecraftInstance();
    
    //Music
    SoundManager sndManager = new SoundManager();
    public static String s;
  
	//Multi-Textures
	public static int grassTop = ModLoader.addOverride("/terrain.png", "/metroid/grassTop.png");
	public static int grassSide = ModLoader.addOverride("/terrain.png", "/metroid/grassSide.png");
	public static int grassBottom = ModLoader.addOverride("/terrain.png", "/metroid/grassBottom.png");
    public static int powerCannon4 = ModLoader.addOverride("/gui/items.png", "/metroid/powerCannon2.png");
    public static int powerCannon3 = ModLoader.addOverride("/gui/items.png", "/metroid/powerCannon3.png");
    public static int powerCannon2 = ModLoader.addOverride("/gui/items.png", "/metroid/powerCannon4.png");
    public static int powerCannon1 = ModLoader.addOverride("/gui/items.png", "/metroid/powerCannon1.png");
	public static int logSide = ModLoader.addOverride("/terrain.png", "/metroid/darkAetherLogSide.png");
	public static int logBottom = ModLoader.addOverride("/terrain.png", "/metroid/darkAetherLogBottom.png");
	
	//Random
	protected Random rand;
	
	//Counters and Other integers
	public static int counter = 0;
	public static int suitType = 15;
	
	//Blocks    
    public static final Block lightBlock;
    public static final BlockDarkAetherPortal darkAetherPortal = (BlockDarkAetherPortal)
    		(new BlockDarkAetherPortal(212).setStepSound(Block.soundWoodFootstep).setHardness(0.5F)
    		.setResistance(600000F).setBlockName("Dark Aether Portal")).setBlockUnbreakable();
    public static final Block phazonBlock;
    public static final Block pureBlock;
    public static final Block darkAetherGrassBlock;
    public static final Block darkAetherSapling;
    public static final Block darkAetherLog;
    public static final Block darkAetherLeaf;
    public static final Block lightCrystalBeacon1 = (new BlockLightCrystalBeacon(149, net.minecraft.src.TileEntityLightCrystalBeacon.class)).setHardness(1.0F).setResistance(1000F).setStepSound(Block.soundWoodFootstep).setBlockName("lightCrystal");
    public static final Block energyShieldBlock;
    public static final Block powerDoorBlock;

    //Items
    public static final Item powerSuitHelmet;
    public static final Item powerSuitChest;
    public static final Item powerSuitLegs;
    public static final Item powerSuitBoots;
    public static final Item darkSuitHelmet;
    public static final Item darkSuitChest;
    public static final Item darkSuitLegs;
    public static final Item darkSuitBoots;
    public static final Item phazon = (new Item(500)).setItemName("phazon").setMaxStackSize(64);
    public static final Item phazonCondensed = (new Item(501)).setItemName("phazonCondensed").setMaxStackSize(64);
    public static final Item infusedCoal = (new Item(506)).setItemName("infusedCoal").setMaxStackSize(64);
    public static final Item plasma = (new Item(507)).setItemName("plasma").setMaxStackSize(64);
    public static final Item emptyCell = (new Item(508)).setItemName("emptyCell").setMaxStackSize(64);
    public static final Item pureEnergy = (new Item(509)).setItemName("pureEnergy").setMaxStackSize(64);
    public static final Item plasmaEnergy = (new Item(510)).setItemName("plasmaEnergy").setMaxStackSize(64);
    public static final Item powerEnergy = (new Item(511)).setItemName("powerEnergy").setMaxStackSize(64);
    public static final Item powerBeamCell = (new Item(512)).setItemName("powerBeamCell").setMaxStackSize(64);
    public static final Item chargedPowder = (new Item(513)).setItemName("chargedPowder").setMaxStackSize(64);
    public static final Item powerCannon = (new ItemPowerCannon(514)).setItemName("Power Cannon").setMaxStackSize(1);
    public static final Item missileCannon = (new ItemMissileCannon(515)).setItemName("Missile Cannon").setMaxStackSize(1);
    public static final Item missile = (new Item(516)).setItemName("missile").setMaxStackSize(64);
    public static final Item lightEnergy = (new Item(517)).setItemName("lightEnergy").setMaxStackSize(64);
    public static final Item darkEnergy = (new Item(518)).setItemName("darkEnergy").setMaxStackSize(64);
    public static final Item lightCrystalBeacon = (new ItemReed(519, lightCrystalBeacon1)).setItemName("lightCrystalBeacon").setMaxStackSize(64);
    public static final Item lightCrystal = (new Item(520)).setItemName("lightCrystal").setMaxStackSize(64);
    public static final Item suitPowerup = (new ItemSuitPowerup(525)).setItemName("suitPowerup").setMaxStackSize(1);
    
    //Booleans
    public static boolean variaHelm = false;
    public static boolean variaBoots = false;
    public static boolean variaChest = false;
    public static boolean variaLegs = false;
    public static boolean variaSuit = false;
    public static boolean darkHelm = false;
    public static boolean darkBoots = false;
    public static boolean darkChest = false;
    public static boolean darkLegs = false;
    public static boolean darkSuit = false;
    public static boolean music = true;
        
    public void addRenderer(Map map)
    {
        map.put(net.minecraft.src.EntityPowerBeam.class, new RenderPowerBeam());
        map.put(net.minecraft.src.EntityMissile.class, new RenderMissile());
        map.put(net.minecraft.src.EntityIng.class, new RenderIng(new ModelIng(), 1.0F));
    }
    
    public mod_MetroidCraft()
    {
    	Entity entity = minecraft.thePlayer;
    	ModLoader.setInGameHook(this, true, true);
    	ModLoader.setInGUIHook(this, true, true);
        rand = new Random();
    }
    
    public boolean onTickInGame(float f, Minecraft minecraft)
    {
    	Minecraft minecraft1 = ModLoader.getMinecraftInstance();
    	Entity entity = minecraft1.thePlayer;
    	double x1;
    	double z1;
    	double y1;
    	int x;
    	int z;
    	int y;

    	//Boots
    	if (minecraft.thePlayer.inventory.armorItemInSlot(0) != null)
        {
            ItemStack itemstack0 = minecraft1.thePlayer.inventory.armorItemInSlot(0);
            mod_MetroidCraft mod_MetroidCraft1 = this;
            
            if (itemstack0.itemID == powerSuitBoots.shiftedIndex)
            {
            	variaBoots = true;
            }
            else
            {
        		variaBoots = false;

            }
            if (itemstack0.itemID == darkSuitBoots.shiftedIndex)
            {
            	darkBoots = true;
            }
            else
            {
            	darkBoots = false;
            }
    	
        }
    	else
    	{
    		variaBoots = false;
    		darkBoots = false;
    	}
    	
    	//Legs
    	if (minecraft.thePlayer.inventory.armorItemInSlot(1) != null)
        {
            ItemStack itemstack1 = minecraft1.thePlayer.inventory.armorItemInSlot(1);
            mod_MetroidCraft mod_MetroidCraft2 = this;
            
            if (itemstack1.itemID == powerSuitLegs.shiftedIndex)
            {          
            	variaLegs = true;
            }
            else
            {
        		variaLegs = false;

            }
            if (itemstack1.itemID == darkSuitLegs.shiftedIndex)
            {          
            	darkLegs = true;
            }
            else
            {
        		darkLegs = false;

            }
    	
        }
    	else
    	{
    		variaLegs = false;
    		darkLegs = false;
    	}
    	
    	//Chest
    	if (minecraft.thePlayer.inventory.armorItemInSlot(2) != null)
        {
            ItemStack itemstack2 = minecraft1.thePlayer.inventory.armorItemInSlot(2);
            mod_MetroidCraft mod_MetroidCraft3 = this;
            
            if (itemstack2.itemID == powerSuitChest.shiftedIndex)
            {
            	variaChest = true;
            }
            else
            {
        		variaChest = false;

            }
            if (itemstack2.itemID == darkSuitChest.shiftedIndex)
            {
            	darkChest = true;
            }
            else
            {
        		darkChest = false;

            }
    	
        }
    	else
    	{
    		variaChest = false;
    		darkChest = false;
    	}
    
    	//Helmet
    	if (minecraft.thePlayer.inventory.armorItemInSlot(3) != null)
        {
            ItemStack itemstack3 = minecraft1.thePlayer.inventory.armorItemInSlot(3);
            mod_MetroidCraft mod_MetroidCraft4 = this;
            
            if (itemstack3.itemID == powerSuitHelmet.shiftedIndex)
            {
            	minecraft.thePlayer.experienceLevel = 50;
            	variaHelm = true;
            }
            else
            {
        		variaHelm = false;

            }
            if (itemstack3.itemID == darkSuitHelmet.shiftedIndex)
            {
            	darkHelm = true;
            }
            else
            {
        		darkHelm = false;

            }
    	
        }
    	else
    	{
    		variaHelm = false;
    		darkHelm = false;
    	}
    	
    	//Varia Suit Check
    	if (variaHelm == true && variaChest == true && variaBoots == true && variaLegs == true)
    	{
    		variaSuit = true;
    		if (variaSuit = true)
    		{
    			minecraft1.thePlayer.isImmuneToFire = true;
    			suitType = 20;
    		}
    	}
    	else
    	{
    		variaSuit = false;
    		minecraft1.thePlayer.isImmuneToFire = false;
    	}

    	//Dark Suit Check
    	if (darkHelm == true && darkChest == true && darkBoots == true && darkLegs == true)
    	{
    		darkSuit = true;
    		if (darkSuit = true)
    		{
    			suitType = 40;
    		}
    	}
    	else 
    	{
    		darkSuit = false;
    	}
    	
    	//No suit Checks
    	if (minecraft.thePlayer.inventory.armorItemInSlot(3) == null || minecraft.thePlayer.inventory.armorItemInSlot(2) == null || minecraft.thePlayer.inventory.armorItemInSlot(1) == null || minecraft.thePlayer.inventory.armorItemInSlot(0) == null)
    	{
    		suitType = 15;
    	}
    	
    	if (variaSuit == false && darkSuit == false)
    	{
    		suitType = 15;
    	}
    	
    	//Dark Aether
    	 x1 = entity.posX;
    	 z1 = entity.posZ;
    	 y1 = entity.posY;
    	 x = (int)x1;
    	 z = (int)z1;
    	 y = (int)y1;
    	if (minecraft1.thePlayer.worldObj.getBiomeGenForCoords(x, z) == BiomeGenBase.darkAetherPlains)
    	{
    		counter = counter + 1;
    		if (counter == suitType && minecraft1.theWorld.getBlockId(x, y - 2, z) != mod_MetroidCraft.energyShieldBlock.blockID && minecraft1.theWorld.getBlockId(x, y - 3, z) != mod_MetroidCraft.energyShieldBlock.blockID)
    		{
    			entity.attackEntityFrom(DamageSource.magic, 1);
    			counter = 0;
    		}
    		if (counter > suitType)
    		{
    			counter = 0;
    		}
    	}
    	
    return true;
    }
    
    public boolean onTickInGUI(float f, Minecraft minecraft, GuiScreen guiscreen)
    {
    	if (music)
    	{
    		minecraft.sndManager.playSoundFX("metroid.title", 1.0F, 1.0F);
    		ModLoader.setInGameHook(this, true, true);
    		music = false;
    	}
        return true;
    }

    public void load()
    {	
        ModLoader.registerBlock(lightBlock);
        ModLoader.registerBlock(phazonBlock);
        ModLoader.registerBlock(pureBlock);
        ModLoader.registerBlock(darkAetherGrassBlock); 
        ModLoader.registerBlock(darkAetherLog);
        ModLoader.registerBlock(darkAetherLeaf);
        ModLoader.registerBlock(darkAetherSapling);
        ModLoader.registerBlock(lightCrystalBeacon1);
        ModLoader.registerBlock(energyShieldBlock);
        
        //Item Names
        ModLoader.addName(lightBlock, "Light Block");
        ModLoader.addName(missileCannon, "Missile Cannon");
        ModLoader.addName(missile, "Missile");
        ModLoader.addName(powerCannon, "Power Cannon");
        ModLoader.addName(chargedPowder, "Charged Powder");
        ModLoader.addName(powerBeamCell, "Power Beam Cell");
        ModLoader.addName(powerEnergy, "Power Energy");
        ModLoader.addName(plasmaEnergy, "Plasma Energy");
        ModLoader.addName(pureEnergy, "Pure Energy");
        ModLoader.addName(pureBlock, "Pure Energy Block");
        ModLoader.addName(emptyCell, "Empty Cell");
        ModLoader.addName(plasma, "Plasma");
        ModLoader.addName(phazonCondensed, "Condensed Phazon");
        ModLoader.addName(phazon, "Phazon");
        ModLoader.addName(phazonBlock, "Phazon Block");
        ModLoader.addName(infusedCoal, "Infused Coal");
        ModLoader.addName(powerSuitHelmet, "Varia Suit Visor");
        ModLoader.addName(powerSuitChest, "Varia Suit Chestplate");
        ModLoader.addName(powerSuitLegs, "Varia Suit Greaves");
        ModLoader.addName(powerSuitBoots, "Varia Suit Boots");
        ModLoader.addName(darkAetherLog, "Dark Aether Log");
        ModLoader.addName(darkAetherGrassBlock, "Dark Aether Grass");
        ModLoader.addName(darkAetherLeaf, "Dark Aether Leaf");
        ModLoader.addName(darkAetherSapling, "Dark Aether Sapling");
        ModLoader.addName(lightEnergy, "Light Energy");
        ModLoader.addName(darkEnergy, "Dark Energy");
        ModLoader.addName(lightCrystalBeacon, "Light Beacon");
        ModLoader.addName(lightCrystal, "Light Crystal");
        ModLoader.addName(energyShieldBlock, "Energy Shield Block");
        ModLoader.addName(darkSuitHelmet, "Dark Suit Visor");
        ModLoader.addName(darkSuitChest, "Dark Suit Chestplate");
        ModLoader.addName(darkSuitLegs, "Dark Suit Greaves");
        ModLoader.addName(darkSuitBoots, "Dark Suit Boots");
        
        //Images
        missile.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/missile.png");
        missileCannon.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/missileCannon.png");
        powerCannon.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/powerCannon.png");
        chargedPowder.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/chargedPowder.png");
        powerBeamCell.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/powerBeamCell.png");
        powerEnergy.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/powerEnergy.png");
        plasmaEnergy.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/plasmaEnergy.png");
        pureEnergy.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/pureEnergy.png");
        emptyCell.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/emptyCell.png");
        plasma.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/plasma.png");
        phazonCondensed.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/phazonCondensed.png");
        phazon.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/phazon.png");
        infusedCoal.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/infusedCoal.png");
        pureBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/metroid/powerBlock.png");
        phazonBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/metroid/phazonBlock.png");
        lightBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/metroid/lightBlock.png");
        darkAetherLeaf.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/metroid/darkAetherLeaf.png");
        darkAetherSapling.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/metroid/darkAetherSapling.png");
        lightEnergy.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/lightEnergy.png");
        darkEnergy.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/darkEnergy.png");
        lightCrystal.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/lightCrystal.png");
        lightCrystalBeacon.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/lightCrystalBeacon.png");
        energyShieldBlock.blockIndexInTexture = ModLoader.addOverride("/terrain.png", "/metroid/energyShieldBlock.png");
        powerSuitHelmet.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/powerSuitHelmet.png");
        powerSuitChest.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/powerSuitChest.png");
        powerSuitLegs.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/powerSuitLegs.png");
        powerSuitBoots.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/powerSuitBoots.png");
        darkSuitHelmet.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/darkSuitHelmet.png");
        darkSuitChest.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/darkSuitChest.png");
        darkSuitLegs.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/darkSuitLegs.png");
        darkSuitBoots.iconIndex = ModLoader.addOverride("/gui/items.png", "/metroid/darkSuitBoots.png");

        //Recipes
        ModLoader.addRecipe(new ItemStack(plasma, 1), new Object[]
                {
                    " X ", "XOX", " X ", 'X', phazonCondensed, 'O', infusedCoal
                });
        ModLoader.addRecipe(new ItemStack(emptyCell, 16), new Object[]
                {
                    "OXO", "X X", "OXO", 'X', Block.glass, 'O', Block.stone
                });
        ModLoader.addRecipe(new ItemStack(plasmaEnergy, 4), new Object[]
                {
                    " X ", "XOX", " X ", 'X', pureEnergy, 'O', plasma
                });
        ModLoader.addRecipe(new ItemStack(powerEnergy, 4), new Object[]
                {
                    " X ", "XOX", " X ", 'X', pureEnergy, 'O', Item.ingotIron
                });
        ModLoader.addRecipe(new ItemStack(powerCannon, 1), new Object[]
                {
                    "XO ", "OYO", " OZ", 'X', chargedPowder, 'O', Item.ingotIron, 'Y', powerBeamCell, 'Z',
                    Block.stone
                });
        ModLoader.addRecipe(new ItemStack(missileCannon, 1), new Object[]
                {
                    "XO ", "OYO", " OZ", 'X', chargedPowder, 'O', Item.ingotIron, 'Y', missile, 'Z',
                    Block.stone
                });
        ModLoader.addRecipe(new ItemStack(missile, 1), new Object[]
                {
                    "ZXZ", "OYO", " N ", 'X', chargedPowder, 'O', Item.ingotIron, 'Y', Item.arrow, 'Z',
                    new ItemStack(Item.dyePowder, 1, 11), 'N', Block.stone
                });
        ModLoader.addShapelessRecipe(new ItemStack(powerBeamCell), new Object[]
                {
                    powerEnergy, emptyCell
                });
        ModLoader.addShapelessRecipe(new ItemStack(chargedPowder, 1), new Object[]
                {
                    Item.redstone, Item.redstone, Item.redstone, Item.gunpowder
                });
        ModLoader.addRecipe(new ItemStack(lightBlock, 1), new Object[]
                {
                    " X ", "XOX", " X ", 'X', Block.planks, 'O', Block.dirt
                });
        ModLoader.addRecipe(new ItemStack(lightEnergy, 4), new Object[]
                {
                    " X ", "XOX", " X ", 'X', pureEnergy, 'O', Block.glowStone
                });
        ModLoader.addRecipe(new ItemStack(darkEnergy, 4), new Object[]
                {
                    " X ", "XOX", " X ", 'X', pureEnergy, 'O', Block.obsidian
                }); 
        ModLoader.addRecipe(new ItemStack(lightCrystal, 1), new Object[]
                {
                    " X ", "XOX", " X ", 'X', lightEnergy, 'O', Block.glass
                });
        ModLoader.addRecipe(new ItemStack(lightCrystalBeacon, 1), new Object[]
                {
                    " X ", " O ", " O ", 'X', lightCrystal, 'O', Item.stick
                });
        ModLoader.addRecipe(new ItemStack(powerSuitHelmet, 1), new Object[]
                {
                    "XXX", "X X", 'X', Block.dirt,
                });
        ModLoader.addRecipe(new ItemStack(powerSuitChest, 1), new Object[]
                {
                    "X X", "XXX", "XXX", 'X', Block.dirt,
                });
        ModLoader.addRecipe(new ItemStack(powerSuitLegs, 1), new Object[]
                {
                    "XXX", "X X", "X X", 'X', Block.dirt,
                });
        ModLoader.addRecipe(new ItemStack(powerSuitBoots, 1), new Object[]
                {
                    "X X", "X X", 'X', Block.dirt,
                });
        ModLoader.addRecipe(new ItemStack(darkSuitHelmet, 1), new Object[]
                {
                    "XXX", "X X", 'X', Block.planks,
                });
        ModLoader.addRecipe(new ItemStack(darkSuitChest, 1), new Object[]
                {
                    "X X", "XXX", "XXX", 'X', Block.planks,
                });
        ModLoader.addRecipe(new ItemStack(darkSuitLegs, 1), new Object[]
                {
                    "XXX", "X X", "X X", 'X', Block.planks,
                });
        ModLoader.addRecipe(new ItemStack(darkSuitBoots, 1), new Object[]
                {
                    "X X", "X X", 'X', Block.planks,
                });
        
        //Dark Aether
        DimensionAPI.registerDimension(new WorldProviderDarkAether());
    	
        LightBeaconRender render = new LightBeaconRender();
        RenderPowerDoor render2 = new RenderPowerDoor();
    	ModLoader.registerTileEntity(net.minecraft.src.TileEntityLightCrystalBeacon.class, "lightCrystalBeacon", render);
    	ModLoader.registerTileEntity(net.minecraft.src.TileEntityPowerDoor.class, "lightCrystalBeacon", render2);
        ModLoader.registerEntityID(net.minecraft.src.EntityIng.class, "Warrior Ing", ModLoader.getUniqueEntityId());
        Minecraft minecraft = ModLoader.getMinecraftInstance();
        minecraft.installResource("newsound/metroid/powerBeamFire.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/powerBeamFire.ogg"));
        minecraft.installResource("newsound/metroid/powerBeamCharge.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/powerBeamCharge.ogg"));
        minecraft.installResource("newsound/metroid/powerBeamIdle.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/powerBeamIdle.ogg"));
        minecraft.installResource("newsound/metroid/powerBeamHit.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/powerBeamHit.ogg"));
        minecraft.installResource("newsound/metroid/noAmmo.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/noAmmo.ogg"));
        minecraft.installResource("newsound/metroid/missile.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/missile.ogg"));
        minecraft.installResource("newsound/metroid/title.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/title.ogg"));
        minecraft.installResource("newsound/metroid/dangerPoison.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/dangerPoison.ogg"));
        minecraft.installResource("newsound/metroid/amorbis theme.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/amorbis theme.ogg"));
        minecraft.installResource("newsound/metroid/chykka theme.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/chykka theme.ogg"));
        minecraft.installResource("newsound/metroid/quadraxis theme.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/quadraxis theme.ogg"));
        minecraft.installResource("newsound/metroid/classic metroid remix.ogg", new File(minecraft.mcDataDir, "resources/newsound/metroid/classic metroid remix.ogg"));
    }

    public String getVersion()
    {
        return "1.2.5";
    }

    public void generateSurface(World world, Random random, int i, int j)
    {
    	//Tree
    	{
    		BiomeGenBase biome = world.getWorldChunkManager().getBiomeGenAt(i, j); 
    		WorldGenDarkAetherTree tree = new WorldGenDarkAetherTree(); 
    		if(biome instanceof BiomeGenDarkAetherPlains)

    		{ 
    			for(int x = 0; x < 50; x++)
    			{
    				int Xcoord = i + random.nextInt(16); 
    				int Zcoord = j + random.nextInt(16); 
    				int l = world.getHeightValue(Xcoord, Zcoord); 
    				tree.generate(world, random, Xcoord, l, Zcoord); 
    			}
    		} 
    	}
        
        //Ores
        for (int k = 0; k < 15; k++)
        {
            int l = i + random.nextInt(16);
            int i1 = random.nextInt(16) + 16;
            int j1 = j + random.nextInt(16);
            (new WorldGenMinable(phazonBlock.blockID, 7)).generate(world, random, l, i1, j1);
        }

        for (int k = 0; k < 25; k++)
        {
        	int l = i + random.nextInt(16);
        	int i1 = random.nextInt(48) + 16;
        	int j1 = j + random.nextInt(16);
        	(new WorldGenMinable(pureBlock.blockID, 7)).generate(world, random, l, i1, j1);
        }      
        
    }    

    static
    {
        powerSuitHelmet = (new ItemArmor(502, EnumArmorMaterial.POWER, ModLoader.addArmor("power"), 0)).setItemName("powerSuitHelmet");
        powerSuitChest = (new ItemArmor(503, EnumArmorMaterial.POWER, ModLoader.addArmor("power"), 1)).setItemName("powerSuitChest");
        powerSuitLegs = (new ItemArmor(504, EnumArmorMaterial.POWER, ModLoader.addArmor("power"), 2)).setItemName("powerSuitLegs");
        powerSuitBoots = (new ItemArmor(505, EnumArmorMaterial.POWER, ModLoader.addArmor("power"), 3)).setItemName("powerSuitBoots");
        phazonBlock = (new BlockPhazon(140, Material.rock)).setHardness(2.0F).setResistance(50F).setBlockName("phazonBlock").setLightValue(0.6F).setLightOpacity(1);
        pureBlock = (new BlockPower(143, Material.rock)).setHardness(2.0F).setResistance(50F).setBlockName("pureBlock");
        lightBlock = (new BlockLight(144, Material.rock)).setHardness(2.0F).setResistance(50F).setBlockName("lightBlock");
        darkAetherGrassBlock = (new BlockDarkAetherGrass(145, Material.grass)).setHardness(2.0F).setStepSound(Block.soundGrassFootstep).setBlockName("darkAetherGrassBlock");
        darkAetherLog = (new DarkAetherLog(146)).setHardness(0.6F).setStepSound(Block.soundWoodFootstep).setBlockName("darkAetherLog");
        darkAetherLeaf = (new DarkAetherLeaf(147, 52)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("darkAetherLeaf").setRequiresSelfNotify();
        darkAetherSapling = (new DarkAetherSapling(148, 15)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("darkAetherSapling");    
        energyShieldBlock = (new BlockEnergyShield(150, Material.rock)).setBlockUnbreakable().setResistance(20000F).setStepSound(Block.soundStoneFootstep).setBlockName("energyShieldBlock");    
        darkSuitHelmet = (new ItemArmor(521, EnumArmorMaterial.DARK, ModLoader.addArmor("dark"), 0)).setItemName("darkSuitHelmet");
        darkSuitChest = (new ItemArmor(522, EnumArmorMaterial.DARK, ModLoader.addArmor("dark"), 1)).setItemName("darkSuitChest");
        darkSuitLegs = (new ItemArmor(523, EnumArmorMaterial.DARK, ModLoader.addArmor("dark"), 2)).setItemName("darkSuitLegs");
        darkSuitBoots = (new ItemArmor(524, EnumArmorMaterial.DARK, ModLoader.addArmor("dark"), 3)).setItemName("darkSuitBoots");    
        powerDoorBlock = (new BlockPowerDoor(151, net.minecraft.src.TileEntityPowerDoor.class)).setBlockUnbreakable().setResistance(100000F);
    }
    
}
