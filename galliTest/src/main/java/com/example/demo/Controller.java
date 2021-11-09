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
	
	@GetMapping ("/space")
	public ResponseModel getSpace(HttpServletRequest request) {
		if(space.isEmpty()) {
			return new ResponseModel(Instant.now(), 200, "OK", "The space is empty", request.getRequestURL().toString());
		}
	
		return new ResponseModel(Instant.now(), 200, "OK", space, request.getRequestURL().toString());
	}
	
	@GetMapping("/space/{n}")
	public ResponseModel getPoint(@PathVariable int n, HttpServletRequest request) {
		
		Set<Point> passing = new HashSet<>();
		
		for(Point p : space) {
			if (p.getX() == n)
				passing.add(p);
		}
		
		return new ResponseModel(Instant.now(), 200, "OK", (!passing.isEmpty()) ? passing : "There are no point passing on " + n, request.getRequestURL().toString());
	}
	
	@PostMapping("/point")
	public ResponseModel addPoint(@Valid @RequestBody PointRequest p, HttpServletRequest request) {
		
		Point point = new Point(p.getX(), p.getY());
		
		space.add(point);
		
		return new ResponseModel(Instant.now(), 200, "OK", "Added the point with coordinates (" + point.getX() + ", " + point.getY() + ")", request.getRequestURL().toString());
	}
	
	
	@DeleteMapping("/space")
	public ResponseModel emptySpace(HttpServletRequest request) {
		
		if(space.isEmpty()) 
			return new ResponseModel(Instant.now(), 200, "OK", "The space was already empty", request.getRequestURL().toString());
		
		space.removeAll(space);
		return new ResponseModel(Instant.now(), 200, "OK", "The space has been emptied", request.getRequestURL().toString());
	}
}
