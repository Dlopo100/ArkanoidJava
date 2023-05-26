
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
	private boolean speedUp = false;
	private Double speedNormal = 1.2;
	private Double speedFast = 1.5;

	public int getWidth() {
		return width;
	}

	public Racquet(Game game) {
		this.game = game;
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
		Double speed = speedNormal;
		if (speedUp)
			speed = speedFast;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			xa = -game.getSpeed() * speed;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			xa = game.getSpeed() * speed;
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			game.ball.thrust();
	}
	public void newSpeedUp(){

        Thread thread = new Thread(new Runnable() {
            public void run() {
				speedUp = true;
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				speedUp = false;
			}
        });
        thread.start();
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