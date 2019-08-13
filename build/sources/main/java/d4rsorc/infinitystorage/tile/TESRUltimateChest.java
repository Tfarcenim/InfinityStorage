//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package d4rsorc.infinitystorage.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "infinitystorage",value = Side.CLIENT)
public class TESRUltimateChest extends TileEntitySpecialRenderer<TileInfiniteStorage> {
  private final ModelChest modelChest = new ModelChest();
  private static final ResourceLocation ULTIMATE_CHEST_TEXTURE = new ResourceLocation("infinitystorage:blocks/ultimate_chest");
  public static int X;
  public static int Y;

  public TESRUltimateChest() {
  }

  @SubscribeEvent
  public static void getMap(TextureStitchEvent event) {
    event.getMap().registerSprite(ULTIMATE_CHEST_TEXTURE);
  }

  public void render(TileInfiniteStorage te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
    int i = 0;
    TextureMap map = Minecraft.getMinecraft().getTextureMapBlocks();
    TextureAtlasSprite atlas = map.getAtlasSprite(ULTIMATE_CHEST_TEXTURE.toString());
    X = atlas.getOriginX();
    Y = atlas.getOriginY();
    if (te.hasWorld()) {
      i = te.getBlockMetadata();
    }

    if (destroyStage >= 0) {
      this.bindTexture(DESTROY_STAGES[destroyStage]);
      GlStateManager.matrixMode(5890);
      GlStateManager.pushMatrix();
      GlStateManager.scale(4.0F, 4.0F, 1.0F);
      GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
      GlStateManager.matrixMode(5888);
    } else {
      this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
      int w = GlStateManager.glGetTexLevelParameteri(3553, 0, 4096);
      int h = GlStateManager.glGetTexLevelParameteri(3553, 0, 4097);
      this.modelChest.chestBelow.setTextureSize(w, h);
      this.modelChest.chestBelow.cubeList.remove(0);
      this.modelChest.chestBelow.cubeList.add(new ModelBox(this.modelChest.chestBelow, X, Y + 19, 0.0F, 0.0F, 0.0F, 14, 10, 14, 0.0F));
      this.modelChest.chestKnob.setTextureSize(w, h);
      this.modelChest.chestKnob.cubeList.remove(0);
      this.modelChest.chestKnob.cubeList.add(new ModelBox(this.modelChest.chestKnob, X, Y, -1.0F, -2.0F, -15.0F, 2, 4, 1, 0.0F));
      this.modelChest.chestLid.setTextureSize(w, h);
      this.modelChest.chestLid.cubeList.remove(0);
      this.modelChest.chestLid.cubeList.add(new ModelBox(this.modelChest.chestLid, X, Y, 0.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F));
    }

    GlStateManager.pushMatrix();
    GlStateManager.enableRescaleNormal();
    GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
    GlStateManager.translate((float)x, (float)y + 1.0F, (float)z + 1.0F);
    GlStateManager.scale(1.0F, -1.0F, -1.0F);
    GlStateManager.translate(0.5F, 0.5F, 0.5F);
    int j = 0;
    if (i == 2) {
      j = 180;
    }

    if (i == 3) {
      j = 0;
    }

    if (i == 4) {
      j = 90;
    }

    if (i == 5) {
      j = -90;
    }

    GlStateManager.rotate((float)j, 0.0F, 1.0F, 0.0F);
    GlStateManager.translate(-0.5F, -0.5F, -0.5F);
    float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;
    f = 1.0F - f;
    f = 1.0F - f * f * f;
    this.modelChest.chestLid.rotateAngleX = -(f * 1.5707964F);
    this.modelChest.renderAll();
    GlStateManager.disableRescaleNormal();
    GlStateManager.popMatrix();
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
    if (destroyStage >= 0) {
      GlStateManager.matrixMode(5890);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
    }

  }
}
