package assignment5;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import assignment5.Critter.CritterShape;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {
	
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
	public Canvas world;
	
	
	
	
	
	public void makeButtonClicked() {
		String critterType = makeCritter.getAccessibleText();
		String makeNum = makeNumber.getText();
		System.out.println("Make " + makeNum + " " + critterType);
	}
	
	public void statsButtonClicked(){
		String critterType = statsCritter.getAccessibleText();
		System.out.println("Run stats on " + critterType);
	}
	
	public void stepButtonClicked(){
		String steps = stepNumber.getText();
		System.out.println("Step " + steps + " time(s)");		
	}
	
	public void seedButtonClicked(){
		System.out.println("Set Seed to " + seedNumber.getText());
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
		int max = CritterView.maxDimensionOfShape;
		ArrayList<CritterView> critters = new ArrayList<>();		 	
	        critters.add(new CritterView(CritterShape.CIRCLE, Color.ORANGE, Point.getrandomPoint(max, 400)));
	        critters.add(new CritterView(CritterShape.SQUARE, Color.BLUE, Point.getrandomPoint(max, 400)));
	        critters.add(new CritterView(CritterShape.TRIANGLE, Color.BLACK, Point.getrandomPoint(max, 400)));
	        critters.add(new CritterView(CritterShape.DIAMOND, Color.RED, Point.getrandomPoint(max, 400)));
	        critters.add(new CritterView(CritterShape.STAR, Color.GREEN, Point.getrandomPoint(max, 400)));
	        for(CritterView c : critters) {
	        	gc.setFill(c.getColor());
	        	gc.fillPolygon(c.getXCoords(), c.getYCoords(), c.getXCoords().length);
	        }
	}

	
}

