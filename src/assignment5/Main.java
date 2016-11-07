package assignment5;
	
import java.io.*;
import java.util.ArrayList;
import javafx.application.Application;
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
    	stage.setResizable(false);
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

        root.getChildren().add(parent);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();    
        

        double height = primaryStage.getHeight();
        double width = primaryStage.getWidth();
        double leftMargin = 110;
        double rightMargin = 20;
        double bottomMargin = 130;
        double topMargin = 35;
        Params.setCanvasParams(width - (leftMargin + rightMargin), height - (topMargin + bottomMargin));
        Params.setWorldParams(50, 50);
        canvas = new Canvas(Params.canvas_width,Params.canvas_height);
        canvas.setLayoutX(leftMargin);
        canvas.setLayoutY(topMargin);
        Controller.world = canvas;
        root.getChildren().add(canvas);

    }
    
    public void close(){
    	
    }
}
