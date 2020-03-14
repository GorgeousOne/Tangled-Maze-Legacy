package me.gorgeousone.tangledmaze.generation.blocklocators;

import me.gorgeousone.tangledmaze.generation.terrainmap.MazeAreaType;
import me.gorgeousone.tangledmaze.generation.terrainmap.TerrainMap;
import org.bukkit.Location;
import org.bukkit.block.BlockState;

import java.util.HashSet;
import java.util.Set;

public class FloorBlockLocator extends AbstractBlockLocator {
	
	@Override
	public Set<BlockState> locateBlocks(TerrainMap terrainMap) {
		
		Set<BlockState> relevantBlocks = new HashSet<>();
		
		for (int x = terrainMap.getMinX(); x <= terrainMap.getMaxX(); x++) {
			for (int z = terrainMap.getMinZ(); z <= terrainMap.getMaxZ(); z++) {
				
				if (terrainMap.getAreaType(x, z) == MazeAreaType.PATH) {
					Location blockLoc = new Location(terrainMap.getMaze().getWorld(), x, terrainMap.getFloorHeight(x, z), z);
					relevantBlocks.add(blockLoc.getBlock().getState());
				}
			}
		}
		
		return relevantBlocks;
	}
}
