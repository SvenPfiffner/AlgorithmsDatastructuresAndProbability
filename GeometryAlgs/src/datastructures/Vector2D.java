package datastructures;

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
	 * Performs a scalar multiplication on the vector
	 * @param v the scalar to multiply
	 * @return a new vector that represents a scalar multiplication of this vector and given scalar
	 */
	public Vector2D scalarMul(double v) {
		return new Vector2D(x*v,y*v);
	}
	
	/**
	 * Adds another vector to this one (vector addition)
	 * @param v vector to add
	 * @return new vector with corresponding values
	 */
	public Vector2D add(Vector2D v) {
		return new Vector2D(x+v.getX(), y+v.getY());
	}
	
}
