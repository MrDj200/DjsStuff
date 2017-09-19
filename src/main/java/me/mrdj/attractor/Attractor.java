package me.mrdj.attractor;

import me.mrdj.attractor.proxy.CommonProxy;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 * @author Dj
 */
@Mod(   modid = Attractor.MODID,
        name = Attractor.NAME,
        version = Attractor.VERSION, 
        acceptedMinecraftVersions = "[1.12,1.12.2]",
        modLanguage = "java",
        dependencies = "required-after:djscore"
    ) 
public class Attractor 
{
    @SidedProxy(clientSide = "me.mrdj.attractor.proxy.ClientProxy", serverSide = "me.mrdj.attractor.proxy.CommonProxy") 
    public static CommonProxy proxy = new CommonProxy();
    public static final String MODID = "djsattractor";
    public static final String NAME = "Attractor";
    public static final String VERSION = "1.4"; 
    public static final boolean DEVENV = (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment");
    
    @EventHandler
    void preInit( FMLPreInitializationEvent event )
    {
        proxy.preInit(event);        
    }
    
    @EventHandler
    void init( FMLInitializationEvent event )
    {
        proxy.init(event);
    }
    
    @EventHandler
    void postInit( FMLPostInitializationEvent event )
    {
        proxy.postInit(event);        
    }
}
