package d4rsorc.infinitystorage.network;

import java.io.IOException;

import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.handler.codec.EncoderException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;

public class NetworkUtils {

  public static void writeNBT(ByteBuf buf, @Nullable NBTTagCompound nbt) {
    if (nbt == null) {
      buf.writeByte(0);
    } else {
      try {
        CompressedStreamTools.write(nbt, new ByteBufOutputStream(buf));
      } catch (IOException ioexception) {
        throw new EncoderException(ioexception);
      }
    }
  }

  public static NBTTagCompound readNBT(ByteBuf buf) {
    int i = buf.readerIndex();
    byte b0 = buf.readByte();

    if (b0 == 0) {
      return null;
    } else {
      buf.readerIndex(i);
      try {
        return CompressedStreamTools.read(new ByteBufInputStream(buf), new NBTSizeTracker(2097152L));
      } catch (IOException ioexception) {
        throw new EncoderException(ioexception);
      }
    }
  }

  public static void writeExtendedItemStack(ByteBuf buf, ItemStack stack) {
    if (stack.isEmpty()) {
      buf.writeInt(-1);
    } else {
      buf.writeInt(Item.getIdFromItem(stack.getItem()));
      buf.writeInt(stack.getCount());
      buf.writeInt(stack.getMetadata());
      NBTTagCompound nbttagcompound = null;

      if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
        nbttagcompound = stack.getItem().getNBTShareTag(stack);
      }

      writeNBT(buf, nbttagcompound);
    }
  }

  public static void writeExtendedItemStackFromClientToServer(ByteBuf buf, ItemStack stack) {
    if (stack.isEmpty()) {
      buf.writeInt(-1);
    } else {
      buf.writeInt(Item.getIdFromItem(stack.getItem()));
      buf.writeInt(stack.getCount());
      buf.writeInt(stack.getMetadata());
      NBTTagCompound nbttagcompound = null;

      if (stack.getItem().isDamageable() || stack.getItem().getShareTag()) {
        nbttagcompound = stack.getTagCompound();
      }

      writeNBT(buf, nbttagcompound);
    }
  }

  public static ItemStack readExtendedItemStack(ByteBuf buf) throws IOException {
    int i = buf.readInt();

    if (i < 0) {
      return ItemStack.EMPTY;
    } else {
      int j = buf.readInt();
      int k = buf.readInt();
      ItemStack itemstack = new ItemStack(Item.getItemById(i), j, k);
      itemstack.setTagCompound(readNBT(buf));
      return itemstack;
    }
  }

}
