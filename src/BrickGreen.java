public class BrickGreen extends Brick{
    private int count_collision_ball = 0;

    public BrickGreen(Game game, int x, int y, int width, int height) {
        super(game, x, y, 151, 227, 194, width, height, 300);
    }
    protected void collisionWithBall() {
        count_collision_ball++;
        switch(count_collision_ball){
            case 3:
                delteBrick();
                break;
            default:
                color_blue -= 75;
                color_green -= 75;
                color_red -= 75;
                break;
        }
    }
    
}
