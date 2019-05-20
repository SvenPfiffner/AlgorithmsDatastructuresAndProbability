package application;

import java.util.ArrayList;

import algs.Clarkson;
import datastructures.Point;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	static ArrayList<Point> points = new ArrayList<Point>();
	
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
		
		//Event handling
		clarkson.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				openDrawArea("Clarksons Min Circle", "Test");
			}});
		
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
		Button reset = new Button();
		reset.setText("Reset Canvas");
		
		//Configure layout
		BorderPane bp = new BorderPane();
		Pane wrapperPane = new Pane();
		wrapperPane.getChildren().add(canvas);
		VBox leftBox = new VBox(reset);
		leftBox.setAlignment(Pos.CENTER);
		bp.setCenter(wrapperPane);
		bp.setLeft(leftBox);
		canvas.widthProperty().bind(wrapperPane.widthProperty());
		canvas.heightProperty().bind(wrapperPane.heightProperty());
		VBox.setMargin(reset, new Insets(10));
		wrapperPane.getStyleClass().add("canvas");
		
		//Event handling
		reset.setOnAction(event -> {reset(canvas);});

		
		canvas.setOnMouseClicked(event -> {
			Point p = new Point(event.getX(), event.getY());
			points.add(p);
			update(canvas);
		});
		
		Scene scene = new Scene(bp, 900, 900);
		scene.getStylesheets().add("/application/application.css");
		stage.setScene(scene);
		stage.setTitle(title);
		stage.setResizable(false);
		stage.show();
	}
	
	public static void reset(Canvas canvas) {
		points.clear();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public static void update(Canvas canvas) {
		//Clear canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		//draw all points
		for(Point p: points) {
			p.drawOnCanvas(canvas);
		}
		
		//Get min circle
		Clarkson clarkson = new Clarkson(points);
		clarkson.findMinCirc();
		
		//Draw circle
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
