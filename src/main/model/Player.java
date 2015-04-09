package main.model;

public class Player {
	private int health;
	private int gold;
	private int level;
	
	public Player() {
		this.health = 100;
		this.gold = 2000;
		this.level = 1;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getGold() {
		return gold;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setGold(int gold) {
		this.gold = gold;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void makePurchase(int gold) {
		this.gold -= gold;
	}
	
	public void addGold(int gold) {
		this.gold += gold;
	}
}
