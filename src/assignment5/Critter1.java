/* Critter2 Critter1.java
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

import assignment5.Critter;
import assignment5.Critter1;
import assignment5.CritterWorld;
import assignment5.Params;

public class Critter1 extends Critter{

	private int dir; //direction the critter goes
	private int[] genes = new int[8]; //genes of the critter
	
	private boolean isAwake;//if the critter is awake or not
	private static final int playing = 1;// 1/24 chance that the critter is playing
	private static final int lookingForFood = 5;// 5/24 chance that the critter is searching for food
	private static final int sleeping = 18;// 18/24 chance that the critter is sleeping
	
	private static final int awake = playing + lookingForFood;
	/**
	 * Constructor
	 */
	public Critter1(){
		for(int i = 0; i < 8; i++){
			genes[i] = Critter.getRandomInt(8);
		}
		
		isAwake = true;
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public CritterShape viewShape() {
		return CritterShape.SQUARE;
	}
	public javafx.scene.paint.Color viewColor() { return javafx.scene.paint.Color.BLUE; }

	@Override
	public void doTimeStep() {
		if(!isAwake){//Is the cat asleep?
			isAwake = willWakeUp();//Will it wake up?
		}
		if(isAwake){//Cat either is awake or just woke up
			int num = Critter.getRandomInt(awake);
			if(num < lookingForFood){
				String find = look(dir,true);
				if(find != null){
					if(find.equals("@")){
						run(dir);
					}
				}else{
					walk(dir+1);
				}
				
			}else{
				//cat will play in a set direction
				run(dir);
			}
		}
		if(getEnergy() > (8*Params.min_reproduce_energy)){
			int numKittens = Critter.getRandomInt(3);
			for(int i = 0; i < numKittens; i++){					
				Critter1 kitten = new Critter1();
				kitten.genes = this.genes;
				reproduce(kitten, Critter.getRandomInt(8));
			}
		}
			
	}

	/**
	 * If the cat is woken up, it will fight
	 * else
	 * if the cat is pregnant, it'll try to avoid fighting
	 * but if not pregnant, it will just fight
	 * @param opponent is the toString of the opponent, so if there are special interactions, you can code them
	 * @return if the critter will fight
	 */
	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("1")){

			return CritterWorld.tryMove(this,dir, 1);
		}
		else if(opponent.equals("@")){
			return true;
		}
		else if(!isAwake){
			isAwake = true;
			return true;
		}else{
			return CritterWorld.tryMove(this,dir, 1);
		}
	}

	private boolean willWakeUp(){
		int num = Critter.getRandomInt(24);
		if(num > sleeping){
			return true;
		}
		else return false;
	}
	@Override
	public String toString(){
		return "1";
	}
	
}