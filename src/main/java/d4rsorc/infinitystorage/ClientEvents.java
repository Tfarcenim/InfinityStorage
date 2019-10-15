package d4rsorc.infinitystorage;

import d4rsorc.infinitystorage.tile.TESRUltimateChest;
import d4rsorc.infinitystorage.tile.TileInfiniteStorage;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT,modid = InfinityStorage.MODID)

public class ClientEvents {
  @SubscribeEvent
  public static void registerRenders(ModelRegistryEvent event) {
    registerRender(Item.getItemFromBlock(InfinityStorage.Objects.ultimate_chest));
    ClientRegistry.bindTileEntitySpecialRenderer(TileInfiniteStorage.class, new TESRUltimateChest());
    registerRender(InfinityStorage.Objects.ultimate_bag);
  }

  public static void registerRender(Item item) {
    ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
  }
}
