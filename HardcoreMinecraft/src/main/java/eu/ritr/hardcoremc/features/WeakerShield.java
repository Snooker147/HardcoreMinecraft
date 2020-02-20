package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.Util;
import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

public class WeakerShield extends Feature
{

    public WeakerShield()
    {
        super("weakerShield");
    }

    /**
     * {@link FoodUnstackable#onStart(FMLServerStartedEvent)} for more info of why I used this system
     * @param e
     */
    @SuppressWarnings("deprecation")
	@SubscribeEvent
    public void onStart(FMLServerStartedEvent e)
    {	
        for (Item item : Registry.ITEM)
        {
            if(item instanceof ShieldItem)
            {
            	Util.setPrivateValue(Item.class, item, "maxDamage", "field_77699_b", 1);
            }
        }
    }
}
