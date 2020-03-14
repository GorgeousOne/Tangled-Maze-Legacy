package me.gorgeousone.tangledmaze.generation.blockdatapickers;

import me.gorgeousone.tangledmaze.generation.BlockComposition;
import me.gorgeousone.tangledmaze.generation.terrainmap.TerrainMap;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;

public abstract class AbstractMaterialDataPicker {
	
	public abstract MaterialData pickMaterialData(BlockState block, BlockComposition blockComposition,
	                                              TerrainMap terrainMap);
}
