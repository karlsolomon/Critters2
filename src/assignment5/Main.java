/* Critter2 Main.java
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
	
import java.io.*;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage stage;
	static Canvas canvas;
	static GridPane grid = new GridPane();
	public static ArrayList<String> critterList = new ArrayList<String>();

	
	int size = 500;
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) throws Exception {
    	stage = primaryStage;
    	final String s = System.getProperty("user.dir");
		File src = new File(s + "\\src\\assignment5");
		File[] listOfFiles = src.listFiles();
		
		for(File f : listOfFiles){
			String fName = f.toString();
			String name = fName.substring(fName.lastIndexOf("\\") + 1, fName.lastIndexOf("."));
			try{
				Class<?> c = Class.forName("assignment5."+name);
				if(assignment5.Critter.class.isAssignableFrom(c)){
					critterList.add(name);
				}
			} catch(ClassNotFoundException e) {}
		}
		critterList.remove("Critter");
		for(String x : critterList){
			System.out.println(x);
		}
	    primaryStage.setTitle("Critters");
        Group root = new Group();
        Parent parent = FXMLLoader.load(getClass().getResource("IDK.fxml"));

        root.getChildren().add(parent);
        Scene scene = new Scene(root);
        primaryStage.setHeight(700);
        primaryStage.setWidth(820);
        scene.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
		        if(newSceneWidth.doubleValue() < 820) {
		        	scene.getWindow().setWidth(820);
		        	CritterView.setCanvasWidth(820);
		        }
		        else
		        	CritterView.setCanvasWidth(newSceneWidth.doubleValue());
		        CritterView.setBin();
		    }
		});
        scene.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
		        if(newSceneHeight.doubleValue() < 700) {
		        	scene.getWindow().setHeight(700);
		        	CritterView.setCanvasHeight(700);
		        }
	        	else
	        		CritterView.setCanvasHeight(newSceneHeight.doubleValue());
		        CritterView.setBin();
		    }
		});
        primaryStage.setScene(scene);
        primaryStage.show();
        
        double height = primaryStage.getHeight();
        double width = primaryStage.getWidth();
        CritterView.setCanvasWidth(width);
        CritterView.setCanvasHeight(height);
        CritterView.setWorldParams(50, 50);
        Controller.canvas.setLayoutX(CritterView.leftMargin);
        Controller.canvas.setLayoutY(CritterView.topMargin);
        root.getChildren().add(Controller.canvas);
    }
    
    public void close(){
    	
    }
}
