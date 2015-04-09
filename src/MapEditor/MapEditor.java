package MapEditor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;

/**
 * 
 * @author Andoni Roman
 * @version 1.0
 * Window class. This class is responsible of loading texture images and tracing the initial blank map.
 */
public class MapEditor extends JPanel implements ActionListener, Runnable //set as a runnable
{  
	
	public static Point mousePoint =new Point(0,0);    //location of the mouse on the screen 
	
	//constants and instantiations
	public static Image grass;       
	public static Image path;
	public static Image creeper;
	public static int windWidth, windHeight;
	public static boolean isFirst=true;
	public static Map map;
	public static MapLoad mapLoad;   //loading map
    public boolean newORsave;
    public JButton play;
	public JButton restart;
	Thread thread= new Thread(this); //thread creation
	public static boolean allowedToEdit;
	public static boolean allowedToStart=false;
	GameDisplay disp;
	static String userDefinedMapSize;
	//public boolean editMode;
	/**
	 * Constructor for Editor class (implemented as Thread)
	 */
	public MapEditor(GameDisplay disp, boolean newORsave)
	{	
		this.newORsave=newORsave;
		this.addMouseListener(new KeyAndMouse());    //will extend KeyAndMouse methods (used for user interaction)
		this.addMouseMotionListener(new KeyAndMouse());
		initialize();
		thread.start();
		this.disp=disp;
		
	}
	
	
	
	/**
	 * Initialize method. This method initializes the map and loads+filters images from the img folder. The images are
	 * cropped to suit any tile size. TO NOTE: as tile size is increased, pixelation will be obvious. This can be fixed by
	 * creating a texture with higher resolution. For testing purposes, textures were downloaded.
	 * FilteredImageSource is called with provided texture file directories as well as CropImage Filter, which crops the image to
	 * tile size respectably (in this case, tileSize is 64, thus 64/2=32 by x and y components)
	 * 
	 * NOTE2: initialize has 2 cases. If in Game Display LoadButton is clicked, initialize loads a presaved
	 * existing map from a text file. If NewMap button is clicked, initialize creates a new button
	 
	 * NOTE3:(Logic)
	 *
	 *The editor Works in 2 Modes. First mode, if user clicks create new map. This will allow tile selection to be on
	 *Once path is done, user clicks save and play button which starts the game logic and creeter movement.
	 *The second mode allows the user to load map from previously saved file by using the MapLoad class. The editing
	 *is also allowed in case of incomplete map. Once path has been completed, 
	 *
	 *NOTE4: Restart button allows User to completely restart map building.
	 */
	public void initialize()
	{
		
	    grass = new ImageIcon("src/main/view/img/landscape.png").getImage();   //getting texture from img folder
	    path  = new ImageIcon("src/main/view/img/path.png").getImage();//getting texture from img folder 
	   // this.setLayout(new BorderLayout());
	    
	    //Mode1: Loading of previously build map + intermediate edit + Saving
	    
	    
	    if(newORsave==true)
	    {
	    	
	  
	    allowedToEdit=true;
	    this.setLayout(null);
		play=new JButton("Save And Play");
		restart = new JButton("Restart");
	    map=new Map();
		mapLoad=new MapLoad(); 
	    mapLoad.loadMap(new File("src/main/mapsaves/newMap.txt"));  //if load save button is clicked, load from save file
	    
	    //Making buttons and issuing listeners
	    play.setBounds(200,600,100, 100);
	    this.add(play,BorderLayout.SOUTH);
	    
		restart.setBounds(400,600,100, 100);
		this.add(restart, BorderLayout.SOUTH);
		
		play.addActionListener(this);
		restart.addActionListener(this);
		
	    }
	    
	    
	    //Mode2: Creation of new Map + Saving
	    else
	    {
	  
	    allowedToEdit=true;
	    this.setLayout(null);
	    play=new JButton("Save And Play");
	    restart = new JButton("Restart");
	   
	    
	
	    
	    //Message for USER for map building guidelines
	    
		JOptionPane.showMessageDialog(null, "BEFORE YOU MAKE YOUR OWN MAP"+"\n"+"Here are the rules:"+"\n"+
				"1.No loops in path are allowed"+"\n"+
				"2.In order to select a tile, click in the center of the Tile"+"\n"+
				"3.Path must be unidirectional (no tiles are allowed to be grouped together)"+"\n"+
				"4.ONE WAY ONLY! Enjoy :)");
		
		
	    map=new Map();
	    play.setBounds(200,600,100, 100);
	    this.add(play,BorderLayout.SOUTH);
	    
		restart.setBounds(400,600,100, 100);
		this.add(restart, BorderLayout.SOUTH);
	    
		play.addActionListener(this);
		restart.addActionListener(this);
		
		
		
	    }
	}
	    
		
		
	/**
	 * paint method. This method traces the actual blank map (derived from JComponent)
	 */
	
	public void paintComponent(Graphics graphics)                  //drawer method
	{
		super.paintComponent(graphics);
		
		
		if(isFirst)
		{
			windWidth=getWidth();
			windHeight=getHeight();
			isFirst= false;
		}
		
		map.traceMap(graphics);//actually drawing the Map	
		
	}
	
	
	/**
	 * actionPerformed method. This method is used with JButtons.
	 */
	public void actionPerformed(ActionEvent e)
	{
		JButton clickedButton = (JButton) e.getSource();                //neutral button (to be clicked)
        String buttonText = clickedButton.getText();
        
        //Refresh panel, issue blank map if Restart is clicked
        if (buttonText.equals("Restart"))
        {
           map=new Map();
        }
        
        //Save map in a file with MapSaver, allow game console to appear.
        if (buttonText.equals("Save And Play") || buttonText.equals("Play"))
        {   
        	MapSaver ms = new MapSaver();
        	ms.save();                        //Map saving into a 2D array with 1's as path and 0's as landscape
        	//WILL LAUNCH THE MAIN GAME
        	allowedToStart=true;
        	disp.setup();	
        }
        
        
		
	}
	
	
	/**
	 * run method for Thread
	 */
	public void run()
	{
		while(true)
		{	
			
		 repaint();
		
		try
		  {
		    Thread.sleep(15); //Set interval between graphical changes
		  }
		      catch(Exception e)
			  {
		    	  
			  }
			
	     }
		
	}
	
	
	
	
}
	

	
	

	


