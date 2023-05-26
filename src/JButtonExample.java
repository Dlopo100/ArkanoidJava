import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JButtonExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JButton Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        JButton button = new JButton("Click Me!");
        button.setBounds(100, 70, 100, 30);

        button.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Button clicked!");
        });

        frame.add(button);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}