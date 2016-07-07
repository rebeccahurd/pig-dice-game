package com.techelevator.pigdicegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameCLI {

	public static final int WINNING_SCORE = 50;
	private boolean someoneWon = false;
	List<Player> players = new ArrayList<>();
	
	public static void main (String[] args) {
		GameCLI pigDemo = new GameCLI();
		pigDemo.playGame();
	}
		
// -------------------- GAME PLAY METHODS --------------------	
	
	private void playGame() {
		promptForNumberOfPlayers();
		promptForNumberOfDieFaces();
		printGameStartMessage();
		takeTurns();
		printFinalScores();
	}
	
	private void promptForNumberOfPlayers() {
		System.out.println("How many players are playing?");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int numberOfPlayers = scan.nextInt();
		if (numberOfPlayers > 0) {
			addPlayers(numberOfPlayers);
		} else {
			System.out.println("Error: Please enter a valid number of players to start the game.");
		}
	}
	
	private void addPlayers(int numberOfPlayers) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		for (int i = 1; i <= numberOfPlayers; i++) {
			System.out.println("Please enter a name for Player " + i + ": ");
			String name = scan.nextLine();
			Player p = new Player(name);
			players.add(p);
		}
	}
	
	private void promptForNumberOfDieFaces() {
		System.out.println("How many faces would you like each die to have?");
		Scanner scan = new Scanner(System.in);
		int numberOfFaces = Integer.parseInt(scan.nextLine());
		if (numberOfFaces > 0) {
			Die die = new Die(numberOfFaces);
			for (Player p : players) {
				p.setDie(die);
			}
			System.out.println("Great! Each die will have " + numberOfFaces + "faces.");
		}
	}
	
	private void printGameStartMessage() {
		System.out.println("The game has begun!\n");
		System.out.println("The rules of the game are as follows: each player has 2 dice. On your turn, you'll roll the dice, and if"
				+ " \neither die shows a 1, you score nothing and your turn ends. If both dice show a 1, then your game total"
				+ "\nis wiped out. If you roll anything else, the sum of those dice is added to your turn total. After you roll, "
				+ "\nyou get to choose whether or not to roll again or to hold. If you hold, your turn total is added to your running"
				+ "\ngame total. First person to " + WINNING_SCORE + " points wins!\n");
	}
	
	private void takeTurns() {
		try(Scanner scan = new Scanner(System.in)) {
			while (!someoneWon) {
				for (Player p : players) {
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
		for (Player p : players) {
			System.out.println(p.getName().toUpperCase() + " - " + p.getGameScore());
		}
		System.out.println("===================");
	}
	
	private void printFinalScores() {
		System.out.print("FINAL SCORES: \n");
		for (Player p : players) {
			System.out.println(p.getName().toUpperCase() + " - " + p.getGameScore());
		}
		System.out.println("===================");
	}
	
	
	
}
