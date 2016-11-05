package assignment5;

/*
 * This Critter impulates a Knight in chess. It will move in the L pattern with three moves in one direction and then one in another.
 * This critter will walk, run, and rest at roughly equal quantities
 * Example critter
 */
public class Critter4 extends Critter{

	@Override
	public CritterShape viewShape() {
		return CritterShape.DIAMOND;
	}
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BLACK; }

	@Override
	public String toString() { return "4"; }
	
	private int dir = 0;
	private int moveCounter = 0;
	int[] pattern = new int[4];
	public Critter4() {
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String opponent) {
		reproduce(new Critter4(), Critter.getRandomInt(8));	// reproduces before potentially fighting to save the family line
		return CritterWorld.tryMove(this, dir, 1);
	}
	
	private void move() {	
		int action = Critter.getRandomInt(3);
		if(action == 0) {
			super.walk(pattern[moveCounter]);
			moveCounter +=1;
		}
		else if (action ==1 && moveCounter < 3) {
			super.run(pattern[moveCounter+1] - pattern[moveCounter]);
			moveCounter +=2;
		}		
		if(moveCounter > 3) {
			updatePattern();
		}
	}
	
	private void updatePattern() {
		dir = Critter.getRandomInt(8);
		boolean clockWise = (Critter.getRandomInt(2) == 1);
		if(clockWise) {
			pattern[3] = dir+2;
		}
		else{
			pattern[3] = dir-2;
		}
		pattern[3] %= 8;
	}

	@Override
	public void doTimeStep() {
		/* take one step forward */
		move();		
		if (getEnergy() > 130) {
			Critter4 child = new Critter4();
			reproduce(child, Critter.getRandomInt(8));
		}
	}
}
