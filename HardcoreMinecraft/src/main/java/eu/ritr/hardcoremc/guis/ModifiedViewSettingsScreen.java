package eu.ritr.hardcoremc.guis;

import eu.ritr.hardcoremc.base.Features;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.AbstractOption;
import net.minecraft.client.settings.FullscreenResolutionOption;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class ModifiedViewSettingsScreen extends VideoSettingsScreen
{
    protected Screen parent;

    public ModifiedViewSettingsScreen(Screen parent, GameSettings settings)
    {
        super(parent, settings);

        this.parent = parent;
    }

    @Override
    protected void init()
    {
        List<AbstractOption> optionsToAdd = new ArrayList<>();

        optionsToAdd.add(AbstractOption.GRAPHICS);
        optionsToAdd.add(AbstractOption.RENDER_DISTANCE);
        optionsToAdd.add(AbstractOption.AO);
        optionsToAdd.add(AbstractOption.FRAMERATE_LIMIT);
        optionsToAdd.add(AbstractOption.VSYNC);
        optionsToAdd.add(AbstractOption.VIEW_BOBBING);
        optionsToAdd.add(AbstractOption.GUI_SCALE);
        optionsToAdd.add(AbstractOption.RENDER_CLOUDS);
        optionsToAdd.add(AbstractOption.FULLSCREEN);
        optionsToAdd.add(AbstractOption.PARTICLES);
        optionsToAdd.add(AbstractOption.MIPMAP_LEVELS);
        optionsToAdd.add(AbstractOption.ENTITY_SHADOWS);
        optionsToAdd.add(AbstractOption.BIOME_BLEND_RADIUS);

        if(!Features.LOW_BRIGHTNESS.isEnabled())
        {
            optionsToAdd.add(AbstractOption.GAMMA);
        }

        if(!Features.DISABLE_COOLDOWN.isEnabled())
        {
            optionsToAdd.add(AbstractOption.ATTACK_INDICATOR);
        }

        ObfuscationReflectionHelper.setPrivateValue(VideoSettingsScreen.class, this, this.minecraft.gameSettings.mipmapLevels, "field_213108_e");

        // basically overwriting it
        OptionsRowList list = new OptionsRowList(this.minecraft, this.width, this.height, 32, this.height - 32, 25);
        list.func_214333_a(new FullscreenResolutionOption(this.minecraft.func_228018_at_()));
        list.func_214335_a(optionsToAdd.toArray(new AbstractOption[optionsToAdd.size()]));
        
        this.children.add(list);

        this.addButton(new Button(this.width / 2 - 100, this.height - 27, 200, 20, I18n.format("gui.done", new Object[0]), (p_213106_1_) -> {
            this.minecraft.gameSettings.saveOptions();
            this.minecraft.func_228018_at_().update();
            this.minecraft.displayGuiScreen(parent);
        }));

        ObfuscationReflectionHelper.setPrivateValue(VideoSettingsScreen.class, this, list, "field_146501_h");
    }
}
