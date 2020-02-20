package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.base.Feature;
import eu.ritr.hardcoremc.base.Features;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DisableFood extends Feature
{

    public DisableFood()
    {
        super("disableFood");
    }

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Pre e)
    {
        if(e.getType() == RenderGameOverlayEvent.ElementType.FOOD)
        {
            e.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onTick(PlayerTickEvent e)
    {
        e.player.getFoodStats().setFoodLevel(Features.DISABLE_SPRINT.isEnabled() ? 2 : 8);
    }

    @SubscribeEvent
    public void onEatPre(LivingEntityUseItemEvent.Start e)
    {
        ItemStack stack = e.getItem();

        if(!stack.isFood() || !(e.getEntityLiving() instanceof PlayerEntity))
        {
            return;
        }

        PlayerEntity p = (PlayerEntity)e.getEntityLiving();
        e.setCanceled(p.getHealth() >= p.getMaxHealth());
    }

    @SubscribeEvent
    public void onEaten(LivingEntityUseItemEvent.Finish e)
    {
        ItemStack stack = e.getItem();

        if(!stack.isFood() || !(e.getEntityLiving() instanceof PlayerEntity))
        {
            return;
        }

        PlayerEntity p = (PlayerEntity)e.getEntityLiving();
        Food food = stack.getItem().getFood();

        float hp = p.getHealth() + (int)MathHelper.clamp(food.getSaturation() * 10, 1, 20);
        
        p.setHealth(MathHelper.clamp(hp, 1, p.getMaxHealth()));
    }

}
