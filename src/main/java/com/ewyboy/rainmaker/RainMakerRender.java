package com.ewyboy.rainmaker;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RainMakerRender implements ISimpleBlockRenderingHandler {

    public int renderID;

    public RainMakerRender() {
        renderID = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

        Tessellator tessellator = Tessellator.instance;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

        tessellator.startDrawingQuads();
            renderWater(tessellator, 0.0, 0.0, 0.0);
            renderGlassContainer(tessellator, 0.0, 0.0, 0.0);
        tessellator.draw();

        block.setBlockBoundsForItemRender();
        renderer.setRenderBoundsFromBlock(block);
    }

    private void renderGlassContainer(Tessellator tessellator, double x, double y, double z) {

        final float FACE_XZ_NORMAL = 0.8944F;
        final float FACE_Y_NORMAL  = 0.4472F;

        IIcon icon1 = Blocks.glass.getIcon(0,0);

        double minU1 = (double)icon1.getMinU();
        double minV1 = (double)icon1.getMinV();
        double maxU1 = (double)icon1.getMaxU();
        double maxV1 = (double)icon1.getMaxV();

        //East
        tessellator.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
            tessellator.addVertexWithUV(x+0.0, y+0.0, z+0.0, maxU1, maxV1);
                tessellator.addVertexWithUV(x+0.30, y+1.0, z+0.30, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.70, y+1.0, z+0.30, minU1, minV1);
            tessellator.addVertexWithUV(x+1.0, y+0.0, z+0.0, minU1, maxV1);
        tessellator.draw();

        //West
        tessellator.startDrawingQuads();
            tessellator.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+1.0, minU1, maxV1);
                    tessellator.addVertexWithUV(x+0.30, y+1.0, z+0.70, minU1, minV1);
                    tessellator.addVertexWithUV(x+0.30, y+1.0, z+0.30, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+0.0, maxU1, maxV1);
        tessellator.draw();

        //North
        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
            tessellator.addVertexWithUV(x+1.0, y+0.0, z+0.0, maxU1, maxV1);
                tessellator.addVertexWithUV(x+0.70, y+1.0, z+0.30, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.70, y+1.0, z+0.70, minU1, minV1);
            tessellator.addVertexWithUV(x+1.0, y+0.0, z+1.0, minU1, maxV1);
        tessellator.draw();

        //South
        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
                tessellator.addVertexWithUV(x+1.0, y+0.0, z+1.0, minU1, maxV1);
                    tessellator.addVertexWithUV(x+0.70, y+1.0, z+0.70, minU1, minV1);
                    tessellator.addVertexWithUV(x+0.30, y+1.0, z+0.70, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+1.0, maxU1, maxV1);
        tessellator.draw();

        //Top
        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, FACE_Y_NORMAL, 0.0F);
                tessellator.addVertexWithUV(x+0.3, y+1.0, z+0.3, maxU1, minV1);
                    tessellator.addVertexWithUV(x+0.3, y+1.0, z+0.7, maxU1, maxV1);
                    tessellator.addVertexWithUV(x+0.7, y+1.0, z+0.7, minU1, maxV1);
                tessellator.addVertexWithUV(x+0.7, y+1.0, z+0.3, minU1, minV1);
        tessellator.draw();

        //Bottom
        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
                tessellator.addVertexWithUV(x+1.0, y+0.0, z+0.0, minU1, maxV1);
                    tessellator.addVertexWithUV(x+1.0, y+0.0, z+1.0, minU1, minV1);
                    tessellator.addVertexWithUV(x+0.0, y+0.0, z+1.0, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+0.0, maxU1, maxV1);
    }

    private void renderWater(Tessellator tessellator, double x, double y, double z) {

        final float FACE_XZ_NORMAL = 0.8944F;
        final float FACE_Y_NORMAL  = 0.4472F;

        IIcon icon1 = Blocks.flowing_water.getIcon(0,0);

        double minU1 = (double)icon1.getMinU();
        double minV1 = (double)icon1.getMinV();
        double maxU1 = (double)icon1.getMaxU();
        double maxV1 = (double)icon1.getMaxV();

        //East
            tessellator.setNormal(FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+0.0, maxU1, maxV1);
                    tessellator.addVertexWithUV(x+0.30, y+0.70, z+0.30, maxU1, minV1);
                    tessellator.addVertexWithUV(x+0.70, y+0.70, z+0.30, minU1, minV1);
                tessellator.addVertexWithUV(x+1.0, y+0.0, z+0.0, minU1, maxV1);
        tessellator.draw();

        //West
        tessellator.startDrawingQuads();
            tessellator.setNormal(-FACE_XZ_NORMAL, FACE_Y_NORMAL, 0.0F);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+1.0, minU1, maxV1);
                    tessellator.addVertexWithUV(x+0.30, y+0.70, z+0.70, minU1, minV1);
                    tessellator.addVertexWithUV(x+0.30, y+0.70, z+0.30, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+0.0, maxU1, maxV1);
        tessellator.draw();

        //North
        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, FACE_Y_NORMAL, -FACE_XZ_NORMAL);
                tessellator.addVertexWithUV(x+1.0, y+0.0, z+0.0, maxU1, maxV1);
                    tessellator.addVertexWithUV(x+0.70, y+0.70, z+0.30, maxU1, minV1);
                    tessellator.addVertexWithUV(x+0.70, y+0.70, z+0.70, minU1, minV1);
                tessellator.addVertexWithUV(x+1.0, y+0.0, z+1.0, minU1, maxV1);
        tessellator.draw();

        //South
        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, FACE_Y_NORMAL, FACE_XZ_NORMAL);
                tessellator.addVertexWithUV(x+1.0, y+0.0, z+1.0, minU1, maxV1);
                    tessellator.addVertexWithUV(x+0.70, y+0.70, z+0.70, minU1, minV1);
                    tessellator.addVertexWithUV(x+0.30, y+0.70, z+0.70, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+1.0, maxU1, maxV1);
        tessellator.draw();

        // Top
        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, FACE_Y_NORMAL, 0.0F);
                tessellator.addVertexWithUV(x+0.3, y+0.7, z+0.3, maxU1, minV1);
                    tessellator.addVertexWithUV(x+0.3, y+0.7, z+0.7, maxU1, maxV1);
                    tessellator.addVertexWithUV(x+0.7, y+0.7, z+0.7, minU1, maxV1);
                tessellator.addVertexWithUV(x+0.7, y+0.7, z+0.3, minU1, minV1);
        tessellator.draw();

        tessellator.startDrawingQuads();
            tessellator.setNormal(0.0F, -1.0F, 0.0F);
                tessellator.addVertexWithUV(x+1.0, y+0.0, z+0.0, minU1, maxV1);
                    tessellator.addVertexWithUV(x+1.0, y+0.0, z+1.0, minU1, minV1);
                    tessellator.addVertexWithUV(x+0.0, y+0.0, z+1.0, maxU1, minV1);
                tessellator.addVertexWithUV(x+0.0, y+0.0, z+0.0, maxU1, maxV1);
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

        Tessellator tessellator = Tessellator.instance;

        int lightValue = block.getMixedBrightnessForBlock(world, x, y, z);
        tessellator.setBrightness(lightValue);
        tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);

        renderWater(tessellator, (double) x, (double) y, (double) z);
        renderGlassContainer(tessellator, (double)x, (double)y, (double) z);

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderID;
    }
}
