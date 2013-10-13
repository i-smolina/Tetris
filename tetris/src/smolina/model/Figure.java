package smolina.model;

import java.awt.Color;
import smolina.Settings;

/**
 * 
 * @author smolina irina
 *
 * abstract figure
 */
public abstract class Figure {
	protected int x, y;			// center of the figure
	protected int dy, dxl, dxr; // shift to the down, left and right from the center of figure
	protected Cell[] cells;	    // cells of the figure
	protected Color color;		// color of the figure
	public StateFigure state;	// state of the figure
	
	public Figure(int x, int y){
		this.x = x;
		this.y = y;
		cells = new Cell[4];
		createCells();
	}
	
	/**
	 * move to the left
	 */
	public void left(){
		if (x - dxl <= 0) return;
		x--;
		for (Cell c : cells)
			c.x--;
	}
	
	/**
	 * move to the right
	 */
	public void right(){
		if (x + dxr >= Settings.countX - 1) return;
		x++;
		for (Cell c : cells)
			c.x++;
	}
	
	/**
	 * move down
	 */
	public void down(){
		y--;
		for (Cell c : cells)
			c.y--;
	}
	
	public Cell[] getCells() {
		return cells;
	}
	
	public Color getColor() {
		return color;
	}
	
	/**
	 * get y-coordinate of the lower bound of the figure 
	 * @return y-coordinate
	 */
	public int getBottom() {
		return y - dy;
	}
	
	/**
	 * set offset from the center of the figure
	 * @param dy - down shift
	 * @param dxl - left shift
	 * @param dxr - right shift
	 */
	public void setOffset(int dy, int dxl, int dxr) {
		this.dy = dy;
		this.dxl = dxl;
		this.dxr = dxr;
	}
	
	public void setColor(Color color) {
		this.color = color;
		for (Cell c: cells) 
			c.color = color;
	}
	
	/**
	 * rotate the figure
	 */
	public void rotate() {
		state.nextState();
	}
	
	public void setCoordCenter(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	protected abstract void createCells();
		
	protected enum State {top, right, bottom, left};
	
	/**
	 * 
	 * @author smolina irina
	 *
	 * state of the figure (need for make rotation)
	 */
	protected abstract class StateFigure {
		
		protected State curState;
		
		public StateFigure() {
			curState = State.top;
		}
		
		/**
		 * change and apply the current state of the figure
		 */
		public void nextState(){
			switch (curState) {
			case top:
				curState = State.right;
				turnRight();
				break;
			case right:
				curState = State.bottom;
				turnDown();
				break;
			case bottom:
				curState = State.left;
				turnLeft();
				break;
			case left:
				curState = State.top;
				turnUp();
			}
		}
		
		protected abstract void turnUp();
		protected abstract void turnLeft();
		protected abstract void turnRight();
		protected abstract void turnDown();
	}
}
