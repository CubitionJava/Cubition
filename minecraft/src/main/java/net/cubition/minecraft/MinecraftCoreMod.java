package net.cubition.minecraft;

import net.cubition.api.API;
import net.cubition.api.mod.Mod;
import net.cubition.api.mod.ModResourceFile;
import net.cubition.api.mod.ModResourceFile.Dependency;
import net.cubition.minecraft.block.*;
import net.cubition.minecraft.item.*;
import net.cubition.minecraft.tool.ToolAxe;
import net.cubition.minecraft.tool.ToolHoe;
import net.cubition.minecraft.tool.ToolPickaxe;
import net.cubition.minecraft.tool.ToolShovel;
import net.cubition.minecraft.weapon.WeaponArrow;
import net.cubition.minecraft.weapon.WeaponBow;
import net.cubition.minecraft.weapon.WeaponSword;

import java.io.File;

public class MinecraftCoreMod implements Mod {

	private final ModResourceFile resFile = new ModResourceFile(
			"Minecraft CoreMod", "Cubition", "0.1",
			"net.cubition.minecraft.MinecraftCoreMod", new Dependency[0]);

	@Override
	public String customController() {
		return null;
	}

	@Override
	public File getDataFolder() {
		return new File(new File(System.getProperty("user.dir"), "mods"),
				resFile.getName().replace(" ", "_"));
	}

	@Override
	public ModResourceFile getResourceFile() {
		return resFile;
	}

	@Override
	public void initialize() {

		// Register all the ToolTypes of Minecraft
		API.getToolTypeRegister().register(ToolShovel.class.getName(),
				new ToolShovel());
		API.getToolTypeRegister().register(ToolAxe.class.getName(),
				new ToolAxe());
		API.getToolTypeRegister().register(ToolPickaxe.class.getName(),
				new ToolPickaxe());
		API.getToolTypeRegister().register(ToolHoe.class.getName(),
				new ToolHoe());

		// Register all the WeaponTypes of Minecraft
		API.getWeaponTypeRegister().register(WeaponSword.class.getName(),
				new WeaponSword());
		API.getWeaponTypeRegister().register(WeaponBow.class.getName(),
				new WeaponBow());
		API.getWeaponTypeRegister().register(WeaponArrow.class.getName(),
				new WeaponArrow());

		// Register all the items of Minecraft
		API.getItemRegister().register(ItemWoodenSword.class.getName(),
				new ItemWoodenSword());
		API.getItemRegister().register(ItemWoodenShovel.class.getName(),
				new ItemWoodenShovel());
		API.getItemRegister().register(ItemWoodenPickaxe.class.getName(),
				new ItemWoodenPickaxe());
		API.getItemRegister().register(ItemWoodenHoe.class.getName(),
				new ItemWoodenHoe());
		API.getItemRegister().register(ItemWoodenAxe.class.getName(),
				new ItemWoodenAxe());
		API.getItemRegister().register(ItemStoneSword.class.getName(),
				new ItemStoneSword());
		API.getItemRegister().register(ItemStoneShovel.class.getName(),
				new ItemStoneShovel());
		API.getItemRegister().register(ItemStonePickaxe.class.getName(),
				new ItemStonePickaxe());
		API.getItemRegister().register(ItemStoneHoe.class.getName(),
				new ItemStoneHoe());
		API.getItemRegister().register(ItemStoneAxe.class.getName(),
				new ItemStoneAxe());
		API.getItemRegister().register(ItemShears.class.getName(),
				new ItemShears());
		API.getItemRegister().register(ItemIronSword.class.getName(),
				new ItemIronSword());
		API.getItemRegister().register(ItemIronShovel.class.getName(),
				new ItemIronShovel());
		API.getItemRegister().register(ItemIronPickaxe.class.getName(),
				new ItemIronPickaxe());
		API.getItemRegister().register(ItemIronHoe.class.getName(),
				new ItemIronHoe());
		API.getItemRegister().register(ItemIronAxe.class.getName(),
				new ItemIronAxe());
		API.getItemRegister().register(ItemGoldSword.class.getName(),
				new ItemGoldSword());
		API.getItemRegister().register(ItemGoldShovel.class.getName(),
				new ItemGoldShovel());
		API.getItemRegister().register(ItemGoldPickaxe.class.getName(),
				new ItemGoldPickaxe());
		API.getItemRegister().register(ItemGoldHoe.class.getName(),
				new ItemGoldHoe());
		API.getItemRegister().register(ItemGoldAxe.class.getName(),
				new ItemGoldAxe());
		API.getItemRegister().register(ItemDiamondSword.class.getName(),
				new ItemDiamondSword());
		API.getItemRegister().register(ItemDiamondShovel.class.getName(),
				new ItemDiamondShovel());
		API.getItemRegister().register(ItemDiamondPickaxe.class.getName(),
				new ItemDiamondPickaxe());
		API.getItemRegister().register(ItemDiamondHoe.class.getName(),
				new ItemDiamondHoe());
		API.getItemRegister().register(ItemDiamondAxe.class.getName(),
				new ItemDiamondAxe());
		API.getItemRegister().register(ItemBow.class.getName(),
				new ItemBow());
		API.getItemRegister().register(ItemArrow.class.getName(),
				new ItemArrow());

		// Register all blocks of Minecraft
		API.getBlockTypeRegister().register(BlockStone.class.getName(),
				new BlockStone());
		API.getBlockTypeRegister().register(BlockGrass.class.getName(),
				new BlockGrass());
		API.getBlockTypeRegister().register(BlockDirt.class.getName(),
				new BlockDirt());
		API.getBlockTypeRegister().register(BlockCobblestone.class.getName(),
				new BlockCobblestone());
		API.getBlockTypeRegister().register(BlockSand.class.getName(),
				new BlockSand());
		API.getBlockTypeRegister().register(BlockObsidian.class.getName(),
				new BlockObsidian());
		API.getBlockTypeRegister().register(BlockGold.class.getName(),
				new BlockGold());
		API.getBlockTypeRegister().register(BlockTNT.class.getName(),
				new BlockTNT());

		// Register all blocks of Minecraft as Items, so they can be in your
		// inventory.
		API.getItemRegister().register(BlockStone.class.getName(),
				new BlockStone());
		API.getItemRegister().register(BlockGrass.class.getName(),
				new BlockGrass());
		API.getItemRegister().register(BlockDirt.class.getName(),
				new BlockDirt());
		API.getItemRegister().register(BlockCobblestone.class.getName(),
				new BlockCobblestone());
		API.getItemRegister().register(BlockSand.class.getName(),
				new BlockSand());
		API.getItemRegister().register(BlockObsidian.class.getName(),
				new BlockObsidian());
		API.getItemRegister().register(BlockGold.class.getName(),
				new BlockGold());
		API.getItemRegister().register(BlockTNT.class.getName(),
				new BlockTNT());
	}
}