package me.gorgeousone.tangledmaze.data;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import me.gorgeousone.tangledmaze.util.Utils;

import java.util.List;
import java.util.TreeSet;

public class Constants {
	
	public static final String
			RELOAD_PERM = "tangledmaze.reload",
			BUILD_PERM = "tangledmaze.build",
			WAND_PERM = "tangledmaze.getwand",
			MAZE_TP_PERM = "tangledmaze.teleport",
			MAZE_SAVE_PERM = "tangledmaze.save";
	
	public static final String prefix =
			ChatColor.DARK_GREEN + "["  +
			ChatColor.GREEN      + "TM" + 
			ChatColor.DARK_GREEN + "] " +
			ChatColor.YELLOW;
	
	public static final Material
			MAZE_BORDER = Material.REDSTONE_BLOCK,
			MAZE_MAIN_EXIT = Material.DIAMOND_BLOCK,
			MAZE_EXIT = Material.EMERALD_BLOCK,
			CLIPBOARD_BORDER = Material.GOLD_BLOCK,
			CLIPBOARD_CORNER = Material.LAPIS_BLOCK;

	public static final TreeSet<Material>
			NOT_SOLIDS = new TreeSet<>(),
			REPLACEABLE_SOLIDS = new TreeSet<>();
	
	public static final String[] MAZE_WAND_ENCHANTS = {
			"Difficult Handling II",
			"Would Recommend X/X",
			"Unbreaking ∞",
			"Overpowered X",
			"Tangly III",
			"Wow I",
			"Ignoring WorldGuard V",
			"Wubba Lubba Dub Dub IV",
			"Artifact Lv. XCIX"
	};
	
	@SuppressWarnings("unchecked")
	public static void loadConstants() {
		
		YamlConfiguration materialLists = Utils.loadDefaultConfig("material_lists");

		for(String materialName : (List<String>) materialLists.getList("not-solid-materials")) {

			try {
				NOT_SOLIDS.add(Material.valueOf(materialName));
			}catch (IllegalArgumentException ignored) {}
		}

		for(String materialName : (List<String>) materialLists.getList("replaceable-solid-materials")) {

			try {
				REPLACEABLE_SOLIDS.add(Material.valueOf(materialName));
			}catch (IllegalArgumentException ignored) {}
		}
	}
}