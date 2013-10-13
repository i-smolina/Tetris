package smolina.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import smolina.Settings;
import smolina.model.Glass;
import smolina.model.Cell;
import smolina.model.Figure;

/**
 * 
 * @author smolina irina
 *
 * panel for the game
 */
public class GamePanel extends JPanel{
	private Glass box;
	private Figure figure;
	private int sideCell; // size of the side of the cell
	int countY;  // number of the cells in height 
	int countX;  // number of the cells in width 
	int h;
	int offsetX, offsetY; // offset (that field of game will be in the center of the panel)
	
	public GamePanel() {
		countY = Settings.countY;
		countX = Settings.countX;
		setFocusable(true);
		setBorder(new EtchedBorder());
	}
	
	public void setFigure(Figure figure) {
		this.figure = figure;
		repaint();
	}
	
	public void start(Glass box, Figure figure) {
		this.box = box;
		this.figure = figure;
		sideCell = (int)Math.min(getWidth() / Settings.countX, getHeight() / Settings.countY);
		offsetX = (getWidth() - sideCell * Settings.countX) / 2;
		offsetY = (getHeight() - sideCell * Settings.countY) / 2;
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (box == null) return;
		
		paintFigure(g);
		paintBox(g);
		paintGrid(g);
	}
	
	private void paintFigure(Graphics g) {
		g.setColor(figure.getColor());
		for (Cell cell : figure.getCells()) {
			g.fillRect(offsetX + cell.getX() * sideCell, 
					offsetY + (countY - cell.getY() - 1) * sideCell, 
					sideCell, sideCell);
		}
	}
	
	private void paintGrid(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i <= Settings.countY; i++){
			g.drawLine(offsetX, offsetY + i * sideCell, 
					offsetX + sideCell * Settings.countX, offsetY + i * sideCell);
		}
		for (int j = 0; j <= Settings.countX; j++) {
			g.drawLine(offsetX + j * sideCell, offsetY, 
					offsetX + j * sideCell, offsetY + sideCell * Settings.countY);
		}
	}
	
	private void paintBox(Graphics g) {
		List<Cell[]> rows = box.getRows();
		for (int i = 0; i < rows.size(); i++) {
			for (int j = 0; j < countX; j++) {
				Cell cell = rows.get(i)[j];
				if (cell == null) continue;
				else {
					g.setColor(cell.getColor());
					g.fillRect(offsetX + j * sideCell, 
							offsetY + (countY - i - 1) * sideCell, 
							sideCell, sideCell);
				}
			}
		}
	}
}
