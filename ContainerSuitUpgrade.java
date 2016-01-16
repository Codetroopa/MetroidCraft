package net.minecraft.src;

public class ContainerSuitUpgrade extends Container 
{

    private TileEntitySuitUpgrade suit;
	
	public ContainerSuitUpgrade(InventoryPlayer par1InventoryPlayer, TileEntitySuitUpgrade par2TileEntityFurnace)
	{
		suit = par2TileEntityFurnace;
        addSlot(new Slot(par2TileEntityFurnace, 0, 56, 17));
        addSlot(new Slot(par2TileEntityFurnace, 1, 56, 53));
        addSlot(new SlotFurnace(par1InventoryPlayer.player, par2TileEntityFurnace, 2, 116, 35));

        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 9; k++)
            {
                addSlot(new Slot(par1InventoryPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++)
        {
            addSlot(new Slot(par1InventoryPlayer, j, 8 + j * 18, 142));
        }
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		// TODO Auto-generated method stub
		return false;
	}

}
