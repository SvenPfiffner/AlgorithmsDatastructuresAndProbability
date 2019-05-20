package algs;

import java.util.ArrayList;

import datastructures.Point;

/**
 * This class implements the clarkson min-circle algorithm
 * @author Sven Pfiffner
 */
public class Clarkson {

	counterPoint[] points;
	Point circCenter;
	double circDiameter;
	
	public Clarkson(ArrayList<Point> points) {
		this.points = new counterPoint[points.size()];
		int i = 0;
		for(Point p: points) {
			this.points[i++] = new counterPoint(p.getXPos(),p.getYPos());
		}
	}
	
	/**
	 * Find the smallest possible circle for the given points
	 */
	public void findMinCirc() {
		
	}
	
}

class counterPoint extends Point{
	int count = 0;
	
	public counterPoint(double x, double y) {
		super(x,y);
	}
	
	/**
	 * Increment the counter on this point by one
	 */
	public void inc() {
		count++;
	}
	
	/**
	 * Give the counter on this point a new value
	 * @param v new value
	 */
	public void setCount(int v) {
		count = v;
	}
	
}
