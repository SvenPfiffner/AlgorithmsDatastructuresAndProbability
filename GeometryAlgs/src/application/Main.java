package application;

import java.util.ArrayList;

import algs.Megiddo;
import algs.Voronoi;
import datastructures.Vector2D;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * A simple JavaFx window where the user can draw points on a canvas
 * @author Sven Pfiffner
 *
 */
public class Main extends Application {
	
	static ArrayList<Vector2D> points = new ArrayList<Vector2D>();
	final double deleteTresh = 10;
	final RadioButton rb1 = new RadioButton("Smallest enclosing circle");
	final RadioButton rb2 = new RadioButton("Voronoi Diagram");
	
	@Override
	public void start(Stage primaryStage) {
		//Configure UI elements
		Canvas canvas = new Canvas(); //This is the canvas where the user can draw points
		Button reset = new Button(); //This button will reset the canvas
		Label instruction = new Label(); //This displays the programs instruction
		Label radioLabel = new Label(); //Describes the radio buttons
		reset.setText("Reset Canvas");
		instruction.setText("Use mouse left to draw points | mouse right to delete points");
		radioLabel.setText("Choose Algorithm");
		
		ToggleGroup group = new ToggleGroup();
		rb1.setToggleGroup(group);
		rb1.setSelected(true);
		
		rb2.setToggleGroup(group);
		rb2.setSelected(false);
		
		//Configure layout
		BorderPane bp = new BorderPane();
		Pane wrapperPane = new Pane();
		wrapperPane.getChildren().add(canvas);
		VBox leftBox = new VBox(reset);
		VBox rightBox = new VBox(radioLabel, rb1, rb2);
		HBox bottomBox = new HBox(instruction);
		leftBox.setAlignment(Pos.CENTER);
		rightBox.setAlignment(Pos.CENTER_LEFT);
		bottomBox.setAlignment(Pos.CENTER);
		bp.setCenter(wrapperPane);
		bp.setLeft(leftBox);
		bp.setRight(rightBox);
		bp.setBottom(bottomBox);
		canvas.widthProperty().bind(wrapperPane.widthProperty());
		canvas.heightProperty().bind(wrapperPane.heightProperty());
		VBox.setMargin(reset, new Insets(10));
		VBox.setMargin(radioLabel, new Insets(10));
		HBox.setMargin(instruction, new Insets(10));
		HBox.setMargin(rb1, new Insets(10));
		HBox.setMargin(rb2, new Insets(10));
		BorderPane.setMargin(wrapperPane, new Insets(10));
		wrapperPane.getStyleClass().add("canvas");
				
		//Event handling
		reset.setOnAction(event -> {
			//TODO: Fill in functionality of reset button
			reset(canvas);
		});

		canvas.setOnMouseClicked(event -> {
			
			if(event.getButton() == MouseButton.PRIMARY) {
				Vector2D v = new Vector2D(event.getX(), event.getY());
				points.add(v);
				
			}
			
			if(event.getButton() == MouseButton.SECONDARY) {
				Vector2D v = new Vector2D(event.getX(), event.getY());
				for(Vector2D p: points) {
					if(Math.abs(p.getX()-v.getX()) <= deleteTresh && Math.abs(p.getY()-v.getY()) <= deleteTresh) {
						points.remove(p);
						break;
					}
				}
			}
			
			update(canvas);
			
		});
				
		Scene scene = new Scene(bp, 1200, 900);
		scene.getStylesheets().add("/application/application.css");
		primaryStage.setScene(scene);
		primaryStage.setTitle("ETH Zurich 2019 | Geometry Algorithms | by Sven Pfiffner");
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
	public void update(Canvas canvas) {
		//Clear canvas
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
				
		//draw all points
		for(Vector2D v: points) {
			v.drawOnCanvas(canvas);
		}
		
		//Perform choosen algorithm
		if(rb1.isSelected()) {
			//Find smallest circle
			Megiddo m = new Megiddo(points);
			m.draw(canvas);
		} else {
			//Perform Voronoi
			Voronoi v = new Voronoi(points);
			v.draw(canvas);
		}
		
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
