package MapEditor;

import java.awt.*;
import javax.swing.*;
/**
 * Tile class. This class is responsible of tile objects placed on the map.
 * @author Andoni Roman
 * @version 3.0
 *
 */
public class Tile extends Rectangle    //extends java's geometric Rectangle class to uses methods such as setBounds
{
	
	

	public  int grassID;
	public   int pathID;
	public static int clickedID=0;       //for mouse use
	public static boolean startSelected=false;
	public static boolean finishSelected=false;
	
	/**
	 * Tile constructor
	 * @param posX
	 * @param posY
	 * @param width
	 * @param height
	 * @param grassID
	 * @param pathID
	 */

	public Tile(int posX, int posY, int width, int height, int grassID, int pathID) //constructor
	{
		setBounds(posX, posY, width, height);  //setting bounds of each Tile
		this.grassID=grassID;			   //individual ID's
		this.pathID=pathID;
	}
	
	
	/**
	 * trace method. This method is being called in maps to trace out individual tiles on X and Y positions of the map. 
	 * @param graphics
	 */
	public void traceTileGrass(Graphics graphics)       //method that draws every rectangle and texture values 
	{    
		
		
		graphics.drawImage(MapEditor.grass, x, y, width, height, null); //drawing grass textures (for blank map)
		graphics.drawRect(x, y, width, height); //drawing GRID in order to better visualize the map
		
		
	}
	
	public void traceTilePath(Graphics graphics)       //method that draws every rectangle and texture values 
	{    
		
		
		graphics.drawImage(MapEditor.path, x, y, width, height, null); //drawing grass textures (for blank map)
		graphics.drawRect(x, y, width, height); //drawing GRID in order to better visualize the map
		
		
			
	}
	
	
	
	
	

	
}


	
	


