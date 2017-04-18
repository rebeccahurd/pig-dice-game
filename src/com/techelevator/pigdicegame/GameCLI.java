package com.techelevator.pigdicegame;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameCLI {

	// Game Settings
	public static final int WINNING_SCORE = 50;
	private boolean someoneWon = false;
	List<HumanPlayer> players = new ArrayList<>();
	private int numPlayers = 0;
	private int numDieFaces = 0;
	
	public static void main (String[] args) {
		GameCLI pigDemo = new GameCLI();
		pigDemo.playGame();
	}
		
// -------------------- GAME PLAY METHODS --------------------	
	
	private void playGame() {
		try {
			promptForNumberOfPlayers();
			assignNumberOfPlayers();
			addPlayers();
			promptForNumberOfDieFaces();
			assignDieFaces();
			// promptForNumberOfDicePerPlayer();
			// assignNumberOfDicePerPlayer();
			printGameStartMessage();
			takeTurns();
			printFinalScores();			
		}
		catch (Exception ex) {
			System.out.println("Error occurred: " + ex.getMessage());
		}
	}
	
	private void promptForNumberOfPlayers() {
		System.out.println("How many players are playing?");
	}
	
	private void assignNumberOfPlayers() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int numPlayersInput = scan.nextInt();
		
		if (numPlayersInput <= 0) {
			System.out.println("Error: Please enter a valid number of players to start the game.");
		}
		else {
			numPlayers = numPlayersInput;
		}
	}
	
	private void addPlayers() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		
		if (numPlayers > 0) {
			for (int i = 1; i <= numPlayers; i++) {
				System.out.println("Please enter a name for Player " + i + ": ");
				String name = scan.nextLine();
				HumanPlayer player = new HumanPlayer(name);
				players.add(player);
			}
		}
		else {
			System.out.println("Error: invalid number of players.");
		}
	}
	
	private void promptForNumberOfDieFaces() {
		System.out.println("How many faces would you like each die to have?");
	}
	
	private void assignDieFaces() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int numDieFacesInput = Integer.parseInt(scan.nextLine());
		
		if (numDieFacesInput > 0) {
			numDieFaces = numDieFacesInput;
			Die die = new Die(numDieFaces);
			
			for (HumanPlayer p : players) {
				p.setDie(die);
			}
			System.out.println("Great! Each die will have " + numDieFaces + " faces.");
		}
	}
	/*
	private void promptForNumberOfDicePerPlayer() {
		System.out.println("How many dice would you like each player to have?");
	}
	
	private void assignNumberOfDicePerPlayer() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int numDicePerPlayer = Integer.parseInt(scan.nextLine());
		
		if (numDicePerPlayer > 0) {
			
			
			Die die = new Die(numberOfFaces);
			for (HumanPlayer p : players) {
				p.setDie(die);
			}
			System.out.println("Great! Each die will have " + numberOfFaces + " faces.");
		}
	}
	*/
	
	private void printGameStartMessage() {
		System.out.println("The game has begun!\n");
		System.out.println("The rules of the game are as follows: each player has 2 dice. On your turn, you'll roll the dice, and if"
				+ " \neither die shows a 1, you score nothing and your turn ends. If both dice show a 1, then your game total"
				+ "\nis wiped out. If you roll anything else, the sum of those dice is added to your turn total. After you roll, "
				+ "\nyou get to choose whether or not to roll again or to hold. If you hold, your turn total is added to your running"
				+ "\ngame total. First person to " + WINNING_SCORE + " points wins!\n");
	}
	
	private void takeTurns() {
		try (Scanner scan = new Scanner(System.in)) {
			while (!someoneWon) {
				for (HumanPlayer p : players) {
					System.out.println("It is now " + p.getName() + "'s turn. Rolling the dice...");
					p.takeTurn();	
					
					if (p.hasWon()) {
						someoneWon = true;
						break;
					}
				}
				if (!someoneWon) {
					printRoundScores();
				}
			}	
		}
		
	}
	
	private void printRoundScores() {
		System.out.print("ROUND SCORES: \n");
		for (HumanPlayer p : players) {
			System.out.println(p.getName().toUpperCase() + " - " + p.getGameScore());
		}
		System.out.println("===================");
	}
	
	private void printFinalScores() {
		System.out.print("FINAL SCORES: \n");
		for (HumanPlayer p : players) {
			System.out.println(p.getName().toUpperCase() + " - " + p.getGameScore());
		}
		System.out.println("===================");
	}
	
	
	
}