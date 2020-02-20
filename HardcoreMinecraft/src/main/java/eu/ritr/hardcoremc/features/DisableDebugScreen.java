package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DisableDebugScreen extends Feature 
{

	public DisableDebugScreen() 
	{
		super("disableDebugScreen");
	}
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent e)
	{
		if(e.getType() == ElementType.DEBUG)
		{
			Minecraft.getInstance().fontRenderer.drawString("Debug Info Disabled", 2f, 12f, 0xFFFFFFFF);
			
			e.setCanceled(true);
		}
	}
	
}
