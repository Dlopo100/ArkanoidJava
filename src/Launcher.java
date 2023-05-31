

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Launcher extends JFrame {
    private int WIDTH = 350;
    private int HEIGHT = 500;
    private int widthButton = 150;
    private int heightButton = 50;
    private int yButtonInitial = 125;
    private Main main;


    private String[] buttonStrings = {"Jugar", "Opciones", "Salir"};
    private JButton[] jButtons = new JButton[buttonStrings.length];

    public Launcher(Main mainSended){
        this.main = mainSended;
        this.setSize(WIDTH+15, HEIGHT); // set window size
        this.setVisible(true); // show window
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close window
        this.setLocationRelativeTo(null); // center window
        this.setBackground(Color.BLACK); // set background color
        this.setLayout(null); // set layout to null
        this.setResizable(false); //fix size
        this.setTitle("Mini Tennis - Launcher"); // set title
        this.createButtons((Graphics2D) this.getGraphics());
    }

    private void createButtons(Graphics2D g){
        int yButton = this.yButtonInitial;
        int diferenceBetweenButtons = this.CalculateBeetweenButtons(g);
        for (int i = 0; i < buttonStrings.length; i++) {
            String bString = buttonStrings[i];
            if (i == 1) {
                if (main.isSoundState()) {
                    bString = "Sonido: ON";
                } else {
                    bString = "Sonido: OFF";
                }
            }
            jButtons[i] = new JButton(bString);
            jButtons[i].setBounds((this.WIDTH/2)-(widthButton/2), yButton, widthButton, heightButton);
            jButtons[i].addActionListener(createActionListener(i));
    
            yButton += heightButton + diferenceBetweenButtons;
        }
    }

    private void paintButtons(){
        for (int i = 0; i < buttonStrings.length; i++) {
            this.add(jButtons[i]);
        }
    }

    private ActionListener createActionListener(int id_button) {
        ActionListener acEvent = new ActionListener(){  
                public void actionPerformed(ActionEvent e){   
                    if (id_button == 1) {
                        if (main.isSoundState()) {
                            jButtons[id_button].setText("Sonido: OFF");
                            main.setSoundState(false);
                        } else {
                            jButtons[id_button].setText("Sonido: ON");
                            main.setSoundState(true);
                        }
                        repaint();
                    } else 
                        main.launcher_button_clicked(id_button);
                }  
            };
        return acEvent;
    }
    private int CalculateBeetweenButtons(Graphics2D g){
        Rectangle rect = new Rectangle((this.WIDTH - (this.WIDTH-50))/2, this.yButtonInitial, this.WIDTH-50, this.HEIGHT-200);

        return (rect.height - (buttonStrings.length * heightButton)) / buttonStrings.length;
        
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font, Color color) {
        // Get the FontMetrics
        FontMetrics metrics = g.getFontMetrics(font);
        // Determine the X coordinate for the text
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        // Set the font
        g.setFont(font);
        // Draw the String
        g.drawString(text, x, y);
    }

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        this.drawCenteredString(g2d, "Arkanoid", new Rectangle(0, 30, this.WIDTH, 50), new Font("Verdana", Font.BOLD, 30), Color.BLACK);
        this.drawCenteredString(g2d, "by Pol Dotras", new Rectangle(this.WIDTH/2-7, 67, 75, 20), new Font("Verdana", Font.PLAIN, 12), Color.BLACK);

        this.paintButtons();
    }


    public void destroy(){
        this.dispose();
    }
   
}
