package main.model;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;

import main.model.critter.Critter;
import main.model.critter.CritterManager;
import main.model.tower.SpellTower;
import main.model.tower.Tower;
import main.model.tower.TowerManager;

public class AttackThread extends Thread {
	//Constants:
	int MIN_SPEED = 5;
	int REFRESH_RATE = 100;
	int DISTANCE_MODIFIER = 10;
	int WAIT_DELAY = 500; //Delay in ms that the thread pauses when it doesn't find towers
	
	private CritterManager critterManager;
	private TowerManager towerManager;
	private Player player;
	
	//This will tick every time the towers are checked
	int tick =0;
	
	public AttackThread(CritterManager cm, TowerManager tm, Player p){	
		this.critterManager = cm;
		this.towerManager = tm;
		this.player = p;
	}


	public void run() {

		//Keep track of the towers
		LinkedList<Tower> towerList = new LinkedList<Tower>();
		LinkedList<Critter> critterList = new LinkedList<Critter>();
		Hashtable<Tower,LinkedList<Critter>> aimed = new Hashtable<Tower,LinkedList<Critter>>();



		//this thread will run during each level
		while(true){
			//First look for any deadCritters  and update CritterManager and Player's account accordingly
			critterList = critterManager.getCrittersList();
			for (Iterator<Critter> iterator = critterList.iterator(); iterator.hasNext();) {
			    Critter currentCritter = iterator.next();
			    if(currentCritter.getHitPoints()<=0){
					player.addGold(currentCritter.getReward());
					critterManager.removeCritter(currentCritter);
				}
			}
			
			critterList = critterManager.getCrittersList();
			//Once all criters are dead, stop the thread (make sure to wait some time)
			if((critterList.size()<1 || player.getGold()<0) && tick>15){
				return;
			}
			
			//If no towers are set, no need to attack, so wait for the user to place any towers
			while(towerManager.getTowersList() == null){
				try {
					//Make sure that this is not a spin lock
					Thread.sleep(WAIT_DELAY);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			//Find the LinkedList of towers and update the hashtable accordingly
			if(!towerList.equals(towerManager.getTowersList())){
				towerList = towerManager.getTowersList();
				for(Tower currentTower:towerList){
					//Traverse the LinkedList of towers, add the tower to the hashtable if it isn't in it already.
					if(!aimed.containsKey(currentTower)){
						aimed.put(currentTower,new LinkedList<Critter>());
					}
				}
				//Remove towers not on the board anymore
				Enumeration<Tower> registeredTowers = aimed.keys();
				while(registeredTowers.hasMoreElements()){
					Tower currentTower = registeredTowers.nextElement();
					if(!towerList.contains(currentTower)){
						aimed.remove(currentTower);
					}
				}
			}
			
			//Cleans the critter list of any dead critters
			for(Tower currentTower:towerList){
				LinkedList<Critter> aimedCritters = aimed.get(currentTower);
				while(aimedCritters.size()>0 && aimedCritters.remove(null)){
					
				}
			}
			critterList = critterManager.getCrittersList();

			for(Tower currentTower:towerList){
				Vector2D towerPosition = currentTower.getPosition();
				for(Critter currentCritter:critterList){
					Vector2D critterPosition = currentCritter.getPosition();
					//Compute the displacement between the tower and the critter:
					Vector2D displacement = towerPosition.getDisplacementVector(critterPosition);
					int distance = displacement.getMagnitude()/DISTANCE_MODIFIER;
					//Add to the LinkedList if a critter is found to be in range
					if(distance<currentTower.getRange()){
						if(aimed.get(currentTower)==null){
							LinkedList<Critter> towerCritters = new LinkedList<Critter>();
							towerCritters.add(currentCritter);
						//  aimed.replace(currentTower, towerCritters);
						}
						else if(!aimed.get(currentTower).contains(currentCritter)){
							LinkedList<Critter> towerCritters = aimed.get(currentTower);
							towerCritters.add(currentCritter);
						//	aimed.replace(currentTower, towerCritters);
						}
					}

				}
			}
			
			

			for(Tower currentTower:towerList){
				//If the tower has had time to "reload"
				int reloadTime = (int)((1.0/currentTower.getRateOfFire())*10);
				if(tick%reloadTime==0){
					Vector2D towerPosition = currentTower.getPosition();
					//Get the LinkedList of critters seen by the tower
					LinkedList<Critter> towerCritters = aimed.get(currentTower);
					for(Critter currentCritter:towerCritters){
						Vector2D critterPosition = currentCritter.getPosition();
						//Compute the displacement between the tower and the critter:
						Vector2D displacement = towerPosition.getDisplacementVector(critterPosition);
						//If the critter is in range and not dead
						
						int distance = displacement.getMagnitude()/DISTANCE_MODIFIER;
						//System.out.println("Distance: " + distance);
						
						if(distance<currentTower.getRange() && currentCritter.getHitPoints()>0){
							System.out.println("HIT");
							//Notify observers
							int currHP = currentCritter.takeDamage();
							if(currentTower instanceof SpellTower){
								//If the tower is a spellTower, decrease the speed of the critter
								int power = currentTower.getPower();
								int currSpeed = currentCritter.getSpeed();
								currSpeed -= (int)((float)currSpeed*10.0/(float)power);
								if(currSpeed<MIN_SPEED){
									currentCritter.setSpeed(MIN_SPEED);
								}
								else{
									currentCritter.setSpeed(currSpeed);
								}
							}
							else{
								//Compute the new health of the critter
								currHP -= computeDamage(currentTower,currentCritter);
								currentCritter.setHitPoints(currHP);
							}

							break;
						}
					}	
				}

			}

			//Make the thread sleep for a while before checking again (200 is a good value)
			tick++;
			try {
				Thread.sleep(REFRESH_RATE);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public int computeDamage(Tower t, Critter c){
		//TODO: implement better damage computation (x10 for testing)
		//System.out.println("Power: "+ t.getPower() + " Strength: " + c.getStrength() + " Damage: " + t.getPower()/c.getStrength()*10);
		return (int)((float)t.getPower()/(float)c.getStrength()*10);
	}
}
