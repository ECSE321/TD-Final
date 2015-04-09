package main.model.critter;

public class FastCritter extends Critter{

	public FastCritter(int level) {
		super(level,50+(level*20),50+(level*10),50+(level*10),15 + (level/3));
		
		/*
		reward = 50+(level*20)
		hitPoints = 50+(level*10)
		strength = 50+(level*10)
		speed = 15 + (int)(level/3)
		armored = false
		*/
	}

}
