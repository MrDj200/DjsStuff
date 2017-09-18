package me.mrdj.attractor.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author Dj
 */
public class ModBlocks 
{    
    @GameRegistry.ObjectHolder("djsattractor:attractor")
    public static BlockAttractor blockAttractor;
    
    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        blockAttractor.initModel();
    }
}
