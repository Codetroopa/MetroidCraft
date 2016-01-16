package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class LightBeaconRender extends TileEntitySpecialRenderer
{
	public LightBeaconRender()
    {
        aModel = new ModelLightCrystal();
    }
       
    public void renderAModelAt(TileEntityLightCrystalBeacon tileentity1, double d, double d1, double d2, float f)
    {  
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
        GL11.glRotatef(180, 180, 0, 0);
        bindTextureByName("/metroid/lightCrystalTexture.png");
        GL11.glPushMatrix();
        aModel.renderModel(0.0625F);
        GL11.glPopMatrix();    
        GL11.glPopMatrix();                    
    }
 
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2,
            float f)
    {
        renderAModelAt((TileEntityLightCrystalBeacon)tileentity, d, d1, d2, f);
    }
 
    private ModelLightCrystal aModel;
}
