package me.gorgeousone.tangledmaze.utils;

import me.gorgeousone.tangledmaze.data.Messages;
import me.gorgeousone.tangledmaze.messages.PlaceHolder;
import me.gorgeousone.tangledmaze.messages.TextException;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

public final class MaterialDataReader {
	
	private MaterialDataReader() {}
	
	public static MaterialData read(String materialData) throws TextException {
		
		Material type;
		byte data;
		
		String typeString;
		String dataString;
		
		if(materialData.contains(":")) {
			String[] split = materialData.split(":");
			typeString = split[0];
			dataString = split[1];
			
		}else {
			typeString = materialData;
			dataString = "0";
		}
		
		type = Material.matchMaterial(typeString);
		
		if(type == null || !type.isBlock())
			throw new TextException(Messages.ERROR_INVALID_BLOCK_NAME, new PlaceHolder("block", typeString));
		
		try {
			data = Byte.parseByte(dataString);
			
		} catch (NumberFormatException ex) {
			throw new TextException(Messages.ERROR_INVALID_NUMBER, new PlaceHolder("number", dataString));
		}
		
		return new MaterialData(type, data);
	}
}
