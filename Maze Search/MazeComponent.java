// Name: Ruchin Patel
// USC loginid: ruchinpa@usc.edu
// CS 455 PA3
// Spring 2017

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{

   private static final int START_X = 10; // top left of corner of maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze "location"
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   
   private int maze_frame_height = 0;
   private int maze_frame_width = 0;
   private int start_row=0,start_column=0,end_row=0,end_column=0;
   private boolean[][] maze_data;
   MazeCoord startLoc;
   MazeCoord exitLoc;
   private LinkedList<MazeCoord> final_path = new LinkedList<MazeCoord>() ;
   ListIterator<MazeCoord> iterator = final_path.listIterator();
   
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {  
	  MazeCoord startLoc = maze.getEntryLoc();
	  MazeCoord exitLoc = maze.getExitLoc();
	  start_row = startLoc.getRow()*BOX_HEIGHT;
	  start_column = startLoc.getCol()*BOX_WIDTH;
	  end_row = exitLoc.getRow()*BOX_HEIGHT;
	  end_column = exitLoc.getCol()*BOX_WIDTH;
	  maze_frame_height = maze.numRows() * BOX_HEIGHT;
	  maze_frame_width = maze.numCols() * BOX_WIDTH;
      maze_data = maze.maze_dat;
      final_path = maze.getPath();
	  /*for(int i=0;i<final_path.size();i++){
		  MazeCoord current_coord = iterator.next();
		  System.out.println("row and column are:"+current_coord.getRow()+" "+current_coord.getCol());
	  }*/
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
	   Graphics2D g2 = (Graphics2D)g;   
	   Rectangle box = new Rectangle(START_X,START_Y,maze_frame_width,maze_frame_height);
	   g2.draw(box);
	   for(int i =0;i<maze_frame_height/BOX_HEIGHT;i++){
		   for(int j =0;j<maze_frame_width/BOX_WIDTH;j++){
			   if(maze_data[i][j]==true){
				   g2.setColor(Color.BLACK);
				   box = new Rectangle(START_X+(j*BOX_WIDTH),START_Y+(i*BOX_HEIGHT),BOX_WIDTH,BOX_HEIGHT);
				   g2.draw(box);
				   g2.fill(box);
			   }
		   }
	   }
	   g2.setColor(Color.YELLOW);
	   box = new Rectangle(START_X+start_column+3,START_Y+start_row+3,BOX_WIDTH-6,BOX_HEIGHT-6);
	   g2.draw(box);
	   g2.fill(box);
	   g2.setColor(Color.GREEN);
	   box = new Rectangle(START_X+end_column+3,START_Y+end_row+3,BOX_WIDTH-6,BOX_HEIGHT-6);
	   g2.draw(box);
	   g2.fill(box);
	   ListIterator<MazeCoord> iterator = final_path.listIterator();
	   /*for(int i=0;i<final_path.size();i++){
			  MazeCoord current_coord = iterator.next();
			  System.out.println("row and column are:"+current_coord.getRow()+" "+current_coord.getCol());
		  }*/
	   
	   System.out.println("the path size is:"+final_path.size());
	   
	   if(final_path.size() > 0){
		   MazeCoord current_coord = iterator.next();
		   for(int i=0;i<final_path.size()-1;i++){
			   MazeCoord next_coord = iterator.next();
			   int x1=0,x2=0,y1=0,y2=0;
			   int row_1 = current_coord.getRow();
			   int col_1 = current_coord.getCol();
			   int row_2 = next_coord.getRow();
			   int col_2 = next_coord.getCol();
			   Line2D.Double line;
			   //System.out.println("row1: "+row_1+", col1:"+col_1+", row2: "+row_2+", col2:"+col_2);
			   if((col_2 > col_1) && (row_2 == row_1)){  //moves right
				   //System.out.println("row1: "+row_1+", col1:"+col_1+"row2: "+row_2+", col2:"+col_2);
				   line = new Line2D.Double(START_X +((col_1 * BOX_WIDTH)+BOX_WIDTH/2),START_Y + ((row_1*BOX_HEIGHT)+BOX_HEIGHT/2),START_X +((col_2 * BOX_WIDTH)+BOX_WIDTH/2),START_Y + ((row_1*BOX_HEIGHT)+BOX_HEIGHT/2));
				   g2.setColor(Color.BLUE);
				   g2.draw(line);
			   }
			   else if((col_2 == col_1) && (row_2>row_1)){  //down
				   //System.out.println("row1: "+row_1+", col1:"+col_1+"row2: "+row_2+", col2:"+col_2);
				   
				   line = new Line2D.Double(START_X + ((col_1*BOX_WIDTH)+BOX_WIDTH/2),START_Y +((row_1 * BOX_HEIGHT)+BOX_HEIGHT/2),START_X + ((col_1*BOX_WIDTH)+BOX_WIDTH/2),START_Y +((row_2 * BOX_HEIGHT)+BOX_HEIGHT/2));
				   g2.setColor(Color.BLUE);
				   g2.draw(line);
			   }
			   else if((col_2 == col_1) && (row_2<row_1)){  //up
				   //System.out.println("row1: "+row_1+", col1:"+col_1+"row2: "+row_2+", col2:"+col_2);
				   
				   line = new Line2D.Double(START_X +((col_1* BOX_WIDTH)+BOX_WIDTH/2),START_Y + ((row_1*BOX_HEIGHT)+BOX_HEIGHT/2),START_X +((col_1* BOX_WIDTH)+BOX_WIDTH/2),START_Y + ((row_2*BOX_HEIGHT)+BOX_HEIGHT/2));
				   g2.setColor(Color.BLUE);
				   g2.draw(line);
			   }
			   else/*((col_2 < col_1) && (row_2 == row_1))*/{  //moves left
				   //System.out.println("row1: "+row_1+", col1:"+col_1+"row2: "+row_2+", col2:"+col_2);
				   
				   line = new Line2D.Double(START_X +((col_1 * BOX_WIDTH)+BOX_WIDTH/2),START_Y + ((row_1*BOX_HEIGHT)+BOX_HEIGHT/2),START_X +((col_2 * BOX_WIDTH)+BOX_WIDTH/2),START_Y + ((row_1*BOX_HEIGHT)+BOX_HEIGHT/2));
				   g2.setColor(Color.BLUE);
				   g2.draw(line);
			   }
			   g2.setColor(Color.BLUE);
			   g2.draw(line);
			   current_coord = next_coord;
		   }
	   }
	   
   }
   
}


