import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class EncryptionGUI 
{
    // declare GUI components
    private JFrame frame;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextField keyField;
    private JTextField inputFileField;
    private JTextField encryptedFileField;
    private JTextField decryptedFileField;

    public static void main(String[] args) 
    {
        // create and show GUI
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new EncryptionGUI().createAndShowGUI();
            }
        });
    }

    private void createAndShowGUI() {
        // create frame and set layout
        frame = new JFrame("File Encryption and Decryption");
        frame.setLayout(new GridLayout(5, 2));

        // create key label and field
        JLabel keyLabel = new JLabel("Enter Secret Key(Must be longer than 7 characters):");
        keyField = new JTextField();
        frame.add(keyLabel);
        frame.add(keyField);

        // create input file label and field
        JLabel inputFileLabel = new JLabel("Enter Input File Path:");
        inputFileField = new JTextField();
        frame.add(inputFileLabel);
        frame.add(inputFileField);

        // create encrypted file label and field
        JLabel encryptedFileLabel = new JLabel("Enter Encrypted File Path:");
        encryptedFileField = new JTextField();
        frame.add(encryptedFileLabel);
        frame.add(encryptedFileField);

        // create decrypted file label and field
        JLabel decryptedFileLabel = new JLabel("Enter Decrypted File Path:");
        decryptedFileField = new JTextField();
        frame.add(decryptedFileLabel);
        frame.add(decryptedFileField);

        // create encrypt button and add action listener
        encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                // get key, input file, and encrypted file paths from GUI fields
                String key = keyField.getText();
                String inputFile = inputFileField.getText();
                String encryptedFile = encryptedFileField.getText();
                // perform encryption
                try {
                    DESEncryption.encryptFile(key, inputFile, encryptedFile);
                    JOptionPane.showMessageDialog(frame, "Encryption Successful!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Encryption Failed: " + ex.getMessage());
                }
            }
        });
        frame.add(encryptButton);

        // create decrypt button and add action listener
        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                // get key, encrypted file, and decrypted file paths from GUI fields
                String key = keyField.getText();
                String encryptedFile = encryptedFileField.getText();
                String decryptedFile = decryptedFileField.getText();
                // perform decryption
                try {
                    DESEncryption.decryptFile(key, encryptedFile, decryptedFile);
                    JOptionPane.showMessageDialog(frame, "Decryption Successful!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Decryption Failed: " + ex.getMessage());
                }
            }
        });
        frame.add(decryptButton);

        // set frame properties and show frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
