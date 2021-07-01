package main.java.exception;

public class IncorrectZombieMovement extends RuntimeException{
	
	public IncorrectZombieMovement(String errorMessage) {
		super(errorMessage);
	}

}
