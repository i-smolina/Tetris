package smolina.controller;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author smolina irina
 *
 * panel for the score of the game
 */
public class ScorePanel extends JPanel {
	
	private JLabel jscore;
	
	public ScorePanel() {
		super();
		setBorder(new EtchedBorder());
		jscore = new JLabel();
		add(jscore);
	}
	
	public void start() {
		setScore(0);
	}
	
	public void setScore(int score) {
		jscore.setText(String.valueOf(score));
	}
}
