package corsojava.frames;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        JFrame frame = new JFrame("Corso JAVA");
        Container c = frame.getContentPane();
        c.add(new JLabel("Buona Lezione"));

        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
