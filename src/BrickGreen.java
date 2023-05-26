public class BrickGreen extends Brick{
    public BrickGreen(Game game, int x, int y, int width, int height) {
        super(game, x, y, 151, 227, 194, width, height, 300);
    }
    protected void collisionWithBall() {
        delteBrick();
    }
    
}
