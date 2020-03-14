package me.gorgeousone.tangledmaze.generation;

import org.bukkit.block.BlockState;

import java.util.HashMap;
import java.util.Set;

public class MazePartBlockBackup {
	
	private HashMap<MazePart, Set<BlockState>> partBackupLists;
	
	public MazePartBlockBackup() {
		partBackupLists = new HashMap<>();
	}
	
	public Set<BlockState> getPartBackup(MazePart part) {
		return partBackupLists.get(part);
	}
	
	public boolean isEmpty() {
		return partBackupLists.isEmpty();
	}
	
	public void setBackup(MazePart part, Set<BlockState> blockBackup) {
		partBackupLists.put(part, blockBackup);
	}
	
	public void deleteBackup(MazePart mazePart) {
		partBackupLists.remove(mazePart);
	}
	
	public boolean hasBackup(MazePart part) {
		return partBackupLists.containsKey(part);
	}
}