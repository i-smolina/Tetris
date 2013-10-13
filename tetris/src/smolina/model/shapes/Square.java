package smolina.model.shapes;

import java.awt.Color;
import smolina.model.Cell;
import smolina.model.Figure;

public class Square extends Figure {
	
	public Square(int x, int y) {
		super(x, y);
		state = new SquareState();
		setColor(Color.ORANGE);
	}
	
	class SquareState extends StateFigure {

		@Override
		protected void turnUp() {} // don't have to change state of the square

		@Override
		protected void turnLeft() {} // don't have to change state

		@Override
		protected void turnRight() {}

		@Override
		protected void turnDown() {}
	}

	@Override
	public void createCells() {
		setOffset(0, 0, 1);
		cells[0] = new Cell(x, y);
		cells[1] = new Cell(x + 1, y);
		cells[2] = new Cell(x, y + 1);
		cells[3] = new Cell(x + 1, y + 1);
	}
}
