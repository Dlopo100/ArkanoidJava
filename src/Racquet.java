
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private int width;
	private int height;
	private double x;
	private double xa;
	private int y;
	private Game game;
	private Ball ball;

	public int getWidth() {
		return width;
	}

	public Racquet(Game game, Ball ball) {
		this.game = game;
		this.ball = ball;
        x = (700/2) - (width/2);
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - width)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.fillRect((int)x, (int)y, width, height);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.getSpeed() * 1.2;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.getSpeed() * 1.2;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			ball.thrust();
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}

	public int getTopY() {
		return y - height;
	}
	public void PrepareToStart(){
		width = (int) (game.getWidth()*0.0857);
		height = (int) (game.getHeight()*0.025);
		x = (game.getWidth()/2) - (width/2);
		xa = 0;
		y = (int) (game.getHeight() - (game.getHeight()*0.079) - (height/2)) ;
		System.out.println("Racquet: " + x + " " + y + " " + width + " " + height);
	}
}