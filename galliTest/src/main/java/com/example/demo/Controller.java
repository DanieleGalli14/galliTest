package com.example.demo;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	private LinkedList<Point> space = new LinkedList<Point>();
	private LinkedList<Segment> segments = new LinkedList<>();
	
	/**
	 * This method returns the list of point saves in the space
	 * 
	 * @return the list of points
	 */
	@GetMapping ("/space")
	public ResponseModel getSpace(HttpServletRequest request) {
		if(space.isEmpty()) {
			return new ResponseModel(Instant.now(), 200, "OK", "The space is empty", request.getRequestURL().toString());
		}
	
		return new ResponseModel(Instant.now(), 200, "OK", space, request.getRequestURL().toString());
	}
	
	/**
	 * Given a value n, this method returns a set of line segments which passes through at least n points
	 * 
	 * @param n
	 * @return the set of line segments which passed at least n points in the space
	 */
	@GetMapping("/lines/{n}")
	public ResponseModel getPoint(@PathVariable int n, HttpServletRequest request) {
		
		if(space.isEmpty())
			return new ResponseModel(Instant.now(), 200, "OK", "There are no point in the space ", request.getRequestURL().toString());
		
		if(n < 2)
			return new ResponseModel(Instant.now(), 200, "OK", "Invalid output (n must be at least 2) ", request.getRequestURL().toString());
		
		Set<Segment> passing = new HashSet<>();
		int i = 0;
		
		for(Segment s : segments) {
			for(Point p : space) {
				if(s.contains(p)) i++;
			}
			
			if(i >= n) passing.add(s);
		}

		return new ResponseModel(Instant.now(), 200, "OK", (!passing.isEmpty()) ? passing : "There are no segment passing at least " + n + " points", request.getRequestURL().toString());
	}
	
	/**
	 * addPoint adds a new point to the space
	 * 
	 * @param A new point p
	 */
	@PostMapping("/point")
	public ResponseModel addPoint(@Valid @RequestBody PointRequest p, HttpServletRequest request) {
		
		Point point = new Point(p.getX(), p.getY());
		
		space.add(point);
		
		for(Point p1 : space) {
			if(!p1.equals(point))
				segments.add(new Segment(p1, point));
		}
		
		return new ResponseModel(Instant.now(), 200, "OK", "Added the point with coordinates (" + point.getX() + ", " + point.getY() + ")", request.getRequestURL().toString());
	}
	
	/**
	 * emptySpace deletes all the points saved in the space
	 * 
	 */
	@DeleteMapping("/space")
	public ResponseModel emptySpace(HttpServletRequest request) {
		
		if(space.isEmpty()) 
			return new ResponseModel(Instant.now(), 200, "OK", "The space was already empty", request.getRequestURL().toString());
		
		space.clear();
		segments.clear();
		
		return new ResponseModel(Instant.now(), 200, "OK", "The space has been emptied", request.getRequestURL().toString());
	}
}
