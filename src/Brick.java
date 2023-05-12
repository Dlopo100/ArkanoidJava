
import java.awt.Graphics2D;
import java.awt.Rectangle;

abstract public class Brick {
    protected static int width = 60;
    protected static int height = 30;
    protected int x = 0;
    protected int y = 0;
    protected int color_red = 0;
    protected int color_green = 0;
    protected int color_blue = 0;

	private Game game;

	public Brick(Game game, int x, int y, int color_red, int color_green, int color_blue) {
		this.game = game;
        this.x = x;
        this.y = y;
        this.color_red = color_red;
        this.color_green = color_green;
        this.color_blue = color_blue;
	}

	public void paint(Graphics2D g) {
        g.setColor(new java.awt.Color(color_red, color_green, color_blue));
		g.fillRect(x, y, width, height);
        //paint rect with color






	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
    
    public void delteBrick() {
        this.x = -100;
        this.y = -100;
    }
}