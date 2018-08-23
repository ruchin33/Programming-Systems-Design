import javax.swing.JFrame;

/**
 * @author ruchinpatel
 *	The given class CoinSimViewer, creates a Jframe and calls the runSimulation method
 *  runSimulation method runs the simulation of tossing the coin and is in CoinSimComponent class
 */
public class CoinSimViewer{
	
	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 500;
	private static final int FRAME_TOP_BOTTOM_SPACE = 50;
	private static final int LABEL_SPACE = 50;
	
	public static void main(String[] args){
		int frame_height=0,frame_width=0;
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("Coin Toss Simulator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CoinSimComponent Toss = new CoinSimComponent(frame_width,frame_height,FRAME_TOP_BOTTOM_SPACE,LABEL_SPACE);
		Toss.runSimuation();  
		frame.add(Toss);
		frame.setVisible(true);
	}
}