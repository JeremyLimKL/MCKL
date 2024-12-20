// JLKL
// class WinCondition extends from Board to be able to use some of its methods.

public class WinCondition extends Board
{
	public String[][] board;	//same value as board
	
	public WinCondition(String[][] Board) {
		this.board = Board;		//use to store new update value of the board
	}
	
	public boolean CheckHori(int row,int col, String turn) {
		if (board[row][col]==turn &&board[row][col+1]==turn &&board[row][col+2]==turn &&board[row][col+3]==turn)
			return true;	//Checks if the horizontal row matches 4 in a row. Yes = true.
		else
			return false;
	}
	public boolean CheckVert (int row, int col, String turn) {
		if (board[row][col]==turn &&board[row+1][col]==turn &&board[row+2][col]==turn &&board[row+3][col]==turn)
			return true;	//Checks if the vertical column matches 4 in a row. Yes = true.
		else
			return false;
	}
	public boolean CheckDiag1 (int row, int col, String turn) {
		if (board[row][col]==turn &&board[row+1][col+1]==turn &&board[row+2][col+2]==turn &&board[row+3][col+3]==turn)
			return true;	//Checks if the diagonal row to NorthEast matches 4 in a row. Yes = true.
		else
			return false;
	}
	public boolean CheckDiag2 (int row, int col, String turn) {
		if (board[row][col]==turn &&board[row+1][col-1]==turn &&board[row+2][col-2]==turn &&board[row+3][col-3]==turn)
			return true;	//Checks if the diagonal row to NorthWest matches 4 in a row. Yes = true.
		else
			return false;
	}
	
	
	public boolean MainChecker(String turn) //Compiles all above Check methods and use it for 
	{										// all the possible win rows/combinations.
		boolean win = false;
		for (int r=0; r<6; r++) {			//Checks for Horizontals.
			for (int c=0; c<4; c++) {
				if (CheckHori(r,c, turn))
					win = true;
			}
		}
		for (int c=0; c<7; c++) {			//Checks for Verticals.
			for (int r=0; r<3; r++) {
				if (CheckVert(r,c, turn))
					win = true;
			}
		}
		for (int c=0; c<=3; c++) {			//Checks for NorthEast diagonals.
			for (int r=0;r<4 ; r++) { 
				if (CheckDiag1(r,c, turn))
					win = true;
			}
		}
		for (int c=6; c>=3; c--) {			//Checks for NorthWest diagonals.
			for (int r=0;r<4 ; r++) {
				if (CheckDiag2(r,c, turn))
					win = true;
			}
		}
		
		return win;				//returns whether a possible win (true) is found or not (false)
	}
	
	public boolean drawChecker() {			//Checks if a Draw has happened
		for (int b=0; b< 7; b++) {
			if (board[5][b] == " - ")		// by checking if there isnt anymore default values.
				return false;
		}
		return true;
	}
}
