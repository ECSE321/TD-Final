package main.model.map;

import java.util.ArrayList;
import java.util.List;

import main.model.Vector2D;

public class Map {
	private List<Tile> tiles;
	private Tile selectedTile;
	private Tile firstTile;
	private Tile lastTile;
	
	public Map() {
		this.tiles = new ArrayList<Tile>();
	}
	
	public Tile getTileAt(Vector2D v) {
		for(Tile t : tiles) {
			if(t.isHere(v)) return t;
		}
		return null;
	}
	
	public List<Tile> getTilesList() {
		return tiles;
	}
	
	public Tile getFirstTile() {
		return firstTile;
	}
	
	public Tile getLastTile() {
		return lastTile;
	}
	
	public void selectTile(Vector2D v) {
		Tile t = getTileAt(v);
		if(!t.isPath()) {
			selectedTile = t;
		}
	}
	
	public Tile getSelectedTile() {
		return selectedTile;
	}
	
	/*
	 * FOR BUILDING MAP
	 */
	public void addTile(Tile t) {
		tiles.add(t);
	}
	
	public void setFirstTile(Tile t) {
		firstTile = t;
	}
	
	public void setLastTile(Tile t) {
		lastTile = t;
	}
	
	public void tracePath(List<Vector2D> path) {
		Vector2D tilePosition;
		for(Vector2D v : path) {
			tilePosition = v;
			Tile t = new Path(tilePosition);
			addTile(t);
			if(firstTile == null) {
				setFirstTile(t);
			} else {
				Tile previousTile = tiles.get(tiles.size()-2);
				((Path)previousTile).setNext(t);
			}
		}
		setLastTile(tiles.get(tiles.size()-1));
	}
}
