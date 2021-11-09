package com.example.demo;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * This class defines a request of a Point in the addPoint method in Controller
 *
 */
public class PointRequest {
	
	@NotNull @Digits(fraction = 0, integer = 3)
	private int x;
	
	@NotNull @Digits(fraction = 0, integer = 3)
	private int y;

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
