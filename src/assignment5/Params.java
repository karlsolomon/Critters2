package assignment5;

public class Params {
	public static int world_width = 50;
	public static int world_height = 50;
	public static int canvas_width = 800;
	public static int canvas_height = 600;
	public static int walk_energy_cost = 2;
	public static int run_energy_cost = 5;
	public static int rest_energy_cost = 0;
	public static int min_reproduce_energy = 20;
	public static int refresh_algae_count = 10;
	public static int bin_size = Math.min(canvas_width/world_width, canvas_height/world_height);
	public static int photosynthesis_energy_amount = 1;
	public static int start_energy = 100;
	public static int look_energy_cost = 1;
	public static int annimation_speed = 1;

	public static void setWorldParams(int width, int height) {
		world_width = width;
		world_height = height;
		setBin();
	}
	
	private static void setBin() {		
		int size = Math.min(canvas_width/world_width, canvas_height/world_height);
		if(size % 2 != 0) {
			size--;
		}
		bin_size = size;
	}
}
