package main.model.map;

import main.model.Vector2D;

public class Path extends Tile{
	private Tile nextTile;
	
	public Path(Vector2D v) {
		super(v);
	}

	public Tile getNext() {
		return nextTile;
	}
	
	public void setNext(Tile nextTile) {
		this.nextTile = nextTile;
	}
}
