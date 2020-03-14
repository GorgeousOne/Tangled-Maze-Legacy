package me.gorgeousone.tangledmaze.data;

import me.gorgeousone.tangledmaze.utils.MaterialDataReader;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public final class ConfigSettings {
	
	public static Material MAZE_WAND_MATERIAL;
	
	private ConfigSettings() {}
	
	public static void loadSettings(FileConfiguration config) {
		
		MAZE_WAND_MATERIAL = Material.matchMaterial(config.getString("wand-item"));
		
		if (MAZE_WAND_MATERIAL == null || !MAZE_WAND_MATERIAL.isItem())
			MAZE_WAND_MATERIAL = Material.GOLD_SPADE;
	}
}