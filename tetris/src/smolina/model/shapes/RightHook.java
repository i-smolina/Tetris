package smolina.model.shapes;

import java.awt.Color;

import smolina.model.Cell;
import smolina.model.Figure;

public class RightHook extends Figure{
	
	public RightHook(int x, int y) {
		super(x, y);
		state = new RightHookState();
		setColor(Color.GREEN);
	}

	@Override
	public void createCells() {
		setOffset(1, 0, 1);
		cells[0] = new Cell(x, y -1);
		cells[1] = new Cell(x, y);
		cells[2] = new Cell(x, y + 1);
		cells[3] = new Cell(x + 1, y + 1);
	}
	
	class RightHookState extends StateFigure {

		@Override
		protected void turnUp() {
			setOffset(1, 0, 1);
			cells[0].setCoord(x, y - 1);
			cells[1].setCoord(x, y);
			cells[2].setCoord(x, y + 1);
			cells[3].setCoord(x + 1, y + 1);
		}

		@Override
		protected void turnLeft() {
			setOffset(0, 1, 1);
			cells[0].setCoord(x - 1, y);
			cells[1].setCoord(x, y);
			cells[2].setCoord(x + 1, y);
			cells[3].setCoord(x - 1, y + 1);
		}

		@Override
		protected void turnRight() {
			setOffset(1, 1, 1);
			cells[0].setCoord(x + 1, y - 1);
			cells[1].setCoord(x - 1, y);
			cells[2].setCoord(x, y);
			cells[3].setCoord(x + 1, y);
		}

		@Override
		protected void turnDown() {
			setOffset(1, 1, 0);
			cells[0].setCoord(x - 1, y - 1);
			cells[1].setCoord(x, y - 1);
			cells[2].setCoord(x, y);
			cells[3].setCoord(x, y + 1);
		}
	}
}
