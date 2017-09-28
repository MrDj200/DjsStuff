package me.mrdj.djsstuff.compat;

import me.mrdj.djsstuff.compat.top.TOPCompatibility;
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