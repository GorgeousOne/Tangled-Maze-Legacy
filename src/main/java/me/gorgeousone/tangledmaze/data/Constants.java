package me.gorgeousone.tangledmaze.data;

import me.gorgeousone.tangledmaze.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.TreeSet;

public class Constants {
	
	public static final int BUKKIT_VERSION = Integer.parseInt(Bukkit.getBukkitVersion().split("\\.")[1]);
	
	public static final String
			BUILD_PERM = "tangledmaze.build",
			WAND_PERM = "tangledmaze.getwand",
			MAZE_TP_PERM = "tangledmaze.teleport",
			RELOAD_PERM = "tangledmaze.reload";
	
	public static final String prefix =
			ChatColor.DARK_GREEN + "[" +
			ChatColor.GREEN + "TM" +
			ChatColor.DARK_GREEN + "] " +
			ChatColor.YELLOW;
	
	public static final Material
			MAZE_BORDER = Material.REDSTONE_BLOCK,
			MAZE_MAIN_EXIT = Material.DIAMOND_BLOCK,
			MAZE_EXIT = Material.EMERALD_BLOCK,
			CLIPBOARD_BORDER = Material.GOLD_BLOCK,
			CLIPBOARD_VERTEX = Material.LAPIS_BLOCK;
	
	
	public static final TreeSet<Material>
			NOT_SOLIDS = new TreeSet<>(),
			REPLACEABLE_SOLIDS = new TreeSet<>();
	
	public static final String MAZE_WAND_NAME = ChatColor.DARK_GREEN + "Maze Wand";
	
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
	
	public static void loadMaterialLists(JavaPlugin plugin) {
		
		YamlConfiguration materialLists = ConfigUtils.loadDefaultConfig("material_lists", plugin);
		
		for (String materialName : (List<String>) materialLists.getList("not-solid-materials")) {
			try {
				NOT_SOLIDS.add(Material.valueOf(materialName));
			} catch (IllegalArgumentException ignored) {
			}
		}
		
		for (String materialName : (List<String>) materialLists.getList("replaceable-solid-materials")) {
			try {
				REPLACEABLE_SOLIDS.add(Material.valueOf(materialName));
			} catch (IllegalArgumentException ignored) {
			}
		}
	}
}