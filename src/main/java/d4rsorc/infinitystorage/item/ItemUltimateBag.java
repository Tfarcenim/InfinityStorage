//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package d4rsorc.infinitystorage.item;

import javax.annotation.Nullable;

import d4rsorc.infinitystorage.InfinityStorage;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemUltimateBag extends Item {
  public ItemUltimateBag() {
    this.addPropertyOverride(new ResourceLocation("open"), new IItemPropertyGetter() {
      @SideOnly(Side.CLIENT)
      public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
        return ItemUltimateBag.isOpen(stack) ? 1.0F : 0.0F;
      }
    });
  }

  protected static boolean isOpen(ItemStack stack) {
    if (stack.hasTagCompound()) {
      return stack.getTagCompound().hasKey("open") && stack.getTagCompound().getBoolean("open");
    } else {
      return false;
    }
  }

  public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
    if (worldIn.isRemote) {
      return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    } else {
      if (playerIn.getHeldItemMainhand().getItem() == this) {
        playerIn.openGui(InfinityStorage.instance, 2, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
      } else {
        playerIn.openGui(InfinityStorage.instance, 2, worldIn, playerIn.getPosition().getX(), playerIn.getPosition().getY(), playerIn.getPosition().getZ());
      }

      return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
   // if (stack.hasTagCompound())tooltip.add(stack.getTagCompound().toString());
  }
}
