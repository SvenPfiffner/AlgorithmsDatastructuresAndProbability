package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	Label instructions;
	Button clarkson;
	
	@Override
	public void start(Stage primaryStage) {
		
		//Configure UI Elements
		instructions = new Label();
		instructions.setText("Please choose algorithm to display");
		instructions.setAlignment(Pos.CENTER);
		clarkson = new Button();
		clarkson.setText("Clarkson Min Circle");
		clarkson.setMaxWidth(Double.MAX_VALUE);
		
		//Configure layout
		VBox buttonPane = new VBox(instructions,clarkson);
		VBox.setMargin(instructions, new Insets(10));
		VBox.setMargin(clarkson, new Insets(10));
		buttonPane.setAlignment(Pos.TOP_CENTER);
		
		//Configure stage
		Scene scene = new Scene(buttonPane, 300, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("©Sven Pfiffner");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void openDrawArea(String title, String instructions) {
		Stage stage = new Stage();
		
		//Configure UI elements
		Canvas canvas = new Canvas();
		
		//Configure layout
		BorderPane bp = new BorderPane();
		bp.setCenter(canvas);
		
		Scene scene = new Scene(bp, 900, 900);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
