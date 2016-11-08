package assignment5;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StatBox {
	
	public boolean running;
	public String name;
	public Label label;
	public Stage window;
	
	public StatBox(String s){
		running = true;
		name = s;
		window = new Stage();
		window.setTitle(name + " Stats");
		window.setMaxWidth(250);
		
		label = new Label();		
		VBox v = new VBox();
		v.getChildren().add(label);
		v.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(v);
		window.setScene(scene);
		window.show();
	}
		
	public void update(){
		String s;
		try {
			Class<?> c = Class.forName("assignment5."+name);
			List<Critter> list = Critter.getInstances(name);
			s = Critter.runStats(list);
			label.setText(s);
			window.show();
			
		} catch (ClassNotFoundException |InvalidCritterException e) {}
	}
	
}
