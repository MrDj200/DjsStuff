package me.mrdj.attractor.compat.top;

import java.util.function.Function;
import javax.annotation.Nullable;
import mcjty.theoneprobe.api.IProbeHitData;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.IProbeInfoProvider;
import mcjty.theoneprobe.api.ITheOneProbe;
import mcjty.theoneprobe.api.ProbeMode;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInterModComms;

/**
 *
 * @author Dj
 */
public class TOPCompatibility 
{

    private static boolean registered;

    public static void register()
    {
        if (registered)
            return;
        registered = true;
        FMLInterModComms.sendFunctionMessage("theoneprobe", "getTheOneProbe", "me.mrdj.attractor.compat.top.TOPCompatibility$GetTheOneProbe");
    }


    public static class GetTheOneProbe implements Function<ITheOneProbe, Void> 
    {

        public static ITheOneProbe probe;

        @Nullable
        @Override
        public Void apply(ITheOneProbe theOneProbe) {
            probe = theOneProbe;
            //ModTut.logger.log(Level.INFO, "Enabled support for The One Probe");
            probe.registerProvider(new IProbeInfoProvider() {
                @Override
                public String getID()
                {
                    return "modtut:default";
                }

                @Override
                public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, EntityPlayer player, World world, IBlockState blockState, IProbeHitData data) 
                {
                    if (blockState.getBlock() instanceof TOPInfoProvider) {
                        TOPInfoProvider provider = (TOPInfoProvider) blockState.getBlock();
                        provider.addProbeInfo(mode, probeInfo, player, world, blockState, data);
                    }

                }
            });
            return null;
        }
    }
}
