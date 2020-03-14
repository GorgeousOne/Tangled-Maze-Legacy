package me.gorgeousone.tangledmaze.listeners;

import me.gorgeousone.tangledmaze.data.Constants;
import me.gorgeousone.tangledmaze.handlers.ClipToolHandler;
import me.gorgeousone.tangledmaze.handlers.MazeHandler;
import me.gorgeousone.tangledmaze.handlers.Renderer;
import me.gorgeousone.tangledmaze.handlers.ToolHandler;
import me.gorgeousone.tangledmaze.maze.Maze;
import me.gorgeousone.tangledmaze.tools.ClipTool;
import me.gorgeousone.tangledmaze.tools.ToolType;
import me.gorgeousone.tangledmaze.utils.WandUtils;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class PlayerWandInteractionListener implements Listener {
	
	private ToolHandler toolHandler;
	private ClipToolHandler clipHandler;
	private MazeHandler mazeHandler;
	private Renderer renderer;
	
	public PlayerWandInteractionListener(ToolHandler toolHandler,
	                                     ClipToolHandler clipHandler,
	                                     MazeHandler mazeHandler,
	                                     Renderer renderer) {
		this.toolHandler = toolHandler;
		this.clipHandler = clipHandler;
		this.mazeHandler = mazeHandler;
		this.renderer = renderer;
	}
	
	@EventHandler
	public void onItemDamage(PlayerItemDamageEvent e) {
		
		if (WandUtils.isMazeWand(e.getItem())) {
			e.setCancelled(true);
			e.getPlayer().updateInventory();
		}
	}
	
	@EventHandler
	public void onBlockClick(PlayerInteractEvent event) {
		
		Action action = event.getAction();
		
		if (action != Action.LEFT_CLICK_BLOCK &&
		    action != Action.RIGHT_CLICK_BLOCK)
			return;
		
		try {
			if(event.getHand() != EquipmentSlot.HAND)
				return;
		} catch (NoSuchMethodError ignored) {
			//the off hand only exists for 1.12 I think?
		}
		
		Player player = event.getPlayer();
		Block clickedBlock = event.getClickedBlock();
		ItemStack heldItem = event.getItem();
		
		if (WandUtils.isMazeWand(heldItem)) {
			
			event.setCancelled(true);
			
			if (player.hasPermission(Constants.BUILD_PERM))
				toolHandler.handleToolInteraction(player, clickedBlock, action);
			else
				destroyMazeWand(player, heldItem);
			
		} else
			hidePlayersClipsIfHit(player, clickedBlock);
	}
	
	private void hidePlayersClipsIfHit(Player player, Block clickedBlock) {
		
		if (!player.hasPermission(Constants.BUILD_PERM))
			return;
		
		Maze maze = mazeHandler.getMaze(player);
		
		if (maze.hasClip() && renderer.isMazeVisible(maze) && maze.getClip().isBorderBlock(clickedBlock))
			renderer.hideMaze(maze);
		
		if (!clipHandler.hasClipTool(player))
			return;
		
		ClipTool clipTool = clipHandler.getClipTool(player);
		
		if (renderer.isClipToolVisible(clipTool) && (clipTool.isVertex(clickedBlock) || clipTool.getClip().isBorderBlock(clickedBlock)))
			renderer.hideClipTool(clipTool, false);
	}
	
	@EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
	public void onSlotSwitch(PlayerItemHeldEvent event) {
		
		Player player = event.getPlayer();
		
		if (!player.hasPermission(Constants.BUILD_PERM))
			return;
		
		ItemStack newItem = player.getInventory().getItem(event.getNewSlot());
		
		if (!WandUtils.isMazeWand(newItem))
			return;
		
		Maze maze = mazeHandler.getMaze(player);
		
		if (maze.hasClip() && !maze.isConstructed())
			renderer.displayMaze(mazeHandler.getMaze(player));
		
		if (clipHandler.hasClipTool(player) && toolHandler.getToolType(player) == ToolType.CLIP_TOOL)
			renderer.displayClipTool(clipHandler.getClipTool(player));
	}
	
	private void destroyMazeWand(Player player, ItemStack wand) {
		
		player.getInventory().remove(wand);
		player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "It seems like you are unworthy to use such mighty tool, it broke apart.");
		player.damage(0);
		
		if(Constants.BUKKIT_VERSION == 8) {
			
			player.getWorld().playSound(player.getEyeLocation(), Sound.valueOf("ITEM_BREAK"), 1f, 1f);
			player.getWorld().playEffect(player.getLocation().add(0, 1, 0), Effect.valueOf("EXPLOSION_HUGE"), 0);
			
		}else {
			
			player.getWorld().playSound(player.getEyeLocation(), Sound.ENTITY_ITEM_BREAK, 1f, 1f);
			player.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, player.getLocation(), 1);
		}
	}
}