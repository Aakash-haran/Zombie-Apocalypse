package main.java;

import java.util.ArrayList;
import java.util.List;

public class InputsForZombieApocalypse {
	
	/* This Class can be changed to driver class by taking inputs from user and creating and initiating the game
	 * and the proper error message can be thrown here and re asking the user to provide proper inputs before setting up
	 * instead of Runtime Exception done in ZombieApocalypse.
	 * 
	 * */
	
	public static void main(String args[]) {
		/* Change the inputs here to test various scenarios
		 * and the output is logged in console
		 */
		Position zombiePosition = Position.setNewPosition(2, 2);
		String moves = "RRUDLLRUD";
		List<Position> creaturesPos = new ArrayList<>();
		creaturesPos.add(Position.setNewPosition(2, 3));
		creaturesPos.add(Position.setNewPosition(2, 1));
		creaturesPos.add(Position.setNewPosition(0, 1));
		
		ZombieApocalypse apocalypse = ZombieApocalypse.setUpZombieWorld(4,zombiePosition,creaturesPos,moves);
		apocalypse.startZombieInfection();
		
	}

}
