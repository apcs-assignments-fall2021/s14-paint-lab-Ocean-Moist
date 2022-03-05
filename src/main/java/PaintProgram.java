import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class PaintProgram implements ActionListener {
    JFrame frame;
    DrawingPanel dPanel;
    JPanel buttonPanel, colorPanel;
    JButton pencilButton, eraserButton;
    int x1, y1;
    private int rectEndY, rectEndX, rectStartY, rectStartX;
    Graphics g2;
    JTextField redField, greenField, blueField;
    // This is the PaintProgram constructor which sets up the JFrame
    // and all other components and containers
    // ** Code to be edited in Part C **
    public PaintProgram() {
        rectStartX = -1;
        // Set up JFrame using BorderLayout
        frame = new JFrame("Paint Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add DrawingPanel to CENTER
        dPanel = new DrawingPanel();
        frame.add(dPanel);

        // Create buttonPanel and buttons
        buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        colorPanel = new JPanel();
        frame.add(colorPanel, BorderLayout.EAST);
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        pencilButton = new JButton("Pencil");
        pencilButton.addActionListener(this);
        buttonPanel.add(pencilButton);

        eraserButton = new JButton("Eraser");
        eraserButton.addActionListener(this);
        buttonPanel.add(eraserButton);

        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        JButton sprayButton = new JButton("Spray");
        sprayButton.addActionListener(this);
        buttonPanel.add(sprayButton);

        JButton fiilButton = new JButton("Fill");
        fiilButton.addActionListener(this);
        buttonPanel.add(fiilButton);

        // color buttons
        JButton redButton = new JButton("Red");
        redButton.addActionListener(this);
        colorPanel.add(redButton);

        JButton blueButton = new JButton("Blue");
        blueButton.addActionListener(this);
        colorPanel.add(blueButton);

        JButton greenButton = new JButton("Green");
        greenButton.addActionListener(this);
        colorPanel.add(greenButton);

        JButton blackButton = new JButton("Black");
        blackButton.addActionListener(this);
        colorPanel.add(blackButton);

        // custom rgb color
        JButton customButton = new JButton("Custom Color");
        customButton.addActionListener(this);
        colorPanel.add(customButton);

        // three fields for red green and blue
        JTextField redField = new JTextField(3);
        redField.addActionListener(this);
        colorPanel.add(redField);

        JTextField greenField = new JTextField(3);
        greenField.addActionListener(this);
        colorPanel.add(greenField);

        JTextField blueField = new JTextField(3);
        blueField.addActionListener(this);
        colorPanel.add(blueField);

        // Set the size and set the visibility
        frame.pack();
        frame.setVisible(true);
    }

    // This the code that is called when any button is pressed
    // We should have a separate case for each button
    // ** Code to be edited in Part B **
    public void actionPerformed(ActionEvent ae) {
        // If pencilButton is pressed, set drawingPanel mode to "Pencil"
        if (ae.getActionCommand().equals("Pencil")) {
            dPanel.setMode("Pencil");
        }

        // If eraserButton is pressed, set drawingPanel mode to "Eraser"
        if (ae.getActionCommand().equals("Eraser")) {
            dPanel.setMode("Eraser");
        }

        if (ae.getActionCommand().equals("Red")) {
            dPanel.setColor(Color.RED);
        }

        if (ae.getActionCommand().equals("Blue")) {
            dPanel.setColor(Color.BLUE);
        }

        if (ae.getActionCommand().equals("Green")) {
            dPanel.setColor(Color.GREEN);
        }

        if (ae.getActionCommand().equals("Custom Color")) {
            dPanel.setColor(new Color(Integer.parseInt(redField.getText()), Integer.parseInt(greenField.getText()), Integer.parseInt(blueField.getText())));
        }

        if (ae.getActionCommand().equals("Black")) {
            dPanel.setColor(Color.BLACK);
        }

        if (ae.getActionCommand().equals("Clear")) {
            dPanel.clear();
        }

        if (ae.getActionCommand().equals("Spray")) {
            dPanel.setMode("Spray");
        }

        if (ae.getActionCommand().equals("Fill")) {
            dPanel.setMode("Fill");
        }

        if (ae.getActionCommand().equals("Rectangle")) {
            dPanel.setMode("Rectangle");
        }

        if (ae.getActionCommand().equals("Custom Color")) {
            dPanel.setMode("Custom");
        }

    }

    // Main method just creates a PaintProgram object
    public static void main(String[] args) {
        PaintProgram x = new PaintProgram();
    }

    class DrawingPanel extends JPanel implements MouseListener, MouseMotionListener{
        // DrawingPanel has the following instance variables:

        // A 2D array which stores whether or not
        // each pixel should be painted
        // ** To be used in Part B **
        public boolean[][] isPainted;

        // A 2D array which stores the Java Colors
        // of each pixel
        // ** To be used in Part C **
        public Color[][] colors;

        // The mode is a String that we can use to keep track of
        // what should happen if the user presses the mouse
        // ** To be used in Part B **
        private String mode;

        // This keeps track of the current selected color
        // ** To be used in Part C **
        private Color color;

        // These are constant values
        public static final int WIDTH = 500;
        public static final int HEIGHT = 500;

        // Constructor sets up DrawingPanel
        // ** You should never need to edit this code **
        public DrawingPanel() {
            // Set background color
            setBackground(Color.WHITE);

            // Add mouse listeners
            addMouseListener(this);
            addMouseMotionListener(this);

            // Initialize instance variables
            isPainted = new boolean[WIDTH][HEIGHT];
            colors = new Color[WIDTH][HEIGHT];
            mode = "Pencil";
            color = Color.BLACK;
        }

        // Can be called to change the current mode
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setMode(String mode) {
            this.mode = mode;
        }

        // Can be called to change the current color
        // of the drawing panel
        // ** You should never need to edit this code **
        public void setColor(Color color) {
            this.color = color;
        }

        // Sets the size of the DrawingPanel, so frame.pack() considers
        // its preferred size when sizing the JFrame
        // ** You should never need to edit this code **
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(WIDTH, HEIGHT);
        }

        // This is the method that draws everything
        // ** Code to be edited in Part C **
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics g2 = g;
            // Loop through the 2D array and draw a 1x1 rectangle
            // on each pixel that is currently painted
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    if (isPainted[x][y]) {
                        g.setColor(colors[x][y]);
                        g.drawRect(x, y, 1, 1);
                    }
                }
            }

        }

        // MouseListener methods
        // This is the method that is called when the mouse
        // is pressed. This is where most of your code will go
        // ** Code to be edited in Part B **
        public void mousePressed(MouseEvent e) {
            // Check the current mode
            // * If "pencil" mode, we should mark the current
            //   pixel as painted
            if (mode.equals("Pencil")) {
                // Check that mouse is in bounds of panel
                if (e.getX() >= 0 && e.getX() < WIDTH &&
                        e.getY() >= 0 && e.getY() < HEIGHT) {
                    // Set current pixel as painted
                    colors[e.getX()][e.getY()] = color;
                    isPainted[e.getX()][e.getY()] = true;
                }
            }
            // erase 5x5 rectangle
            if (mode.equals("Eraser")) {
                // Check that mouse is in bounds of panel
                if (e.getX() >= 0 && e.getX() < WIDTH &&
                        e.getY() >= 0 && e.getY() < HEIGHT) {
                    // Set current pixel as painted
                    isPainted[e.getX()][e.getY()] = false;
                    for (int x = e.getX() - 5; x <= e.getX() + 5; x++) {
                        for (int y = e.getY() - 5; y <= e.getY() + 5; y++) {
                            if (x >= 0 && x < WIDTH &&
                                    y >= 0 && y < HEIGHT) {
                                isPainted[x][y] = false;
                            }
                        }
                    }
                }
                repaint();
            }

            if (mode.equals("Spray")) {
                if (e.getX() >= 0 && e.getX() < WIDTH &&
                        e.getY() >= 0 && e.getY() < HEIGHT) {
                    for (int x = e.getX() - 10; x <= e.getX() + 10; x++) {
                        for (int y = e.getY() - 10; y <= e.getY() + 10; y++) {
                            if (x >= 0 && x < WIDTH &&
                                    y >= 0 && y < HEIGHT) {
                                if (Math.random() < 0.03) {
                                    colors[x][y] = color;
                                    isPainted[x][y] = true;
                                }
                            }
                        }
                    }
                }
            }

            // flood fill
            if (mode.equals("Fill")) {
                if (e.getX() >= 0 && e.getX() < WIDTH &&
                        e.getY() >= 0 && e.getY() < HEIGHT) {
                    floodFill(e.getX(), e.getY(), color);
                }
            }

            // rect tool
            if (mode.equals("Rectangle")) {
                if (e.getX() >= 0 && e.getX() < WIDTH &&
                        e.getY() >= 0 && e.getY() < HEIGHT) {
                    if (rectStartX == -1) {
                        rectStartX = e.getX();
                        rectStartY = e.getY();
                    } else {
                        rectEndX = e.getX();
                        rectEndY = e.getY();
                    }
                    for (int x = rectStartX; x <= rectEndX; x++) {
                        for (int y = rectStartY; y <= rectEndY; y++) {
                            if (x >= 0 && x < WIDTH &&
                                    y >= 0 && y < HEIGHT) {
                                colors[x][y] = color;
                                isPainted[x][y] = true;
                            }
                        }
                    }
                    rectStartX = -1;
                }


            }
            // We need to manually tell the panel to repaint
            // and call the paintComponent method
            repaint();
        }

        // This is a MouseMotionListener method
        // We have this method so that we don't need to click each
        // pixel that we want to draw
        // ** You should never need to edit this code **
        public void mouseDragged(MouseEvent e) {
            mousePressed(e);
        }

        // The remaining MouseListener and MouseMotionLister
        // methods are left blank
        // ** You should never need to edit this code **
        public void mouseReleased(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseEntered(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseExited(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseClicked(MouseEvent e) {
            // This method is intentionally blank
        }

        // ** You should never need to edit this code **
        public void mouseMoved(MouseEvent e) {
            // This method is intentionally blank
        }


        public void clear() {
            for (int x = 0; x < WIDTH; x++) {
                for (int y = 0; y < HEIGHT; y++) {
                    isPainted[x][y] = false;
                }
            }
            repaint();
        }
    }

    private void floodFill(int x, int y, Color color) {
        // queue
        Queue<Point> queue = new LinkedList<Point>();
        queue.add(new Point(x, y));
        while (!queue.isEmpty()) {
            Point p = queue.remove();
            int x1 = p.x;
            int y1 = p.y;
            if (x1 >= 0 && x1 < 500 &&
                    y1 >= 0 && y1 < 500) {
                if (!(dPanel.colors[x1][y1] == color)) {
                    dPanel.colors[x1][y1] = color;
                    dPanel.isPainted[x1][y1] = true;
                    queue.add(new Point(x1 - 1, y1));
                    queue.add(new Point(x1 + 1, y1));
                    queue.add(new Point(x1, y1 - 1));
                    queue.add(new Point(x1, y1 + 1));
                }
            }
        }
    }


    public void rect(int x1, int y1, int x2, int y2, Graphics g) {
            g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }

}
