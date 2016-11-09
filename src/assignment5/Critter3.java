/* Critter2 Critter3.java
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
 * This Critter's name is Donald. He doesn't like being labeled as a number. He thinks its unfair.
 * This is unfair treatment. He has more money than any other critter who has ever been simulated.
 * We are never really know what he is going to do or where he is going to go. Based on his level
 * of activity we are fairly sure that he will be active roughly four hours per week, awake roughly
 * 2/3 of the time, and asleep roughly 1/3 of the time. Being a critter wasn't his first choice, and
 * he wasn't the people's choice, but he's what we've got.
 * Donald seldom tells the truth, and seldom does what you expect from a critter. Every time step in 
 * which he is not asleep, he randomly chooses a response to his suggested direction from the RNCC 
 * (Republican National Critter Council) when he gets a new direction, he will only go that direction
 * 4% of the time since that's how often he tells the truth. The worse his lie the further from the 
 * suggested direction he goes. We keep track of what directions he moves and his fact check record.
 * @author KSolomon
 */

public class Critter3 extends Critter{

	@Override
	public CritterShape viewShape() {
		return CritterShape.STAR;
	}
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.ORANGE; }

	@Override
	public String toString(){ return "3";}
	
	private static final int truth = 3;
	private static final int mostlyTruth = 11 + truth;
	private static final int halfTruth = 14 + mostlyTruth;
	private static final int mostlyFalse = 18 + halfTruth;
	private static final int pantsOnFire = 52 + mostlyFalse;
	private static final int[] truthTable = {truth, mostlyTruth, halfTruth, mostlyFalse, pantsOnFire};
	private int[] factCheckRecord = new int[5];

	private static final int active = 4;	// active on average 4 hours per week to maintain BMI = 30;
	private static final int awake = 168-49;	//sleep 7 hours per day
	
	private static final int moveTotal = 8;
	private int[] moves = new int[moveTotal];
	
	private int dir;
	
	public Critter3() {
		dir = Critter.getRandomInt(8);
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}
		
	@Override
	public void doTimeStep() {
		dir = toDirection();
		moves[dir] +=1;	//keep track of his movements
		int activity = Critter.getRandomInt(168);
		if(activity < active) {
			run(dir);
		}
		else if(activity < awake) {
			walk(dir);
		}
		if(getEnergy() > 8*Params.start_energy/(Params.min_reproduce_energy)){
			Critter3 newDonald = new Critter3();
			newDonald.moves = this.moves;	//asexual reproduction
			reproduce(newDonald, activity);
		}
	}

	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("@")){
			return true;
		}
		else {
			
		}
		return true;
	}
	
	/**
	 * Move in a direction that is as close to the expected direction as Trump is to telling the truth.
	 * @return
	 */
	private int toDirection() {
		int statement = Critter.getRandomInt(pantsOnFire+1);
		int truth = 0;
		for(int i = 1; i < truthTable.length; i++) {
			if(statement < truthTable[i]) {
				dir += i;
				truth += 1;
			}
		}
		factCheckRecord[truth] +=1;	//fact Check Donald and see how honest he was
		dir %= 8;
		return dir;
	}
}
