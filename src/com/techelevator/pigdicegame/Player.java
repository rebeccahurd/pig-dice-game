package com.techelevator.pigdicegame;

import java.util.Scanner;

public class Player {
	
	private String name;
	private int turnScore;
	private int gameScore;
	private boolean playerBust = false;
	private boolean wonGame = false;
	private Die die;
	
	public Player(String name) {
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
		wonGame = gameScore + turnScore >= GameCLI.WINNING_SCORE;
		return wonGame;
	}
	
	public boolean hasBust() {
		return playerBust;
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
			if (!playerBust) {
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
	
	public void rollDice() {
		int dieRoll1 = die.rollDie();
		int dieRoll2 = die.rollDie();
		System.out.println("You rolled a " + dieRoll1 + " and a " + dieRoll2 + ".");

		if (dieRoll1 != 1 && dieRoll2 != 1) {
			turnScore += dieRoll1 + dieRoll2;
			playerBust = false;
			System.out.println("You're safe! Your turn total is now " + turnScore + ".");
			
		} else if (dieRoll1 == 1 ^ dieRoll2 == 1) {
			turnScore = 0;
			playerBust = true;
			System.out.println("You bust! Your game total is now " + gameScore + ". Onto the next player's turn. Whomp whomp.\n");
		
		} else {
			turnScore = 0;
			gameScore = 0;
			playerBust = true;
			System.out.println("OUCH! Your game score is wiped out! Onto the next player's turn.");
		} 
	}
	
	public void hold() {
		gameScore += turnScore;
		turnScore = 0;
		if (!wonGame) {
			System.out.println("Chicken! You chose to hold, so your game total is now: " + gameScore);
			System.out.println("Onto the next player's turn.\n");
		}
	}	
	
}
