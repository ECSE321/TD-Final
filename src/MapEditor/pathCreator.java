package MapEditor;

import java.awt.Window;
import java.io.*;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Andoni Roman
 * pathCreator class 
 * This class takes saved map from a file, turns it into a 2D array, finds the initial and final 
 * values in the 2D array and calls the PAth Sequencer to issue points in order for correct path 
 * displaying on the game panel.
 */
public class pathCreator
{
	
	static File mapload;
	int[][] arrayMap = new int[MapEditor.map.mapHeight][MapEditor.map.mapWidth];
	Pt startPoint;
	Pt endPoint;
	PathSequencer maz;
	
	
	public pathCreator(File mapload)
	{
		this.mapload=mapload;
		loadMap();
	}
	
	
  
  
  public void loadMap()
  {
	  try
	  
	  
	  {
		  Scanner fileScan= new Scanner(mapload);
		  
		  for(int y=0; y<MapEditor.map.mapHeight; y++)
		  {
			  for(int x=0; x<MapEditor.map.mapWidth; x++)
			  {
				   arrayMap[y][x] = fileScan.nextInt();
				   
				   System.out.print(" "+ arrayMap[y][x]);
				   
				   if(x==MapEditor.map.mapWidth-1)
				   {
					   System.out.print("\n");
				   }
			  }
			  
		  }
		  
		  fileScan.close();
	  } 
	  
	  
	  
	  catch(Exception e)
	  {
		 // System.out.print("No file found");
	  }
	  
	  
	  compute(arrayMap);
	  
   }
  
  //computes initial and final points of the path of the 2D array
   public void compute(int[][] arrayMap)
   {
	   int counter=0;
	   
	   for(int y=0; y<MapEditor.map.mapHeight; y++)
	   {
		   for(int x=0; x<MapEditor.map.mapWidth; x++)
		   {
			  if(arrayMap[y][0]==1)
			  {
				 counter=0;
				 
				 if(arrayMap[y-1][0]==1)
				 {
					 counter++;
				 }
				 if(arrayMap[y][1]==1)
				 {
					 counter++;
				 }
				 if(arrayMap[y+1][0]==1)
				 {
					 counter++;
				 }
			  }
			 	   
		   } 
	   }
	   
	     if (counter==1)
	     {
	    	 for(int y=0; y<MapEditor.map.mapHeight; y++)
	  	   {
	  		   for(int x=0; x<MapEditor.map.mapWidth; x++)
	  		   {
	  			  if(arrayMap[y][0]==1)
	  			  {
	  				
	  			    startPoint= new Pt(y,x);
	  				System.out.print(startPoint.toString());
	  				break;
	  			  }
			
	  		   }
  
           }
	    	 
         
	     }
	     
 counter=0;
	     
	    
	     
	     for(int y=0; y<MapEditor.map.mapHeight; y++)
		   {
			   for(int x=0; x<MapEditor.map.mapWidth; x++)
			   {
				  if(arrayMap[y][MapEditor.map.mapWidth-1]==1)
				  {
					// counter=0;
					 
					 if(arrayMap[y-1][MapEditor.map.mapWidth-1]==1)
					 {
						 counter++;
					 }
					 if(arrayMap[y][MapEditor.map.mapWidth-2]==1)
					 {
						 counter++;
					 }
					 if(arrayMap[y+1][MapEditor.map.mapWidth-1]==1)
					 {
						 counter++;
					 }
				  }
				  
				  if (counter==1)
				  {
				  if(arrayMap[y][MapEditor.map.mapWidth-1]==1 && counter==1)
	  			  {
					 
	  			    endPoint= new Pt(y, MapEditor.map.mapWidth-1);
	  				System.out.println(endPoint.toString());
	  				break;
	  			  }
				  }
				  
				 
				 	    
			   } 
		   }
	     //in a sequence find points given map and extrema points
	     maz= new PathSequencer(arrayMap, endPoint, startPoint); 
   }
   
   
   
	     
	 
}

	     
	    
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	    
   

  
  
  
  
  
