
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
	ArrayList<Brick> bricks = new ArrayList<Brick>();
	ArrayList<Brick> bricksForDelate = new ArrayList<Brick>();

	private int score = 0;
	private double speed = 2;
	private int widthScreen = 700;
	private int heightScreen = 400;
	private int lives = 3;

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public int getScore() {
		return this.score ;
	}

	public int getWidthScreen() {
		return this.widthScreen;
	}

	public int getHeightScreen() {
		return this.heightScreen;
	}

	public int getLives() {
		return this.lives ;
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
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).paint(g2d);
		}

		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString("Points:" + String.valueOf(getScore()), 10, 30);
		g2d.setColor(Color.GRAY);
		g2d.setFont(new Font("Verdana", Font.BOLD, 30));
		g2d.drawString("Vides:" + String.valueOf(getLives()), 10, 60);
	}

	public void gameOver() {
		RestartGame();
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

		int countBricksInRow = 10;
		int countBricksInColumn = 5;

		int x_space_between_bricks = 0;
		int y_space_between_bricks = 0;

		int max_space_width_screen = widthScreen;
		int max_space_height_screen = (int)((double) heightScreen/3.2);

		int width_brick = max_space_width_screen / countBricksInRow;
		int height_brick = max_space_height_screen / countBricksInColumn;


		for (int columns = 0; columns < countBricksInColumn; columns++) {
			for (int rows = 0; rows <= countBricksInRow; rows++) {
				switch (count % 3){
					case 0:
						bricks.add(new BrickRed(this, next_x, next_y, width_brick, height_brick));
						break;
					case 1:
						bricks.add(new BrickGreen(this, next_x, next_y, width_brick, height_brick));
						break;
					case 2:
						bricks.add(new BrickBlue(this, next_x, next_y, width_brick, height_brick));
						break;
				}
				next_x += width_brick + x_space_between_bricks;
				count += 1;
			}
			next_y += height_brick + y_space_between_bricks;
			next_x = 0;
		}


	}

	public void init() throws InterruptedException  {
		
		this.frame.add(this);
		this.frame.setSize(widthScreen, heightScreen);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
        this.frame.setLayout(null); // set layout to null
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.repaint();
		//setBackground(#ffeed9);
		this.setBackground(new Color(255, 238, 217));
		// this.setBackground(Color.);
		this.paintBricks();
		StartGame();

		while (true) {
			this.move();
			this.repaint();
			Thread.sleep(10);
		}
	}

	public void delteBricks() {
		for(Brick brick : bricksForDelate) {
			bricks.remove(brick);
			// score += brick.getScore();
		}
		bricksForDelate.clear();
	}

	public void delteBrick(Brick brick) {
		bricksForDelate.add(brick);
	}

	public void SubstractLive() {
		this.lives -= 1;
		if(this.lives == 0) {
			this.gameOver();
		}
		for (Brick brick : bricks) {
			if (!brick.collide) {
				brick.delteBrick();
			}
		}
		RestartGame();
	}

	public void StartGame(){
		//set speed
		ball.PrepareToStart();
		racquet.PrepareToStart();
	}

	public void RestartGame(){
		StartGame();
	}



}