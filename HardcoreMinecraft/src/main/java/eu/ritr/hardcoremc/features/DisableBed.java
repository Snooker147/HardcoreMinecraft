package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DisableBed extends Feature
{

    public DisableBed()
    {
        super("disableBed");
    }

    @SubscribeEvent
    public void onSleep(PlayerSleepInBedEvent e)
    {
    	e.setResult(SleepResult.OTHER_PROBLEM);
    	e.getPlayer().sendMessage(new StringTextComponent("It's hardcore, you think I would let you sleep?"));
    }

}
