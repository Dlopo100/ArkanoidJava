
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.awt.geom.Ellipse2D;

public class Ball {
	private static final int DIAMETER = 30;
	
	double x = 350;
	double y = 300;
	double xa = -1;
	double ya = -1;
	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	void move() {
		// boolean changeDirection = true;
		int colision =  collision();
		boolean hadejadodecolisionar = true;
		if (x + xa < 0)
			xa = game.getSpeed();
		else if (x + xa > game.getWidth() - DIAMETER)
			xa = -game.getSpeed();
		else if (y + ya < 0)
			ya = game.getSpeed();
		else if (y + ya > game.getHeight() - DIAMETER)
			game.gameOver();
		else if (colision != -1 && hadejadodecolisionar){
			hadejadodecolisionar = false;
			switch (colision) {
				case 1:
					xa = -game.getSpeed();
					break;
				case 2:
					if (xa < 0)
						xa = -game.getSpeed();
					else{
						xa = game.getSpeed();
					}
					break;
				case 3:
					xa = game.getSpeed();
					break;
			}

			ya = -game.getSpeed();
			game.setSpeed(game.getSpeed()+0.2);
		} 
		else if (colision == -1 && !hadejadodecolisionar){
			hadejadodecolisionar = true;
		}
        // else 
			// changeDirection = false;
		
		// if (changeDirection) 
		// 	Sound.BALL.play();
		x = x + xa;
		y = y + ya;
	}

	private int collision() {
		if (game.racquet.getBounds().intersects(getBounds())){ //toca o no amb la raqueta
			
			double racquetXpos = game.racquet.getX();
			double ballXpos = this.x;
			double diference = (double) racquetXpos / ballXpos;
			if (diference > 1.1){
				return 1;
			}
			else if (diference < 0.9){
				return 3;
			}
			else {
				return 2;
			}
		}
		else {
			return -1;
		}

	}

	public void paint(Graphics2D g) {
        g.setColor(new java.awt.Color(255, 255, 255));
		g.fillOval((int)x, (int)y, DIAMETER, DIAMETER);
	}

	public Rectangle2D getBounds() {
		return new Ellipse2D.Double(x, y, DIAMETER, DIAMETER).getBounds2D();
	}

	public void oppositeWay() {
		if (xa < 0)
			xa = -game.getSpeed();
		else{
			xa = game.getSpeed();
		}
		ya = game.getSpeed();
	}

}