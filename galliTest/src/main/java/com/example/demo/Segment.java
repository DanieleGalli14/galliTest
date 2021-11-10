package com.example.demo;

public class Segment {
	private Point p1;
	private Point p2;
	
	public Segment(Point p1, Point p2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}
	
	public double getSlope() {
		double diffX = p2.getX() - p1.getX();
		double diffY = p2.getY() - p1.getY();
		
		return diffY/diffX;
	}
	
	public double getIntercept() {
		return p1.getY() - getSlope() * p1.getX();
	}
	
	public boolean contains(Point p) {
		
		double m = getSlope();
		double i = getIntercept();
		double maxX, minX, maxY, minY;
		
		if(m * p.getX() +i == p.getY()) {
			
			maxX = Math.max(p1.getX(), p2.getX());
			minX = Math.min(p1.getX(), p2.getX());
			maxY = Math.max(p1.getY(), p2.getY());
			minY = Math.min(p1.getY(), p2.getY());
			
			return ((p.getX() >= minX && p.getX() <= maxX) && (p.getY() >= minY && p.getY() <= maxY));
			
		}
		
		return false;
	}
	
}
