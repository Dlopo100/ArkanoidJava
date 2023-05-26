
import java.awt.Graphics2D;
import java.awt.Rectangle;

abstract public class Brick {
    // protected static int width = 60;
    // protected static int height = 30;
    protected int width;
    protected int height;
    protected int x = 0;
    protected int y = 0;
    protected int color_red = 0;
    protected int color_green = 0;
    protected int color_blue = 0;
    protected boolean collide = true;
    protected boolean destroy = false;
    protected int score;

	protected Game game;

	public Brick(Game game, int x, int y, int color_red, int color_green, int color_blue, int width, int height, int score) {
		this.game = game;
        this.x = x;
        this.y = y;
        this.color_red = color_red;
        this.color_green = color_green;
        this.color_blue = color_blue;
        this.width = width;
        this.height = height;
        this.score = score;
	}


    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }
	public void paint(Graphics2D g) {
        g.setColor(new java.awt.Color(color_red, color_green, color_blue));
		g.fillRect(x, y, width, height);
        //paint rect with color

	}

    public void checkCollision(){
        if (collide){
            if (iscollisionWithBall()){
                game.ball.oppositeWay();
                collisionWithBall();
            } 
        }
    }

	protected boolean iscollisionWithBall() {
		return game.ball.getBounds().intersects(getBounds());
	}

	protected boolean iscollisionWithRacquet() {
		return game.racquet.getBounds().intersects(getBounds());
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
    
    protected void delteBrick() {
        destroy = true;
        game.delteBrick(this);
    }
    abstract protected void collisionWithBall();
}