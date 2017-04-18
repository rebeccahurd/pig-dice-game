package com.techelevator.pigdicegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer {
	
	private String name;
	private int turnScore;
	private int gameScore;
	private boolean hasBust = false;
	private boolean hasWon = false;
	private Die die;
	private List<Die> dice = null;
	
	public HumanPlayer(String name) {
		this.name = name;
	}
	
// --------------------   GETTERS & SETTERS  ---------------------
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getGameScore () {
		return gameScore;
	}
	
	public boolean hasWon() {
		hasWon = gameScore + turnScore >= GameCLI.WINNING_SCORE;
		return hasWon;
	}
	
	public boolean hasBust() {
		return hasBust;
	}
	
	public Die getDie() {
		return die;
	}
	
	public void setDie(Die die) {
		this.die = die;
	}

// -------------------- GAME ACTION METHODS --------------------
	
	public void takeTurn() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String rollAgain = "Y";
		do {
			rollDice();
			if (!hasBust) {
				System.out.println("Roll again? Y/N: ");
				rollAgain = scan.nextLine();
				if (rollAgain.equals("N") || rollAgain.equals("n")) {
					hold();
				}
			}
		} while ((rollAgain.equals("Y") || rollAgain.equals("y")) && !hasBust());
		
		if (hasWon()) {
			System.out.println(name + " has won with a score of " + gameScore + "! Game over.");
		}
	}
	
	public List<Integer> rollDice() {
		List<Integer> results = new ArrayList<>();
		
		for (Die die : dice) {
			int dieRoll = die.roll();
			results.add(dieRoll);
		}
		
		return results;
		
//		System.out.println("You rolled a " + dieRoll1 + " and a " + dieRoll2 + ".");
//
//		if (dieRoll1 != 1 && dieRoll2 != 1) {
//			turnScore += dieRoll1 + dieRoll2;
//			hasBust = false;
//			System.out.println("You're safe! Your turn total is now " + turnScore + ".");
//			
//		} else if (dieRoll1 == 1 ^ dieRoll2 == 1) {
//			turnScore = 0;
//			hasBust = true;
//			System.out.println("You bust! Your game total is now " + gameScore + ". Onto the next player's turn. Whomp whomp.\n");
//		
//		} else {
//			turnScore = 0;
//			gameScore = 0;
//			hasBust = true;
//			System.out.println("OUCH! Your game score is wiped out! Onto the next player's turn.");
//		} 
	}
	
	public void hold() {
		gameScore += turnScore;
		turnScore = 0;
		if (!hasWon) {
			System.out.println("Chicken! You chose to hold, so your game total is now: " + gameScore);
			System.out.println("Onto the next player's turn.\n");
		}
	}	
	
}
