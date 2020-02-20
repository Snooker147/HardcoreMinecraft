package eu.ritr.hardcoremc.base;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

/**
 * It is automatically registered as Event listener
 */
public abstract class Feature
{

    protected final String name;
    private boolean enabled;

    public Feature(String name)
    {
        this.name = name;
        this.enabled = true;
    }

    public void setup(FMLCommonSetupEvent e) {}
    public void setupClient(FMLClientSetupEvent e) {}

    public String getName()
    {
        return name;
    }

    public void setEnabled(boolean enabled)
    {
        this.enabled = enabled;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

}
