package assignment5;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	
	private Point location = new Point();
	private boolean hasMoved = false;
	
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}
	
	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background 
	 * 
	 * critters must override at least one of the following three methods, it is not 
	 * proper for critters to remain invisible in the view
	 * 
	 * If a critter only overrides the outline color, then it will look like a non-filled 
	 * shape, at least, that's the intent. You can edit these default methods however you 
	 * need to, but please preserve that intent as you implement them. 
	 */
	public javafx.scene.paint.Color viewColor() { 
		return javafx.scene.paint.Color.WHITE; 
	}
	
	public javafx.scene.paint.Color viewOutlineColor() { return viewColor(); }
	public javafx.scene.paint.Color viewFillColor() { return viewColor(); }
	
	public abstract CritterShape viewShape(); 
	
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	protected String look(int direction, boolean steps) {
		energy -= Params.look_energy_cost;
		int x = this.location.getX();
		int y = this.location.getY();
		String s = null;
		if(!steps){
			if(direction == 0){
				Point pt = new Point(x+1,y);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 1){
				Point pt = new Point(x+1,y-1);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 2){
				Point pt = new Point(x,y-1);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 3){
				Point pt = new Point(x-1,y-1);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 4){
				Point pt = new Point(x-1,y);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 5){
				Point pt = new Point(x-1,y+1);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 6){
				Point pt = new Point(x,y+1);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 7){
				Point pt = new Point(x+1,y+1);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			
		}else{
			if(direction == 0){
				Point pt = new Point(x+2,y);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 1){
				Point pt = new Point(x+2,y-2);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 2){
				Point pt = new Point(x,y-2);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 3){
				Point pt = new Point(x-2,y-2);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 4){
				Point pt = new Point(x-2,y);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 5){
				Point pt = new Point(x-2,y+2);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 6){
				Point pt = new Point(x,y+2);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
			else if(direction == 7){
				Point pt = new Point(x+2,y+2);
				if(CritterWorld.isOccupied(pt) ){
					s = CritterWorld.getCritter(pt).toString();
				}
			}
		}
		
		return s;
	}
	
	/* rest is unchanged from Project 4 */
	
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	
	protected final void walk(int direction) {
		energy -= Params.walk_energy_cost;
		if (!hasMoved && energy > 0) {//checking if the critter has moved this step
			direction = direction % 8;
			location.update(direction);
			hasMoved = true;//Comment out for testWalk
		}
	}
	
	protected final void run(int direction) {
		energy -= Params.run_energy_cost;
		if (!hasMoved && energy > 0) {//checking if critter has moved already this step
			direction = direction % 8;
			location.update(direction);
			location.update(direction);
			hasMoved = true;//Comment out for testRun
		}
	}
	
	protected final void reproduce(Critter offspring, int direction) {
		if(this.energy >= Params.min_reproduce_energy) {
			offspring.energy = this.energy/2;
			this.energy += this.energy %2;
			this.energy /= 2;
			//^^Dividing up the energy between the baby and the parent
			offspring.location = new Point(this.location.getX(), this.location.getY());
			offspring.location.update(direction);//giving the baby a location
			CritterWorld.babies.put((Critter) offspring, offspring.location);//adding baby to CritterWorld baby list
		}
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	public static void worldTimeStep() {
		CritterWorld.doTimeStep();//does all time steps
		for(Critter i : CritterWorld.critterMap.keySet()) {
			i.energy -= Params.rest_energy_cost;
		}
		handleInteractions();//handles fights
		CritterWorld.removeDead();//removes all dead from the World
		CritterWorld.makeAlgae();//adds algae to board
		CritterWorld.addBabies();
		for(Critter i: CritterWorld.critterMap.keySet()) {
			i.hasMoved = false;
		}
	}
	
	public static void displayWorld() {
		CritterWorld.printWorld();
	}
	
	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		Critter critter = null;
		try{
			Class<?> c = Class.forName("assignment4."+critter_class_name);
			Constructor<?> newCon = c.getConstructor();
			Object obj = newCon.newInstance();
			critter = (Critter)obj;
			//^^This is to get an object of the class requested
		}catch(Exception e){
			throw new InvalidCritterException(critter_class_name);
		}
		addCritter(critter);//send the critter to addCritter to be added to World
	}
	
	/**
	 * Adds critter to CritterWorld
	 * @param critter to be added
	 * **Make Public For Testing Purposes
	 */
	private static void addCritter(Critter critter) {
		if(critter != null) {
			critter.location = CritterWorld.getRandomLocation();//returns a random location
			CritterWorld.addCritter(critter, critter.location);//adds the critter to CritterWorld
			critter.energy = Params.start_energy;//sets that critter's energy to startEnergy
		}
	}
	
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		return null;
	}
	
	public static void runStats(List<Critter> critters) {}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}
	
	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		CritterWorld.cleanWorld();
	}
	
	/**
	 * Cycles through all the critters
	 * checking to see if any are in the same location
	 * if they are, this calls their fight methods to see if they will fight or attempt to move
	 * if they fight, a roll is done between 0 and the critters energy
	 * the critter with the higher roll gains 1/2 the energy from the loser
	 * the loser dies and loses its energy
	 */
 	private static void handleInteractions(){
		boolean aFight;
		boolean bFight;
		int aRoll = 0;
		int bRoll = 0;
		for(Critter a : CritterWorld.critterMap.keySet()) {
			for(Critter b : CritterWorld.critterMap.keySet()) {
				if(!a.equals(b)) {
					if(a.location.equals(b.location)) {
						aFight = a.fight(b.toString());
						bFight = b.fight(a.toString());
						if(aFight){
							aRoll = Critter.getRandomInt(a.getEnergy());
						}else{
							aRoll = 0;
						}
						if(bFight){
							bRoll = Critter.getRandomInt(b.getEnergy());
						}else{
							bRoll = 0;
						}
						if (a.location.equals(b.location)) {	// neither moved
							if (aRoll > bRoll) {
								a.energy += b.energy / 2;
								b.energy = 0;
								b.location = new Point(-1, -1);
							} else {
								b.energy += a.energy / 2;
								a.energy = 0;
								a.location = new Point(-1, -1);
							} 
						}
					}
				}
			}
		}
	}	
	
}
