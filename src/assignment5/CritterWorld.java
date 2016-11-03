package assignment5;

import java.util.HashMap;
import java.util.Map;

public class CritterWorld {
	public static Map<Critter, Point> critterMap = new HashMap<Critter, Point>();	//official record of Critters and their location
	public static Map<Critter, Point> babies = new HashMap<>();
	private static int width = Params.world_width;
	private static int height = Params.world_height;
	private static String[][] world = new String[width][height];	// for display purposes only
 
}
