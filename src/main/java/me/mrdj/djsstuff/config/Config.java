package me.mrdj.djsstuff.config;

import me.mrdj.djsstuff.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;

/**
 *
 * @author Dj
 */
public class Config
{
    private static final String CATEGORY_ATTRACTOR = "attractor";

    // This values below you can access elsewhere in your mod:
    public static int totalMaxAttractorRadius = 5;
    public static int minAttractorRadius = 3;
    public static int maxAttractorRadius = 100;
    
    public static boolean canBeMobGriefed = false;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initAttractorConfig(cfg);
        } catch (Exception e1) {
            //logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initAttractorConfig(Configuration cfg) 
    {
        cfg.addCustomCategoryComment(CATEGORY_ATTRACTOR, "AttractorBlock configuration");
        totalMaxAttractorRadius = cfg.getInt("Radius", CATEGORY_ATTRACTOR, totalMaxAttractorRadius, 3, 100, "Set the max radius of the Attractor block");
        canBeMobGriefed = cfg.getBoolean("Can be mob griefed", CATEGORY_ATTRACTOR, canBeMobGriefed, "Enable or disable if the block is destructible by mobs");
    }
}
