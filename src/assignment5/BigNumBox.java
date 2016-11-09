package assignment5;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BigNumBox {

	public static Stage window;
	
	public static void display(){
		window = new Stage();
		window.setTitle("Big Number");
		window.setMinWidth(250);
		window.setMinHeight(100);
		
		
		Label label = new Label();
		label.setText("Error: Number input is too big");
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
