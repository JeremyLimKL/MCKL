// JLKL
// CSC 2000- Java 2 Project
// Date: 9/12/2023
// SelecterRSA class is used to handle RSA4096 & RSA8192.
// It writes and reads the value in "encrypt.txt" and checks Key Length.
import java.io.*;
import java.math.BigInteger;
public class SelecterRSA
{
	// Use for printing out bytes.
	public String byteArrayToString(byte[] array) {
		StringBuilder result = new StringBuilder();
        for (byte b : array) {
            result.append(Byte.toString(b)).append(" ");
        }
        return result.toString();
    }
	// Use to get the key length stored.
    public String readKeyLengthFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            BigInteger publicKeyE = new BigInteger(reader.readLine());
            BigInteger modulusN = new BigInteger(reader.readLine());
			String keyLength = reader.readLine();
			return(keyLength);
        } catch (IOException e) {
			e.printStackTrace();
			return(null);
		}
    }
	
	// Writes a byte array message to a file
    public void writeMessageToFile(String fileName, byte[] message) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            outputStream.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	// Reads a byte array message from a file
    public byte[] readMessagefromFile(String fileName) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] data = inputStream.readAllBytes();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
			return(null);
        }
    }
	
}
