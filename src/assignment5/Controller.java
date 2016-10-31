package assignment5;

import java.util.ArrayList;
import java.util.Collections;

import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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
	}
	
	public void lookButtonClicked(){
		System.out.println("Clicked the look Button!");		
	}

	
}

