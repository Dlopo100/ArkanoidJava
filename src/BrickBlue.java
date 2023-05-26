public class BrickBlue extends Brick{
    public BrickBlue(Game game, int x, int y, int width, int height) {
        super(game, x, y, 153, 195, 236, width, height, 100);
    }
    
    protected void collisionWithBall() {
        game.racquet.newSpeedUp();
        delteBrick();
    }
}