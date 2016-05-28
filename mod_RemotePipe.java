package net.minecraft.src;

import net.minecraft.src.buildcraft.core.CoreProxy;
import net.minecraft.src.buildcraft.transport.BlockGenericPipe;
import net.minecraft.src.buildcraft.transport.Pipe;
import net.minecraft.src.forge.MinecraftForgeClient;

public class mod_RemotePipe extends BaseMod {
	public static Item RemotePipeItem;
	public static Item EnderRemoteOrderer;
	private final String RemotePipePath = "texture/RemotePipe.png";
	private final String EnderRemoteOrdererPath="texture/Orderer.png";
	static int EnderOrdererTexture;
	public static int RemotePipeTexture;
	@MLProp(min=256)
	public static int RemotePipeID = 11111;
	@MLProp(min=256)
	static public int EnderRemoteOrdererID = 11112;

	public String getVersion() {
		return "1.0.0";
	}

	public void load() {
		EnderRemoteOrderer = new EnderRemoteOrderer(EnderRemoteOrdererID-256).setItemName("EnderRemoteOrderer");
		ModLoader.addName(EnderRemoteOrderer, "EnderRemoteOrderer");
		EnderRemoteOrderer.iconIndex=ModLoader.addOverride("/gui/items.png", EnderRemoteOrdererPath);
	}

	public void modsLoaded() {
		RemotePipeTexture = CoreProxy.addCustomTexture(this.RemotePipePath);
		RemotePipeItem = create(RemotePipeID-256, RemotePipe.class, "RemotePipe");
		addRecipe();
	}
	public void addRecipe(){
ModLoader.addRecipe(new ItemStack(EnderRemoteOrderer,1),new Object[]{"X","Y",Character.valueOf('X'),Item.enderPearl,Character.valueOf('Y'),core_LogisticsPipes.LogisticsRemoteOrderer});
ModLoader.addRecipe(new ItemStack(RemotePipeItem,1),new Object[]{"X","Y",Character.valueOf('X'),Item.enderPearl,Character.valueOf('Y'),core_LogisticsPipes.LogisticsBasicPipe});

	}

	protected static Item create(int defaultID, Class<? extends Pipe> clas, String descr) {
		// String name = Character.toLowerCase(clas.getSimpleName().charAt(0))
		// + clas.getSimpleName().substring(1);

		Item res = BlockGenericPipe.registerPipe(defaultID, clas);
		res.setItemName(clas.getSimpleName());
		CoreProxy.addName(res, descr);
		MinecraftForgeClient.registerItemRenderer(res.shiftedIndex, mod_BuildCraftTransport.instance);

		return res;
	}
	@Override
	public String getPriorities()
	{
		return "before:mod_CompactEngine";
	}

}
