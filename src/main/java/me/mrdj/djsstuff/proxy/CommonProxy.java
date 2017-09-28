package me.mrdj.djsstuff.proxy;

import java.io.File;
import me.mrdj.djsstuff.DjsStuff;
import me.mrdj.djsstuff.blocks.attractor.BlockAttractor;
import me.mrdj.djsstuff.blocks.ModBlocks;
import me.mrdj.djsstuff.compat.MainCompatHandler;
import me.mrdj.djsstuff.config.Config;
import me.mrdj.djsstuff.events.EventHandler;
import me.mrdj.djsstuff.tiles.TileAttractor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 *
 * @author Dj
 */
@Mod.EventBusSubscriber
public class CommonProxy 
{
    public void registerModels() {}
    
    public static Configuration config;
    
    public void preInit(FMLPreInitializationEvent event) 
    {
        GameRegistry.registerTileEntity(TileAttractor.class, "djsstuff:tile_attractor");
        File cfgDirectory = event.getModConfigurationDirectory();
        config = new Configuration(new File(cfgDirectory.getPath(), "djsmods/" + DjsStuff.MODID + ".cfg"));
        Config.readConfig();
        MainCompatHandler.registerTOP();
    }
    
    public void init(FMLInitializationEvent event) 
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
    
    public void postInit(FMLPostInitializationEvent event) 
    {
        if (config.hasChanged())
        {
            config.save();
        }
    }
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) 
    {
        event.getRegistry().register(new BlockAttractor());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) 
    {
        event.getRegistry().register(new ItemBlock(ModBlocks.blockAttractor).setRegistryName(ModBlocks.blockAttractor.getRegistryName()));
    }
}
