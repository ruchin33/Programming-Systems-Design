import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import javax.swing.JComponent;

public class CoinSimComponent extends JComponent{
	
	public static final Color HEAD_HEAD_COLOR = Color.RED; 
	public static final Color HEAD_TAIL_COLOR = Color.GREEN;
	public static final Color TAIL_TAIL_COLOR = Color.BLUE;
	public static final int   BAR_WIDTH = 100;
	
	/**
	 * hh_percentage : The percentage of two heads occurring
	 * hh_num        : The number of two heads occurring.
	 * tt_percentage : The percentage of two tails occurring
	 * tt_num		 : The number of two tails occurring.
	 * ht_percentage : The percentage of one head one tail occurring
	 * ht_num 		 : The number of one head one tail occurring.
	 * hh_X_coor 	 : The X coordinate of the first Bar(Two Heads Bar)
	 * ht_X_coor 	 : The X coordinate of the second Bar(One Head One Tail Bar)
	 * tt_X_coor     : The X coordinate of the third Bar(Two Tails Bar)
	 * scale 		 : The unit measure for all bars which constitutes to 1 percent of any of the event occuring
	 */
	
	private int frame_height=0,frame_width=0,trials=0, Vb=0, label_space=0,W=0,bar_height =0,bar_width=0; //The variable for dimensions of bar
	private double hh_percentage=0,ht_percentage=0,tt_percentage=0; // the variables for storing outcomes of toss
	private int hh_num=0,ht_num=0,tt_num=0; // the variables for storing outcomes of toss
	private int hh_X_coor,ht_X_coor,tt_X_coor,label_bottom; //the coordinates of the edges of the bars
	private double scale=0.0; //variable for dimension of bar

	
	/**
	 	This constructor initializes the frame height,frame width.
	 	It also initializes the space to be left from top and bottom and the space that
	 	the label beneath the bars take.
	 * @param frameWidth    : Width of the frame
	 * @param frameHeight	: Height of the frame
	 * @param verticalSpace	: The space left from top and bottom 
	 * @param labelSpace	: The space in which we are allowed to write text below the bars
	 */
	public CoinSimComponent(int frameWidth,int frameHeight,int verticalSpace,int labelSpace){
		
		this.frame_height = frameHeight;
		this.frame_width = frameWidth;
		this.Vb = verticalSpace;
		this.label_space = labelSpace;
		
	}
	
	/**
	 	Runs the simulation of tossing two fair coins by calling run method in CoinTossSimulator
	 	This method also stored the quantitative variables of the coin toss hich are used in later
	 	part of the program
	 */
	public void runSimuation(){
		CoinTossSimulator Coin = new CoinTossSimulator();
		Scanner in = new Scanner(System.in);
	
		while(trials<1){
			System.out.println("Enter the number of trials:");
			trials = in.nextInt();
		}
	
		Coin.run(trials);
	
		hh_percentage = (100*(double)Coin.getTwoHeads()/(double)Coin.getNumTrials());
		double hh_percentage1 = BigDecimal.valueOf(hh_percentage).setScale(1,RoundingMode.HALF_UP).doubleValue();
		hh_percentage = hh_percentage1; // here we changed the percentage precision to 1 decimal points by above formula
		
		tt_percentage = (100*(double)Coin.getTwoTails()/(double)Coin.getNumTrials());
		double tt_percentage1 = BigDecimal.valueOf(tt_percentage).setScale(1,RoundingMode.HALF_UP).doubleValue();
		tt_percentage = tt_percentage1; // here we changed the percentage precision to 1 decimal points by above formula
		
		ht_percentage = (100*(double)Coin.getHeadTails()/(double)Coin.getNumTrials());
		double ht_percentage1 = BigDecimal.valueOf(ht_percentage).setScale(1,RoundingMode.HALF_UP).doubleValue();
		ht_percentage = ht_percentage1; // here we changed the percentage precision to 1 decimal points by above formula
		
		hh_num = Coin.getTwoHeads();
		ht_num = Coin.getHeadTails();
		tt_num = Coin.getTwoTails();
	
		Coin.reset();
	}
	
	/**
	 	This method is called every time the size of the frame is changed and it draws bars of different sizes
	 */
	public void paintComponent(Graphics g){
	
			frame_height = getHeight();
			frame_width = getWidth();
			W = (int)Math.round((frame_width-(3*BAR_WIDTH))/4.0);
			scale = (frame_height -((2*Vb) +label_space))/100.0;
			bar_height = (int)Math.round(100*scale);
			hh_X_coor = W;
			bar_width=100;
			ht_X_coor = (2*W)+bar_width;
			tt_X_coor = (3*W)+(2*bar_width);
			label_bottom = Vb + label_space + (int)(100*scale);
			
			Graphics2D g2 = (Graphics2D)g;
			Bar Head_Head_bar = new Bar(label_bottom,hh_X_coor,BAR_WIDTH,(int)Math.round((hh_percentage*scale)),scale,HEAD_HEAD_COLOR,"Two Heads: "+hh_num+"("+hh_percentage+"% )");
			Head_Head_bar.draw(g2);
			
			Bar Head_Tail_bar = new Bar(label_bottom,ht_X_coor,BAR_WIDTH,(int)Math.round((ht_percentage*scale)),scale,HEAD_TAIL_COLOR,"One Head,Tail: "+ht_num+"("+ht_percentage+"% )");
			Head_Tail_bar.draw(g2);
			
			Bar	Tail_Tail_bar = new Bar(label_bottom,tt_X_coor,BAR_WIDTH,(int)Math.round((tt_percentage*scale)),scale,TAIL_TAIL_COLOR,"Two Tails: "+tt_num+"("+tt_percentage+"% )");
			Tail_Tail_bar.draw(g2);
	
	}
}