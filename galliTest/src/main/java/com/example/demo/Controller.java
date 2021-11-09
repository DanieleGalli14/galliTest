package com.example.demo;

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
	public LinkedList<Point> getSpace() {
		if(space.isEmpty()) {
			return null;
		}
	
		return space;
	}
	
	@GetMapping("/space/{n}")
	public Set<Point> getPoint(@PathVariable int n) {
		
		Set<Point> passing = new HashSet<>();
		
		for(Point p : space) {
			if (p.getX() == n)
				passing.add(p);
		}
		
		return passing;
	}
	
	@PostMapping("/point")
	public String addPoint(@Valid @RequestBody PointRequest p, HttpServletRequest request) {
		
		Point point = new Point(p.getX(), p.getY());
		
		space.add(point);
		
		return "Added the point with coordinates (" + point.getX() + ", " + point.getY() + ")";
	}
	
	@DeleteMapping("/space")
	public String emptySpace() {
		
		if(space.isEmpty()) 
			return "The space was already empty";
		
		space.removeAll(space);
		return "The space has been emptied";
		
	}
}
