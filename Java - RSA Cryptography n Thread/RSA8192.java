// JLKL
// CSC 2000- Java 2 Project
// Date: 9/12/2023
// RSA Key Length 8192.
import java.io.*;
import java.math.BigInteger;
import java.util.Random;

public class RSA8192 {
    private BigInteger primeP;
    private BigInteger primeQ;
    private BigInteger modulusN;
    private BigInteger totientPHI;
    private BigInteger publicKeyE;
    private BigInteger privateKeyD;
    private int keyLength = 8192;
    private Random random;

    public RSA8192() {
        random = new Random();
        primeP = BigInteger.probablePrime(keyLength, random);
        primeQ = BigInteger.probablePrime(keyLength, random);
        modulusN = primeP.multiply(primeQ);
        totientPHI = primeP.subtract(BigInteger.ONE).multiply(primeQ.subtract(BigInteger.ONE));
        publicKeyE = generatePublicKey();
        privateKeyD = publicKeyE.modInverse(totientPHI);
    }
	
    private BigInteger generatePublicKey() {
        BigInteger e = BigInteger.probablePrime(keyLength / 2, random);
        while (totientPHI.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(totientPHI) < 0) {
            e = e.add(BigInteger.ONE);
        }
        return e;
    }

    public byte[] encryptMessage(byte[] message) {
        return (new BigInteger(message)).modPow(publicKeyE, modulusN).toByteArray();
    }

    public byte[] decryptMessage(byte[] message) {
        return (new BigInteger(message)).modPow(privateKeyD, modulusN).toByteArray();
    }
	
	
	
    // Additional methods to write public key to file.
	
	// Writes the public key (e, N) to a file
    public void writePublicKeyToFile(String fileName, String kL) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(publicKeyE.toString());
            writer.newLine();
            writer.write(modulusN.toString());
			writer.newLine();
			writer.write(kL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	// Reads the public key (e, N) from a file
    public void readPublicKeyFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            publicKeyE = new BigInteger(reader.readLine());
            modulusN = new BigInteger(reader.readLine());
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
