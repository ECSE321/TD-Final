package main.model.critter;
//Average Critter

public class NormalCritter extends Critter{
	
	public NormalCritter(int level) {
		
		super(level,50+(level*10),50+(level*10),50+(level*10),10);
		//level,reward,hitPoints,strength,speed,armored
		/*
			reward = 50+(level*10);
			hitPoints = 50+(level*10);
			strength = 50+(level*10);
			speed = 10;
			Armored = false;
		*/
		
	}
		
	
	
}
