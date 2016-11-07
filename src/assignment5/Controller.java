package assignment5;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
	
	public Button buttonMake;
	public Button buttonStats;
	@FXML
	public static Button buttonStep = new Button();
	public Button buttonSeed;
	public Button buttonQuit;
	public Button buttonShow;
	public Button startButton;
	public Button quitButton;
	public TextField makeNumber;
	@FXML
	public static TextField stepNumber;
	public TextField seedNumber;
	public SplitPane splitPane;
	@FXML
	public static Pane worldPane;
	public CheckBox steps;
	public CheckBox continuous;
	public ChoiceBox<String> makeCritter;
	public ChoiceBox<String> statsCritter;
	public Slider worldWidth;
	public Slider worldHeight;
	public Slider speedSlider;
	public TextField widthDisplay;
	public TextField heightDisplay;
	public TextField speedDisplay;
	
	@FXML
	public static Canvas world;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> list = FXCollections.observableArrayList(Main.critterList);
		makeCritter.setItems(list);
		statsCritter.setItems(list);
		
		worldWidth.setValue(Params.world_width);
		worldHeight.setValue(Params.world_height);
		worldWidth.valueProperty().addListener((obs, oldval, newVal) -> {
			worldWidth.setValue(newVal.intValue());
//			Params.world_width = newVal.doubleValue();
//			Params.setBin();
			
		});
		worldHeight.valueProperty().addListener((obs, oldval, newVal) -> {
			worldHeight.setValue(newVal.intValue());
//			Params.world_height = newVal.doubleValue();
//			Params.setBin();
		});
		speedSlider.valueProperty().addListener((obs, oldval, newVal) -> speedSlider.setValue(newVal.intValue()));
				
		widthDisplay.setText(new Integer(Params.world_width.intValue()).toString());
		heightDisplay.setText(new Integer(Params.world_height.intValue()).toString());
		widthDisplay.textProperty().bindBidirectional(worldWidth.valueProperty(), NumberFormat.getNumberInstance());
		heightDisplay.textProperty().bindBidirectional(worldHeight.valueProperty(), NumberFormat.getNumberInstance());
		
		speedSlider.setValue(Params.animation_speed);
		speedDisplay.setText(new Integer(Params.animation_speed).toString());
		speedDisplay.textProperty().bindBidirectional(speedSlider.valueProperty(), NumberFormat.getNumberInstance());
		
//		speedLabel.setText(Math.round(speedSlider.getValue()) + "");
//		speedLabel.textProperty().bind(Bindings.format("%d",speedSlider.valueProperty().intValue()));
		
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
		buttonStep.setOnAction( e -> {
			GraphicsContext gc = Main.canvas.getGraphicsContext2D();
		    gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

		});
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
			CritterWorld.doTimeStep();
		}	
		showButtonClicked();
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
		if(world != Main.canvas)
			world = Main.canvas;
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
	public void changeWorldDimensions(){
		
		if (!isStarted) {
			Params.world_width = worldWidth.getValue();
			Params.world_height = worldHeight.getValue();
			Params.setBin();
		}else{
			//Pop-Up saying can not change world dimensions
			Stage window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setMinWidth(250);
			
			Label label = new Label();
			label.setText("The world dimensions cannot be changed once a Critter has been created.");
			
			Button okButton = new Button("Ok");
			
			okButton.setOnAction(e -> {
				window.close();
			});
			
			VBox layout = new VBox();
			layout.getChildren().addAll(label, okButton);
			layout.setAlignment(Pos.CENTER);
			Scene scene = new Scene(layout);
			window.setScene(scene);
			window.showAndWait();
			
			worldWidth.setValue(Params.world_width);
			worldHeight.setValue(Params.world_height);
			widthDisplay.setText("" + Params.world_width.intValue());
			heightDisplay.setText("" + Params.world_height.intValue());
		}
	}

	
}

