package MapEditor;



/**
 * Point class.  provides Point objects that are used in
 * ShortestPathCalculator class //DIFFERENT FROM TILE CLASS *But could be joined later 
 * 
 * @author R
 * @version 1.0
 * 
 */

public class Pt {

	private int x;
	private int y;

	public Pt(int y, int x) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean equals(Object o) {
		if (o instanceof Pt) {
			Pt point = (Pt) o;
			if (point.getX() == this.getX() && point.getY() == this.getY()) {
				
				return true;
				
			}
		}
		return false;
	}

	
	
	
	public String toString() {
		return "(x=" + x + " y=" + y+")";
	}

}