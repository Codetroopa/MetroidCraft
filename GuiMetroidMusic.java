package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class GuiMetroidMusic extends GuiScreen 
{
	public void initGui()
    {
        controlList.clear();
        controlList.add(new GuiButton(0, width / 2 - 75, height / 2 - 50, 150, 20, "Title Music"));
        controlList.add(new GuiButton(2, width / 2 - 75, height / 2 - 26, 150, 20, "Amorbis Boss Theme"));
        controlList.add(new GuiButton(3, width / 2 - 75, height / 2 - 2, 150, 20, "Chykka Boss Theme"));
        controlList.add(new GuiButton(4, width / 2 - 75, height / 2 + 22, 150, 20, "Quadraxis Boss Theme"));
        controlList.add(new GuiButton(5, width / 2 - 75, height / 2 - 74, 150, 20, "Classic Metroid Remix"));
        controlList.add(new GuiButton(1, width / 2 - 75, height / 2 + 46, 150, 20, "Stop Metroid Music"));
    }

	public void onUpdate()
	{
		
	}
	
	protected void actionPerformed(GuiButton par1GuiButton)
    {
		//Title
        if (par1GuiButton.id == 0)
        {
        	if (SoundManager.sndSystem.playing(mod_MetroidCraft.s))
        	{
        		SoundManager.sndSystem.stop(mod_MetroidCraft.s);
        		mc.sndManager.playSoundFX("metroid.title", 1.0F, 1.0F);
                mc.displayGuiScreen(null);
        	}
        	else
        	{
        		mc.sndManager.playSoundFX("metroid.title", 1.0F, 1.0F);                
        		mc.displayGuiScreen(null);
        	}
        }
        
      //Amorbis
        if (par1GuiButton.id == 2)
        {
        	if (SoundManager.sndSystem.playing(mod_MetroidCraft.s))
        	{
        		SoundManager.sndSystem.stop(mod_MetroidCraft.s);
        		mc.sndManager.playSoundFX("metroid.amorbis theme", 1.0F, 1.0F);
                mc.displayGuiScreen(null);
        	}
        	else
        	{
        		mc.sndManager.playSoundFX("metroid.amorbis theme", 1.0F, 1.0F);                
        		mc.displayGuiScreen(null);
        	}
        }
        
        //Chykka
        if (par1GuiButton.id == 3)
        {
        	if (SoundManager.sndSystem.playing(mod_MetroidCraft.s))
        	{
        		SoundManager.sndSystem.stop(mod_MetroidCraft.s);
        		mc.sndManager.playSoundFX("metroid.chykka theme", 1.0F, 1.0F);
        		mc.displayGuiScreen(null);
        	}
        	else
        	{
        		mc.sndManager.playSoundFX("metroid.chykka theme", 1.0F, 1.0F);                
        		mc.displayGuiScreen(null);
        	}
        }

        //Quadraxis
        if (par1GuiButton.id == 4)
        {
        	if (SoundManager.sndSystem.playing(mod_MetroidCraft.s))
        	{
        		SoundManager.sndSystem.stop(mod_MetroidCraft.s);
        		mc.sndManager.playSoundFX("metroid.quadraxis theme", 1.0F, 1.0F);
        		mc.displayGuiScreen(null);
        	}
        	else
        	{
        		mc.sndManager.playSoundFX("metroid.quadraxis theme", 1.0F, 1.0F);                
        		mc.displayGuiScreen(null);
        	}
        }

        //Classic
        if (par1GuiButton.id == 5)
        {
        	if (SoundManager.sndSystem.playing(mod_MetroidCraft.s))
        	{
        		SoundManager.sndSystem.stop(mod_MetroidCraft.s);
        		mc.sndManager.playSoundFX("metroid.classic metroid remix", 1.0F, 1.0F);
        		mc.displayGuiScreen(null);
        	}
        	else
        	{
        		mc.sndManager.playSoundFX("metroid.classic metroid remix", 1.0F, 1.0F);                
        		mc.displayGuiScreen(null);
        	}
        }

        if (par1GuiButton.id == 1)
        {
    		SoundManager.sndSystem.stop(mod_MetroidCraft.s);
        }
    }
	
    public void drawScreen(int par1, int par2, float par3)
    {
        drawDefaultBackground();
        super.drawScreen(par1, par2, par3);
    }
}
