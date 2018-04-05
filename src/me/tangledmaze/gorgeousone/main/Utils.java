package me.tangledmaze.gorgeousone.main;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public abstract class Utils {

	private static ArrayList<Vector> CARDIANL_DIRACTIONS = new ArrayList<>(Arrays.asList(
			new Vector( 1, 0,  0),
			new Vector( 0, 0,  1),
			new Vector(-1, 0,  0),
			new Vector( 0, 0, -1)));
				
	private static ArrayList<Vector> DIRECTIONS = new ArrayList<>(Arrays.asList(
			new Vector( 1, 0,  0),
			new Vector( 1, 0,  1),
			new Vector( 0, 0,  1),
			new Vector(-1, 0,  1),
			new Vector(-1, 0,  0),
			new Vector(-1, 0, -1),
			new Vector( 0, 0, -1),
			new Vector( 1, 0, -1)));

	@SuppressWarnings("unchecked")
	public static ArrayList<Vector> getCardinalDirs() {
		return (ArrayList<Vector>) CARDIANL_DIRACTIONS.clone();
	}

	public static void sendBlockLater(Player p, Block b, Material mat) {
		BukkitRunnable r = new BukkitRunnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				p.sendBlockChange(b.getLocation(), mat, (byte) 0);
			}
		};
		r.runTask(TangledMain.plugin);
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Vector> getDirs() {
		return (ArrayList<Vector>) DIRECTIONS.clone();
	}
	
	public static Location getNearestSurface(Location loc) {
		Location iter = loc.clone();
		
		if(loc.getBlock().getType() == Material.AIR) {
			while(iter.getY() >= 0) {
				iter.add(0, -1, 0);
				
				if(iter.getBlock().getType().isSolid())
					return iter;
			}
			iter.setY(loc.getY());
		
		}else {
			while(iter.getY() <= 255) {
				iter.add(0, 1, 0);
				
				if(iter.getBlock().getType() == Material.AIR) {
					iter.add(0, -1, 0);
					return iter;
				}
			}
		}
		return loc;
	}
}