/* 
 * Copyright (c) 2018 Project Nayuki
 * https://www.nayuki.io/page/smallest-enclosing-circle
 * 
 * Modified 2019 Sven Pfiffner
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program (see COPYING.txt and COPYING.LESSER.txt).
 * If not, see <http://www.gnu.org/licenses/>.
 */

package algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import datastructures.Circle;
import datastructures.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * Implements clarksons min circle algorithm
 * @author Sven Pfiffner
 */
public class Megiddo {
	
	ArrayList<Vector2D> points;
	int[] pointWeight;
	
	Circle circle;
	
	final double approx = 0.001;
	
	public Megiddo(ArrayList<Vector2D> points) {
		
		//Create a copy of input points
		this.points = new ArrayList<Vector2D>(points);
		
		//Initialize array for point "weight" with 1's
		this.pointWeight = new int[points.size()];
		Arrays.fill(pointWeight, 1);
		
		//Set initial smallest circle to 0 and at origin
		this.circle = new Circle(new Vector2D(0,0), 0);
		
		//Compute smallest circle if we have at least two points
		if(points.size() >= 2) {findMinCircle();}
		
	}
	
	/**
	 * Get the radius of this min circe
	 * @return
	 */
	public double getRadius() {
		return circle.getRadius();
	}
	
	/**
	 * Get the center of this min circle
	 * @return
	 */
	public Vector2D getCenter() {
		return circle.getCenter();
	}
	
	/**
	 * Draw the found min circle on a given canvas
	 * @param canvas the canvas
	 */
	public void draw(Canvas canvas) {
		circle.drawOnCanvas(canvas);
	}
	
	/* 
	 * Returns the smallest circle that encloses all the given points. Runs in expected O(n) time, randomized.
	 * Note: If 0 or 1 point is given, a circle of radius 0 is returned.
	 */
	private void findMinCircle() {
		circle = makeCircle(points);
	}
	
	public static Circle makeCircle(List<Vector2D> points) {
		// Clone list to preserve the caller's data, randomize order
		List<Vector2D> shuffled = new ArrayList<>(points);
		Collections.shuffle(shuffled, new Random());
		
		// Progressively add points to circle or recompute circle
		Circle c = null;
		for (int i = 0; i < shuffled.size(); i++) {
			Vector2D p = shuffled.get(i);
			if (c == null || !c.contains(p))
				c = makeCircleOnePoint(shuffled.subList(0, i + 1), p);
		}
		return c;
	}
	
	
	// One boundary point known
	private static Circle makeCircleOnePoint(List<Vector2D> points, Vector2D p) {
		Circle c = new Circle(p, 0);
		for (int i = 0; i < points.size(); i++) {
			Vector2D q = points.get(i);
			if (!c.contains(q)) {
				if (c.getRadius() == 0)
					c = makeDiameter(p, q);
				else
					c = makeCircleTwoPoints(points.subList(0, i + 1), p, q);
			}
		}
		return c;
	}
	
	
	// Two boundary points known
	private static Circle makeCircleTwoPoints(List<Vector2D> points, Vector2D p, Vector2D q) {
		Circle circ = makeDiameter(p, q);
		Circle left  = null;
		Circle right = null;
		
		// For each point not in the two-point circle
		Vector2D pq = q.sub(p);
		for (Vector2D r : points) {
			if (circ.contains(r))
				continue;
			
			// Form a circumcircle and classify it on left or right side
			double cross = pq.cross(r.sub(p));
			Circle c = makeCircumcircle(p, q, r);
			if (c == null)
				continue;
			else if (cross > 0 && (left == null || pq.cross(c.getCenter().sub(p)) > pq.cross(left.getCenter().sub(p))))
				left = c;
			else if (cross < 0 && (right == null || pq.cross(c.getCenter().sub(p)) < pq.cross(right.getCenter().sub(p))))
				right = c;
		}
		
		// Select which circle to return
		if (left == null && right == null)
			return circ;
		else if (left == null)
			return right;
		else if (right == null)
			return left;
		else
			return left.getRadius() <= right.getRadius() ? left : right;
	}
	
	
	static Circle makeDiameter(Vector2D a, Vector2D b) {
		Vector2D c = new Vector2D((a.getX() + b.getX()) / 2, (a.getY() + b.getY()) / 2);
		return new Circle(c, Math.max(c.distance(a), c.distance(b)));
	}
	
	
	static Circle makeCircumcircle(Vector2D a, Vector2D b, Vector2D c) {
		// Mathematical algorithm from Wikipedia: Circumscribed circle
		double ox = (Math.min(Math.min(a.getX(), b.getX()), c.getX()) + Math.max(Math.min(a.getX(), b.getX()), c.getX())) / 2;
		double oy = (Math.min(Math.min(a.getY(), b.getY()), c.getY()) + Math.max(Math.min(a.getY(), b.getY()), c.getY())) / 2;
		double ax = a.getX() - ox,  ay = a.getY() - oy;
		double bx = b.getX() - ox,  by = b.getY() - oy;
		double cx = c.getX() - ox,  cy = c.getY() - oy;
		double d = (ax * (by - cy) + bx * (cy - ay) + cx * (ay - by)) * 2;
		if (d == 0)
			return null;
		double x = ((ax*ax + ay*ay) * (by - cy) + (bx*bx + by*by) * (cy - ay) + (cx*cx + cy*cy) * (ay - by)) / d;
		double y = ((ax*ax + ay*ay) * (cx - bx) + (bx*bx + by*by) * (ax - cx) + (cx*cx + cy*cy) * (bx - ax)) / d;
		Vector2D p = new Vector2D(ox + x, oy + y);
		double r = Math.max(Math.max(p.distance(a), p.distance(b)), p.distance(c));
		return new Circle(p, r);
	}
	
}
