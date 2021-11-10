package com.example.demo;

/**
 * The class Segment defines a line segment, define by passing the two points
 *
 */
public class Segment {
	private Point p1;
	private Point p2;
	
	public Segment(Point p1, Point p2) {
		
		if(p1 == null || p2 == null)
			throw new NullPointerException("No null Point are allowed");

		this.p1 = p1;
		this.p2 = p2;
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}
	
	/**
	 * Calculate the slope of the segment
	 * @return the slope of the segment
	 */
	public double slope() {
		double diffX = p2.getX() - p1.getX();
		double diffY = p2.getY() - p1.getY();
		
		return diffY/diffX;
	}
	
	/**
	 * Calculate the intercept of the segment
	 * @return calculate the intercept of the segment
	 */
	public double intercept() {
		return p1.getY() - slope() * p1.getX();
	}
	
	/**
	 * Determines if a given point passes through the segment
	 * @param p, a point
	 * @return true if p passes through the segment, false if not
	 */
	public boolean contains(Point p) {
		
		//First evaluates slope and intercept, to obtain the line function
		double m = slope();
		double i = intercept();
		double maxX, minX, maxY, minY;
		
		//ensure that f(p.getX) is equal to p.getY
		if(m * p.getX() + i == p.getY()) {
			//We are sure that p is a point of the line, we need to check if it's also a point of the segment
			maxX = Math.max(p1.getX(), p2.getX());
			minX = Math.min(p1.getX(), p2.getX());
			maxY = Math.max(p1.getY(), p2.getY());
			minY = Math.min(p1.getY(), p2.getY());
			
			//with the max and min values defines over, we need to check if both the values of p is between the corresponding values of p1 and p2
			return ((p.getX() >= minX && p.getX() <= maxX) && (p.getY() >= minY && p.getY() <= maxY));
			
		}
		
		return false;
	}
	
}
