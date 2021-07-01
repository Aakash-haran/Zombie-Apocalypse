package main.java;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import main.java.exception.IncorrectPositionException;
import main.java.exception.IncorrectZombieMovement;
import main.java.exception.InsufficientZombieWorldSizeException;

public class ZombieApocalypse {
	
	private Queue<Position> zombiePosition;
	private List<Position> creaturesPosition;
	private int[][] world;
	private String zombieMovement;
	
	public static ZombieApocalypse setUpZombieWorld(int worldSize, Position zombiePos, List<Position> creaturesPos, String moves) {
		ZombieApocalypse apocalypse = new ZombieApocalypse();
		if(worldSize <=0) {
			throw new InsufficientZombieWorldSizeException("Please provide a valid size(greater than 0) for Zombie World");
		}
		apocalypse.world = new int[worldSize][worldSize];
		apocalypse.zombiePosition = new LinkedList<>();
		if(zombiePos.getX() >= worldSize || zombiePos.getY() >= worldSize) {
			throw new IncorrectPositionException("Please provide a Zombie position lesser than the world size");
		}
		if(zombiePos.getX() < 0 || zombiePos.getY() < 0) {
			throw new IncorrectPositionException("Please provide an absolute Zombie position which fits within the world size");
			
		}
		apocalypse.zombiePosition.add(zombiePos);
		apocalypse.world[zombiePos.getX()][zombiePos.getY()] = ApocalypseCodes.ZOMBIE.getCode();
		apocalypse.creaturesPosition = new ArrayList<>();
		Iterator<Position> creaturesPosIterator = creaturesPos.listIterator();
		while(creaturesPosIterator.hasNext()){
			Position tempPos = creaturesPosIterator.next();
			if(tempPos.getX() >= worldSize || tempPos.getY() >= worldSize) {
				throw new IncorrectPositionException("Please provide a Creature position lesser than the world size");
			}
			if(tempPos.getX() < 0 || tempPos.getY() < 0) {
				throw new IncorrectPositionException("Please provide an absolute Creature position which fits within the world size");
			}
//			If Zombie awakens at the creatures position itself initially 
//			the below can be enabled if zombie can start at creatures place and infect it without starting any movement
//			if(tempPos.equals(zombiePos)) {				
//				apocalypse.zombiePosition.add(zombiePos);								
//			}
			apocalypse.creaturesPosition.add(tempPos);
			apocalypse.world[tempPos.getX()][tempPos.getY()] =  ApocalypseCodes.CREATURES.getCode();
		}
		if(!moves.matches("^[UDLR]+$")) {
			throw new IncorrectZombieMovement("Please provide a valid zombie movement string: UDLR");
		}
		apocalypse.zombieMovement = moves;
		return apocalypse;
	}
	
	public void startZombieInfection() {
		int zombieNumber = 0;
		int strLen = this.zombieMovement.length();
		while(!zombiePosition.isEmpty()){
		Position initialPosition = zombiePosition.poll();
			int x = initialPosition.getX();
			int y = initialPosition.getY();			
			int moveStart = 0;
			System.out.println("zombie " + zombieNumber + " starts at (" + x + ',' + y +")");
			while(moveStart < strLen){
				char move = this.zombieMovement.charAt(moveStart);
				Position updatedPosition = this.moveZombie(x,y,move);	
				x = updatedPosition.getX();
				y= updatedPosition.getY();
				System.out.println("zombie " + zombieNumber + " moved to (" + x +',' + y +")");
				if(world[x][y] == ApocalypseCodes.CREATURES.getCode()){
					this.world[x][y] = ApocalypseCodes.ZOMBIE.getCode();
					this.creaturesPosition.remove(updatedPosition);
					System.out.println("zombie " + zombieNumber + " infected creature at (" + x +',' + y + ")");
					this.zombiePosition.add(updatedPosition);
				}
				moveStart++;
			}
			this.world[x][y] = ApocalypseCodes.ZOMBIE_FINALPOSITION.getCode();;
			zombieNumber++;
		}
		printFinalPositions();
	}
	
	private Position moveZombie(int x, int y, char command){
		switch(command){
			case 'U': 
			return moveZombieUp(x, y);
			case 'D': 
			return moveZombieDown(x, y);
			case 'L': 
			return moveZombieLeft(x, y);
			case 'R': 
			return moveZombieRight(x, y);
		}
		return null;
	}
	
	private Position moveZombieUp(int x, int y){
		if(x == 0){
			x = world.length - 1;
			return Position.setNewPosition(x, y);
		}
		return Position.setNewPosition(x-1, y);
	}
	
	private Position moveZombieDown(int x, int y){
		if(x == world.length - 1){
			x = 0;
			return Position.setNewPosition(x, y);
		}
		return Position.setNewPosition(x+1,y);
	}
	
	private Position moveZombieLeft(int x, int y){
		if(y== 0){
			y = world[0].length - 1;
			return Position.setNewPosition(x,y);
		}
		return Position.setNewPosition(x,y-1);
	}
	
	private Position moveZombieRight(int x, int y){
		if(y == world[0].length - 1){
			y = 0;
			return Position.setNewPosition(x,y);
		}
		return Position.setNewPosition(x,y+1);
	}
	
	private void printFinalPositions(){
		System.out.println("Zombie's Positions:");
		for (int row = 0; row < world.length; row++) {
			for (int column = 0; column < world[0].length; column++) {
				if(world[row][column] == ApocalypseCodes.ZOMBIE_FINALPOSITION.getCode()) {
					System.out.println("(" + row + ","+ column + ")" + "\t");
				}
			}
		}
            
		if(creaturesPosition.size() == 0) {
			System.out.println("Creatures Positions: none" );
		}else {
			Iterator<Position> creatPosItr = creaturesPosition.iterator();
			System.out.println("Creatures Positions:");
			while(creatPosItr.hasNext()){
				Position creaturePos = creatPosItr.next();
				System.out.println("(" + creaturePos.getX() + ","+ creaturePos.getY() + ")" + "\t");
			}
		}				
	}

	public int[][] getWorld() {
		return world;
	}

}
