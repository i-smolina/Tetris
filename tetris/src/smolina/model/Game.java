package smolina.model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import smolina.Settings;
import smolina.controller.FuturePanel;
import smolina.controller.GamePanel;
import smolina.controller.ScorePanel;
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
 * logic of the game
 */
public class Game {
	GamePanel gamePanel;
	FuturePanel futurePanel;
	ScorePanel scorePanel;
	Glass box;
	Figure figure; // the current figure
	Figure nextFigure;
	Timer timer;
	Random random;
	int countFigure;
	int numFigure; // number of the figure
	int score; // score of the game
	
	public Game(GamePanel panel, FuturePanel fpanel, ScorePanel spanel) {
		this.gamePanel = panel;
		this.futurePanel = fpanel;
		this.scorePanel = spanel;
		addListeners();
		timer=new Timer();
		random = new Random();
		countFigure = Settings.numberFigures;
		score = 0;
	}
	
	/**
	 * create the figure
	 * @return figure
	 */
	Figure createFigure(){
		Figure figure = null;
		// calculate of center figure
		int x = Settings.countX / 2 - 1;
		int y = Settings.countY - 2;

		numFigure = random.nextInt(countFigure);
		switch (numFigure) {
		case 0:
			figure = new Square(x, y);
			break;
		case 1:
			figure = new Line(x, y);
			break;
		case 2:
			figure = new Triangle(x, y);
			break;
		case 3:
			figure = new LeftHook(x, y);
			break;
		case 4:
			figure = new RightHook(x, y);
			break;
		case 5:
			figure = new LeftZigzag(x, y);
			break;
		case 6:
			figure = new RightZigzag(x, y);
			break;
		}
		return figure;
	}
	
	public void play(){
		box = new Glass();
		figure = createFigure();
		gamePanel.start(box, figure);
		nextFigure = createFigure();
		futurePanel.start(numFigure);
		scorePanel.start();
		
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				
				if (box.canCatch(figure)){ // if box can catch figure
					box.catchFigure(figure);
					int n = box.removeFullRows();
					// change the score
					if (n > 0) {
						score += n;
						scorePanel.setScore(score);
					}
					// finish game
					if (box.isFull()){
						System.out.println("Finish");
						timer.cancel();
						return;
					}
					else { // create new figure
						figure = nextFigure;
						gamePanel.setFigure(figure);
						nextFigure = createFigure();
						futurePanel.setFigure(numFigure);
					}
				}
				else
					figure.down(); 
				gamePanel.repaint();
			}
		};
				
		timer.scheduleAtFixedRate(task, 300, 500);
	}
	
	void addListeners() {
		gamePanel.addKeyListener(new PanelKeyListener());
	}
	
	public void timerCancel() {
		timer.cancel();
	}
	
	class PanelKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				figure.left();
				break;
			case KeyEvent.VK_RIGHT:
				figure.right();
				break;
			case KeyEvent.VK_DOWN:
				if (!box.canCatch(figure))
					figure.down();
				break;
			case KeyEvent.VK_ENTER:
				figure.rotate();
				break;
			default:
				return;
			}
			gamePanel.repaint();
		}
	}
}
