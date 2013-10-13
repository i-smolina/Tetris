package smolina;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import smolina.controller.FuturePanel;
import smolina.controller.GamePanel;
import smolina.controller.ScorePanel;
import smolina.model.Game;


public class Tetris extends JFrame {

	Game game;
	private final GamePanel gamePanel;
	private final FuturePanel futurePanel;
	private final ScorePanel scorePanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Tetris frame = new Tetris();
			frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Tetris() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setSize(screenSize.width / 2, screenSize.height);
		setLocation(screenSize.width / 4, 0);
		setTitle("Tetris");
		
		JPanel comment = new JPanel();
		getContentPane().add(BorderLayout.EAST, comment);
		getContentPane().add(BorderLayout.WEST, new JPanel());
		getContentPane().add(BorderLayout.NORTH, new JPanel());
		getContentPane().add(BorderLayout.SOUTH, new JPanel());
		
		Box vbox = Box.createVerticalBox();
		comment.add(vbox);
		
		vbox.add(Box.createVerticalStrut(30));
		vbox.add(getLabel("Next"));
		
		futurePanel = new FuturePanel();
		futurePanel.setPreferredSize(new Dimension(100, 100));
		vbox.add(futurePanel);
		
		vbox.add(Box.createVerticalStrut(20));
		vbox.add(getLabel("Score"));
		scorePanel = new ScorePanel();
		vbox.add(scorePanel);
		
		JMenuBar menubar = new JMenuBar();
		menubar.add(createGameMenu());
		setJMenuBar(menubar);
			
		gamePanel = new GamePanel();
		getContentPane().add(BorderLayout.CENTER, gamePanel);
	}
	
	private JMenu createGameMenu() {
		JMenu menu = new JMenu("Game");
		menu.add(new JMenuItem(new NewGameAction()));
		menu.addSeparator();
		menu.add(new JMenuItem(new ExitAction()));
		return menu;
	}
	
	private JPanel getLabel(String text) {
		JLabel jNext = new JLabel(text);
		JPanel panNext = new JPanel(new FlowLayout());
		panNext.setAlignmentX(CENTER_ALIGNMENT);
		panNext.add(jNext);
		return panNext;
	}
		
	class ExitAction extends AbstractAction {
		
		public ExitAction(){
			putValue(NAME, "Exit");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (game != null)
				game.timerCancel();
			System.exit(0);
		}
	}
	
	class NewGameAction extends AbstractAction {
		
		public NewGameAction() {
			putValue(NAME, "New Game");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (game != null)
				game.timerCancel(); // switch off timer of the previous game
			game = new Game(gamePanel, futurePanel, scorePanel);
			game.play();
		}
	}
}
