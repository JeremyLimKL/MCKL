// JLKL
// CSC 2000- Java 2 Project
// Date: 9/12/2023
// Java program to implement Atbash Cipher for Key 1
 
import java.util.HashMap;
 
public class AtbashKey1 {
    // Map to lookup various alphabets
    static HashMap<Character, Character> lookup_table = new HashMap<Character, Character>(){{
        put('A', 'Z'); put('B', 'Y'); put('C', 'X'); put('D', 'W'); put('E', 'V'); put('F', 'U');
        put('G', 'T'); put('H', 'S'); put('I', 'R'); put('J', 'Q'); put('K', 'P'); put('L', 'O');
        put('M', 'N'); put('N', 'M'); put('O', 'L'); put('P', 'K'); put('Q', 'J'); put('R', 'I');
        put('S', 'H'); put('T', 'G'); put('U', 'F'); put('V', 'E'); put('W', 'D'); put('X', 'C');
        put('Y', 'B'); put('Z', 'A');
    }};
 
    // Function to implement Atbash Cipher
    public String atbash(String message)
    {
        String cipher = "";
        for(char letter : message.toCharArray())
        {
            // Checking for space
            if(letter != ' ')
            {
                // Adds the corresponding letter from the lookup_table
                cipher += Character.toLowerCase(lookup_table.get(Character.toUpperCase(letter)));
            }
            else
            {
                // Adds space
                cipher += ' ';
            }
        }
        return cipher;
    }
}
// Contributed by adityasharmadev01, Geeks For Geeks
