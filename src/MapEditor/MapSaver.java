package MapEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 
 * @author Andoni Roman
 * @version 1/0
 *
 * MapSaver class. This class allows to scan the existing map and save it accordingly to tile ID's. If tile is 
 * a grass tile, Saver writes 0 into a 2D array stored in a text file and 1 if Tile ID is path. 
 */
public class MapSaver{
	
	/**
	 * save method. Responsible of map scanning and value saving. 
	 */
	
	public void save()
	{
	try (
            PrintStream output = new PrintStream(new File("src/main/mapsaves/newMap.txt"));
        )
        
        {
        	 for(int y=0; y<MapEditor.map.tile.length; y++)
   		      {
   			  for(int x=0; x<MapEditor.map.tile[0].length; x++)
   			  {
   				  //IF ID=grass , save 0 and TAB 
   				  if (MapEditor.map.tile[y][x].grassID==0)
   				  {
   					  output.print("0"+"\t");
   					  
   				  }
   				  //IF ID=path, save 1 and TAB
   				  if (MapEditor.map.tile[y][x].grassID==TileVals.path)
   				 {
   				   output.print("1"+"\t");
   				 }
   				
   				  //IF reached end of map width, print new line
   				  if(x==MapEditor.map.tile.length+1)
   				  {
   					 output.print("\n"); 
   				  }
   				  

   			  }
   			  
            
            
        }
        	 
        output.close();

    }
	catch(FileNotFoundException e) {

        e.printStackTrace();
    }

}
}
