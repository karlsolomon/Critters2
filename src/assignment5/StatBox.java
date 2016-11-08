package assignment5;

import java.util.List;

public class StatBox {
	
	public boolean running;
	public String name;
	
	public StatBox(String s){
		running = true;
		name = s;
		
		try {
			Class<?> c = Class.forName("assignment5."+name);
			List<Critter> list = Critter.getInstances(name);
		} catch (ClassNotFoundException |InvalidCritterException e) {}
	}
		
	public void update(){
		
	}
	
}
