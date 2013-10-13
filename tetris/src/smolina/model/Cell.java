package smolina.model;

import java.awt.Color;

/**
 * 
 * @author smolina irina
 * 
 * cell of the figure
 */
public class Cell {
	Color color;
	int x;
	int y;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Color getColor() {
		return color;
	}
}
