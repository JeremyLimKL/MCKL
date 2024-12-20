// Jeremy Lim Kin Lok
// CSC 2000- Java 2 Project
// Date: 9/12/2023
// Contains GUI panel and basic interaction and the executor.
// To run the whole program, execute this class.
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame
{
	private ButtonGroup AtbashRSA = new ButtonGroup();
	// Panel 1
	private JPanel Panel_1 = new JPanel(new BorderLayout());
	public JTextArea userInput = new JTextArea();
	private JLabel encryptLabel = new JLabel("Text Message to Encrypt", SwingConstants.CENTER);
	
	// Panel 2 & 3
	private JPanel Panel_2and3 = new JPanel(new GridLayout(2,2));
	// Panel 2
	public JCheckBox Atbash = new JCheckBox("Atbash"); 
	private JPanel Panel_2_right = new JPanel(new GridLayout(2,1));
	public JRadioButton Key1 = new JRadioButton("Key 1");
	public JRadioButton Key2 = new JRadioButton("Key 2");
	private ButtonGroup Panel_2_right_buttonGroup = new ButtonGroup();
	// Panel 3
	private JCheckBox RSA = new JCheckBox("RSA"); 
	private JPanel Panel_3_right = new JPanel(new GridLayout(2,2));
	public JRadioButton bit4096 = new JRadioButton("4096");
	public JRadioButton bit4096t = new JRadioButton("4096 + Threads");
	private JRadioButton bit8192 = new JRadioButton("8192");
	private JRadioButton bit8192t = new JRadioButton("8192 + Threads");
	private ButtonGroup Panel_3_right_buttonGroup = new ButtonGroup();
	// Panel 4 & 5
	private JPanel Panel_4and5 = new JPanel(new BorderLayout());
	// Panel 4
	private JPanel Panel_4 = new JPanel(new FlowLayout());
	private JButton PKButton = new JButton("Public Key to File");
	private JButton encryptButton = new JButton("Encrypt to File");
	private JButton decryptButton = new JButton("Decrypt from File");
	
	// Panel 5
	private JPanel Panel_5 = new JPanel(new BorderLayout());
	public JTextArea userOutput = new JTextArea();
	private JLabel decryptLabel = new JLabel("Text Message Decrypted", SwingConstants.CENTER);
	
	// For RSA
	private SelecterRSA selectionRSA;
	private RSA4096 rsa4096;
	private RSA8192 rsa8192;
	private RSA4096Thread rsa4096T;
	private RSA8192Thread rsa8198T;
	
	public MainFrame()
	{
		super("Encodex");
		selectionRSA = new SelecterRSA();
		SelecterAtbash selection = new SelecterAtbash();
		//
		AtbashRSA.add(Atbash);
		AtbashRSA.add(RSA);
		// set Panel 1
		Panel_1.add(userInput, BorderLayout.CENTER);
		Panel_1.add(encryptLabel, BorderLayout.SOUTH);
		// set Panel 2
			// set Panel_2_right
		Panel_2_right_buttonGroup.add(Key1);
		Panel_2_right_buttonGroup.add(Key2);
		Panel_2_right.add(Key1);
		Panel_2_right.add(Key2);
		
		// set Panel 3
			// set Panel_3_right
		Panel_3_right_buttonGroup.add(bit4096);
		Panel_3_right_buttonGroup.add(bit4096t);
		Panel_3_right_buttonGroup.add(bit8192);
		Panel_3_right_buttonGroup.add(bit8192t);
		Panel_3_right.add(bit4096);
		Panel_3_right.add(bit4096t);
		Panel_3_right.add(bit8192);
		Panel_3_right.add(bit8192t);
		// set Panel 2 & 3
		Panel_2and3.add(Atbash);
		Panel_2and3.add(Panel_2_right);
		Panel_2and3.add(RSA);
		Panel_2and3.add(Panel_3_right);
		
		// set Panel 4
		Panel_4.add(PKButton);
		Panel_4.add(encryptButton);
		Panel_4.add(decryptButton);
		// set Panel 5
		Panel_5.add(userOutput, BorderLayout.CENTER);
		Panel_5.add(decryptLabel, BorderLayout.SOUTH);
		
		// set Panel 4 & 5
		Panel_4and5.add(Panel_4, BorderLayout.NORTH);
		Panel_4and5.add(Panel_5, BorderLayout.CENTER);
		
		// set MainFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setVisible(true);
		
		setLayout(new GridLayout(3,1));
		add(Panel_1);
		add(Panel_2and3);
		add(Panel_4and5);
		
		// set the ActionListener
		//	Atbash and RSA
		Atbash.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DisableJRB("Atbash");
				}
			}
		);
		RSA.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DisableJRB("RSA");
				}
			}
		);
		
		
		//	Send Public Key to file "public.txt"
		PKButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String out = "";
					if (RSA.isSelected()) {
						if (bit4096.isSelected()) {
							rsa4096 = new RSA4096();
							rsa4096.writePublicKeyToFile("public.txt", "4096");
							System.out.println("-Finish sending Public Key & Key Length-");
						}
						if (bit8192.isSelected()) {
							rsa8192 = new RSA8192();
							rsa8192.writePublicKeyToFile("public.txt", "8192");
							System.out.println("-Finish sending Public Key & Key Length-");
						}
						if (bit4096t.isSelected()) {
							rsa4096T = new RSA4096Thread();
							rsa4096T.performPublicKeyToFileOperation();
							System.out.println("-Finish sending Public Key & Key Length-");
						}
						if (bit8192t.isSelected()) {
							rsa8198T = new RSA8192Thread();
							rsa8198T.performPublicKeyToFileOperation();
							System.out.println("-Finish sending Public Key & Key Length-");
						}
					} else {
						userOutput.setText("-Error: RSA value not selected-");
					}
				}
			}
		);
		
		//	Encrypt to text file "encrypt.txt"
		encryptButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String in = userInput.getText();
					if (Key1.isSelected()) {
						selection.Key1_select(in);
					} if (Key2.isSelected()) {
						selection.Key2_select(in);
					} if (bit4096.isSelected()) {
						byte[] originalData = in.getBytes();
						byte[] encryptedData = rsa4096.encryptMessage(originalData);
						selectionRSA.writeMessageToFile("encrypt.txt",encryptedData);
					} if (bit8192.isSelected()) {
						byte[] originalData = in.getBytes();
						byte[] encryptedData = rsa8192.encryptMessage(originalData);
						selectionRSA.writeMessageToFile("encrypt.txt",encryptedData);
					} if (bit4096t.isSelected()) {
						rsa4096T.performEncryptToFileOperation(in);
					} if (bit8192t.isSelected()) {
						rsa8198T.performEncryptToFileOperation(in);
					}
					System.out.println("-Finish encrypting-");
				}
			}
		);
		//	Decrypt to File button
		decryptButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String out = "-Error: Empty Data-";
					if (Key1.isSelected()) {
						out=selection.Key1_select();
					} if (Key2.isSelected()) {
						out=selection.Key2_select();
					} if (RSA.isSelected()) {	// Auto checks the key length so that user does not have to choose correct kL
						String kL = selectionRSA.readKeyLengthFromFile("public.txt");
						if (kL.equals("4096")) {
							byte[] rawData = selectionRSA.readMessagefromFile("encrypt.txt");
							byte[] decryptedData = rsa4096.decryptMessage(rawData);
							out = new String(decryptedData);
						}
						if (kL.equals("8192")) {
							byte[] rawData = selectionRSA.readMessagefromFile("encrypt.txt");
							byte[] decryptedData = rsa8192.decryptMessage(rawData);
							out = new String(decryptedData);
						}
						if (kL.equals("4096t")) {
							rsa4096T.performDecryptFromFileOperation();
							out = rsa4096T.getDecryptedText();
						}
						if (kL.equals("8192t")) {
							rsa8198T.performDecryptFromFileOperation();
							out = rsa8198T.getDecryptedText();
						}
					}
					userOutput.setText(out);
				}
			}
		);
	}	// end of JFrame method
	
	// Disabling JRadioButtons
	public void DisableJRB(String Choice) {
		JRadioButton[] AtbashGroup = new JRadioButton[]{Key1,Key2};
		JRadioButton[] RSAGroup = new JRadioButton[]{bit4096,bit4096t,bit8192,bit8192t};
		if (Choice == "Atbash") {
			for (JRadioButton btn : AtbashGroup) {
				btn.setEnabled(true);
			}
			for (JRadioButton btn : RSAGroup) {
				//btn.setSelected(false);
				btn.setEnabled(false);
			}
			Panel_3_right_buttonGroup.clearSelection();
		} else if (Choice == "RSA") {
			for (JRadioButton btn : AtbashGroup) {
				//btn.setSelected(false);
				btn.setEnabled(false);
			}
			Panel_2_right_buttonGroup.clearSelection();
			for (JRadioButton btn : RSAGroup) {
				btn.setEnabled(true);
			}
		}
	}	

	// Execute Encodex Java File:
	public static void main (String[] args)
	{
		MainFrame prompter = new MainFrame();
	}
}