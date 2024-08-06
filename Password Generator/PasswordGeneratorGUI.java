import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.security.SecureRandom;
import javax.swing.*;

public class PasswordGeneratorGUI extends JFrame implements ActionListener {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

    private JLabel titleLabel;
    private JLabel lengthLabel;
    private JTextField lengthField;
    private JButton generateButton;
    private JTextField passwordField;
    private JButton copyButton;

    public PasswordGeneratorGUI() {
        
        setTitle("Random Password Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        titleLabel = new JLabel("Random Password Generator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        lengthLabel = new JLabel("Length:");
        lengthLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        lengthField = new JTextField("12");
        lengthField.setPreferredSize(new Dimension(100, 25));

        generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Arial", Font.PLAIN, 14));
        generateButton.addActionListener(this);

        passwordField = new JTextField();
        passwordField.setEditable(false);
        passwordField.setPreferredSize(new Dimension(300, 25));
        passwordField.setHorizontalAlignment(JTextField.CENTER);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));

        copyButton = new JButton("Copy to Clipboard");
        copyButton.setFont(new Font("Arial", Font.PLAIN, 14));
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringSelection stringSelection = new StringSelection(passwordField.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JOptionPane.showMessageDialog(null, "Password copied to clipboard.");
            }
        });
        copyButton.setEnabled(false);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        JPanel panel = new JPanel(layout);

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 20, 20, 20);
        panel.add(titleLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.insets = new Insets(10, 20, 10, 10);
        panel.add(lengthLabel, constraints);

        constraints.gridx = 1;
        constraints.insets = new Insets(10, 10, 10, 20);
        panel.add(lengthField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 20, 20, 20);
        panel.add(generateButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(0, 20, 10, 20);
        panel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(10, 20, 20, 20);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        panel.add(copyButton, constraints);

        panel.setBackground(new Color(240, 240, 240));

        add(panel);
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            int length = 0;
            try {
                length = Integer.parseInt(lengthField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                return;
            }
    
            if (length < 4) {
                JOptionPane.showMessageDialog(null, "Password length must be at least 4 characters.");
                return;
            }
    
            String password = generatePassword(length);
            passwordField.setText(password);
            copyButton.setEnabled(true);
        }
    }
    
    private String generatePassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder builder = new StringBuilder(length);
    
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(index));
        }
    
        return builder.toString();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PasswordGeneratorGUI();
            }
        });
    }
}    