package MapEditor;

import java.awt.*;
import java.util.Observable;
/**
 * 
 * @author R
 * @version 5.0
 * Map class. This class builds a map of  specified dimentions, made out of Tile objects.
 *
 */
public class Map //extends Rectangle //For assignemnt 2, Map is Observable.
{
	
	//defining physical properties of map *subject to change*
	
	
	
	public int mapWidth=8;
	public int mapHeight=8;
	public int tileSize=TileVals.tileSiz;
	
	public static int clickedID=0;       //for mouse use
	public static boolean startSelected=false;
	public static boolean finishSelected=false;
	
	
	//Instantiating Tile object.
	public Tile[][] tile;
	
	/**
	 * Map constructor
	 */
	public Map()
	{
		
		build();
		
	}
	
	
	/**
	 * initialization method. This method creates tiles at X and Y coordinates of the map with appropriate size and texture IDs
	 */
	public void build()
	{
		tile=new Tile[mapHeight][mapWidth];	                    //Initializing Tile object
		
		for(int posY=0; posY<tile.length; posY++)				//Initializing blocks within the map (by X and Y locations)
		{
			for(int posX=0; posX<tile[0].length; posX++)
			{
				tile[posY][posX] = new Tile(posX * tileSize, posY * tileSize, tileSize, tileSize, TileVals.grass, TileVals.path); //Dont forget to chose the offset
				//Geometric allocation of each Tile.
				
			}
			
		}
		
		
	}
	
	/* Map geometrics: 
	 * ------------------------> X
	 * |
	 * |
	 * |
	 * |
	 * |
	 * |
	 * |
	 * ^
	 * Y
	 */

	/**
	 * drawer method. This method traces each tile by calling trace method of Tile class. 
	 * @param graphics
	 */
	public void traceMap(Graphics graphics)
	{
		for(int posY=0; posY<tile.length; posY++)
		{
			for(int posX=0; posX<tile[0].length; posX++)
			{
				
				//drawing grass if ID from scanned file=0
				if(tile[posY][posX].grassID==TileVals.grass)
				{
				tile[posY][posX].traceTileGrass(graphics); 
				}
				//drawing path if ID from scanned file=1
			    if (tile[posY][posX].grassID ==TileVals.path)
				{
				tile[posY][posX].traceTilePath(graphics);	
				}
				
				
				
			}
		}
		
		
}
}
	
	
	
		
	
	
	


