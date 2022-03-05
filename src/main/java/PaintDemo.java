import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaintDemo {
    JFrame frame;
    DrawingPanel dPanel;

    // This is the constructor which sets up the JFrame
    // and all other components and containers
    public PaintDemo() {
        // Set up JFrame
        frame = new JFrame("Paint Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);

        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // Main method just creates a PaintDemo object
    public static void main(String[] args) {
        PaintDemo x = new PaintDemo();
    }

    class DrawingPanel extends JPanel {
        // Constructor sets up DrawingPanel
        public DrawingPanel() {
            setBackground(Color.WHITE);
        }

        // Sets the size of the DrawingPanel, so frame.pack() considers
        // its preferred size when sizing the JFrame
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(500, 500);
        }

        // This is the method that draws everything
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // draw box
            g.setColor(Color.BLACK);
            g.drawRect(100, 100, 200, 200);

            // draw circle
            g.setColor(Color.RED);
            g.fillOval(200, 200, 200, 200);

            // fill box
            g.setColor(Color.BLUE);
            g.fillRect(300, 300, 200, 200);

            // draw oval
            g.setColor(Color.GREEN);
            g.drawOval(400, 400, 200, 200);

            // draw a skinny oval
            g.setColor(Color.MAGENTA);
            g.drawOval(500, 500, 200, 100);

        }
    }
}
