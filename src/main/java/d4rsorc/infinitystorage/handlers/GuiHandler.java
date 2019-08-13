//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package d4rsorc.infinitystorage.handlers;

import d4rsorc.infinitystorage.InfinityBagHandler;
import d4rsorc.infinitystorage.InfinityHandler;
import d4rsorc.infinitystorage.container.InfinityBagContainer;
import d4rsorc.infinitystorage.container.InfinityBagScreen;
import d4rsorc.infinitystorage.container.InfinityChestContainer;
import d4rsorc.infinitystorage.container.InfinityChestScreen;
import d4rsorc.infinitystorage.tile.TileInfiniteStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import static d4rsorc.infinitystorage.InfinityStorage.Objects.*;

public class GuiHandler implements IGuiHandler {


 /* public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == 1) {
      return new InfinityChestContainer(player.inventory, new BlockPos(x, y, z), player);
    } else if (ID == 2) {
      ItemUltimateBag item;
      if (player.getHeldItemMainhand().getItem() == UltimateStorage.Objects.ultimate_bag) {
        item = (ItemUltimateBag)player.getHeldItemMainhand().getItem();
        return new InfinityChestContainer(player.inventory, new BlockPos(x, y, z), player);
      } else {
        item = (ItemUltimateBag)player.getHeldItemOffhand().getItem();
        return new InfinityChestContainer(player.inventory, new BlockPos(x, y, z), player);
      }
    } else {
      return null;
    }
  }

  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    if (ID == 1) {
      return new InfinityChestScreen(new InfinityChestContainer(player.inventory,new BlockPos(x,y,z),player));
    } else if (ID == 2) {
      ItemUltimateBag item;
      if (player.getHeldItemMainhand().getItem() == UltimateStorage.Objects.ultimate_bag) {
        item = (ItemUltimateBag)player.getHeldItemMainhand().getItem();
        return new InfinityChestScreen(new InfinityChestContainer(player.inventory,new BlockPos(x,y,z),player));
      } else {
        item = (ItemUltimateBag)player.getHeldItemOffhand().getItem();
        return new InfinityChestScreen(new InfinityChestContainer(player.inventory,new BlockPos(x,y,z),player));
      }
    } else {
      return null;
    }
  }*/


  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    BlockPos pos = new BlockPos(x, y, z);
    TileEntity te = world.getTileEntity(pos);
    if (ID == 1 && te instanceof TileInfiniteStorage) {
      return new InfinityChestContainer((TileInfiniteStorage) te, player);
    }
    if (ID == 2 && player.getHeldItemMainhand().getItem() == ultimate_bag) {
      InfinityBagHandler handler = new InfinityBagHandler(27,player.getHeldItemMainhand());
      return new InfinityBagContainer(handler,player);
    }
    return null;
  }

  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    BlockPos pos = new BlockPos(x, y, z);
    TileEntity te = world.getTileEntity(pos);
    if (ID == 1 && te instanceof TileInfiniteStorage) {
      InfinityChestContainer container = new InfinityChestContainer((TileInfiniteStorage) te, player);
      return new InfinityChestScreen(container, player.inventory);
    }
    if (ID == 2 && player.getHeldItemMainhand().getItem() == ultimate_bag) {
      InfinityBagHandler handler = new InfinityBagHandler(27,player.getHeldItemMainhand());
      InfinityBagContainer container = new InfinityBagContainer(handler,player);
      return new InfinityBagScreen(container,player.inventory);
    }

    return null;
  }
}

