package eu.ritr.hardcoremc.base;

import eu.ritr.hardcoremc.features.*;

public final class Features
{
    private Features() {}

    public static final DisableBed DISABLE_BED = new DisableBed();
    public static final DisableCooldown DISABLE_COOLDOWN = new DisableCooldown();
    public static final DisableFood DISABLE_FOOD = new DisableFood();
    public static final DisableSprint DISABLE_SPRINT = new DisableSprint();
    public static final FoodUnstackable FOOD_UNSTACKABLE = new FoodUnstackable();
    public static final LowBrightness LOW_BRIGHTNESS = new LowBrightness();
    public static final MinecraftVersion MINECRAFT_VERSION = new MinecraftVersion();
    public static final WeakerShield WEAKER_SHIELD = new WeakerShield();
    public static final DisableDebugScreen DISABLE_DEBUG_SCREEN = new DisableDebugScreen();

    public static final Feature[] FEATURES = new Feature[] {
            DISABLE_BED,
            DISABLE_COOLDOWN,
            DISABLE_FOOD,
            DISABLE_SPRINT,
            FOOD_UNSTACKABLE,
            LOW_BRIGHTNESS,
            MINECRAFT_VERSION,
            WEAKER_SHIELD,
            DISABLE_DEBUG_SCREEN
    };
}
