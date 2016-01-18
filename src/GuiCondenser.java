package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiCondenser extends GuiContainer
{
    private TileEntityCondenser CondenserInventory;

    public GuiCondenser(InventoryPlayer inventoryplayer, TileEntityCondenser tileentitycondenser)
    {
        super(new ContainerCondenser(inventoryplayer, tileentitycondenser));
        CondenserInventory = tileentitycondenser;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everythin in front of the items)
     */
    protected void drawGuiContainerForegroundLayer()
    {
        fontRenderer.drawString("Phazon Infuser", 50, 6, 0x404040);
        fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
        int k = mc.renderEngine.getTexture("/metroid/phazonCondenser.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(k);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

        if (CondenserInventory.isBurning())
        {
            int j1 = CondenserInventory.getBurnTimeRemainingScaled(12);
            drawTexturedModalRect(l + 56, (i1 + 36 + 12) - j1, 176, 12 - j1, 14, j1 + 2);
        }

        int k1 = CondenserInventory.getCookProgressScaled(24);
        drawTexturedModalRect(l + 79, i1 + 34, 176, 14, k1 + 1, 16);
    }
}
