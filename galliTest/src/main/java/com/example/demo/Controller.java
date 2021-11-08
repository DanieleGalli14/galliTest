package com.example.demo;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	private LinkedList<Point> space = new LinkedList<Point>();
	
	@GetMapping("/")
	public String index() {
		return "Hello World";
	}
	
	@PostMapping("/point")
	public String addPoint(@Valid @RequestBody PointRequest p, HttpServletRequest request) {
		
		Point point = new Point(p.getX(), p.getY());
		
		space.add(point);
		
		return "Added the point with coordinates (" + point.getX() + ", " + point.getY() + ")";
	}
}
