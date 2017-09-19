package me.mrdj.attractor.config;

import me.mrdj.attractor.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

/**
 *
 * @author Dj
 */
public class Config
{
    private static final String CATEGORY_GENERAL = "general";

    // This values below you can access elsewhere in your mod:
    public static int maxAttractorRadius = 5;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            //logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) 
    {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        maxAttractorRadius = cfg.getInt("Radius", CATEGORY_GENERAL, maxAttractorRadius, 3, 100, "Set the radius of the Attractor block");
    }
}
