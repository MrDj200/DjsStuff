package me.mrdj.djsstuff.blocks.attractor;

/*
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
*/

import java.util.List;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.ProbeMode;
import me.mrdj.djsstuff.DjsStuff;
import me.mrdj.djsstuff.blocks.BlockBaseDj;
import me.mrdj.djsstuff.compat.top.TOPInfoProvider;
import me.mrdj.djsstuff.config.Config;
import me.mrdj.djsstuff.tiles.TileAttractor;
import net.minecraft.block.Block;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 *
 * @author Dj
 */
public class BlockAttractor extends BlockBaseDj implements ITileEntityProvider, TOPInfoProvider//, IPeripheral
{
    
    public BlockAttractor() {
        super("attractor", Material.DRAGON_EGG, DjsStuff.MODID);
        this.setHarvestLevel("pickaxe", 3);
        this.setHardness(10);
        this.setCreativeTab(CreativeTabs.MISC);
        this.setLightOpacity(0);
    }
    
    @Override
    public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return side == EnumFacing.DOWN;
    }    

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if( playerIn.isSneaking() && playerIn.getHeldItemMainhand().isItemEqual(new ItemStack(Item.getByNameOrId("minecraft:stick"))) )
        {
            if ( worldIn.getTileEntity(pos) instanceof TileAttractor )
            {
                TileAttractor myTile = (TileAttractor) worldIn.getTileEntity(pos);
                myTile.setIsInverted(!myTile.getIsInverted());
                return true;
            }
        }
        return false;
    }     
    
    @Override
    public boolean canPlaceTorchOnTop(IBlockState state, IBlockAccess world, BlockPos pos) {
        return false;
    }
    
    @Override
    public boolean canConnectRedstone(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return true;
    }    

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) 
    {
        TileAttractor myTile = (TileAttractor) worldIn.getTileEntity(pos);
        int powered = worldIn.isBlockIndirectlyGettingPowered(pos);
        if( powered > 0 )
        {
            
            myTile.setIsActive(false);
            return;
        }        
        myTile.setIsActive(true);
        return;
    }

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) 
    {
        return entity instanceof EntityPlayer || Config.canBeMobGriefed;
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
        return new TileAttractor();
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
                    .text("Range: " + attractorTile.getCurRange() + " Blocks");
            probeInfo.horizontal()
                    .text("Active: " + attractorTile.getIsActive());
            probeInfo.horizontal()
                    .text("Inverted: " + attractorTile.getIsInverted());
                    
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