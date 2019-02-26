package me.gorgeousone.tangledmaze.command;

import java.util.ArrayList;

import me.gorgeousone.tangledmaze.generation.MazeGenerator;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import me.gorgeousone.tangledmaze.core.Maze;
import me.gorgeousone.tangledmaze.handler.MazeHandler;
import me.gorgeousone.tangledmaze.handler.ToolHandler;
import me.gorgeousone.tangledmaze.util.Constants;
import me.gorgeousone.tangledmaze.util.MaterialReader;

@SuppressWarnings("deprecation")
public class BuildMaze {

	private MazeGenerator generator;

	public BuildMaze() {
		generator = new MazeGenerator();
	}

	public void execute(Player player, ArrayList<String> serializedMaterialData) {
		
		if(!player.hasPermission(Constants.buildPerm)) {
			player.sendMessage(Constants.insufficientPerms);
			return;
		}
		
		Maze maze = MazeHandler.getMaze(player);
		
		if(!maze.isStarted()) {
			
			if(!ToolHandler.hasClipboard(player)) {
				player.sendMessage(ChatColor.RED + "Please select a clipboard with a maze wand first.");
				player.sendMessage("/tangledmaze wand");
				return;	
			}
			
			player.sendMessage(ChatColor.RED + "Please start a maze first.");
			player.sendMessage("/tangledmaze start");
			return;
		}
		
		if(maze.getExits().isEmpty()) {
			player.sendMessage(
					ChatColor.RED + "Please mark an exit at the maze's border. " +
					ChatColor.GREEN + "(You know, the generator needs a start point for building walls and everything.)");
			player.sendMessage("/tangledmaze select exit");
			return;
		}
		
		if(serializedMaterialData.isEmpty()) {
			player.sendMessage(ChatColor.RED + "Please specify a block type this maze should be built out of.");
			player.sendMessage("/tangledmaze build <block> <block>...");
			return;
		}
		
		ArrayList<MaterialData> composition;
		
		try {
			composition = getWallComposition(serializedMaterialData);
		} catch (Exception e) {
			player.sendMessage(e.getMessage());
			return;
		}
		
		maze.setWallComposition(composition);

		MazeHandler.buildMaze(maze, generator);
		player.sendMessage(Constants.prefix + "Started building your maze.");

		ToolHandler.resetToDefaultTool(player);
		MazeHandler.setMaze(player, new Maze(player));
	}
	
	private static ArrayList<MaterialData> getWallComposition(ArrayList<String> serializedMaterialData) {
		ArrayList<MaterialData> composition = new ArrayList<>();
		
		for(String materialData : serializedMaterialData) {
			composition.add(MaterialReader.readMaterialData(materialData));
		}
		
		return composition;
	}
}