import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * 
 * Practice Exam 2 event handler question.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */

public class HoverSquares extends MouseAdapter implements Runnable {

    // square size
    public static final int SIZE = 50;

    // list of squares currently on the screen
    private java.util.List<Point> upperLefts;

    private JPanel panel;
    Point currentPos;

    Random rand = new Random();

    /**
     * The run method to set up the graphical user interface
     */
    @Override
    public void run() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("HoverSquares");
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JPanel with a paintComponent method
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {

                super.paintComponent(g);

                // draw the squares
                for (Point p : upperLefts) {

                    if(currentPos.x > p.x && currentPos.x < p.x + SIZE && currentPos.y > p.y && currentPos.y < p.y + SIZE){
                        g.setColor(Color.RED);
                    }else{
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect(p.x, p.y, SIZE, SIZE);
                }

            }
        };
        frame.add(panel);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);

        upperLefts = new ArrayList<Point>();

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Mouse press event handler to create a new Square with upper left at
     * the press point.
     * 
     * @param e mouse event info
     */
    @Override
    public void mousePressed(MouseEvent e) {

        upperLefts.add(e.getPoint());
        panel.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e){
        currentPos = e.getPoint();
        panel.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e){
        for (Point p : upperLefts){
            p.x = rand.nextInt(700);
            p.y = rand.nextInt(700);
            panel.repaint();
        }
    }
    public static void main(String args[]) {

        javax.swing.SwingUtilities.invokeLater(new HoverSquares());
    }
}
