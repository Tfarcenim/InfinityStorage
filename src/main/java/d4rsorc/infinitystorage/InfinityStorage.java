//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package d4rsorc.infinitystorage;

import d4rsorc.infinitystorage.block.BlockUltimateChest;
import d4rsorc.infinitystorage.handlers.GuiHandler;
import d4rsorc.infinitystorage.item.ItemUltimateBag;
import d4rsorc.infinitystorage.network.PacketHandler;
import d4rsorc.infinitystorage.tile.TileInfiniteStorage;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(
        modid = "infinitystorage",
        name = "Infinity Storage",
        version = "@VERSION@",
        acceptedMinecraftVersions = "[1.12.2]"
)
@Mod.EventBusSubscriber
public class InfinityStorage {
  public static final String MODID = "infinitystorage";
  @Instance
  public static InfinityStorage instance;

  public static final CreativeTabs tab = new CreativeTabs(MODID){

    @Override
    public ItemStack createIcon() {
      return new ItemStack(Objects.ultimate_chest);
    }
  };

  @SubscribeEvent
  public static void registerBlocks(RegistryEvent.Register<Block> event) {
    event.getRegistry().register(new BlockUltimateChest().setCreativeTab(InfinityStorage.tab).setRegistryName("ultimate_chest").setTranslationKey(MODID+":ultimate_chest"));
  }

  @SubscribeEvent
  public static void registerItems(RegistryEvent.Register<Item> event) {
    event.getRegistry().register(new ItemUltimateBag().setCreativeTab(InfinityStorage.tab).setRegistryName("ultimate_bag").setTranslationKey(MODID+":ultimate_bag"));
    event.getRegistry().register((new ItemBlock(InfinityStorage.Objects.ultimate_chest)).setRegistryName(InfinityStorage.Objects.ultimate_chest.getRegistryName()));
    GameRegistry.registerTileEntity(TileInfiniteStorage.class, InfinityStorage.Objects.ultimate_chest.getRegistryName());
  }

  @EventHandler
  public void init(FMLInitializationEvent event) {
    NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
    PacketHandler.registerMessages();
  }
  @GameRegistry.ObjectHolder(MODID)
  public static class Objects {
    public static final Item ultimate_bag = null;
    public static final Block ultimate_chest = null;
  }
}
