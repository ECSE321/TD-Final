package main;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

import javax.swing.SwingUtilities;

import MapEditor.GameDisplay;
import MapEditor.pathCreator;
import main.model.GameLogic;
import main.model.Vector2D;
import main.model.map.Map;
import main.model.map.Tile;
import main.view.NewJFrame;

public class Driver implements Runnable{
	
	

	 static boolean done;
	 static List<Vector2D> path = new ArrayList<Vector2D>();
     Thread thread=new Thread(this);
    
     public Driver()
     {
    	thread.start();
     }
 
     
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		GameDisplay gd = new GameDisplay(latch);
		
		
		try {
	      
	        latch.await();
	    } catch (InterruptedException e) {
	       
	    }
		
		path=gd.pathVector;
		
		pathCreator pc = new pathCreator(new File("src/main/mapsaves/newMap.txt"));
		final int[][] binaryMap = pc.arrayMap;
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				
				
				Map map = new Map();
				
				map.tracePath(path);
				map.addLandscape(binaryMap);
		
				
			/*
			for(int i=0; i<=400; i=i+50)
				{
					for(int j=0; j<=400; j=j+50)
					{
						Tile til =new Tile(new Vector2D(i, j));
						
						for(int k=0; k<path.size(); k++)
							
						{
							
							Vector2D tocompare=path.get(k);
							 
							if((tocompare.getX() != i && tocompare.getY() !=j) )
							{
								map.addTile(til);
								
							}
							
							
							
						}
						
					}
				}
				*/
			
					
				
				
				
				/*
				 * END TESTING
				 */
			
				
				
				final GameLogic model = new GameLogic(map);
				
				final NewJFrame frame = new NewJFrame(model);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.validate();
				
				model.addView(frame);
				
				Timer timer = new Timer();

				timer.scheduleAtFixedRate(
				    new TimerTask()
				    {
				        public void run()
				        {
				            model.updateFrame();
				            frame.mapPanel.repaint();
				            frame.mapPanel.revalidate();
				        }
				    },
				    0,
				    50);
			}
	});
	
	}
	@Override
	public void run() {
		
		
		

	}
}
