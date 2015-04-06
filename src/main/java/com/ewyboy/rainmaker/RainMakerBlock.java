package com.ewyboy.rainmaker;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class RainMakerBlock extends Block {

    private int rainTime = Config.rainTime * 20;

    public RainMakerBlock() {
        super(Material.glass);
        setHardness(1.0f);
        setCreativeTab(CreativeTabs.tabAllSearch);
    }
    @SideOnly(Side.SERVER)
    public void explode(World world, int x, int y, int z) {
        //world.createExplosion(null,x,y,z,3f,false);
        world.spawnParticle("explosion", x,y,z,0d,0d,0d);
        world.setBlockToAir(x,y,z);
    }

    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (world.isRaining() && !world.isRemote) {
            EntityLightningBolt Lightning = new EntityLightningBolt(world, 10, 10, 10);
            Lightning.setPosition(x, y, z);
            world.spawnEntityInWorld(Lightning);
            explode(world, x, y, z);
        }
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        if (world.canBlockSeeTheSky(x,y+1,z)) {

            world.getWorldInfo().setRaining(true);
            world.getWorldInfo().setRainTime(rainTime);
            System.out.println("Rain time = " + rainTime);

            if (Config.debugMode) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("Rainmaker activated - Raining for " + rainTime);
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        double posX = (double)((float)x + 0.60F);
        double posY = (double)((float)y + 1.0F + ((float)Math.random() * (3 - 0) + 0));
        double posZ = (double)((float)z + 0.60F);

        int times = 1000;
        int i;

        for(i = 0; i < times; i++) {
            world.spawnParticle("splash", posX , posY + (i / 300) , posZ, 0.0D, 0.0D, 0.0D);
            world.spawnParticle("bobble", posX , posY + (i / 300) , posZ, 0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        world.createExplosion(null,x,y,z,1f,true);
        world.newExplosion(null,x,y,z,1f,true,true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
       return Blocks.glass.getBlockTextureFromSide(1);
    }

    @Override
    public void setBlockBoundsForItemRender() {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int x, int y, int z) {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end) {
        setBlockBoundsBasedOnState(world, x, y, z);
        return super.collisionRayTrace(world, x, y, z, start, end);
    }

    @Override
    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack) {
        return;
    }

    @Override
    public int getRenderType() { return RainMaker.proxy.rainmakerRenderID; }

    @Override
    public boolean isOpaqueCube() { return false; }

    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() { return 0; }

    @Override
    public boolean isBlockSolid(IBlockAccess iBlockAccess, int x, int y, int z, int i) { return true; }

}
