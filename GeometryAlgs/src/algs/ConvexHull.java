package algs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import datastructures.Vector2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ConvexHull {
	
	ArrayList<Vector2D> hull;
	
	public ConvexHull(List<Vector2D> points) {
		ArrayList<Vector2D> input = new ArrayList<Vector2D>(points);
		Collections.sort(input);
		hull = convex_hull(input);
	}

	public void draw(Canvas canvas ) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.RED);
		gc.setLineWidth(2);
		
		if(hull.size()<=1) {
			return;
		}
		
		Vector2D prec = hull.get(0);
		for(Vector2D p: hull.subList(1, hull.size())) {
			gc.strokeLine(prec.getX(), prec.getY(), p.getX(), p.getY());
			prec = p;
		}
		
		gc.strokeLine(hull.get(0).getX(), hull.get(0).getY(), hull.get(hull.size()-1).getX(), hull.get(hull.size()-1).getY());
		
	}
	
	public long cross(Vector2D O, Vector2D A, Vector2D B) {
		return (long)((A.getX() - O.getX()) * (long) (B.getY() - O.getY()) - (A.getY() - O.getY()) * (long) (B.getX() - O.getX()));
	}

	public ArrayList<Vector2D> convex_hull(ArrayList<Vector2D> points) {

		if (points.size() > 1) {
			int n = points.size(), k = 0;
			Vector2D[] H = new Vector2D[2 * n];

			Collections.sort(points);

			// Build lower hull
			for (int i = 0; i < n; ++i) {
				while (k >= 2 && cross(H[k - 2], H[k - 1], points.get(i)) <= 0)
					k--;
				H[k++] = points.get(i);
			}

			// Build upper hull
			for (int i = n - 2, t = k + 1; i >= 0; i--) {
				while (k >= t && cross(H[k - 2], H[k - 1], points.get(i)) <= 0)
					k--;
				H[k++] = points.get(i);
			}
			if (k > 1) {
				H = Arrays.copyOfRange(H, 0, k - 1); // remove non-hull vertices after k; remove k - 1 which is a duplicate
			}
			ArrayList<Vector2D> out = new ArrayList<Vector2D>(Arrays.asList(H));
			return out;
		} else if (points.size() <= 1) {
			return points;
		} else {
			return null;
		}
	}
}
