package datastructures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Datastructure for a point that sits in a two dimensional plane
 * @author Sven Pfiffner
 */
public class Point {
	
	final double xPos;
	final double yPos;

	public Point(double xPos, double yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public double getXPos() {
		return xPos;
	}
	
	public double getYPos() {
		return yPos;
	}
	
	/**
	 * Converts this point to a vector
	 */
	public Vector2D toVector() {
		return new Vector2D(xPos, yPos);
	}
	
	/**
	 * Draw this Point on a given canvas
	 */
	public void drawOnCanvas(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(4);
		gc.strokeOval(xPos-2, yPos-2, 4, 4);
	}
	
}
