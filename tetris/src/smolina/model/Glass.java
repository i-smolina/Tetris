package smolina.model;

import java.util.LinkedList;
import java.util.List;
import smolina.Settings;

/**
 * 
 * @author smolina irina
 *
 * field for game
 */
public class Glass {
	List<Cell[]> rows; // rows with non-empty cells
	int countY;		   // number of the cells in height 
	int countX;		   // number of the cells in width
	
	public Glass() {
		countY = Settings.countY;
		countX = Settings.countX;
		rows = new LinkedList<Cell[]>();
	}
	
	/**
	 * Can catch the figure?
	 * @param figure
	 * @return 
	 */
	boolean canCatch(Figure figure) {
		// above the filled cells
		if (figure.getBottom() > rows.size())
			return false;
		
		// above the bottom of the box
		if (figure.getBottom() > 0) {
			for (Cell c : figure.getCells()) {
				if (c.y - 1 < rows.size()) {
					if (rows.get(c.y - 1)[c.x] != null){
						return true;
					}
				}
			}
			return false;
		}
		
		// on the bottom of the box
		return true;
	}
	
	/**
	 * the box catch the figure
	 * @param figure
	 */
	void catchFigure(Figure figure) {
		for (Cell c : figure.cells) {
			if (c.y == rows.size())
				rows.add(new Cell[countX]);
			rows.get(c.y)[c.x] = c;
		}
	}
	
	/**
	 * remove the full rows in the box
	 * @return number of deleted rows
	 */
	int removeFullRows() {
		boolean isFullRow;
		int count = 0; // number of the rows for delete
		for (int i = 0; i < rows.size(); i++) {
			isFullRow = true;
			for (Cell c : rows.get(i)) {
				if (c == null) {
					isFullRow = false; // row is not full
					break;
				}
			}
			if (isFullRow) {	  // row is full
				count++;
				rows.remove(i--); // remove row and decrement index (that check again)
			}
		}
		return count;
	}
	
	public boolean isFull() {
		return (rows.size() >= countY);
	}
	
	public List<Cell[]> getRows() {
		return rows;
	}
}
