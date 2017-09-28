package me.mrdj.djsstuff.blocks;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author Dj
 */
public class BlockBaseDj extends Block
{    
    public BlockBaseDj(String name, Material materialIn, String modid)
    {
        super(materialIn);
        setUnlocalizedName(modid + "." + name);
        setRegistryName(name);
    }    
    
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) 
    {
        return Item.getItemFromBlock(this);
    }
    
    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random)
    {
        return 1;
    }
    
    
}