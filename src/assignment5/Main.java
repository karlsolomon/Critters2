package assignment5;
	
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.Window;

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
			} catch(Exception e) {}
		}
		critterList.remove("Critter");
		for(String x : critterList){
			System.out.println(x);
		}
	    primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Parent parent = FXMLLoader.load(getClass().getResource("IDK.fxml"));
        canvas = new Canvas(Params.canvas_width,Params.canvas_height);
        Controller.world = canvas;
        root.getChildren().add(parent);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();               
        double leftPaneWidth = 275;
        canvas.setLayoutX(leftPaneWidth);

    }
    
    public void close(){
    	
    }
}
