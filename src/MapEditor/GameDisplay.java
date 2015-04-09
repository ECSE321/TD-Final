package MapEditor;
import main.model.Vector2D;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * ISE321
 * @author R
 * @version 1.1
 * MapObserver Class. Responsible of main window where the game will be running. This class contains the main method.
 * TO NOTE: As for the first assignment, it was decided to implement the graphics to make sure that maps are being built properly. 
 *
 *
 *NOTE2: This class is the Observer class that displays the map and notifies changes in the map. For this example,
 *the class notifies if a tile was added and at what position if was added. 
 */

public class GameDisplay extends JFrame implements ActionListener, Runnable
{
	
	public static CardLayout layout = new CardLayout();
	public static Dimension size = new Dimension(800,750); //setting dimension of the frame (variable)
	public static boolean clicked;
	public JButton loadmap;
	public JButton play;
	public JButton newmap;
	public boolean launchSave;
	public ArrayList<Vector2D> pathVector = new ArrayList<Vector2D>();
	public static boolean doneEditing;
	Thread th = new Thread(this);
	CountDownLatch latch;
	/**
	 * Constructor for main Window where the game will be running.
	 */
	public GameDisplay(CountDownLatch latch)
	{
	    this.latch=latch;
		setLocationRelativeTo(null);
		JLabel mainLabel = new JLabel("A Tower Defence Game (Beta 1.0)"); 
		mainLabel.setFont(new Font("TrebuchetMS", Font.BOLD, 14));
        mainLabel.setBounds(280,500, 400, 20);  
        this.add(mainLabel);
        setResizable(false);
       
		setSize(size);
		setTitle("A Tower Defence Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //exit if closed
		visible();  //Part of visibility of the map 
		th.start();
		
		
	}
	
	/**
	 * Method that makes the game screen visible and instantiates and adds all necessary buttons
	 */	
	public void visible()
	{
	  
	  launchSave=false;
	  JPanel dialogbox = new JPanel();
	  dialogbox.setLayout(null);
	  dialogbox.setBackground(Color.lightGray);
	  loadmap= new JButton("Load Map"); 
	  newmap =new JButton("New Map");
	  loadmap.setFont(new Font("Arial", 1, 12));
	  newmap.setFont(new Font("Arial", 1, 12));
	  
	
	  loadmap.setBounds(200,300,100, 100);
	  dialogbox.add(loadmap, BorderLayout.CENTER);
	  newmap.setBounds(500,300, 100, 100);
	  dialogbox.add(newmap, BorderLayout.CENTER);
	  
	  this.add(dialogbox);
	  
	  loadmap.addActionListener(this);
	  newmap.addActionListener(this);
	
	  
	  setVisible(true);
	  
	}

	
	/**
	 * actionPerformed method. This method sends the game into 2 modes. Either user can load a map
	 * or create a new map. In each case, map editor will be called with different allowances concerning
	 * editing.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		 JButton clickedButton = (JButton) e.getSource();                //neutral button (to be clicked)
         String buttonText = clickedButton.getText();
         
         if (buttonText.equals("Load Map"))                              //load existing map
         {	
        	 launchSave=true;
             this.getContentPane().removeAll();
             MapEditor medit= new MapEditor(this,launchSave);   //launchSave boolean determines if map editor is loaded
             this.getContentPane().add(medit); 							//with existing map or new map
     		 setVisible(true);
     		 
     		
     		
         }
         
         
         else if(buttonText.equals("New Map"))                            //create a new map
         {
        	 
        	 launchSave=false;
        	 this.getContentPane().removeAll();
             MapEditor medit= new MapEditor(this,launchSave);            //launchSave boolean determines if map editor is loaded
             this.getContentPane().add(medit); 							 //with existing map or new map
     		 setVisible(true);
     		 
     		
     		 
         }
         	
	}
	
	/**
	 * setup method. This method is called when the game console is required to be launched.
	 */
	public void setup()
	
	{       
			
		pathCreator pc = new pathCreator(new File("src/main/mapsaves/newMap.txt"));
    		
			pathVector=pc.maz.returnList();
			doneEditing=true;
			this.setVisible(false);
			
		    latch.countDown();
			
			 System.out.print(doneEditing+"");
			
			
	}
	
	public boolean isReady()
	{
		return doneEditing;
	}

	@Override
	public void run() {
		/*synchronized (this) {
		        while(doneEditing){
				if(doneEditing==true){
					doneEditing=true;
					notifyAll();
				}
		        }
				
			
		}*/
		
		
		}
		
	}
	
		 

		
	
	
	
	
	
	
	

