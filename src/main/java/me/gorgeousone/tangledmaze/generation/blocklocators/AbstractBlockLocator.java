package me.gorgeousone.tangledmaze.generation.blocklocators;

import me.gorgeousone.tangledmaze.generation.BlockDataState;
import me.gorgeousone.tangledmaze.generation.terrainmap.TerrainMap;

import java.util.Set;

public abstract class AbstractBlockLocator {
	
	public abstract Set<BlockDataState> locateBlocks(TerrainMap terrainMap);
}
