package main.model.map;

import main.model.Vector2D;
import main.model.tower.Tower;

public class Tile {
	Vector2D position;
	Tower tower;
	int size = 50;
	
	public Tile(Vector2D tilePosition) {
		position = tilePosition;
	}

	public boolean isPath() {
		if (this instanceof Path) {
			return true;
		} else {
			return false;
		}
	}
	
	public Vector2D getPosition() {
		return position;
	}
	
	public Tower getTower() {
		return tower;
	}
	
	public void addTower(Tower tower) {
		this.tower = tower;
	}
	
	public void removeTower(Tower t) {
		this.tower = null;
	}
	
	public boolean isHere(Vector2D v) {
		if(position.getX() <= v.getX()
			&& position.getX() + size > v.getX()
			&& position.getY() <= v.getY()
			&& position.getY() + size >= v.getY()) {
			return true;
		} else {
			return false;
		}
	}
	
	public Vector2D getCenterDrawPosition() {
		return position.getVectorAddition(new Vector2D(12, 12));
	}
}
