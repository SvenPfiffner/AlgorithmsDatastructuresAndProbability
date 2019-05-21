package datastructures;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Datastructure for a two dimensional vector
 * @author Sven Pfiffner
 */
public class Vector2D {
	final double x;
	final double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return The x coordinate of this vector
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return The y coordinate of this vector
	 */
	public double getY() {
		return y;
	}
	
	/**
	 * Adds a scalar to both coordinates
	 * @param v value to add
	 * @return new vector with corresponding values
	 */
	public Vector2D add(double v) {
		return new Vector2D(x+v,y+v);
	}
	
	
	/**
	 * Substracts another vector from this one
	 * @param v vector to substract
	 * @return new vector with corresponding values
	 */
	public Vector2D sub(Vector2D v) {
		return new Vector2D(x-v.getX(), y-v.getY()); 
		
	}
	
	/**
	 * Performs a scalar multiplication on the vector
	 * @param v the scalar to multiply
	 * @return a new vector that represents a scalar multiplication of this vector and given scalar
	 */
	public Vector2D scalarMul(double v) {
		return new Vector2D(x*v,y*v);
	}
	
	/**
	 * Performs a scalar vector multiplication on the vector
	 * @param v the other vector
	 * @return value of the scalar vector multiplication
	 */
	public double scalarMul(Vector2D v) {
		return (x*v.getX()+y*v.getY());
	}
	
	/**
	 * Adds another vector to this one (vector addition)
	 * @param v vector to add
	 * @return new vector with corresponding values
	 */
	public Vector2D add(Vector2D v) {
		return new Vector2D(x+v.getX(), y+v.getY());
	}
	
	/**
	 * Get the length of this vector
	 * @return double value corresponding to lenght
	 */
	public double lenght() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	
	/**
	 * To string method
	 */
	public String toString() {
		return ("["+ x +","+ y +"]");
	}
	
	/**
	 * Draw this vector as a point on a given canvas
	 */
	public void drawOnCanvas(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(4);
		gc.strokeOval(x-2, y-2, 4, 4);
	}
	
}
