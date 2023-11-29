package user.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpDialog extends JDialog {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public SignUpDialog(Frame owner) {
        super(owner, "Create Your Account", true); // true makes it a modal dialog
        setSize(400, 300);
        setLocationRelativeTo(owner);

        addComponentsToPane(getContentPane());
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridLayout(7, 2));

        // First name field
        firstNameField = new JTextField();
        pane.add(new JLabel("First Name:"));
        pane.add(firstNameField);

        // Last name field
        lastNameField = new JTextField();
        pane.add(new JLabel("Last Name:"));
        pane.add(lastNameField);

        // Email field
        emailField = new JTextField();
        pane.add(new JLabel("Email:"));
        pane.add(emailField);

        // Phone number field
        phoneField = new JTextField();
        pane.add(new JLabel("Phone Number:"));
        pane.add(phoneField);

        // Password field
        passwordField = new JPasswordField();
        pane.add(new JLabel("Password:"));
        pane.add(passwordField);

        // Confirm password field
        confirmPasswordField = new JPasswordField();
        pane.add(new JLabel("Confirm Password:"));
        pane.add(confirmPasswordField);

        // Create Your Account button
        JButton createAccountButton = new JButton("Create Your Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Create Your Account button click
                handleCreateAccount();
            }
        });
        pane.add(createAccountButton);
    }

   
    private void handleCreateAccount() {
    // Placeholder method for handling Create Your Account button click
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String email = emailField.getText();
    String phone = phoneField.getText();
    char[] password = passwordField.getPassword();

    // Perform sign-up logic (replace this with your actual sign-up logic)
    if (signUpSuccessful(firstName, lastName, email, phone, password)) {
        openMenuPage(firstName, lastName);
    } else {
        JOptionPane.showMessageDialog(this, "Sign-up failed. Please check your information.");
    }
}

private boolean signUpSuccessful(String firstName, String lastName, String email, String phone, char[] password) {
    // Replace this with your actual sign-up logic
    // For now, just return true if the fields are non-empty
    return !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !phone.isEmpty() && password.length > 0;
}

private void openMenuPage(String firstName, String lastName) {
    // Open the menu page
    MenuPage menuPage = new MenuPage(firstName, lastName);
    menuPage.setVisible(true);
    dispose(); // Close the sign-up dialog
}
}
