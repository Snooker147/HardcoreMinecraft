package eu.ritr.hardcoremc.features;

import eu.ritr.hardcoremc.Main;
import eu.ritr.hardcoremc.base.Feature;
import net.minecraft.client.GameSettings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.AttackIndicatorStatus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DisableCooldown extends Feature
{

    public DisableCooldown()
    {
        super("disableCooldown");
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public void tickClient(PlayerTickEvent e)
    {
        if(e.player.getEntityWorld().isRemote)
        {
            GameSettings s = Minecraft.getInstance().gameSettings;

            if(s.attackIndicator != AttackIndicatorStatus.OFF)
            {
                s.attackIndicator = AttackIndicatorStatus.OFF;
                s.saveOptions();
            }
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent e)
    {
        if(!(e.getEntity() instanceof PlayerEntity))
        {
            return;
        }

        PlayerEntity p = (PlayerEntity)e.getEntity();
        IAttributeInstance attribute = p.getAttribute(SharedMonsterAttributes.ATTACK_SPEED);
        attribute.setBaseValue(1024);
    }

    @SubscribeEvent
    public void onAttack(LivingDamageEvent e)
    {
        Entity source = e.getSource().getImmediateSource();

        if(source == null || !(source instanceof LivingEntity))
        {
            return;
        }

        ItemStack itemStack = ((LivingEntity) source).getHeldItemMainhand();

        if(itemStack.getItem() instanceof AxeItem)
        {
            Main.LOGGER.debug("Item instanceof AxeItem, setting damage to 2");
            e.setAmount(2.0f);
        }
    }

}
