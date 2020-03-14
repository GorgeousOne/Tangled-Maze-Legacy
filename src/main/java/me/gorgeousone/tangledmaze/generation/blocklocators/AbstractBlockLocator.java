package me.gorgeousone.tangledmaze.generation.blocklocators;

import me.gorgeousone.tangledmaze.generation.terrainmap.TerrainMap;
import org.bukkit.block.BlockState;

import java.util.Set;

public abstract class AbstractBlockLocator {
	
	public abstract Set<BlockState> locateBlocks(TerrainMap terrainMap);
}
