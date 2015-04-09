package main.model.critter;

public class TankCritter extends Critter{

	public TankCritter(int level) {
		
		super(level,50+(level*20),50+(level*20),50+(level*20),5);
		
		/*
			reward = 50+(level*20);
			hitPoints = 50+(level*20);
			strength = 50+(level*20);
			speed = 5;
			armored = true;
		*/
	}

}
