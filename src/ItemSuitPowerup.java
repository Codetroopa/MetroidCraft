package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class ItemSuitPowerup extends Item 
{
	Minecraft mc = ModLoader.getMinecraftInstance();
	protected ItemSuitPowerup(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		TileEntitySuitUpgrade tileentitysuitupgrade = new TileEntitySuitUpgrade();
		ModLoader.openGUI(par3EntityPlayer, new GuiMetroidMusic());
		
		return par1ItemStack;
    }

}
