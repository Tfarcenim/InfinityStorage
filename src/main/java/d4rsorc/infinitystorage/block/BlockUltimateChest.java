//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package d4rsorc.infinitystorage.block;

import d4rsorc.infinitystorage.InfinityStorage;
import d4rsorc.infinitystorage.tile.TileInfiniteStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BlockUltimateChest extends Block {

  public static final PropertyDirection FACING = BlockHorizontal.FACING;
  protected static final AxisAlignedBB ULTIMATE_CHEST_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.0625D, 0.9375D, 0.875D, 0.9375D);

  public BlockUltimateChest() {
    super(Material.WOOD);
    this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
  }

  @Override
  public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
    return false;
  }

  public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
    return ULTIMATE_CHEST_AABB;
  }

  public boolean isOpaqueCube(IBlockState state) {
    return false;
  }

  public boolean isFullCube(IBlockState state) {
    return false;
  }

  @SideOnly(Side.CLIENT)
  public boolean hasCustomBreakingProgress(IBlockState state) {
    return true;
  }

  public EnumBlockRenderType getRenderType(IBlockState state) {
    return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
  }

  protected boolean canSilkHarvest() {
    return true;
  }

  public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
    return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
  }

  public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
    worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

  }

  public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
    if (worldIn.getTileEntity(pos) instanceof TileInfiniteStorage) {
      TileInfiniteStorage tileentityshulkerbox = (TileInfiniteStorage)worldIn.getTileEntity(pos);
      ItemStack chest = new ItemStack(InfinityStorage.Objects.ultimate_chest);
      ItemStack stack1 = storeTEInStack(chest,tileentityshulkerbox);
    EntityItem chestEntity = new EntityItem(worldIn,pos.getX(),pos.getY(),pos.getZ(),stack1);
    if (!worldIn.isRemote)
    worldIn.spawnEntity(chestEntity);
    }
  }

  @Override
  public Item getItemDropped(IBlockState state, Random rand, int fortune) {
    return Items.AIR;
  }

  public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
    if (worldIn.isRemote) {
      return true;
    } else {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof TileInfiniteStorage) {
        playerIn.openGui(InfinityStorage.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
      }
      return true;
    }
  }

  public boolean hasTileEntity(IBlockState state) {
    return true;
  }

  @Nullable
  @Override
  public TileEntity createTileEntity(World world, IBlockState state) {
    return new TileInfiniteStorage();  }

  public IBlockState getStateFromMeta(int meta) {
    EnumFacing enumfacing = EnumFacing.byIndex(meta);
    if (enumfacing.getAxis() == Axis.Y) {
      enumfacing = EnumFacing.NORTH;
    }

    return this.getDefaultState().withProperty(FACING, enumfacing);
  }

  public int getMetaFromState(IBlockState state) {
    return state.getValue(FACING).getIndex();
  }

  public IBlockState withRotation(IBlockState state, Rotation rot) {
    return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
  }

  public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
    return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
  }

  protected BlockStateContainer createBlockState() {
    return new BlockStateContainer(this, FACING);
  }

  public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
    return BlockFaceShape.UNDEFINED;
  }

  @Override
  @SideOnly(Side.CLIENT)
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
   // if (stack.hasTagCompound())
    //tooltip.add(stack.getTagCompound().toString());
  }

  public ItemStack storeTEInStack(ItemStack stack, TileEntity te) {
    NBTTagCompound nbttagcompound = te.writeToNBT(new NBTTagCompound());
    stack.setTagInfo("BlockEntityTag", nbttagcompound);
    NBTTagCompound nbttagcompound1 = new NBTTagCompound();
    NBTTagList nbttaglist = new NBTTagList();
    nbttaglist.appendTag(new NBTTagString("(+NBT)"));
    nbttagcompound1.setTag("Lore", nbttaglist);
    stack.setTagInfo("display", nbttagcompound1);
    return stack;
  }
}
