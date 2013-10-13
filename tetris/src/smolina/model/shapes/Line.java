package smolina.model.shapes;

import java.awt.Color;
import smolina.model.Cell;
import smolina.model.Figure;

public class Line extends Figure{
		
	public Line(int x, int y) {
		super(x, y);
		state = new LineState();
		setColor(Color.RED);
	}

	@Override
	public void createCells() {
		setOffset(2, 0, 0);
		cells[0] = new Cell(x, y - 2);
		cells[1] = new Cell(x, y - 1);
		cells[2] = new Cell(x, y);
		cells[3] = new Cell(x, y + 1);
	}
		
	class LineState extends StateFigure {

		@Override
		protected void turnUp() {
			setOffset(2, 0, 0);
			cells[0].setCoord(x, y - 2);
			cells[1].setCoord(x, y - 1);
			cells[2].setCoord(x, y);
			cells[3].setCoord(x, y + 1);
		}

		@Override
		protected void turnLeft() {
			setOffset(0, 1, 2);
			cells[0].setCoord(x - 1, y);
			cells[1].setCoord(x, y);
			cells[2].setCoord(x + 1, y);
			cells[3].setCoord(x + 2, y);
		}

		@Override
		protected void turnRight() {
			turnLeft();
		}

		@Override
		protected void turnDown() {
			turnUp();
		}
	}
}
