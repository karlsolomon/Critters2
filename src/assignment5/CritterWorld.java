package assignment5;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CritterWorld {
	public static Map<Critter, Point> critterMap = new HashMap<Critter, Point>();	//official record of Critters and their location
	public static Map<Critter, Point> updatedCritterMap = new HashMap<Critter, Point>();
	public static Map<Critter, Point> babies = new HashMap<>();
	private static int width = Params.world_width;
	private static int height = Params.world_height;
 
	public static void addCritter(Critter c, Point p){
		critterMap.put(c, p);
	}
	/**
	 * Checks all existing Critter locations for the current point
	 * @param p
	 * @return true if another critter has that same location
	 */
	public static boolean isOccupied(Point p){
		for(Critter i : critterMap.keySet()){
			if(critterMap.get(i).equals(p)) {
				if(i.getEnergy() > 0) { 	// ignore dead critters
					continue;
				}
				else return true;
			}
		}
		return false;
	}
	
	public static Critter getCritter(Point p){
		for(Map.Entry<Critter, Point> i : critterMap.entrySet()){
			if(i.getValue().equals(p)){
				return i.getKey();
			}
		}
		
		return null;
	}
	
	public static boolean tryMove(Critter c, int direction, int distance) {
		Point p1 = critterMap.get(c); 
		for(int i = 0; i < distance; i++) {
			p1.update(direction);			// p1 is desired location
		}		
		
		if(isOccupied(p1)) {
			return false;
		}
		else {
			if(distance == 1) {
				c.walk(direction);
			}
			else if(distance == 2) {
				c.run(direction);
			}
			Point p2 = critterMap.get(c); 	// updated if run/walk are successful (hasn't already been done this timestep
			if(p1.equals(p2)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	/**
	 * Removes all dead critters from the critterMap
	 */
	public  static void removeDead(){
		for(Iterator<Critter> iterator = critterMap.keySet().iterator(); iterator.hasNext();){
			Critter c = iterator.next();
			if(c.getEnergy() < 1) {
				iterator.remove();
			}
		}	
	}
	
	public static void doTimeStep(){
		
		updatedCritterMap.putAll(critterMap);
		for(Critter i : updatedCritterMap.keySet()){
			i.doTimeStep();
		}
		critterMap = updatedCritterMap;
		updatedCritterMap = new HashMap<>();
	}
	/**
	 * Get random location on map to put newly generated Critter via Make Critter
	 * @return random point location on the world map
	 */
	public static Point getRandomLocation(){
		int x = Critter.getRandomInt(width);
		int y = Critter.getRandomInt(height);
		return new Point(x,y);
	}
	/**
	 * At the end of the world time step add babies to the world map
	 */
	public static void addBabies() {
		critterMap.putAll(babies);
		babies = new HashMap<>();
	}
	/**
	 * Deletes all world data
	 */
	public static void clearWorld(){
		critterMap = new HashMap<Critter, Point>();
	}
	/**
	 * Prints world to System.out
	 */
	public static void printWorld(){
		
	}
	/**
	 * Guarantees that the String[][] world only displays live critters and accurate positions
	 */
	public static void cleanWorld(){
		
	}
	
	public static void makeAlgae() {
		for(int i = 0; i < Params.refresh_algae_count; i++) {
			try {
				Critter.makeCritter("Algae");
			} catch (InvalidCritterException e) {
				//Main.printError("Algae");
			}
		}
	}
	
}
