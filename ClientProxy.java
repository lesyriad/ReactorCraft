/*******************************************************************************
 * @author Reika Kalseki
 * 
 * Copyright 2013
 * 
 * All rights reserved.
 * Distribution of the software in any form is only allowed with
 * explicit, prior permission from the owner.
 ******************************************************************************/
package Reika.ReactorCraft;

import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import Reika.DragonAPI.Instantiable.Rendering.ItemSpriteSheetRenderer;
import Reika.ReactorCraft.Auxiliary.ReactorRenderList;
import Reika.ReactorCraft.Entities.EntityNeutron;
import Reika.ReactorCraft.Entities.EntityPlasma;
import Reika.ReactorCraft.Entities.EntityRadiation;
import Reika.ReactorCraft.Entities.RenderNeutron;
import Reika.ReactorCraft.Entities.RenderPlasma;
import Reika.ReactorCraft.Entities.RenderRadiation;
import Reika.ReactorCraft.Registry.ReactorItems;
import Reika.ReactorCraft.Registry.ReactorTiles;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	public static final ItemSpriteSheetRenderer[] items = {
		new ItemSpriteSheetRenderer(ReactorCraft.instance, ReactorCraft.class, "Textures/Items/items1.png"),
	};

	private static final ReactorItemRenderer reactor = new ReactorItemRenderer();

	@Override
	public void registerSounds() {
		//MinecraftForge.EVENT_BUS.register(new SoundLoader(ReactorCraft.instance, SoundRegistry.soundList));
	}

	@Override
	public void registerRenderers() {
		this.loadModels();

		this.registerSpriteSheets();

		RenderingRegistry.registerEntityRenderingHandler(EntityNeutron.class, new RenderNeutron());
		RenderingRegistry.registerEntityRenderingHandler(EntityRadiation.class, new RenderRadiation());
		RenderingRegistry.registerEntityRenderingHandler(EntityPlasma.class, new RenderPlasma());
	}

	private void registerSpriteSheets() {

		for (int i = 0; i < ReactorItems.itemList.length; i++) {
			if (ReactorItems.itemList[i].getSpriteIndex() > -1)
				MinecraftForgeClient.registerItemRenderer(ReactorItems.itemList[i].getShiftedItemID(), items[ReactorItems.itemList[i].getSpriteSheet()]);
		}
	}

	public void loadModels() {
		for (int i = 0; i < ReactorTiles.TEList.length; i++) {
			ReactorTiles m = ReactorTiles.TEList[i];
			if (m.hasRender() && m.renderWorks()) {
				ClientRegistry.bindTileEntitySpecialRenderer(m.getTEClass(), ReactorRenderList.instantiateRenderer(m));
			}
		}

		MinecraftForgeClient.registerItemRenderer(ReactorItems.PLACER.getShiftedItemID(), reactor);
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

}
