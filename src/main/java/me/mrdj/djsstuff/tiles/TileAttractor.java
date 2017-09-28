package me.mrdj.djsstuff.tiles;

import java.util.List;
import me.mrdj.djsstuff.config.Config;
import me.mrdj.djsstuff.utils.MiscStuff;
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
    private int counter = 0;
    private int curRange = Config.totalMaxAttractorRadius;
    private int maxHigh, maxLow;
    private boolean isActive = true;
    private boolean isInverted = false;
    
    private AxisAlignedBB myAABB = null;         
           
    @Override
    public void update() 
    {   
        if(!isActive)
        {
            return;
        }
        if(counter == 5)
        {
            maxHigh = curRange;
            maxLow = curRange * (- 1);                  
            
            myAABB = new AxisAlignedBB(getPos().add(maxLow, maxLow, maxLow), getPos().add(maxHigh, maxHigh, maxHigh));
            List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, myAABB);
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

    public int getCurRange() {
        return curRange;
    }

    public void setCurRange(int curRange) {
        this.curRange = curRange;
    }
            
    private void attract(EntityLivingBase entity) 
    {
        //  Check if the Entity is a player
        if(entity instanceof EntityPlayer)
        {
            return;
            //  Cancel the action when its a player
        }
        
        MiscStuff.setEntityMotionToBlock(entity, this, 0.25F);
    }

    public boolean getIsActive() 
    {
        return isActive;
    }

    public void setIsActive(boolean isActive) 
    {
        this.isActive = isActive;
    }    

    public boolean getIsInverted() {
        return isInverted;
    }

    public void setIsInverted(boolean isInverted) {
        this.isInverted = isInverted;
    }
    
    
}
