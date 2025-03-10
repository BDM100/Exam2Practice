import java.awt.*;

/**
 * This class stores and draws an individual Snowman object.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */
public class Snowman extends Thread {

    // min size of a component snowball
    public static final int MIN_SIZE = 50;

    // location
    private Point upperLeft;

    // diameter
    private int diameter;

    /**
     * Construct a new Snowman object.
     * 
     * @param center point for the center of the snowman's base
     * @param other  another point, which will be on the edge of the
     *               snowman's base
     */
    public Snowman(Point center, Point other) {
        this.diameter = (int) center.distance(other) * 2;
        this.upperLeft = new Point(center.x - diameter / 2, center.y - diameter / 2);
    }

    /**
     * Draw the snowman at its current location.
     * 
     * @param g the Graphics object on which the ball should be drawn
     */
    public void paint(Graphics g) {
        //g.drawOval(upperLeft.x, upperLeft.y, diameter, diameter);
        drawSnowman(upperLeft, g);
    }

    public void drawSnowman(Point upperLeft, Graphics g) {
        g.drawOval(upperLeft.x, upperLeft.y, diameter, diameter);
        diameter = (int)(diameter * 0.75);
        upperLeft = new Point(upperLeft.x + diameter / 6, upperLeft.y - diameter);
        if (diameter > MIN_SIZE) {
            drawSnowman(upperLeft, g);
        }else{
            g.drawOval(upperLeft.x, upperLeft.y, diameter, diameter);
        }
    }
}
