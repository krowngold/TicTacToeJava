import java.util.Scanner;

public class Game {
	
	private static String[] board;
	private static String turn;
	private static Scanner console = new Scanner(System.in);
	private static boolean startGame = false;
	private static String winner;

	public static void main(String[] args) {
		turn = "X";
		board = populateEmptyBoard();
		
		//checking if the user wants to play
		if (start().toLowerCase().equals("y")) {
			startGame = true;
		}
		//ends if user says no
		else {
			System.out.println("I'm sorry to hear that! See you next time! Ending program.");
		}
		
		if (startGame) {
			System.out.println("X's play first. Please select a slot number.");
			printBoard();
			setBoardSlot(console.nextInt());
			while(winner == null) {
				if (hasWon() == null) {
					printBoard();
					System.out.println("It's " + turn + "'s turn. Please enter a slot number.");
					setBoardSlot(console.nextInt());
				}
				else {
					winner = end(hasWon());
				}
			}
		}
	}
	
	public static String start() {
		System.out.println("Welcome to TicTacToe!");
		System.out.println("Are you ready to play? (y/n)");
		return console.next();
	}
	
	public static String end(String winner) {
		if (winner.equals("O") || winner.equals("X")){
			System.out.println("We have a winner! Congratulations to team " + winner + "!");
			System.out.println("Ending the program.");
			return winner;
		}
		else {
			System.out.println("No winner yet!");
			return null;
		}
	}
	
	//Contains turn logic for the game after each character is placed. 
	//Checks if selected slot for character is already occupied, asks the user for another slot if true.
	public static void setBoardSlot(int input) {
		while (board[input].contains("X") || board[input].contains("O")) {
			System.out.println("That slot is already taken, please enter another slot number.");
			input = console.nextInt();
		}
		if (turn == "X") {
			board[input] = "X";
			turn = "O";
		}
		else {
			board[input] = "O";
			turn = "X";
		}
	}
	
	//method for after each turn, checking if turn resulted in win. Will end game if winner found
	public static String hasWon() { 
		//checks horizontal wins
		for (int i = 0; i < 7; i+=3) {
			if (("" + board[i] + board[i+1] + board[i+2]).equals("XXX")) {
				return "X";
			}
			else if (("" + board[i] + board[i+1] + board[i+2]).equals("OOO")) {
				return "O";
			}
		}
		
		//checks vertical wins
		for(int i = 0; i < 3; i++) {
			if (("" + board[i] + board[i+3] + board[i+6]).equals("XXX")) {
				return "X";
			}
			else if (("" + board[i] + board[i+3] + board[i+6]).equals("OOO")) {
				return "O";
			}
		}
		
		//checks diagonal wins
		if (("" + board[0] + board[4] + board[8]).equals("XXX")||("" + board[6] + board[4] + board[2]).equals("XXX")) {
			return "X";
		}
		else if (("" + board[0] + board[4] + board[8]).equals("OOO")||("" + board[6] + board[4] + board[2]).equals("OOO")) {
			return "O";
		}
		
		return null;
	}
	
	//Shows the current game board to the user. 
	public static void printBoard() {
		for (int i = 0; i < 9; i+=3) {
			System.out.println("-------------");
			System.out.println("|-" + board[i] + "-|-" + board[i+1] + "-|-" + board[i+2] + "-|");
		}
		System.out.println("-------------");
	}
	
	//Contains user input for whichever spot they want to place an X or an O. 
	public static String[] populateEmptyBoard() {
		String[] emptyBoard = {" "," "," "," "," "," "," "," "," "};
		return emptyBoard;
	}
}
