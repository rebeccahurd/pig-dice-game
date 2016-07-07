package com.techelevator.pigdicegame;

import java.util.Random;

public class Die {
	
	private int currentPips;
	private int numberOfFaces;
	
	public Die(int numberOfFaces) {
		this.numberOfFaces = numberOfFaces;
	}
	
// GETTERS & SETTERS
	public int getCurrentPips() {
		return currentPips;
	}
	
	public int getNumberOfFaces() {
		return numberOfFaces;
	}
	
	public void setNumberOfFaces(int numberOfFaces) {
		this.numberOfFaces = numberOfFaces;
	}
	
// DICE METHODS 
	public int rollDie() {
		Random rand = new Random();
		currentPips = rand.nextInt(numberOfFaces - 1) + 1;
		return currentPips;
	}
}
