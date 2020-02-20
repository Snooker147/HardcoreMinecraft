package eu.ritr.hardcoremc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.ritr.hardcoremc.base.Feature;
import eu.ritr.hardcoremc.base.Features;
import eu.ritr.hardcoremc.guis.ModifiedViewSettingsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.SettingsScreen;
import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("hardcoremc")
public class Main
{
    public static final String MOD_ID = "hardcoremc";
    public static final Logger LOGGER = LogManager.getLogger("HardcoreMC");

    public Main()
    {
        try
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            File file = this.getConfigFile();

            if(file.exists() && !file.isDirectory())
            {
                String jsonRaw = FileUtils.readFileToString(file, "utf8");
                @SuppressWarnings("unchecked")
				Map<String, Boolean> config = (Map<String, Boolean>)gson.fromJson(jsonRaw, Map.class);

                for(Feature f : Features.FEATURES)
                {
                    if(config.containsKey(f.getName()))
                    {
                        f.setEnabled(config.get(f.getName()).booleanValue());
                    }
                }
            }
            else
            {
                Map<String, Boolean> config = new HashMap<>();

                for(Feature f : Features.FEATURES)
                {
                    config.put(f.getName(), f.isEnabled());
                }

                FileUtils.write(file, gson.toJson(config), "utf8");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupClient);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setupAfter);
        	
        MinecraftForge.EVENT_BUS.register(this);

        for(Feature f : Features.FEATURES)
        {
            if(f.isEnabled())
            {
            	Main.LOGGER.debug("Feature " + f.getName() + " is registering");
            	
                MinecraftForge.EVENT_BUS.register(f);
                MinecraftForge.EVENT_BUS.register(f.getClass());
            }
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        for(Feature f : Features.FEATURES)
        {
            if(f.isEnabled())
            {
                f.setup(event);
            }
        }
    }

    private void setupClient(final FMLClientSetupEvent event)
    {
        for(Feature f : Features.FEATURES)
        {
            if(f.isEnabled())
            {
                f.setupClient(event);
            }
        }
    }
    
    private void setupAfter(final FMLLoadCompleteEvent event)
    {
    	Main.LOGGER.debug("setupAfter");
    	
        for(Feature f : Features.FEATURES)
        {
            if(f.isEnabled())
            {
                f.setupAfter(event);
            }
        }
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void onGuiOpen(GuiOpenEvent e)
    {
        Screen screen = e.getGui();
        
        if(Features.LOW_BRIGHTNESS.isEnabled() && screen instanceof VideoSettingsScreen)
        {
            // prevent duplicate
            if(screen instanceof ModifiedViewSettingsScreen)
            {
                return;
            }

            Screen parent = ObfuscationReflectionHelper.getPrivateValue(SettingsScreen.class, (SettingsScreen) screen, "field_228182_a_");
            e.setGui(new ModifiedViewSettingsScreen(parent, Minecraft.getInstance().gameSettings));
        }


    }

    private File getConfigFile()
    {
        return new File("hardcore-mc-features.json");
    }
}
