package net.minecraft.src;

import java.util.ArrayList;

import net.minecraft.src.buildcraft.transport.BlockGenericPipe;

public class EnderRemoteOrderer extends Item {

	protected EnderRemoteOrderer(int par1) {
		super(par1);
	}

	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z,
			int side) {
		RemotePipe pipe;
		NBTTagCompound nbtTagCompound;

		try {
			pipe = (RemotePipe) BlockGenericPipe.getPipe(world, x, y, z);
			pipe.setting();
		} catch (Exception exception) {
			onItemRightClick(itemStack, world, entityPlayer);
		}
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		NBTTagCompound nbtTagCompound2;
		nbtTagCompound2 = itemStack.getTagCompound();
		RemotePipe pipe;

		try {
			pipe = (RemotePipe) BlockGenericPipe.getPipe(world, nbtTagCompound2.getInteger("xxxxx"),
					nbtTagCompound2.getInteger("yyyyy"), nbtTagCompound2.getInteger("zzzzz"));
			pipe.OpenGUI(entityPlayer);
		} catch (Exception e) {
		}

		return itemStack;
	}
}