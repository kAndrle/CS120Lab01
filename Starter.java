import javax.swing.*;
import java.awt.*;


public class Starter {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Ooooooze");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        DrawingPanel mainPanel = new DrawingPanel();
        mainPanel.setPreferredSize(new Dimension(500,500));
        mainPanel.setBackground(Color.white);
        frame.getContentPane().add(mainPanel);
        frame.pack();

        Timer t = new Timer(30,mainPanel);
        t.start();

        mainPanel.addMouseListener(mainPanel);
        mainPanel.addMouseMotionListener(mainPanel);
        frame.addKeyListener(mainPanel);


    }

}
