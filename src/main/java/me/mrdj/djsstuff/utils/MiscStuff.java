package me.mrdj.djsstuff.utils;

import me.mrdj.djsstuff.tiles.TileAttractor;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 *
 * @author Dj
 */
public class MiscStuff
{
    private static BlockPos originBlock = null;
    private static Vec3d blockVector = null;
    
    public static void setEntityMotionToBlock(Entity entity, TileAttractor originTile, float modifier)
    {
        originBlock = originTile.getPos();
        blockVector = new Vec3d(originBlock.getX(), originBlock.getY(), originBlock.getZ());
        
        Vec3d entityVector = entity.getPositionVector();
        Vec3d finalVector = blockVector.subtract(entityVector);
        
        finalVector.normalize();             
        
        if(originTile.getIsInverted())
        {
            modifier = modifier * (-1);
        }

        entity.motionX = finalVector.x * modifier;
        entity.motionY = finalVector.y * modifier;
        entity.motionZ = finalVector.z * modifier;
    }
}
