// JLKL
// Account would be use to store a unique user's profile.

public class Account
{
	private int credits;		//Stores the credit value
	
	public Account()
	{
		credits = 10;			//Constructor sets credit value to default.
	}
	
	public int getCredits() {
		return credits;			//Use to get value of credit
	}
	
	public void winCredit(boolean win) {	//Use to check if credits are plus or minus
		if (win) 							// after a game ended.
			credits += 1;
		else 
			credits -= 1;
	}
	
	public boolean zeroCredit() {			//Checks if credits are zero to check if user's
		if (credits == 0)					// account can be used or not.
			return true;
		else
			return false;
	}
}
