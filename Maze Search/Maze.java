// Name: Ruchin Patel
// USC loginid: ruchinpa@usc.edu
// CS 455 PA3
// Spring 2017

import java.util.LinkedList;
import java.util.ListIterator;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls

 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   
   public boolean[][] maze_dat;
   private int start_r=0,start_c=0,end_r=0,end_c=0,rows=0,columns=0;
   private LinkedList<MazeCoord> path = new LinkedList<MazeCoord>();
   private ListIterator<MazeCoord> iter = path.listIterator();
   
   
  

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments above for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param exitLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord exitLoc) {
	   
	   // Pre conditions left
	   maze_dat = mazeData;
	   start_r = startLoc.getRow();
	   start_c = startLoc.getCol();
	   end_r = exitLoc.getRow();
	   end_c = exitLoc.getCol();
   }


   /**
      Returns the number of rows in the maze
      @return number of rows
   */
   public int numRows() {
	  rows = maze_dat.length;
      return rows;   
   }

   
   /**
      Returns the number of columns in the maze
      @return number of columns
   */   
   public int numCols() {
	  columns = maze_dat[0].length;
      return columns;   
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
	  if(maze_dat[loc.getRow()][loc.getCol()] == WALL){
		  return true;
	  }
	  else{
		  return false;   
	  }
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return new MazeCoord(start_r,start_c);   
   }
   
   
   /**
     Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return new MazeCoord(end_r,end_c);   
   }

   
   /**
      Returns the path through the maze. First element is start location, and
      last element is exit location.  If there was not path, or if this is called
      before a call to search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {

	  //path = new LinkedList<MazeCoord>();
      return path;   // DUMMY CODE TO GET IT TO COMPILE

   }


   /**
      Find a path from start location to the exit location (see Maze
      constructor parameters, startLoc and exitLoc) if there is one.
      Client can access the path found via getPath method.

      @return whether a path was found.
    */
   public boolean search()  {  
      
	  //System.out.println(x);
	  boolean[][] padded_maze = new boolean[rows+2][columns+2];
	  padded_maze = getPadded(padded_maze,maze_dat);
	  int[][] visited = new int[rows][columns];
	  
	  int[][] current = new int[rows][columns];
	  boolean value = searchPath(maze_dat,start_r,start_c,end_r,end_c,visited);
	  ListIterator<MazeCoord> iterator = path.listIterator();
	  for(int i=0;i<path.size();i++){
		  MazeCoord current_coord = iterator.next();
		  System.out.println("row and column are:"+current_coord.getRow()+" "+current_coord.getCol());
	  }
	  /*if(value == true){
		  System.out.println("TRUTH");
	  }
	  else{
		  System.out.println("FALSE");
	  }*/
	  /*for(int i=0;i<maze_dat.length;i++){
		  for(int j =0;j<maze_dat[0].length;j++){
			  System.out.print(visited[i][j]);
		  }
		  System.out.println();
	  }*/
	  
      return value;  // DUMMY CODE TO GET IT TO COMPILE

   }
   
   private boolean searchPath(boolean[][] maze_dat,int row,int col,int end_row,int end_col,int[][] visited){
	   boolean solved = false;
	   System.out.println("Start position is:"+row+" "+col);
	   System.out.println("Exit position is:"+end_row+" "+end_col);
	   System.out.println("the height and width are:"+maze_dat.length+" "+maze_dat[0].length);
	   if(valid(maze_dat,row,col,visited)/*maze_dat[row][col] == FREE && visited[row][col]!=1*/){
		   visited[row][col] = 1;
		   if((row==end_row) && (col==end_col)){
			   solved = true;
		   }
		   else{
			   solved = searchPath(maze_dat,row+1,col,end_row,end_col,visited);
			   if(!solved){
				   solved = searchPath(maze_dat,row,col+1,end_row,end_col,visited);
			   }
			   if(!solved){
				   solved = searchPath(maze_dat,row-1,col,end_row,end_col,visited);
			   }
			   if(!solved){
				   solved = searchPath(maze_dat,row,col-1,end_row,end_col,visited);
			   }
		   }
		   if(solved){
			   iter.add(new MazeCoord(row,col));
		   }
	   }
	   return solved;
   }
   private boolean valid (boolean[][] maze_dat,int row, int column,int[][] visited) {

	      boolean result = false;
	 
	      // check if cell is in the bounds of the matrix
	      if (row >= 0 && row < maze_dat.length &&
	          column >= 0 && column < maze_dat[0].length)

	         //  check if cell is not blocked and not previously tried
	         if (maze_dat[row][column] == false && visited[row][column] == 0)
	            result = true;

	      return result;

	   }  // method vali
   private static boolean[][] getPadded(boolean[][] padded_maze,boolean[][] maze_data){
	   for(int i=0;i<padded_maze.length;i++){
		   for (int j=0;j<padded_maze[0].length;j++){
			   if(i<1 ||j<1){
				   padded_maze[i][j] = WALL;
			   }
			   else if(i>maze_data.length || j>maze_data[0].length){
				   padded_maze[i][j] = WALL;
			   }
			   else{
				   padded_maze[i][j] = maze_data[i-1][j-1];
			   }
		   }
	   }
	   return padded_maze;
   }

}