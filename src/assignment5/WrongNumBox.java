package assignment5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WrongNumBox {
	
	public static Stage window;
	
	public static void display(){
		window = new Stage();
		window.setTitle("Error");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		
		Label label = new Label();
		label.setText("Error: Input is not a number.\n    Please input a number.");
		Button btn = new Button("OK");
		btn.setOnAction(e -> window.close());
		VBox v = new VBox();
		v.setAlignment(Pos.CENTER);
		v.getChildren().addAll(label, btn);
		Scene s = new Scene(v);
		window.setScene(s);
		window.show();
	}
	
}
