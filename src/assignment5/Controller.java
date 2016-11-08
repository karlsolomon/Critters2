package assignment5;

import java.net.URL;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable{
	
	public boolean isStarted = false;
	public static boolean playing = false;
	
	public Button buttonMake;
	public Button buttonStats;
	@FXML
	public static Button buttonStep = new Button();
	public Button buttonSeed;
	public Button buttonQuit;
	public Button startButton;
	public Button quitButton;
	public TextField makeNumber;
	@FXML
	public static TextField stepNumber;
	public static Canvas canvas = new Canvas();
	public TextField seedNumber;
	public SplitPane splitPane;
	@FXML
	public static Pane worldPane;
	public CheckBox steps;
	public Button continuous = new Button();
	public ChoiceBox<String> makeCritter;
	public ChoiceBox<String> statsCritter;
	public Slider worldWidth;
	public Slider worldHeight;
	public Slider speedSlider = new Slider();
	public TextField widthDisplay;
	public TextField heightDisplay;
	public TextField speedDisplay = new TextField();
	
	@FXML
	public static Canvas world;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
			}
			else {
				continuous.setText("START");
				continuous.setStyle("-fx-text-fill: green");
			}
			Thread play = new Thread() {
				@Override
				public void run() {
					Long waitTime;
					while(playing){
						waitTime = 1000/Long.parseLong(speedDisplay.getText());
						Critter.worldTimeStep();
						CritterView.drawWorld();
						try{
							Thread.sleep(waitTime);
						} catch (Exception e1){
							e1.printStackTrace();
						}
					}
				}
			};
			play.start();
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
	
	/*
	 * DONE
	 */
	public void makeButtonClicked() {
		isStarted = true;
		String critterType = makeCritter.getValue();
		String makeNum = makeNumber.getText();
		int num = Integer.parseInt(makeNum);
		System.out.println("Make " + makeNum + " " + critterType);
		
		try{
			for(int i = 0; i < num; i++) {
				Critter.makeCritter(critterType);
			}
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
		String steps;
		try{
			 steps = stepNumber.getText();
		}
		catch (Exception e) {
			steps = "1";
		}
		System.out.println("Step " + steps + " time(s)");	
		int numSteps = Integer.parseInt(steps);
		for(int i = 0; i < numSteps ; i++) {
			Critter.worldTimeStep();
		}	

		if(world != Main.canvas)
			world = Main.canvas;
		System.out.println("Clicked the show Button!");
	    CritterView.drawWorld();
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

