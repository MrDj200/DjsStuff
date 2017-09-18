package me.mrdj.attractor.utils;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 *
 * @author Dj
 */
public class MiscStuff
{
    public static void setEntityMotionToBlock(Entity entity, BlockPos originBlock, float modifier)
    {
        Vec3d blockVector = new Vec3d(originBlock.getX(), originBlock.getY(), originBlock.getZ());
        
        Vec3d entityVector = entity.getPositionVector();
        Vec3d finalVector = blockVector.subtract(entityVector);
        
        finalVector.normalize();             

        entity.motionX = finalVector.x * modifier;
        entity.motionY = finalVector.y * modifier;
        entity.motionZ = finalVector.z * modifier;
    }
}
