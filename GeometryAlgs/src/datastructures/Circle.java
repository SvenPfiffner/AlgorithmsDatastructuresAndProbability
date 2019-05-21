package datastructures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Datastructure for a circle 
 * @author Sven Pfiffner
 */
public class Circle {
	
	private Vector2D center;
	private double radius;
	
	public Circle(Vector2D center, double radius) {
		this.center = center;
		this.radius = radius;
	}
	
	/**
	 * Checks if the circle contains a given point
	 * @return true if it does
	 */
	public boolean contains(Vector2D point) {
		return center.distance(point) <= radius;
	}
	
	public Vector2D getCenter() {
		return this.center;
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public void setCenter(Vector2D center) {
		this.center = center;
	}
	
	/**
	 * Draw this circle on a given canvas
	 */
	public void drawOnCanvas(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.RED);
		gc.setLineWidth(2);
		
		gc.strokeOval(center.getX() - radius, center.getY() - radius, radius*2, radius*2);
		
	}
	
	/**
	 * Compares two circle by size
	 * @param c1 first circle
	 * @param c2 second circle
	 * @return the smaller circle
	 */
	public static Circle min(Circle c1, Circle c2) {
		if(c1.getRadius() <= c2.getRadius()) {
			return c1;
		} else {
			return c2;
		}
	}
	
	
}
