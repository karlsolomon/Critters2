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
	public static int annimation_speed = 1;

	public static void setWorldParams(double width, double height) {
		world_width = width;
		world_height = height;
		setBin();
	}
	public static void setCanvasParams(double width, double height) {
		canvas_width = width;
		canvas_height = height;
	}
	
	private static void setBin() {		
		Double sizeDouble = (Double) Math.min(canvas_width/world_width, canvas_height/world_height);
		int size = sizeDouble.intValue();
		if(size % 2 != 0) {
			size--;
		}
		bin_size = size;
	}
}
