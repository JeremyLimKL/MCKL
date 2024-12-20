// JLKL
// CSC 2000- Java 2 Project
// Date: 9/12/2023
// Java program to implement Atbash Cipher for Key 2
 
import java.util.HashMap;
 
public class AtbashKey2 {
    // Map to lookup various alphabets
    static HashMap<Character, Character> lookup_table = new HashMap<Character, Character>(){{
        put('G', 'Z'); put('H', 'Y'); put('C', 'I'); put('D', 'U'); put('E', 'T'); put('F', 'W');
        put('O', 'V'); put('A', 'S'); put('X', 'J'); put('P', 'Q'); put('K', 'R'); put('L', 'M');
        put('B', 'N'); put('N', 'B'); put('M', 'L'); put('R', 'K'); put('Q', 'P'); put('J', 'X');
        put('S', 'A'); put('V', 'O'); put('W', 'F'); put('T', 'E'); put('U', 'D'); put('I', 'C');
        put('Y', 'H'); put('Z', 'G');
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
