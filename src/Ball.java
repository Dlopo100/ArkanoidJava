
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	private static final int DIAMETER = 30;
	
	int x = 350;
	int y = 300;
	int xa = -1;
	int ya = 1;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() {
		// boolean changeDirection = true;
		if (x + xa < 0)
			xa = game.speed;
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.speed;
		else if (y + ya < 0)
			ya = game.speed;
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (collision()){
			ya = -game.speed;
			y = game.racquet.getTopY() - DIAMETER;
			game.speed++;
		} 
        // else 
			// changeDirection = false;
		
		// if (changeDirection) 
		// 	Sound.BALL.play();
		x = x + xa;
		y = y + ya;
	}

	private boolean collision() {
        // boolean collision_with_any_object = false;
        // while (!collision_with_any_object){
        //     for (int i = 0; i < game.bricks.size(); i++) {
        //         if (game.bricks.get(i).getBounds().intersects(getBounds())) {
        //             game.bricks.get(i).delteBrick();
        //             collision_with_any_object = true;
        //         }
        //     }
        //     if (game.racquet.getBounds().intersects(getBounds())) {
        //         collision_with_any_object = true;
        //     }
        // }
        // return collision_with_any_object;
		return game.racquet.getBounds().intersects(getBounds());
	}

	public void paint(Graphics2D g) {
        g.setColor(new java.awt.Color(255, 255, 255));
		g.fillOval(x, y, DIAMETER, DIAMETER);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}