package me.gorgeousone.tangledmaze.generation;

import me.gorgeousone.tangledmaze.generation.blockdatapickers.AbstractMaterialDataPicker;
import me.gorgeousone.tangledmaze.generation.terrainmap.TerrainMap;
import org.bukkit.block.BlockState;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

public final class BlockGenerator {
	
	private BlockGenerator() {}
	
	public static void updateBlocks(
			JavaPlugin plugin,
			Set<BlockState> blocksToUpdate,
			BlockComposition blockComposition,
			AbstractMaterialDataPicker materialDataPicker,
			TerrainMap terrainMap,
			ActionListener callback) {
		
		Iterator<BlockState> iter = blocksToUpdate.iterator();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				long timer = System.currentTimeMillis();
				
				while (iter.hasNext()) {
					
					BlockState nextBlock = iter.next();
					
					if (materialDataPicker != null) {
						MaterialData newMaterialData = materialDataPicker.pickMaterialData(nextBlock, blockComposition, terrainMap);
						nextBlock.setType(newMaterialData.getItemType());
						nextBlock.setRawData(newMaterialData.getData());
					}
					
					nextBlock.update(true, false);
					iter.remove();
					
					if (System.currentTimeMillis() - timer >= 49)
						return;
				}
				
				if (callback != null)
					callback.actionPerformed(null);
				
				this.cancel();
			}
		}.runTaskTimer(plugin, 0, 1);
	}
}
