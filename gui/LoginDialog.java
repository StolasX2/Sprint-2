package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private JTextField usernameField;  // Add this line
    private JPasswordField passwordField;  // Add this line

    public LoginDialog(Frame owner) {
        super(owner, "Login", true); // true makes it a modal dialog
        setSize(300, 200);
        setLocationRelativeTo(owner);

        addComponentsToPane(getContentPane());
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridLayout(4, 2));

        // Username field
        usernameField = new JTextField();
        pane.add(new JLabel("Username:"));
        pane.add(usernameField);

        // Password field
        passwordField = new JPasswordField();
        pane.add(new JLabel("Password:"));
        pane.add(passwordField);

        // Stay Signed In checkbox
        JCheckBox staySignedInCheckBox = new JCheckBox("Stay Signed In");
        pane.add(staySignedInCheckBox);

        // Sign In button
        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Sign In button click
                handleSignIn();
            }
        });
        pane.add(signInButton);
    }

    private void handleSignIn() {
        // Placeholder method for handling Sign In button click
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        // Perform login logic (replace this with your actual login logic)
        if (loginSuccessful(username, password)) {
            openMenuPage();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.");
        }
    }

    private boolean loginSuccessful(String username, char[] password) {
        // Replace this with your actual login logic
        // For now, just return true if the username and password are non-empty
        return !username.isEmpty() && password.length > 0;
    }

    private void openMenuPage() {
        // Open the menu page
        MenuPage menuPage = new MenuPage("John", "Doe"); // Replace with actual user information
        menuPage.setVisible(true);
        dispose(); // Close the login dialog
    }
}
