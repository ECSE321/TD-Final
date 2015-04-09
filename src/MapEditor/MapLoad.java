package MapEditor;

import java.io.*;
import java.util.Scanner;


/**
 * 
 * @author Andoni Roman
 * @version 3.0
 *
 *MapLoad class. Responsible of loading map from .txt file. 
 */
public class MapLoad
{

  /**
   * loadMap method. This method uses Scanner and scans throug heach position of 2D array stored in the text file. 
   * The scanned items are tile ID's. Once scanner, each tile is assigned the necessary ID and passed further to map painter.
   * @param loadpath
   */
  public void loadMap(File loadpath)
  {
	  try
	  
	  
	  {
		  Scanner fileScan= new Scanner(loadpath);
		  
		  for(int y=0; y<MapEditor.map.tile.length; y++)
		  {
			  for(int x=0; x<MapEditor.map.tile[0].length; x++)
			  {
				  MapEditor.map.tile[y][x].grassID = fileScan.nextInt();
			  }  
		  }
		  
		  
		 
		  
		  
		  fileScan.close();
	  } 
	  
	  
	  
	  catch(Exception e)
	  {
		  System.out.print("No file found");
	  }
  }
}
