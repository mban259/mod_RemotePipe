package net.minecraft.src;

import java.util.HashMap;
import java.util.LinkedList;

import net.minecraft.src.buildcraft.krapht.IRequestItems;
import net.minecraft.src.buildcraft.krapht.RoutedPipe;
import net.minecraft.src.buildcraft.krapht.gui.GuiOrderer;
import net.minecraft.src.buildcraft.krapht.logic.TemporaryLogic;
import net.minecraft.src.buildcraft.logisticspipes.modules.ILogisticsModule;
import net.minecraft.src.krapht.ItemIdentifier;

public class RemotePipe extends RoutedPipe implements IRequestItems {

	private final LinkedList<HashMap<ItemIdentifier, Integer>> _history = new LinkedList<HashMap<ItemIdentifier, Integer>>();
	public RemotePipe(int itemID) {
		super(new TemporaryLogic(), itemID);
	}

	@Override
	public int getCenterTexture() {
		return mod_RemotePipe.RemotePipeTexture;
	}

	@Override
	public ILogisticsModule getLogisticsModule() {
		return null;
	}

	@Override
	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
		NBTTagCompound nbtTagCompound2=new NBTTagCompound();
		if (entityplayer.getCurrentEquippedItem() != null
				&& entityplayer.getCurrentEquippedItem().getItem() == mod_RemotePipe.EnderRemoteOrderer) {
			entityplayer.addChatMessage("Conected To Pipe");
			entityplayer.getCurrentEquippedItem().setTagCompound(nbtTagCompound2);
			nbtTagCompound2.setInteger("xxxxx", i);
			nbtTagCompound2.setInteger("yyyyy", j);
			nbtTagCompound2.setInteger("zzzzz", k);
		}

		return super.blockActivated(world, i, j, k, entityplayer);

	}
	public void OpenGUI(EntityPlayer entityPlayer) {
		ModLoader.getMinecraftInstance().displayGuiScreen(new GuiOrderer(this, entityPlayer));
	}

	public void setting() {
	}

	@Override
	public void updateEntity() {
		super.updateEntity();
		if (this.worldObj.getWorldTime() % 1200 == 0) {
			_history.addLast(
					core_LogisticsPipes.logisticsManager.getAvailableItems(getRouter().getRouteTable().keySet()));
			if (_history.size() > 20) {
				_history.removeFirst();
			}
		}
	}

	public LinkedList<HashMap<ItemIdentifier, Integer>> getHistory() {
		return _history;
	}

	// @Override
	// public Router getRouter() {
	// return router;
	// }
}
