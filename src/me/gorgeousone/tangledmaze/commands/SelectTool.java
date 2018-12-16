package me.gorgeousone.tangledmaze.commands;

import org.bukkit.entity.Player;

import me.gorgeousone.tangledmaze.mazes.MazeHandler;
import me.gorgeousone.tangledmaze.shapes.*;
import me.gorgeousone.tangledmaze.tools.*;
import me.gorgeousone.tangledmaze.utils.Constants;

public class SelectTool {

	public void execute(Player p, String selectionType) {
		
		if(!p.hasPermission(Constants.buildPerm)) {
			p.sendMessage(Constants.insufficientPerms);
			return;
		}
		
		switch (selectionType.toLowerCase()) {
		case "rect":
		case "rectangle":
			
			try {
				setClipShape(p, Shape.RECT);
				p.sendMessage(Constants.prefix + "Changed cliping shape to rectangle.");
			} catch (Exception e) {}
			
			break;
			
		case "circle":
			
			try {
				setClipShape(p, Shape.CIRCLE);
				p.sendMessage(Constants.prefix + "Changed cliping shape to circle.");
			} catch (Exception e) {}
			
			break;
		
		case "brush":
			
			try {
				setMazeTool(p, new BrushTool(p));
				p.sendMessage(Constants.prefix + "Changed tool to brush.");
			} catch (Exception e) {}
			
			break;
			
		case "exit":
			
			try {
				setMazeTool(p, new ExitSettingTool(p));
				p.sendMessage(Constants.prefix + "Changed tool to exit setter.");
			} catch (Exception e) {}
			
			break;
			
		default:
			p.sendMessage("/tangledmaze help 5");
			break;
		}
	}
	
	private void setClipShape(Player p, Shape type) {
		
		if(!ToolHandler.hasClip(p)) {
			ToolHandler.setTool(p, new ClippingTool(p, type));
			return;
		}
		
		ClippingTool clip = ToolHandler.getClip(p);
		
		if(clip.getType().getClass().equals(type.getClass()))
			throw new IllegalStateException("Tool is already selected.");

		clip.setType(type);
	}
	
	private boolean setMazeTool(Player p, Tool type) {

		if(ToolHandler.getTool(p).getClass().equals(type.getClass()))
			return false;
		
		if(!MazeHandler.getMaze(p).isStarted()) {
			p.sendMessage(Constants.prefix + "This tool can only be used on a maze's ground plot.");
			p.sendMessage("/tangledmaze start");
			return false;
		}
		
		if(ToolHandler.hasClip(p))
			ToolHandler.getClip(p).reset();
			
		ToolHandler.setTool(p, type);
		return true;
	}
}