package net.cubition.minecraft;

import java.io.File;

import net.cubition.api.API;
import net.cubition.api.mod.Mod;
import net.cubition.api.mod.ModResourceFile;
import net.cubition.api.mod.ModResourceFile.Dependency;
import net.cubition.minecraft.block.*;
import net.cubition.minecraft.item.*;
import net.cubition.minecraft.tool.*;

public class MinecraftCoreMod implements Mod {

	private final ModResourceFile resFile = new ModResourceFile
			("Minecraft CoreMod", "Cubition", "0.1", "net.cubition.minecraft.MinecraftCoreMod", new Dependency[0]);
	
	@Override
	public String customController() {
		return null;
	}

	@Override
	public File getDataFolder() {
		return new File (new File (System.getProperty("user.dir"), "mods"), resFile.getName().replace(" ", "_"));
	}

	@Override
	public ModResourceFile getResourceFile() {
		return resFile;
	}

	@Override
	public void initialiaze() {

		// Register all the ToolTypes of Minecraft
		API.getToolTypeRegister ().register(ToolShovel.class.getName (), new ToolShovel ());
		
		// Register all the items of Minecraft
		API.getItemRegister().register(ItemWoodenShovel.class.getName(), new ItemWoodenShovel ());
		
		// Register all blocks of Minecraft
		API.getBlockTypeRegister().register(BlockStone.class.getName(), new BlockStone ());
		API.getBlockTypeRegister().register(BlockGrass.class.getName(), new BlockGrass ());
		API.getBlockTypeRegister().register(BlockDirt.class.getName(), new BlockDirt ());
		API.getBlockTypeRegister().register(BlockCobblestone.class.getName(), new BlockCobblestone());
		API.getBlockTypeRegister().register(BlockSand.class.getName(), new BlockSand ());
		
		// Register all blocks of Minecraft as Items, so they can be in your inventory.
		API.getItemRegister().register(BlockStone.class.getName(), new BlockStone ());
		API.getItemRegister().register(BlockGrass.class.getName(), new BlockGrass ());
		API.getItemRegister().register(BlockDirt.class.getName(), new BlockDirt());
		API.getItemRegister().register(BlockCobblestone.class.getName(), new BlockCobblestone());
		API.getItemRegister().register(BlockSand.class.getName(), new BlockSand ());
		
	}

}
