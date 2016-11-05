package assignment5;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

import assignment5.Critter.CritterShape;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	public Button buttonMake;
	public Button buttonStats;
	public Button buttonStep;
	public Button buttonSeed;
	public Button buttonQuit;
	public Button buttonShow;
	public Button startButton;
	public Button quitButton;
	public TextField makeNumber;
	public TextField stepNumber;
	public TextField seedNumber;
	public SplitPane splitPane;
	public Pane worldPane;
	public CheckBox steps;
	public CheckBox continuous;
	public ChoiceBox<String> makeCritter;
	public ChoiceBox<String> statsCritter;
	public Slider worldWidth;
	public Slider worldHeight;
	public Label widthDisplay;
	public Label heightDisplay;
	
	public static Canvas world;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList(Main.critterList);
		makeCritter.setItems(list);
	}
	
	/*
	 * DONE
	 */
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
	
	/*
	 * DONE
	 */
	public void stepButtonClicked(){
		String steps = stepNumber.getText();
		System.out.println("Step " + steps + " time(s)");	
		int numSteps = Integer.parseInt(stepNumber.getText());
		for(int i = 0; i < numSteps ; i++) {
			CritterWorld.doTimeStep();
		}	
	}
	
	/**
	 * DONE
	 */
	public void seedButtonClicked(){
		System.out.println("Set Seed to " + seedNumber.getText());
		try {
			Long seedNum = Long.parseLong(seedNumber.getText());
			Critter.setSeed(seedNum);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * DONE
	 */
	public void quitButtonClicked(){
		System.out.println("Clicked the quit Button!");
		closeProgram();
	}
	
	/**
	 * DONE
	 */
	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit Critters", "Are you sure you want to quit?");
		if(answer) {
			Main.stage.close();
		}
	}
	
	/**
	 * FIXME: drawWorld doesn't work
	 */
	public void showButtonClicked(){
		System.out.println("Clicked the show Button!");
		CritterView.drawWorld();
	}

	public void startButton(){
		try {
			Parent root = FXMLLoader.load(getClass().getResource("MainController.fxml"));
			Main.stage.setTitle("Hello World");
			Main.stage.setScene(new Scene(root, Params.canvas_width,Params.canvas_height));
			Main.stage.show();
		} catch (Exception e) {
			
		}
	}

	
}

