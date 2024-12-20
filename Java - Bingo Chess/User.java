
// class User uses class Account.

import java.util.Scanner;
public class User
{
	public static Account[] Profile = new Account[5];	//Array stores 5 instinces of Account
	
	public User()
	{
		for (int i=0; i<5; i++) {
			Profile[i] = new Account();		//Sets credit value to default
		}
	}
	
	public int[] EnterUser() {			//Let user input which Account they want
		Scanner input = new Scanner(System.in);
		int n=0;						//Use as a counter.
		int[] tempoUser = {-100,-100};	//Use as base value of storing account number.
		while (n <= 1){
			System.out.println("Enter User number (1-5): ");	
			int enter = input.nextInt();							//Checks if user input value is between 1-5
			if ((enter-1) != tempoUser[0] &&enter > 0 &&enter < 6) {	// and if its not the same as the previous input
				if (Profile[enter-1].zeroCredit() != true) {		// Checks if the Account chosen has 0 credits or not.
					tempoUser[n] = (enter-1);		//if valid, it will store num as the first account
					n = n +1;						//moves onto the next account.
				} else {
					System.out.println("That Profile has 0 credits...");	//print error if no credits
				}
			} else if ((enter-1) == tempoUser[0]) {
				System.out.println("Cannot use same User account.");		//print error if same input num
			} else {
				System.out.println("numbers 1 to 5 only.");					//print if out of Bounds.
			}
		}
		return tempoUser;
	}
}