/**
 *  Program lets user play TicTacToe
 *  @author xwang11
 */
import java.util.Scanner;

public class TicTacToe {
	// Declares public variables so they are accessible by various methods
	public static int GRID_SIZE = 3;
	public static int x, y;
	public static int[][] board = new int[GRID_SIZE][GRID_SIZE];
	public static int movesCount = 0;
	
	// Prints the game board on the console 
	public static void printBoard() {
		System.out.println();
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				switch (board[i][j]) {
				case 1:
					System.out.print("X ");
					break;
				case 2:
					System.out.print("O ");
					break;
				default:
					System.out.print("_ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	// Takes player, prompts player to enter token at an input row and column. 
	// Checks the user input is legal. Adds token to board.  
	public static void enterToken(int player) {
		Scanner input = new Scanner(System.in);
		char token = (player == 1)  ? 'X' : 'O';
		System.out.printf("Player %s Place an %s: \n", player, token);
		System.out.printf("Enter row (1-%s): ", GRID_SIZE);
		x = input.nextInt() - 1;
		if (x<0 || x>GRID_SIZE) {
			System.out.println("row out of bounds. Try again");
			enterToken(player);
		}
		System.out.printf("Enter column (1-%s): ", GRID_SIZE);
		y = input.nextInt() - 1;
		if (y<0 || y>GRID_SIZE) {
			System.out.println("column out of bounds. Try again");
			enterToken(player);
		}
		if (board[x][y] == 0) {
			board[x][y] = player;
		} else {
			System.out.println("Sorry that spots already taken. Try again");
			enterToken(player);
		}

	}
	// Checks the board for win conditions. 
	private static boolean checkWin(int player) {
		boolean hor = true, vert = true, diag = true, diag2 = true;
		for (int i = 0; i < GRID_SIZE; i++) {
			if (player != board[i][GRID_SIZE - (i + 1)])
				diag2 = false;
			if (player != board[i][i])
				diag = false;
			if (player != board[i][y])
				vert = false;
			if (player != board[x][i])
				hor = false;
		}

		if (hor == true || vert == true || diag == true || diag2 == true) {
			return true;
		} else {
			return false;
		}
	}
	// Uses checkWin() to check if the game is over. Prints win or tie messages.
	public static boolean checkStatus(int player) {
		boolean win = checkWin(player);

		if (win == true) {
			System.out.printf("Player %s Wins!\n", player);
			return true;
		} else if (movesCount == Math.pow(GRID_SIZE, 2)) {
			System.out.println("Its a Draw!");
			return true;
		} else {
			// Game is unfinished
			return false;
		}
	}
	
	// Puts all the methods together to play TicTacToe
	public static void main(String[] args) {
		boolean game_over = false;
		final int numberOfPlayers = 2;
		printBoard();
		while (true) {
			for (int player = 1; player <= numberOfPlayers; player++) {
				enterToken(player);
				movesCount++;
				printBoard();
				game_over = checkStatus(player);
				if (game_over == true) {
					break;
				}
			}
			if (game_over == true) {
				break;
			}
		}
	}
}