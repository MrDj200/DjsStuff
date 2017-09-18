package me.mrdj.attractor.events;

import me.mrdj.attractor.Attractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

/**
 *
 * @author Dj
 */
public class EventHandler 
{
    
    @SubscribeEvent
    public void tookFallDmg (LivingFallEvent event)
    {
        if ( event.getEntity() instanceof EntityPlayer )
        {
            if( Attractor.DEVENV || event.getEntity().getName().equals("MrDj200") )
            {
                event.setCanceled(true);
            }            
        }             
    }
    
    @SubscribeEvent
    public void onUpdate(ClientTickEvent event)
    {
    }
}
