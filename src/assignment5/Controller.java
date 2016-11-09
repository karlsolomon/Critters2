/* Critter2 Controller.java
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

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;


public class Controller implements Initializable{
	
	public boolean isStarted = false;
	public static boolean statsRunning = false;
	public String critterType;
	public static boolean playing = false;
	public static StatBox stats;
	
	public Button buttonMake = new Button();
	public Button buttonStats = new Button();
	@FXML
	public Button buttonStep = new Button();
	public Button buttonSeed = new Button();
	public Button buttonQuit = new Button();
	public Button startButton = new Button();
	public Button quitButton = new Button();
	public TextField makeNumber = new TextField();
	@FXML
	public TextField stepNumber = new TextField();
	public static Canvas canvas = new Canvas();
	public TextField seedNumber = new TextField();
	public Button continuous;
	public ChoiceBox<String> makeCritter = new ChoiceBox<>();
	public ChoiceBox<String> statsCritter = new ChoiceBox<>();
	public Slider worldWidth = new Slider();
	public Slider worldHeight = new Slider();
	public Slider speedSlider = new Slider();
	public TextField widthDisplay = new TextField();
	public TextField heightDisplay = new TextField();
	public TextField speedDisplay = new TextField();
	private ArrayList<Node> disabledWhileRunning = new ArrayList<Node>();
	
	/**
	 * this is initializing a bunch of different variables and setting up the ui functions
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addNodes();
		continuous.setText("Start");
		continuous.setStyle("-fx-text-fill: green");		
		ObservableList<String> list = FXCollections.observableArrayList(Main.critterList);
		makeCritter.setItems(list);
		statsCritter.setItems(list);
		
		worldWidth.setValue(Params.world_width);
		worldHeight.setValue(Params.world_height);
		worldWidth.valueProperty().addListener((obs, oldval, newVal) -> {
			worldWidth.setValue(newVal.intValue());

			if (!isStarted) {
				Params.world_width = worldWidth.getValue();
				Params.setBin();
				CritterView.drawWorld();
			}else{
				//Pop-Up saying can not change world dimensions
				
				boolean reset = ConfirmBox.display("Da fuq?", "Are you sure you want to edit world dimensions?\n(Doing so will clear all Critters from the world.)");
				if(reset){
					Params.world_width = worldWidth.getValue();
					Params.setBin();
					isStarted = false;
					CritterWorld.critterMap = new HashMap<>();
					CritterView.drawWorld();
				}else{
					worldWidth.setValue(Params.world_width);
					widthDisplay.setText("" + Params.world_width.intValue());
				}
			}
		});
		
		worldHeight.valueProperty().addListener((obs, oldval, newVal) -> {
			worldHeight.setValue(newVal.intValue());
			if (!isStarted) {
				Params.world_height = worldHeight.getValue();
				Params.setBin();
				CritterView.drawWorld();
			}else{
				//Pop-Up saying can not change world dimensions
				
				boolean reset = ConfirmBox.display("Da fuq?", "Are you sure you want to edit world dimensions?\n(Doing so will clear all Critters from the world.)");
				if(reset){
					Params.world_height = worldHeight.getValue();
					Params.setBin();
					isStarted = false;
					CritterWorld.critterMap = new HashMap<>();
					CritterView.drawWorld();
				} else{
					worldHeight.setValue(Params.world_height);
					heightDisplay.setText("" + Params.world_height.intValue());
				}
			}
		});
		
		
		
		continuous.setOnMouseClicked(e -> {
			playing = !playing;
			if(playing) {
				continuous.setText("STOP");
				continuous.setStyle("-fx-text-fill: red");
				for(Node n : disabledWhileRunning) {
					if(n != null)
						n.setDisable(true);
				}
			}
			else {
				continuous.setText("START");
				continuous.setStyle("-fx-text-fill: green");
				for(Node n : disabledWhileRunning) {
					if(n!= null)
						n.setDisable(false);
				}
			}
			
			Task<Void> task = new Task<Void>(){
				
				@Override
				protected Void call() throws Exception {
					Long waitTime = 1000/Long.parseLong(speedDisplay.getText());
					while(playing){
						Critter.worldTimeStep();
						CritterView.drawWorld();
						Platform.runLater(new Runnable(){
							@Override
							public void run(){
								Critter.worldTimeStep();
								CritterView.drawWorld();
								if(statsRunning) stats.update();
							}
						});
						try{
							Thread.sleep(waitTime);
						}catch(Exception e){
							WrongNumBox.display();
						}
					}
					return null;
				}
		
			};
			
			Thread thread = new Thread(task);
			thread.setDaemon(true);
			thread.start();
		});
		
		speedSlider.valueProperty().addListener((obs, oldval, newVal) -> {
			speedSlider.setValue(newVal.intValue());
		});
				
		widthDisplay.setText(new Integer(Params.world_width.intValue()).toString());
		heightDisplay.setText(new Integer(Params.world_height.intValue()).toString());
		widthDisplay.textProperty().bindBidirectional(worldWidth.valueProperty(), NumberFormat.getNumberInstance());
		heightDisplay.textProperty().bindBidirectional(worldHeight.valueProperty(), NumberFormat.getNumberInstance());
		
		speedSlider.setValue(Params.animation_speed);
		speedDisplay.setText(new Integer(Params.animation_speed).toString());
		speedDisplay.textProperty().bindBidirectional(speedSlider.valueProperty(), NumberFormat.getNumberInstance());
		

		
	}
	
	/**
	 * adding the nodes to be disabled when the animation is being run
	 */
	public void addNodes(){
		disabledWhileRunning.add(buttonMake);
		disabledWhileRunning.add(buttonStats);
		disabledWhileRunning.add(statsCritter);
		disabledWhileRunning.add(makeCritter);
		disabledWhileRunning.add(buttonStep);
		disabledWhileRunning.add(buttonSeed);
		disabledWhileRunning.add(buttonQuit);
		disabledWhileRunning.add(makeNumber);
		disabledWhileRunning.add(stepNumber);
		disabledWhileRunning.add(seedNumber);
		disabledWhileRunning.add(worldWidth);
		disabledWhileRunning.add(worldHeight);
		disabledWhileRunning.add(speedSlider);
		disabledWhileRunning.add(widthDisplay);
		disabledWhileRunning.add(heightDisplay);
		disabledWhileRunning.add(speedDisplay);
	}
	
	/*
	 * makes an input amount of the selected critter and adds them to
	 * the critterMap to be displayed
	 */
	public void makeButtonClicked() {
		isStarted = true;
		String critterType = makeCritter.getValue();
		String makeNum = makeNumber.getText();
		
		System.out.println("Make " + makeNum + " " + critterType);
		
		try{
			int num = Integer.parseInt(makeNum);
			if(num > 100){
				BigNumBox.display();
				return;
			}
			for(int i = 0; i < num; i++) {
				Critter.makeCritter(critterType);
			}
		}catch(Exception e){
			WrongNumBox.display();
		}
	}
	
	/**
	 * Takes in the name of a critter and calls that critters runstats
	 */
	public void statsButtonClicked(){
		String s = statsCritter.getValue();
		if(s == null) return;
		//if stats is already running
			//same critter type
				//update the window
			//different critter type
				//close old window and open new
		//if stats isnt running
			//build new stats window
		if(statsRunning){
			if(critterType.equals(s)){
				stats.update();
			}else{
				stats.close();
				critterType = s;
				stats = new StatBox(critterType);
				stats.update();
			}
		}else{
			statsRunning = true;
			critterType = s;
			stats = new StatBox(critterType);
			stats.update();
		}
		
		
	
	}
	
	/*
	 * Takes in a number for how many steps are to be taken,
	 * and when the button is pressed, the system does a worldTimeStep
	 */
	public void stepButtonClicked(){
		String steps;
		isStarted = true;
		steps = stepNumber.getText();
		if(steps.equals("")){
			steps = "1";
		}
		System.out.println("Step " + steps + " time(s)");	
		int numSteps;
		try{
			numSteps = Integer.parseInt(steps);
		}
		catch (NumberFormatException e) {
			WrongNumBox.display();
			return;
		}
		if(numSteps > 100){
			BigNumBox.display();
			return;
		}
		for(int i = 0; i < numSteps ; i++) {
			Critter.worldTimeStep();
		}	

		System.out.println("Clicked the show Button!");
	    CritterView.drawWorld();
	    if(statsRunning) stats.update();
	}
	
	
	
	/**
	 * user provides a seed input and when the button is pressed, sets the seed for the program
	 */
	public void seedButtonClicked(){
		System.out.println("Set Seed to " + seedNumber.getText());

		try {
			Long seedNum = Long.parseLong(seedNumber.getText());
			Critter.setSeed(seedNum);
		} catch (NumberFormatException e) {
			WrongNumBox.display();
		}
	}
	
	/**
	 * calls closeProgram to close the program
	 */
	public void quitButtonClicked(){
		System.out.println("Clicked the quit Button!");
		closeProgram();
	}
	
	/**
	 * Closes the Program
	 */
	private void closeProgram(){
		Boolean answer = ConfirmBox.display("Quit Critters", "Are you sure you want to quit?");
		if(answer) {
			Main.stage.close();
		}
	}



	public void clearGrid(){
		CritterWorld.critterMap = new HashMap<>();
		CritterView.drawWorld();
	}

	
}

