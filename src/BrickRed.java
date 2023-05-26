public class BrickRed extends Brick {
    private int count_collision_ball = 0;

    public BrickRed(Game game, int x, int y, int width, int height) {
        super(game, x, y, 255, 128, 128, width, height, 200);
    }

    protected void collisionWithBall() {
        count_collision_ball++;
        if (count_collision_ball == 2) {
            fallDown();
        }
        else {
            color_blue -= 75;
            color_green -= 75;
            color_red -= 75;

        }
    }

    protected void fallDown() {
        collide = false;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (y < game.getHeightScreen()-height && !destroy) {
                    y++;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (iscollisionWithRacquet()) {
                        game.gameOver();
                    }
                }
                delteBrick();
            }
        });
        thread.start();
    }

}
