package me.mrdj.attractor.blocks;

import java.util.List;
import java.util.Random;
import me.mrdj.attractor.Attractor;
import me.mrdj.attractor.tiles.TileAttractor;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 *
 * @author Dj
 */
public class BlockAttractor extends BlockBaseDj implements ITileEntityProvider
{
    public BlockAttractor() {
        super("attractor", Material.DRAGON_EGG, Attractor.MODID);
        this.setHarvestLevel("pickaxe", 3);
        this.setHardness(10);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setLightOpacity(0);
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) 
    {
        if(entity instanceof EntityPlayer)
        {
            return true;
        }
        return false;
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
    
    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) 
    {
        tooltip.add("Ender Dragon farm?");
        tooltip.add("Ender Dragon farm!");
    }  
    
    @Override
    @SuppressWarnings("deprecation")
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) 
    {
        return new TileAttractor();
    }
}