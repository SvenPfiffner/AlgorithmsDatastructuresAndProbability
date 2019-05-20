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
import javafx.scene.paint.Color;


public class Main extends Application {
	
	static ArrayList<Point> points = new ArrayList<Point>();
	
	@Override
	public void start(Stage primaryStage) {
		//Configure UI elements
		Canvas canvas = new Canvas(); //This is the canvas where the user can draw points
		Button reset = new Button(); //This button will reset the canvas
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
		BorderPane.setMargin(wrapperPane, new Insets(10));
		wrapperPane.getStyleClass().add("canvas");
				
		//Event handling
		reset.setOnAction(event -> {
			//TODO: Fill in functionality of reset button
			reset(canvas);
		});

		canvas.setOnMouseClicked(event -> {
			//TODO: Fill in what happens upon mouse click on canvas
			Point p = new Point(event.getX(), event.getY());
			points.add(p);
			update(canvas);
		});
				
		Scene scene = new Scene(bp, 900, 900);
		scene.getStylesheets().add("/application/application.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("ETH Zurich 2019");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	/**
	 * Reset the canvas
	 */
	public static void reset(Canvas canvas) {
		points.clear();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	/**
	 * Update canvas
	 */
	public static void update(Canvas canvas) {
		//Clear canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
		//draw all points
		for(Point p: points) {
			p.drawOnCanvas(canvas);
		}
				
		//TODO: Draw desired stuff
		/*
		 * You can draw on the canvas using the gc. methods
		 */
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
