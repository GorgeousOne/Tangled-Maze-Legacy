package me.gorgeousone.tangledmaze.generation;

import org.bukkit.material.MaterialData;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlockComposition {
	
	private LinkedHashMap<MaterialData, Integer> composition;
	private int size;
	
	public BlockComposition() {
		composition = new LinkedHashMap<>();
	}
	
	public int getSize() {
		return size;
	}
	
	public void addBlock(MaterialData data, int amount) {
		if (composition.putIfAbsent(data, amount) == null)
			size += amount;
	}
	
	public MaterialData getDataAtPercentage(double percentage) {
		return getBlockAtAmount((int) (percentage * size + 0.5));
	}
	
	public MaterialData getBlockAtAmount(int amount) {
		
		int iterAmount = 0;
		
		for (Map.Entry<MaterialData, Integer> entry : getComposition().entrySet()) {
			iterAmount += entry.getValue();
			
			if (iterAmount >= amount || iterAmount == size)
				return entry.getKey();
		}
		
		return null;
	}
	
	public Map<MaterialData, Integer> getComposition() {
		return composition;
	}
}
