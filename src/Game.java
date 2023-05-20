
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	JFrame frame = new JFrame("Mini Tennis");
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	private double speed = 2;
	private int Score = 0;
	ArrayList<Brick> bricks = new ArrayList<Brick>();
	ArrayList<Brick> bricksForDelate = new ArrayList<Brick>();
	
	public void setScore(int score) {
		Score = score;
	}
	public int getScore() {
		return Score;
	}

	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			}
		});
		setFocusable(true);
		// Sound.BACK.loop();
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	
	private void move() {
		ball.move();
		racquet.move();
		for(Brick brick : bricks) {
			brick.checkCollision();
		}
		delteBricks();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).paint(g2d);
		}

		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString(String.valueOf(getScore()), 10, 30);
	}

	public void gameOver() {
		// Sound.BACK.stop();
		// Sound.GAMEOVER.play();
		JOptionPane.showMessageDialog(this, "your score is: " + getScore(),
				"Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}

	public void paintBricks() {
		int count = 0;
		int next_x = 0;
		int next_y = 0;
		int x_space_between_bricks = 10;
		int y_space_between_bricks = 1;
		int width_bricks = Brick.width;
		int height_bricks = Brick.height;
		for (int columns = 0; columns < 4; columns++) {
			for (int rows = 0; rows <= 10; rows++) {
				switch (count % 3){
					case 0:
						bricks.add(new BrickRed(this, next_x, next_y));
						break;
					case 1:
						bricks.add(new BrickGreen(this, next_x, next_y));
						break;
					case 2:
						bricks.add(new BrickBlue(this, next_x, next_y));
						break;
				}
				next_x += width_bricks + x_space_between_bricks;
				count += 1;
			}
			next_y += height_bricks + y_space_between_bricks;
			next_x = 0;
		}


	}

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		game.frame.add(game);
		game.frame.setSize(700, 400);
		game.frame.setVisible(true);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setBackground(Color.BLACK);
		game.paintBricks();

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(10);
		}
	}

	public int getFrameHeight() {
		return frame.getHeight();
	}

	public int getFrameWidth() {
		return frame.getWidth();
	}

	public void delteBricks() {
		for(Brick brick : bricksForDelate) {
			bricks.remove(brick);
		}
		bricksForDelate.clear();
	}

	public void delteBrick(Brick brick) {
		bricksForDelate.add(brick);
	}



}