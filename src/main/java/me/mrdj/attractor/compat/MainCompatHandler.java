package me.mrdj.attractor.compat;

import me.mrdj.attractor.compat.top.TOPCompatibility;
import net.minecraftforge.fml.common.Loader;

/**
 *
 * @author Dj
 */
public class MainCompatHandler
{
    public static void registerTOP() {
        if (Loader.isModLoaded("theoneprobe")) {
            TOPCompatibility.register();
        }
    }

}