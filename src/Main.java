import java.util.Scanner;

public class Main {
    private Launcher launcher;
    private Game game;
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.start();
    }

    public void start() throws InterruptedException {
        this.launcher = new Launcher(this);
        this.game = new Game();
        // this.launcher.start();
        GameInit();
        //sc.nextLine();
    }

    public void launcher_button_clicked(int id_button) {

        System.out.println("Button " + id_button + " Clicked");
        System.out.println(this.launcher);
        launcher.destroy();
        
        try {
            GameInit();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("Launcher destroyed");
    }
    public void GameInit() throws InterruptedException{
        System.out.println("GameInit");
        game.init();

    }
}
