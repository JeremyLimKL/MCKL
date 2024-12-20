// Jeremy Lim Kin Lok
// CSC 2000- Java 2 Project
// Date: 9/12/2023
// RSA Key Length 8192 with Threads. (Contains parts of Zheng Yao's thread code)
import java.io.*;
import java.math.BigInteger;
import java.util.Random;

public class RSA8192Thread {
    private String decryptedText;
    private BigInteger primeP;
    private BigInteger primeQ;
    private BigInteger modulusN;
    private BigInteger totientPHI;
    private BigInteger publicKeyE;
    private BigInteger privateKeyD;
    private int keyLength = 8192;
    private Random random;

    // Constructor for generating random RSA key pair
    public RSA8192Thread() {
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

    // Encrypts a byte array message using the public key (e, N)
    public byte[] encryptMessage(byte[] message) {
        return (new BigInteger(message)).modPow(publicKeyE, modulusN).toByteArray();
    }

    // Decrypts a byte array message using the private key (d, N)
    public byte[] decryptMessage(byte[] message) {
        return (new BigInteger(message)).modPow(privateKeyD, modulusN).toByteArray();
    }

    // Writes the public key (e, N) to a file
    public void writePublicKeyToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(publicKeyE.toString());
            writer.newLine();
            writer.write(modulusN.toString());
			writer.newLine();
			writer.write("8192t");	// Set key Length
        } catch (IOException IOe) {
            IOe.printStackTrace();
        }
    }

    // Writes a byte array message to a file
    public void writeMessageToFile(String fileName, byte[] message) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileName))) {
            outputStream.write(message);
        } catch (IOException IOe) {
            IOe.printStackTrace();
        }
    }

    // Reads a byte array message from a file
    public byte[] readMessagefromFile(String fileName) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] data = inputStream.readAllBytes();
            return data;
        } catch (IOException IOe) {
            IOe.printStackTrace(); 
			return(null);
        }
    }

    // Reads the public key (e, N) from a file
    public void readPublicKeyFromFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            publicKeyE = new BigInteger(reader.readLine());
            modulusN = new BigInteger(reader.readLine());
        } catch (IOException IOe) {
			IOe.printStackTrace();
		}
    }

    // Performs the Public Key to File operation in a separate thread
    public void performPublicKeyToFileOperation() {
        // Create a thread for the Public Key to File operation
        Thread publicKeyThread = new Thread(() -> {
            // Use RSA methods for Public Key to File
            writePublicKeyToFile("public.txt");
        });

        // Start the thread
        publicKeyThread.start();
    }// Performs the Encrypt to File operation in a separate thread
    public void performEncryptToFileOperation(String message) {
        // Create a thread for the Encrypt to File operation
        Thread encryptThread = new Thread(() -> {
            // Use RSA methods for Encrypt to File
            try {
                readPublicKeyFromFile("public.txt");
                byte[] encryptedMessage = encryptMessage(message.getBytes());
                writeMessageToFile("encrypt.txt", encryptedMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Start the thread
        encryptThread.start();
    }

    // Performs the Decrypt from File operation in a separate thread
    public void performDecryptFromFileOperation() {
        // Create a thread for the Decrypt from File operation
        Thread decryptThread = new Thread(() -> {
            try {
                byte[] encryptedMessage = readMessagefromFile("encrypt.txt");
                byte[] decryptedMessage = decryptMessage(encryptedMessage);
                decryptedText = new String(decryptedMessage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Start the thread
        decryptThread.start();
    }

    // Getter method to retrieve the decrypted text after decryption operation
    public String getDecryptedText() {
        return decryptedText;
    }
}