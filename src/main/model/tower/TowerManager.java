package main.model.tower;

import java.util.LinkedList;




public class TowerManager {

	private LinkedList<Tower> towers;
	

   public TowerManager() {
	   towers = new LinkedList<Tower>();
	
   }

   public void addTower(Tower t) {
		towers.add(t);
	}
	
	public void removeTower(Tower t) {
		towers.remove(t);
	}
	
	public Tower getTower(Tower t){
		
		int index = towers.indexOf(t);
						
		return towers.get(index);
		
		
	}
	
	public LinkedList<Tower> getTowersList(){
		
		return (LinkedList<Tower>) towers.clone();
	}
	
}
