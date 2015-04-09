package main.model.tower;

import main.model.Vector2D;

/**
 * @author Luis Gallet Zambrano
 *
 */
public class MortarTower extends Tower {

	

	public MortarTower(Vector2D position) {
		
		//Level 1 characteristics of the MortarTower
		super(60,175,175,1,350, 1, 1800, position);
		//range,refund value, power, rate of fire, buying cost, level, upgrading cost, position


	}
	
}
