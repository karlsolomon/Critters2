package assignment5;


import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import assignment5.Critter.CritterShape;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller implements Initializable{
	
	public Button buttonMake;
	public Button buttonStats;
	public Button buttonStep;
	public Button buttonSeed;
	public Button buttonQuit;
	public Button buttonShow;
	public Button buttonLook;	
	public TextField makeNumber;
	public TextField stepNumber;
	public TextField seedNumber;
	
	public ChoiceBox<String> makeCritter;
	public ChoiceBox<String> statsCritter;
	
	@FXML
	public static Canvas world;
	
	public void getClassNames() {
		
	}
	
	public void initialize(){
		
	}
	
	public void makeButtonClicked() {
		
		String critterType = makeCritter.getValue();
		String makeNum = makeNumber.getText();
		System.out.println("Make " + makeNum + " " + critterType);
		
		try{
			Critter.makeCritter(critterType);
		}catch(Exception e){
			
		}
		
	}
	
	public void statsButtonClicked(){
		String critterType = statsCritter.getValue();
		System.out.println("Run stats on " + critterType);
	}
	
	public void stepButtonClicked(){
		String steps = stepNumber.getText();
		System.out.println("Step " + steps + " time(s)");		
	}
	
	public void seedButtonClicked(){
		System.out.println("Set Seed to " + seedNumber.getText());
		Long seedNum = Long.parseLong(seedNumber.getText());
		Critter.setSeed(seedNum);
	}
	
	public void quitButtonClicked(){
		System.out.println("Clicked the quit Button!");		
	}
	
	public void showButtonClicked(){
		System.out.println("Clicked the show Button!");	
		drawWorld();
	}
	
	public void lookButtonClicked(){
		System.out.println("Clicked the look Button!");
	}
	
	public void drawWorld(){
	    GraphicsContext gc = world.getGraphicsContext2D();
	    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
	    drawShapes(gc);
	}
	
	private static void drawShapes(GraphicsContext gc){
		CritterView.drawWorld();
	}



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList(Main.critterList);
		makeCritter.setItems(list);
		statsCritter.setItems(list);
		
	}

	
}

