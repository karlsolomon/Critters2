package assignment5;
	
import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	static GridPane grid = new GridPane();
	public static ArrayList<String> critterList = new ArrayList<String>();

	@Override
	public void start(Stage primaryStage) {
		try {			
			final String s = System.getProperty("user.dir");
			File src = new File(s + "\\src\\assignment5");
			File[] listOfFiles = src.listFiles();
			
			for(File f : listOfFiles){
				String fName = f.toString();
				String name = fName.substring(fName.lastIndexOf("\\") + 1, fName.lastIndexOf("."));
				try{
					Class<?> c = Class.forName("assignment5."+name);
					if(assignment5.Critter.class.isAssignableFrom(c)){
						critterList.add("assignment5."+name);
					}
				}catch(Exception e){}
			}
			critterList.remove("assignment5.Critter");
			for(String x : critterList){
				System.out.println(x);
			}
			
			
			
			grid.setGridLinesVisible(true);

			Scene scene = new Scene(grid, 500, 500);
			primaryStage.setScene(scene);
			
			primaryStage.show();
			
			// Paints the icons.
			Painter.paint();
			
		} catch(Exception e) {
			e.printStackTrace();		
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
