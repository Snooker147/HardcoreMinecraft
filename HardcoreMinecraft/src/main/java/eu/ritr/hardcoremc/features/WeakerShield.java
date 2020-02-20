package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.Util;
import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.item.Item;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

public class WeakerShield extends Feature
{

    public WeakerShield()
    {
        super("weakerShield");
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void setupAfter(FMLLoadCompleteEvent e) 
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
