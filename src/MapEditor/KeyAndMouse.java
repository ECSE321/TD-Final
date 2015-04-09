package MapEditor;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
/**
 * KeyAndMouse class. This class is responsible of mouse interactions. (Looked up in java tutorials)
 * @author AR
 *
 */
public class KeyAndMouse implements MouseMotionListener, MouseListener{

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		  
		
		TileSelector.mouseOnTile(e.getButton());
		
		  
	     // Map.mouseOnTile(e.getButton());   //watch out for graphics
		
		
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {
		
		int offset1=((GameDisplay.size.width - MapEditor.windWidth) / 2);
		int offset2=((GameDisplay.size.height - (MapEditor.windHeight) - (GameDisplay.size.width - MapEditor.windWidth) / 2));
		//clarification: mouse point was calibrated to avoid offsets from window borders
		MapEditor.mousePoint = new Point(e.getX() - offset1, e.getY()- offset2);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}




	

}
