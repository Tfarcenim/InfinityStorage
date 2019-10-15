package d4rsorc.infinitystorage.network;

import d4rsorc.infinitystorage.InfinityStorage;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

  public static SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(InfinityStorage.MODID);

  private static int id = 0;

  public static void registerMessages() {
    INSTANCE.registerMessage(MessageSyncExtendedSlotContents.Handler.class, MessageSyncExtendedSlotContents.class, id++, Side.CLIENT);

    //INSTANCE.registerMessage(MessageClickWindowExtended.Handler.class, MessageClickWindowExtended.class, id++, Side.SERVER);
  }
}
