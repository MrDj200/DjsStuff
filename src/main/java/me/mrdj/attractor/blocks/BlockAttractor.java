package me.mrdj.attractor.blocks;

/*
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
*/

import java.util.List;
import java.util.Random;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import me.mrdj.attractor.Attractor;
import me.mrdj.attractor.compat.top.TOPInfoProvider;
import me.mrdj.attractor.config.Config;
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
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 *
 * @author Dj
 */
public class BlockAttractor extends BlockBaseDj implements ITileEntityProvider, TOPInfoProvider//, IPeripheral
{
    
    private TileAttractor m_attractor;
    
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
        return entity instanceof EntityPlayer || Config.canBeMobGriefed;
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
        tooltip.add(TextFormatting.DARK_RED + "Ender Dragon farm?");
        tooltip.add(TextFormatting.GREEN + "Ender Dragon farm!");
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
        m_attractor = new TileAttractor();
        return m_attractor;
    }

    @Override
    public boolean hasTileEntity() {
        return true;
    } 
    
    @Override
    public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data)
    {
        TileEntity te = world.getTileEntity(data.getPos());
        if (te instanceof TileAttractor) {
            TileAttractor attractorTile = (TileAttractor) te;
            probeInfo.horizontal()
                    //  .item(Item.getItemFromBlock(this).getDefaultInstance())
                    .text("Range: " + attractorTile.getCurRange() + " Blocks");
        }
    }
    /*
    @Override
    public String getType() 
    {
        return "attractor";
    }

    @Override
    public String[] getMethodNames() 
    {
        return new String[]
        {
            "getEntityList",
            "getMaxSize",
            "getSize",
            "setSize"
        };
    }

    @Override
    public Object[] callMethod(IComputerAccess ica, ILuaContext ilc, int method, Object[] os) throws LuaException, InterruptedException 
    {
        switch(method)
        {
            case 0:
            {
                //getEntityList
                return new Object[]{"Not supported Yet!"};
            }
            case 1:
            {
                // getMaxSize
                return new Object[]{Config.totalMaxAttractorRadius};
            }
            case 2:
            {
                // getSize
                return new Object[]{"Not supported Yet!"};
            }
            case 3:
            {
                // setSize     
                return new Object[]{"Not supported Yet!"};
            }
            default:
            {
                throw new LuaException("Method index out of range!");
            }
        }
    }

    @Override
    public boolean equals(IPeripheral ip) 
    {
        if( ip != null && ip instanceof BlockAttractor )
        {
            BlockAttractor otherBlock = (BlockAttractor) ip;
            return otherBlock.m_attractor == m_attractor;
        }
        return false;
    }
    */
}