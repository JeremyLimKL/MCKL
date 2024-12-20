// JLKL
// Board 

import java.util.Scanner;
import java.util.Arrays;
public class Board 
{
	public String[][] board;					//Use for storing the 2d array values
	
	public Board() {							//Sets value for the 2d array to default
		int rows = 6;
		int columns = 7;
		board = new String[rows][columns];		// 6 x 7
		
		for (int a=0; a< rows; a++) {
			for (int b=0; b< columns; b++) {
				board[a][b] = " - ";			//Default value: " - "
			}
		}
	}
	
	public String[][] getBoard() {				//Use to get the value of board[][]
		return board;
	}
	
	public void printBoard() {					//Method prints out board
		for (int n=0; n<7; n++) {				//Prints number value of columns
			System.out.print(n+"    ");
		}
		System.out.println("");
		for (int r=6-1; r>= 0; r--) {				//Prints out the values of board[][]
			for (int c=0; c<7; c++) {				// by row and columns using nested for loop.
				System.out.print(board[r][c] + "  ");
			}
			System.out.println("");
		}
	}
	
	public boolean CheckInput(int column, String turn) {	//check if input is valid
		if (column > -1 && column < 7) {					// Checks if column value is between 0 to 6.
			int bottom = 100;					//base value for most bottom row.
			for (int row=0; row<6; row++) {
				if (board[row][column] == " - ") {		//use to check for the most bottom row which is open
					if (bottom > row)
						bottom = row;
				}
			}
			if (bottom == 100) {						//if base value is the same, error statement is printed out.
				System.out.println("- Error. Column fully occupied-");
				return true;
			} else {
				board[bottom][column] = turn;			//if there is an open row, the symbol value is placed inside.
				return false;
			}
		} else {
			System.out.println("- Enter Column Num from 0 to 6 only -");	//prints error statement for out of bounds.
			return true;
		}
	}
	
	public void EnterInput(String turn) {			//Method use to allow user to enter input.
		Scanner input = new Scanner(System.in);
		int x_cor, y_cor, col;
		
		do {									//Loop use incase input is not accepted.
			System.out.println("Column Number, Row Number | Turn:"+(turn));
			x_cor = input.nextInt();
			y_cor = input.nextInt();
			Point p1 = new Point(x_cor, y_cor);	//user enter coordinates using help of class Point()
			col = p1.getX();
			System.out.println("- - - - - - -");
		} while (CheckInput(col, turn));
		
	}
}
