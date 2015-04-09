package main.model.tower;

import main.model.Vector2D;


/**
 * @author Luis Gallet Zambrano
 *
 */
public class SpellTower extends Tower {

	
	public SpellTower(Vector2D position) {
		
		//Level 1 characteristics of the SpellTower
		super(20,100,5,3, 200,1, 1000, position);
		//range,refund value, power to slow down the critter, rate of fire, buying cost, level, upgrade cost, position
		
	}
}