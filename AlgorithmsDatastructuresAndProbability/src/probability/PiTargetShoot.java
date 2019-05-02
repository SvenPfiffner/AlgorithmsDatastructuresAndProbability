package probability;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Implements a target shooting algorithm to compute pi
 * @author Sven Pfiffner
 */
public class PiTargetShoot {
	
	/**
	 * Compute pi using target shooting (Monte Carlo approach)
	 * @param accuracy How many "Target shoots" to conduct
	 * @return An approximation of pi (the higher the accuracy the better)
	 */
	public static double compute(long accuracy) {
		long hits = 0;
		double x,y;
		for(long i = 1; i<=accuracy; i++) {
			x = Math.random();
			y = Math.random();
			if (Math.hypot(x, y) <= 1) {
				hits++;
			}
		}
		return 4* (double)hits / accuracy;
	}
	
	/**
	 * Compute pi using target shooting on multiple threads (Monte Carlo approach)
	 * @param accuracy How many "Target shoots" to cunduct
	 * @return An approximation of pi (the higher the accuracy the better)
	 */
	public static double computeParallel(long accuracy) {
		
		int cores = Runtime.getRuntime().availableProcessors();
		long hits = 0;
		ExecutorService executor = Executors.newFixedThreadPool(cores);
		
		shootingTask[] tasks = new shootingTask[cores];
		for(int i = 0; i<cores; i++) {
			tasks[i] = new shootingTask(accuracy/cores);
			executor.submit(tasks[i]);
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(shootingTask t: tasks) {
			hits += t.getHits();
		}
		
		return 4* (double)hits / accuracy;
	}

}


class shootingTask implements Runnable {

	private long accuracy;
	private long hits;
	
	public shootingTask(long accuracy) {
		this.accuracy = accuracy;
	}
	
	@Override
	public void run() {
		double x,y;
		for(long i = 1; i<=accuracy; i++) {
			x = Math.random();
			y = Math.random();
			if (Math.hypot(x, y) <= 1) {
				hits++;
			}
		}
	}
	
	public long getHits() {
		return hits;
	}
	
}