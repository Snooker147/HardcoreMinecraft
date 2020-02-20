package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.Util;
import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;

public class FoodUnstackable extends Feature
{

    public FoodUnstackable()
    {
        super("foodUnstackable");
    }
    
    @SuppressWarnings("deprecation")
	@SubscribeEvent
    public void onStart(FMLServerStartedEvent e)
    {
    	// You should not use Registry.XXX but RegistryEvent.Register isntead
    	// however, I couldnt get it to work and I got bored trying to resolve it
    	// so I used this, if anyone has time and idea of why it doesnt work with regitry event below (commented)
    	// let me know
    	// If you're looking at this mod for coding help, I advice you to look for someone elses work, as I did this mod for fun
    	
    	for(Item item : Registry.ITEM)
    	{
    		if (item.isFood())
            {
    			Util.setPrivateValue(Item.class, item, "maxStackSize", "field_77777_bU", 1);
            }
    	}
    }
    
    /*
    @SubscribeEvent
    public static void onRegistry(final RegistryEvent.Register<Item> e)
    {
    	Main.LOGGER.debug("is it?");
    	
        for (Item item : e.getRegistry())
        {
        	System.out.println(item.getTranslationKey());
            if (item.isFood())
            {
                ObfuscationReflectionHelper.setPrivateValue(Item.class, item, 1, "field_200920_a");
            }
        }
        
    }
    */


}
