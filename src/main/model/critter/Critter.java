package main.model.critter;
import main.model.Vector2D;
import main.model.Observable;


public abstract class Critter extends Observable {

	private Vector2D position;
	
	private int level;
	private int reward;
	private int hitPoints;
	private int strength;
	private int speed;
	
	public Critter(int level,int reward, int hitPoints, int strength, int speed){
		this.setLevel(level);
		this.setReward(reward);
		this.setHitPoints(hitPoints);
		this.setStrength(strength);
		this.setSpeed(speed);
		
	}
	
	void move(){
		//TODO: implement move
	}
	
	public int takeDamage() {
		
		notifyObservers("take");
		
		return getHitPoints();
	}
	
	public void stealCoins(){
		
		notifyObservers("steal");
		//return getStrength()*10;
	}
	
	
	
	public Vector2D getPosition(){
		return this.position;
	}
	
	public void setPosition(Vector2D position){
		this.position = position;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	public int getLevel(){
		return this.level;
	}
	
	public void setReward(int reward){
		this.reward = reward;
	}
	public int getReward(){
		return this.reward;
	}
	
	public void setHitPoints(int hitPoints){
		this.hitPoints = hitPoints;
	}
	public int getHitPoints(){
		return this.hitPoints;
	}
	
	public void setStrength(int strength){
		this.strength = strength;
	}
	public int getStrength(){
		return this.strength;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public int getSpeed(){
		return this.speed;
	}
	
	
}
