public class Main {
    private Launcher launcher;
    private Game game;
    public static void main(String[] args) throws InterruptedException {
        // Game game = new Game();
        // game.startGame();

        Main main = new Main();
        main.start();
        



    }

    public void start() throws InterruptedException {
        launcher = new Launcher();
        launcher.start();
        System.out.println("Launcher started");
        System.out.println(launcher);

    }
    public void launcher_button_clicked(int id_button){
        
        // try {
        //     game.startGame();
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
        //destroy launcher


        System.out.println("Button " + id_button + " Clicked");
        System.out.println(launcher);
        launcher.setJFrameVisible(false);
        // launcher.dispose();
        // launcher = null;
        
        System.out.println("Launcher destroyed");
    }
}
