package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerEntity.SleepResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
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
    	
    	PlayerEntity p = e.getPlayer();
    	p.sendMessage(new StringTextComponent("I don't recall having beds in Minecraft").setStyle(new Style().setItalic(true)));
    	
    	p.setSpawnPoint(e.getPos(), false, true, p.dimension);

    	
    }

}
