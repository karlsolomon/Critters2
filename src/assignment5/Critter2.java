/* Critter2 Critter2.java
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

/**
 * This Critter takes after a normal human
 * Day:
 * 8 hours sleeping
 * 16 hours awake
 * 	3 hours active(running)
 * 	3 hours searching for food(walking in random directions)
 * 	10 hours otherwise(walking in ordered fashion)
 * @author Chris
 * */
public class Critter2 extends Critter{

	private static final int sleeping = 8;// 8/24 chance the critter is sleeping
	private static final int active = 3 + sleeping;// 3/24 chance that the critter is active
	private static final int searchingForFood = 3 + active;// 3/24 chance the critter is searching for food
	private int dir;// direction the critter will move
	private int[] genes = new int[8];//genes
	/**
	 * Constructor
	 */
	public Critter2(){
		for(int i = 0; i < 8; i++){
			genes[i] = Critter.getRandomInt(8);
		}
		dir = Critter.getRandomInt(8);
	}

	public int getDir() {
		return dir;
	}


	public void setDir(int dir) {
		this.dir = dir;
	}

	/**
	 * @return 2, this is how critter2 is displayed on screen
	 */
	@Override
	public String toString(){
		return "2";
	}
	
	@Override
	public CritterShape viewShape() {
		return CritterShape.TRIANGLE;
	}
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.RED; }
	
	/**
	 * checks:
	 * 		if critter is asleep
	 * 		if critter is active, runs
	 * 		if searching for food, walk in random directions
	 * 		otherwise, just walks in a set direction
	 */
	@Override
	public void doTimeStep() {
		int num = Critter.getRandomInt(24);
		if(num < sleeping){
		}
		else if(num < active){
			run(dir);
		}
		else if(num < searchingForFood){
			
			String find = look(dir, false);
			if(find != null){
				if(find.equals("@")){
					walk(dir);
				}
			}else walk(dir + 1);
			dir = Critter.getRandomInt(8);
		}
		else{
			walk(dir);
		}
		if(getEnergy() > (8*Params.min_reproduce_energy)){
			int numChildren = Critter.getRandomInt(2);
			for(int i = 0; i < numChildren; i++){
				Critter2 child = new Critter2();
				child.genes = this.genes;
				reproduce(child,dir);
			}
		}
	}
/**
 * checks opponent:
 * 		if algae, fights it
 * 		if 1, tries to walk away
 * 		if 3, tries to run away
 * 		otherwise, fights
 */
	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("@")){
			return true;
		}
		else if(opponent.equals("1")){
			return CritterWorld.tryMove(this, dir, 1);
		}
		else if(opponent.equals("3")){
			return CritterWorld.tryMove(this, dir, 2);
		}else return true;
	}
}
