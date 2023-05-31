import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {
    private Launcher launcher;
    private Game game;
    private boolean runGame = false;
    private boolean soundState = true;
    private String usuario;

    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.start();
    }

    public void start() throws InterruptedException {
        this.launcher = new Launcher(this);
        this.game = new Game();
        GameInit();
        
    }

    public boolean isSoundState() {
        return this.soundState;
    }

    public void setSoundState(boolean soundState) {
        this.soundState = soundState;
    }
    public void launcher_button_clicked(int id_button) {

        launcher.destroy();

        if (id_button == 0) {
            runGame = true;
        } else if (id_button == 2) {
            System.exit(0);
        }
        
        
    }

    public void GameInit() throws InterruptedException{
        
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (!runGame) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    do {
                        usuario = JOptionPane.showInputDialog("Introdueix el teu nom:");
                    } while (usuario.equals(""));
                    game.init(soundState);
                } 
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

    }
}
