
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.awt.geom.Ellipse2D;

public class Ball {
	private int DIAMETER;
	private double x;
	private double y;
	private double xa;
	private double ya;
	private boolean haDejadoDeCollisionarConLaRaqueta;
	private boolean haDejadoDeSobrePassarElLimite;
	private Boolean isBallFree;
	private Game game;
	

	public Ball(Game game) {
		this.game = game;
	}

	public void move() {
		// boolean changeDirection = true;
		if (isBallFree){
			int isCollisionRaquet =  collisionWithRaquet();
			if (x + xa < 0)
				xa = game.getSpeed();
			else if (x + xa > game.getWidth() - DIAMETER)
				xa = -game.getSpeed();
			else if (y + ya < 0)
				ya = game.getSpeed();
			else if (y + ya > game.getHeight() - DIAMETER && haDejadoDeSobrePassarElLimite){
				haDejadoDeSobrePassarElLimite = false;
				game.SubstractLive();
			}
			else if (isCollisionRaquet != -1 && haDejadoDeCollisionarConLaRaqueta){
				haDejadoDeCollisionarConLaRaqueta = false;
				switch (isCollisionRaquet) {
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
			else if (isCollisionRaquet == -1 && !haDejadoDeCollisionarConLaRaqueta){
				haDejadoDeCollisionarConLaRaqueta = true;
			}
			// else 
				// changeDirection = false;
			
			// if (changeDirection) 
			// 	Sound.BALL.play();
			x = x + xa;
			y = y + ya;
		}
		else{
			x = game.racquet.getX() + game.racquet.getWidth()/2 - DIAMETER/2;
			// y = game.racquet.getY() - DIAMETER;
		}
	}

	private int collisionWithRaquet() {
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
        g.setColor(new java.awt.Color(20, 20, 20));
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

	public void PrepareToStart() {
		DIAMETER = 25;
		x = game.getWidth()/2-DIAMETER/2;
		y = game.getHeightScreen()-DIAMETER-75;
		xa = (int) (Math.random() * 3) - 1;
		ya = -1;
		haDejadoDeCollisionarConLaRaqueta = true;
		haDejadoDeSobrePassarElLimite = true;
		isBallFree = false;
	}

	public void thrust() {
		isBallFree = true;
	}

}