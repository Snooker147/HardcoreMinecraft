package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

@SuppressWarnings("unused")
public class LowBrightness extends Feature
{

    public LowBrightness()
    {
        super("lowBrightness");
    }

    @SubscribeEvent
    public void tickClient(PlayerTickEvent e)
    {
        if(e.player.getEntityWorld().isRemote)
        {
            Minecraft minecraft = Minecraft.getInstance();

            if(minecraft.gameSettings.gamma > 0.0)
            {
                minecraft.gameSettings.gamma = 0.0d;
                minecraft.func_228018_at_().update();
            }
        }
    }

}
