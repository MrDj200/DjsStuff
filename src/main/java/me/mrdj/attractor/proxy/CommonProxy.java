package me.mrdj.attractor.proxy;

import me.mrdj.attractor.blocks.BlockAttractor;
import me.mrdj.attractor.blocks.ModBlocks;
import me.mrdj.attractor.events.EventHandler;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 *
 * @author Dj
 */
@Mod.EventBusSubscriber
public class CommonProxy 
{
    public void registerModels() {}
    
    public void preInit(FMLPreInitializationEvent event) 
    {
    }
    
    public void init(FMLInitializationEvent event) 
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
    
    public void postInit(FMLPostInitializationEvent event) 
    {
        
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
