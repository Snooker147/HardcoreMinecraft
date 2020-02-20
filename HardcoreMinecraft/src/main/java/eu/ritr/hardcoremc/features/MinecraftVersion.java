package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MinecraftVersion extends Feature
{

    public MinecraftVersion()
    {
        super("minecraftVersionOverlayText");
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent e)
    {
        if(e.getType() != RenderGameOverlayEvent.ElementType.CHAT)
        {
            return;
        }

        FontRenderer renderer = Minecraft.getInstance().fontRenderer;
        renderer.drawString("Minecraft 1.15.2", 2, 2, 0xffffffff);
    }

}
