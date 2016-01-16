package net.minecraft.src;
 
import org.lwjgl.opengl.GL11;
 
public class RenderPowerDoor extends TileEntitySpecialRenderer
{
 
    public RenderPowerDoor()
    {
        aModel = new ModelPowerDoor();
    }
       
    public void renderAModelAt(TileEntityPowerDoor tileentity1, double d, double d1, double d2, float f)
    {  
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F);
        GL11.glRotatef(180, 180, 0, 0);
        bindTextureByName("/metroid/powerDoor.png");
        GL11.glPushMatrix();
        aModel.renderModel(0.0625F);
        GL11.glPopMatrix();    
        GL11.glPopMatrix();                    
    }
 
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2,
            float f)
    {
        renderAModelAt((TileEntityPowerDoor)tileentity, d, d1, d2, f);
    }
 
    private ModelPowerDoor aModel;
}