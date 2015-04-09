package main.model.tower;
/**
 * @author Luis Enrique Gallet Zambrano
 *
 */ 

import main.model.Vector2D;
import main.model.Observable;

 

public abstract class Tower extends Observable {
    
    protected Vector2D position;

    //Attributes shared by the subclasses
    private int range;
    private int refundValue;
    private int power;
    private int rateOfFire;
    private int buyingCost;
    private int level; 
    private int upgradeCost;

    
    protected boolean attacked = false;
    protected boolean upgraded = false;
    public int enemmysKilled;
     
     
     
    public Tower(int defaultRange, int defaultRefundValue, int defaultPower, int defaultRateOfFire, int buyingCost, int level, int upgradeCost, Vector2D position)
    {
        this.setRange(defaultRange);
        this.setRefundValue(defaultRefundValue);
        this.setPower(defaultPower);
        this.setRateOfFire(defaultRateOfFire);
        this.setBuyingCost(buyingCost);
        this.setLevel(level); 
        this.setUpgradeCost(upgradeCost);
        this.position = position; 
       
         
             
    }
    
   
     
   
     
     
    //Methods shared by the subclasses
     
    /*
     * The upgrade method checks if the User has enough coins to upgrade the Tower.
     * The coins and most of the upgrades are a function of the present tower level, 
     * which starts at 1 by default and increases by one with each call to the method.
    */
    public void upgrade()
    {
       
            
                setRange(getRange()+50);
                setRefundValue((upgradeCost*level)/3);
                setPower(getPower()*(2*level));
                setRateOfFire(getRateOfFire()*(level));
                setLevel(level + 1);
                
                /*
                 * notify observer that tower has upgraded
                 */
                upgraded =true;
                notifyObservers("upgrade");
          
            
    
        
    }
    
    
    public int attack(){
       
               
    	notifyObservers("attack");
             
        return getPower();  
 
    }
    
    /*
     * the attacked attribute needs to be reset to its default value so it can be used again.
     */
    
    public boolean isBeingAttacked(){
    	
    	if(attacked == true){
    		this.attacked = false;
    		return true;
    	}
        
        return false;
    }
 
    /*
     * the upgraded attribute needs to be reset to its default value so it can be used again.
     */
    
    public boolean hasUpgraded(){
    	if(upgraded == true){
    		this.upgraded = false;
    		return true;
    	}
        
        return false;
    }
     
     /*
      * Amount of critters killed
      */
    public boolean killed(){
        
    	enemmysKilled ++;
        
    	return true;
    }
     
    //Setters and getters to initialize and edit the attributes for each subclass
    public int getRange() {
        return range;
    }
 
    public void setRange(int range) {
        this.range = range;
    }
 
    public int getRefundValue() {
        return refundValue;
    }
 
    public void setRefundValue(int refundValue) {
        this.refundValue = refundValue;
    }
 
    public int getPower() {
        return power;
    }
 
    public void setPower(int power) {
        this.power = power;
    }
 
    public int getRateOfFire() {
        return rateOfFire;
    }
 
    public void setRateOfFire(int rateOfFire) {
        this.rateOfFire = rateOfFire;
    }

	public int getBuyingCost() {
		return buyingCost;
	}

	public void setBuyingCost(int buyingCost) {
		this.buyingCost = buyingCost;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpgradeCost() {
		return upgradeCost*level;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}
	
	public Vector2D getPosition() {
		return new Vector2D(position.getX()+12, position.getY()+12);
	}
 
}
