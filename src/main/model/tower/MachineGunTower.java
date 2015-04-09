package main.model.tower;

import main.model.Vector2D;
/**
 * @author Luis Gallet Zambrano
 *
 */
public class MachineGunTower extends Tower {


	public MachineGunTower(Vector2D position) {
		
		//Level 1 characteristics of the MachineGunTower
		super(25,125,10,10,250,1,1500, position );
		//range,refund value, power, rate of fire, buying cost, level, upgrading cost, position

	}
}
