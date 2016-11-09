/* Critter2 Params.java
 * EE422C Project 5 submission by
 * Christopher Sickler
 * cbs2468
 * 16445
 * Karl Solomon
 * kws653
 * 16445
 * Slip days used: <0>
 * Git URL: https://github.com/karlsolomon/Critters2 
 * Fall 2016
 */
package assignment5;

public class Params {
	public static Double world_width = 50.0;
	public static Double world_height = 50.0;
	public static double canvas_width = 800;
	public static double canvas_height = 600;
	public static int walk_energy_cost = 2;
	public static int run_energy_cost = 5;
	public static int rest_energy_cost = 0;
	public static int min_reproduce_energy = 20;
	public static int refresh_algae_count = 10;
	public static int bin_size;
	public static int photosynthesis_energy_amount = 1;
	public static int start_energy = 100;
	public static int look_energy_cost = 1;
	public static int animation_speed = 1;
    static double leftMargin = 180;
    static double rightMargin = 10;
    static double bottomMargin = 25;
    static double topMargin = 35;
    
    
	public static void setWorldParams(double width, double height) {
		world_width = width;
		world_height = height;
		setBin();
	}
	
	public static void setWorldWidth(double width) {
		
	}
	
	public static void setWorldHeight(double height) {
		
	}
	
	public static void setCanvasWidth(double width) {
		canvas_width = width - (leftMargin + rightMargin);
		setBin();
		Controller.canvas.setWidth(canvas_width);
	}
	public static void setCanvasHeight(double height) {
		canvas_height = height - (topMargin + bottomMargin);
		setBin();
		Controller.canvas.setHeight(canvas_height);
	}
	
	public static void setBin() {		
		Double sizeDouble = (Double) Math.min(canvas_width/world_width, canvas_height/world_height);
		int size = sizeDouble.intValue();
		if(size % 2 != 0) {
			size--;
		}
		bin_size = size;
	}
}
