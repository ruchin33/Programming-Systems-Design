// Name:Ruchin Patel
// USC NetID:ruchinpa
// CS 455 PA1
// Spring 2017

// we included the import statements for you
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
   
	private static final int FRAME_TOP_BOTTOM_SPACE = 50;
	
	/**
	 * start_left_corner : X-coordinate of the upper left corner of the bar(x-coordinate of Rectangle object)
	 * bottom_label      : Y-coordinate of the basis point of the text beneath the bar
	 * bar_width         : Width of the bar
	 * bar_height        : Height of the bar
	 * Vb                : the space that is left from the top and bottom of the frame
	 * Y_coor            : Y-coordinate of the topmost left hand side of the bar(y-coordinate of Rectangle object)
	 */
	
	private int start_left_corner=0,bottom_label=0,bar_width=0,bar_height=0,Vb=0,Y_coor=0; //variables for dimension of bar
	private double scale_bar=0.0;//variable for dimension of bar
	private Color color_bar; //object for color of bar
	private String label_bar; //variable for text written beneath the bar
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {
	   
	   
	   this.color_bar = color;
	   bottom_label = bottom;
	   start_left_corner = left;
	   bar_width = width;
	   bar_height = barHeight;
	   scale_bar = scale;
	   label_bar = label;
	   Vb = FRAME_TOP_BOTTOM_SPACE;
	   Y_coor = Vb + (int)Math.round((100*scale_bar)-bar_height);
	   
   }
   
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
	   
	   Graphics2D g = (Graphics2D)g2;
	   Rectangle box = new Rectangle(start_left_corner,Y_coor,bar_width,bar_height);
	   g.setColor(color_bar);
	   g.fill(box);
	   g.drawString(label_bar, start_left_corner, bottom_label);
   }
}