package MapEditor;
import main.model.Vector2D;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.awt.List;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;



public class PathSequencer
{ 
  private final int TRIED = 3;
  private final int PATH = 7;
  public int row;
  public int column;
  public int finishedrow;
  public int finishedcol;
  int[][] grid;
  ArrayList<Vector2D> points = new ArrayList<Vector2D>();
  Vector2D toadd;
	
  public PathSequencer(int[][] arrayMap, Pt startPoint, Pt endPoint){
	  
	  this.grid=arrayMap;
	  this.row=startPoint.getY();
	  this.column=startPoint.getX();
	  this.finishedrow=endPoint.getY();
	  this.finishedcol=endPoint.getX();
	  
	  trav(row, column);
	  
  }
 

   
   public void trav(int row, int column)
   {
	   if(traverse(row, column)){
			  System.out.print("FOUND");
		  }
	   else{
		   System.out.print("Not found");
	   }
   }
  

   public boolean traverse (int row, int column)
   {
	 //this list will be passed to gameplay logic to draw the map   
	
     
	 boolean done = false;
      
      if (valid (row, column))
      {
    	  grid[row][column] = TRIED;

         if (row == finishedrow && column == finishedcol)
            done = true;  // the maze is solved
         else
         {
       
        	    done = traverse (row+1, column);     // down
            
               if (!done)
                  done = traverse (row, column+1);  // right
               if (!done)
                  done = traverse (row-1, column);  // up
               if (!done)
                  done = traverse (row, column-1);  // left
            }

            if (done)
            	grid[row][column] = PATH;
            toadd=new Vector2D(column*50,row*50);     //*******ADDS current path to the vector
       	    points.add(toadd);
             
         }
      
     
         
         return done;
      }
   
   
 //------------------------------------------------------------
   //  Determines if a specific location is valid.
   //------------------------------------------------------------
   private boolean valid (int row, int column)
   {
      boolean result = false;
 
      // check if cell is in the bounds of the matrix
      if (row >= 0 && row < grid.length &&
          column >= 0 && column < grid[row].length)

         //  check if cell is not blocked and not previously 
         //  tried
         if (grid[row][column] == 1){
        	 result = true;
        	 
        	// toadd=new Vector2D(column*50,row*50);     //*******ADDS current path to the vector
        	// points.add(toadd);
         }
      
        
      
      return result;
      
     
   }
   
   
   public ArrayList<Vector2D> returnList()
   {
	  
    return points;
   
   }
   
}



