package test.java;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.java.ApocalypseCodes;
import main.java.Position;
import main.java.ZombieApocalypse;
import main.java.exception.IncorrectPositionException;
import main.java.exception.IncorrectZombieMovement;
import main.java.exception.InsufficientZombieWorldSizeException;

public class ZombieApocalypseTest {
	
	
	@Test
	public void zombieApocalypseExceptionTest() {
		
		String moves = "RRUDLLRUD";
		Position zombiePosition= Position.setNewPosition(2, 1);
		List<Position> creaturesPos = new ArrayList<>();
		creaturesPos.add(Position.setNewPosition(2, 3));
		creaturesPos.add(Position.setNewPosition(2, 1));
		creaturesPos.add(Position.setNewPosition(0, 1));
		
		
		 Assertions.assertThrows(InsufficientZombieWorldSizeException.class, () -> {
			 // grid less than or equal to zero
			 ZombieApocalypse.setUpZombieWorld(0,zombiePosition,creaturesPos,moves);
		  });
		
		 // zombie position at 2,7 which is greater than the world size-4
		Position improperZombiePosition= Position.setNewPosition(2, 7);
		 Assertions.assertThrows(IncorrectPositionException.class, () -> {
			 ZombieApocalypse.setUpZombieWorld(4,improperZombiePosition,creaturesPos,moves);
		  });
		 
		// Zombie moves containing special characters and extra letters
		 String improperMoves= "RRUDLLRUD &A";
		 Assertions.assertThrows(IncorrectZombieMovement.class, () -> {
			 ZombieApocalypse.setUpZombieWorld(4,zombiePosition,creaturesPos,improperMoves);
		  });
		 
		// Creatures position at 2,7 which is greater than world size-4
		 List<Position> ImpropercreaturesPos = new ArrayList<>();
		 ImpropercreaturesPos.add(Position.setNewPosition(2, 7));
		 ImpropercreaturesPos.add(Position.setNewPosition(2, 1));
		 ImpropercreaturesPos.add(Position.setNewPosition(0, 1));
		 Assertions.assertThrows(IncorrectPositionException.class, () -> {
			 ZombieApocalypse.setUpZombieWorld(4,zombiePosition,ImpropercreaturesPos,moves);
		  });
	}
	
	@Test
	public void zombieWorkingSimulationTest() {
		Position zombiePosition = Position.setNewPosition(2, 2);
		String moves = "RRUDLLRUD";
		List<Position> creaturesPos = new ArrayList<>();
		creaturesPos.add(Position.setNewPosition(2, 3));
		creaturesPos.add(Position.setNewPosition(2, 1));
		creaturesPos.add(Position.setNewPosition(0, 1));
		
		ZombieApocalypse apocalypse = ZombieApocalypse.setUpZombieWorld(4,zombiePosition,creaturesPos,moves);
		apocalypse.startZombieInfection();
		int world[][] = apocalypse.getWorld();
		//zombieFinalPositionCheck
		Assertions.assertEquals(world[2][0],ApocalypseCodes.ZOMBIE_FINALPOSITION.getCode());
		Assertions.assertEquals(world[2][2],ApocalypseCodes.ZOMBIE_FINALPOSITION.getCode());
		Assertions.assertEquals(world[2][3],ApocalypseCodes.ZOMBIE_FINALPOSITION.getCode());
		
		//creaturesFinalPositionCheck
		Assertions.assertEquals(world[0][1],ApocalypseCodes.CREATURES.getCode());
		
		
	}

}
