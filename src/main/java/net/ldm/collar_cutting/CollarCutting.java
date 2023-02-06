package net.ldm.collar_cutting;

import com.mojang.logging.LogUtils;
import net.ldm.collar_cutting.config.Config;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(CollarCutting.MODID)
public class CollarCutting {
    public static final String MODID = "collar_cutting";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CollarCutting() {
        //IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "collar_cutting-common.toml");
    }
}
