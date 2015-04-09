package main.model.critter;
import java.util.LinkedList;




public class CritterManager {

	private LinkedList<Critter> critters;
	

	   public CritterManager() {
		   critters = new LinkedList<Critter>();
		
	   }

	   public void addCritter(Critter c) {
			critters.add(c);
		}
		
		public void removeCritter(Critter c) {
			critters.remove(c);
		}
		
		public Critter getCritter(Critter c){
			
			int index = critters.indexOf(c);
							
			return critters.get(index);
			
			
		}
		
		public LinkedList<Critter> getCrittersList(){
			if(critters.isEmpty()) {
				return new LinkedList<Critter>();
			}
			return (LinkedList<Critter>) critters.clone();
		}
		

}
