
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	private final int Y = 330;
	private final int WITH = 60;
	private final int HEIGHT = 10;
	private double x = 0;
	private double xa = 0;
	private Game game;

	public Racquet(Game game) {
		this.game = game;
        x = (700/2) - (WITH/2);
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void move() {
		if (x + xa > 0 && x + xa < game.getWidth() - WITH)
			x = x + xa;
	}

	public void paint(Graphics2D g) {
		g.fillRect((int)x, (int)Y, WITH, HEIGHT);
	}

	public void keyReleased(KeyEvent e) {
		xa = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.getSpeed() * 1.2;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.getSpeed() * 1.2;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)Y, WITH, HEIGHT);
	}

	public int getTopY() {
		return Y - HEIGHT;
	}
}