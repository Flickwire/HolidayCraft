package me.uk.domos.holidaycraft.block;

import java.util.List;

import javax.swing.ImageIcon;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import me.uk.domos.holidaycraft.HolidayCraft;
import me.uk.domos.holidaycraft.tileentity.TileEntityBauble;
import me.uk.domos.holidaycraft.tileentity.TileEntityChineseLantern;
import me.uk.domos.holidaycraft.util.RegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockChineseLantern extends Block {
	
	public static Block instance;
	
	@Override
	public boolean hasTileEntity(int meta){
		return true;
	}
	
	public BlockChineseLantern(int par1) {
		super(par1, Material.cloth);
		setUnlocalizedName("holidaycraft.chineseLantern");
		setHardness(0.2F);
		setResistance(5.0F);
		setStepSound(soundClothFootstep);
		setCreativeTab(RegistryHelper.tabHoliday);
		setTextureName("holidaycraft:chineseLantern");
		setBlockBounds(0.15625F, 0.0625F, 0.15625F, 0.84375F, 1.0F, 0.84375F);
		setLightValue(1.0F);
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
	{
	    return false;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
	    return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
	    return false;
	}
	
	//Can only place below another block
	@Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return (par1World.getBlockMaterial(par2, par3 + 1, par4) != Material.air);
    }
	
	//If the block above this is gone, then poof!
	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (par1World.getBlockMaterial(par2, par3 + 1, par4) == Material.air)
        {
            this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
            par1World.setBlockToAir(par2, par3, par4);
        }
    }
	
	
	//You don't want the normal render type, or it wont render properly.
    @Override
    public int getRenderType() {
            return -1;
    }
	
	@Override
	public TileEntity createTileEntity(World world, int meta){
		try{
			return new TileEntityChineseLantern();
		}
	    catch (Exception var3){
	        throw new RuntimeException(var3);
	    }
	}
}