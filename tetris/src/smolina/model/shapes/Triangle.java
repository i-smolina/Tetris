package smolina.model.shapes;

import java.awt.Color;
import smolina.model.Cell;
import smolina.model.Figure;

public class Triangle extends Figure {
	
	public Triangle(int x, int y) {
		super(x, y);
		state = new TriangleState();
		setColor(Color.BLUE);
	}

	@Override
	public void createCells() {
		setOffset(0, 1, 1);
		cells[0] = new Cell(x - 1, y);
		cells[1] = new Cell(x, y);
		cells[2] = new Cell(x + 1, y);
		cells[3] = new Cell(x, y + 1);
	}
		
	class TriangleState extends StateFigure {

		@Override
		protected void turnUp() {
			setOffset(0, 1, 1);
			cells[0].setCoord(x - 1, y);
			cells[1].setCoord(x, y);
			cells[2].setCoord(x + 1, y);
			cells[3].setCoord(x, y + 1);
		}
		
		@Override
		protected void turnLeft() {
			setOffset(1, 1, 0);
			cells[0].setCoord(x, y - 1);
			cells[1].setCoord(x - 1, y);
			cells[2].setCoord(x, y);
			cells[3].setCoord(x, y + 1);
		}
		
		@Override
		protected void turnDown() {
			setOffset(1, 1, 1);
			cells[0].setCoord(x, y - 1);
			cells[1].setCoord(x - 1, y);
			cells[2].setCoord(x, y);
			cells[3].setCoord(x + 1, y);
		}
		
		@Override
		protected void turnRight() {
			setOffset(1, 0, 1);
			cells[0].setCoord(x, y - 1);
			cells[1].setCoord(x, y);
			cells[2].setCoord(x + 1, y);
			cells[3].setCoord(x, y + 1);
		}
	}
}
