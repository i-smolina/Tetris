package smolina.controller;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import smolina.Settings;
import smolina.model.Cell;
import smolina.model.Figure;
import smolina.model.shapes.LeftHook;
import smolina.model.shapes.LeftZigzag;
import smolina.model.shapes.Line;
import smolina.model.shapes.RightHook;
import smolina.model.shapes.RightZigzag;
import smolina.model.shapes.Square;
import smolina.model.shapes.Triangle;

/**
 * 
 * @author smolina irina
 *
 * panel for the next figure
 */
public class FuturePanel extends JPanel{
	private Figure figure; // next figure
	private Figure[] arrFigures; // array of the all figures
	private int sideCell; // size of side of the cell
	private int[] correctY, correctX; // correct coordinates (the figure will be at the center of the panel)
	private int countCell = 6; // the number of cells in row
	private int index; // index of the figure
	
	public FuturePanel() {
		super();
		setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	}
	
	/**
	 * calculate side of the cell
	 */
	private void calcSideCell() {
		sideCell = (int)Math.min(getWidth() / countCell, getHeight() / countCell);
	}
	
	public void start(int N) {
		calcSideCell();
		this.index = N;
		arrFigures = createArrayFigures();
		setFigure(N);
		repaint();
	}
	
	/**
	 * create array of the figures and correct coordinates of the center
	 * @return
	 */
	private Figure[] createArrayFigures() {
		Figure[] array = new Figure[Settings.numberFigures];
		int x = 2;
		int y = 2;
		array[0] = new Square(x, y);
		array[1] = new Line(x, y);
		array[2] = new Triangle(x, y);
		array[3] = new LeftHook(x, y);
		array[4] = new RightHook(x, y);
		array[5] = new LeftZigzag(x, y);
		array[6] = new RightZigzag(x, y);
		int half = sideCell / 2;
		correctY = new int[] {0, half, 0, half, half, half, half};
		correctX = new int[] {0, half, half, sideCell, 0, 0, sideCell};
		return array;
	}
	
	public void setFigure(int N) {
		this.figure = arrFigures[N];
		this.index = N;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (figure == null) return;
		g.setColor(figure.getColor());
		for (Cell cell : figure.getCells()) {
			g.fillRect(cell.getX() * sideCell + correctX[index], 
					(countCell - 1  - cell.getY()) * sideCell - correctY[index], 
					sideCell, sideCell);
		}
		g.setColor(Color.BLACK);
		for (Cell cell : figure.getCells()) {
			g.drawRect(cell.getX() * sideCell + correctX[index], 
					(countCell - 1 - cell.getY()) * sideCell - correctY[index], 
					sideCell, sideCell);
		}
	}
}
