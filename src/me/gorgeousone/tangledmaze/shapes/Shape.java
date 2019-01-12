package me.gorgeousone.tangledmaze.shapes;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.World;

import me.gorgeousone.tangledmaze.tools.Clip;
import me.gorgeousone.tangledmaze.utils.MazePoint;
import me.gorgeousone.tangledmaze.utils.Utils;

public interface Shape {
	
	public static final Rectangle RECT = new Rectangle();
	public static final Ellipse CIRCLE = new Ellipse();
	
	public int getVertexCount();
	
	public Clip createClip(ArrayList<MazePoint> vertices);
	
	public static ArrayList<MazePoint> createRectangularVertices(MazePoint v0, MazePoint v2) {
		ArrayList<MazePoint> vertices = new ArrayList<>();
		World w = v0.getWorld();
		
		int maxY = Math.max(v0.getBlockY(), v2.getBlockY());
		
		int minX = Math.min(v0.getBlockX(), v2.getBlockX()),
			minZ = Math.min(v0.getBlockZ(), v2.getBlockZ()),
			maxX = Math.max(v0.getBlockX(), v2.getBlockX()),
			maxZ = Math.max(v0.getBlockZ(), v2.getBlockZ());
		
		vertices = new ArrayList<>(Arrays.asList(
				Utils.nearestSurface(new MazePoint(w, minX, maxY, minZ)),
				Utils.nearestSurface(new MazePoint(w, maxX, maxY, minZ)),
				Utils.nearestSurface(new MazePoint(w, maxX, maxY, maxZ)),
				Utils.nearestSurface(new MazePoint(w, minX, maxY, maxZ))));
		
		return vertices;
	}
}