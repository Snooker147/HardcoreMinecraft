package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.base.Feature;
import eu.ritr.hardcoremc.base.Features;
import net.minecraft.client.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.glfw.GLFW;

import java.lang.reflect.Method;
import java.util.UUID;

public class DisableSprint extends Feature
{
    private static final UUID EntitySpeedAttr = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D"); // Not needed maybe?
    private static final Method EntitySetFlagMethod = ObfuscationReflectionHelper.findMethod(Entity.class, "func_70052_a", int.class, boolean.class);
    private KeyBinding sprintKeyBinding;

    public DisableSprint()
    {
        super("disableSprint");
    }

    @Override
    public void setupClient(FMLClientSetupEvent e)
    {
        GameSettings settings = e.getMinecraftSupplier().get().gameSettings;

        KeyBinding[] binds = settings.keyBindings;
        KeyBinding[] nBinds = new KeyBinding[binds.length - 1];

        int counter = 0;
        for(int i = 0; i < binds.length; i++)
        {
            KeyBinding binding = binds[i];

            if(binding.equals(settings.keyBindSprint) == false)
            {
                nBinds[counter++] = binding;
            }
        }

        settings.keyBindings = nBinds;

        sprintKeyBinding = settings.keyBindSprint;

        // this will bind it to F24, a key that nobody probably have on their keyboard
        sprintKeyBinding.setKeyModifierAndCode(KeyModifier.NONE, InputMappings.getInputByCode(GLFW.GLFW_KEY_F24, 0));
    }

    @SubscribeEvent
    public void onPlayerServerTick(PlayerTickEvent e)
    {
        // it is automatically disabled since DISABLE_FOOD sets food level to 2
        if(Features.DISABLE_FOOD.isEnabled())
        {
            return;
        }

        e.player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(EntitySpeedAttr);
        //e.player.setSprinting(false);

        try
        {
            // I have yet to find a better method
            // this sets the sprinting Flag to false which essentially makes everything like it should be
            // the only problem is when the user is spamming the sprint key the client or server probably assumes the client is sprinting
            // for a split second is technically sprinting, this is solved by binding the KeyBinding to key that doesn't exists on most keyboards
            // that is done above
            EntitySetFlagMethod.invoke(e.player, 3, false);
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }

    static {
        EntitySetFlagMethod.setAccessible(true);
    }
}
