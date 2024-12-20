// JLKL
// CSC 2000- Java 2 Project
// Date: 9/12/2023
// SelecterAtbash.java is the midpoint between "MainFrame.java" (GUI) and "AtbashKey"s.java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class SelecterAtbash
{
	
	public void Key1_select(String in)	// method for input
	{
		String out;
		AtbashKey1 AbK1 = new AtbashKey1();
		out = AbK1.atbash(in);
		writeFile(out);
	}
	public String Key1_select()	// Overload method (for output)
	{
		String data;
		String out = "";
		data = readFile();
		AtbashKey1 AbK1 = new AtbashKey1();
		out = AbK1.atbash(data);
		return(out);
	}
	
	public void Key2_select(String in)	// method for input
	{
		String out = "";
		AtbashKey2 AbK2 = new AtbashKey2();
		out = AbK2.atbash(in);
		writeFile(out);
	}
	public String Key2_select() // Overload method (for output)
	{
		String data;
		String out;
		data = readFile();
		AtbashKey2 AbK2 = new AtbashKey2();
		out = AbK2.atbash(data);
		return(out);
	}
	
	public void writeFile(String value)	// writes encryption to "encrypt.txt"
	{
		try {
			FileWriter myWriter = new FileWriter("encrypt.txt");
			myWriter.write(value);
			myWriter.close();
			System.out.println("Successfully encrypt to the file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public String readFile()	// reads encryption from "encrypt.txt"
	{
		String value = "";
		try {
			File myObj = new File("encrypt.txt");
			Scanner myReader = new Scanner(myObj);
			value = myReader.nextLine();
			
			myReader.close();
			System.out.println("Successfully read file.");
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		return(value);
	}
}

					
					
