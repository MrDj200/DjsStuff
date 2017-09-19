package me.mrdj.attractor.tiles;

import java.util.List;
import me.mrdj.attractor.utils.MiscStuff;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;

/**
 *
 * @author Dj
 */
public class TileAttractor extends TileEntity implements ITickable
{
    int counter = 0;
           
    @Override
    public void update() 
    {        
        if(counter == 10)
        {
            List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(getPos().add(-5, -5, -5), getPos().add(5, 5, 5)));
            counter = 0;
            if(list.isEmpty())
            {
               return;
            }
            for( EntityLivingBase e : list)
            {
               attract(e);
            }    
        }else{
            counter++;
        }
    }
    
    
    private void attract(EntityLivingBase entity) 
    {
        //  Check if the Entity is a player
        if(entity instanceof EntityPlayer)
        {
            return;
            //  Cancel the action when its a player
        }
        
        MiscStuff.setEntityMotionToBlock(entity, this.getPos(), 0.25F);
    }
    
}
