package net.ldm.collar_cutting.config;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    //public static final ForgeConfigSpec.ConfigValue<String> cutting_item;
    public static final ForgeConfigSpec.ConfigValue<Integer> durability;
    public static final ForgeConfigSpec.ConfigValue<Boolean> cut_unowned_wolves;

    static {
        BUILDER.push("Configurate the Collar Cutting mod");

        durability = BUILDER.comment("How much durability is used upon collar cut. (Default = 1)").define("Durability to Use", 1);
        cut_unowned_wolves = BUILDER.comment("If players are allowed to cut wolves that aren't their own. (Default = false)").define("Cut Unowned Wolves", false);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
