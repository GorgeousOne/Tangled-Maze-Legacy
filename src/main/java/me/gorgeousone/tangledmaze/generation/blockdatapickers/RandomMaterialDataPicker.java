package me.gorgeousone.tangledmaze.generation.blockdatapickers;

import me.gorgeousone.tangledmaze.generation.BlockComposition;
import me.gorgeousone.tangledmaze.generation.terrainmap.TerrainMap;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;

import java.util.Random;

public class RandomMaterialDataPicker extends AbstractMaterialDataPicker {
	
	private Random random;
	
	public RandomMaterialDataPicker() {
		random = new Random();
	}
	
	@Override
	public MaterialData pickMaterialData(BlockState block, BlockComposition blockComposition, TerrainMap terrainMap) {
		return blockComposition.getBlockAtAmount(random.nextInt(blockComposition.getSize() + 1));
	}
}
