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

        firstNameField = new JTextField();
        pane.add(new JLabel("First Name:"));
        pane.add(firstNameField);

        lastNameField = new JTextField();
        pane.add(new JLabel("Last Name:"));
        pane.add(lastNameField);

        emailField = new JTextField();
        pane.add(new JLabel("Email:"));
        pane.add(emailField);

        phoneField = new JTextField();
        pane.add(new JLabel("Phone Number:"));
        pane.add(phoneField);

        passwordField = new JPasswordField();
        pane.add(new JLabel("Password:"));
        pane.add(passwordField);

        confirmPasswordField = new JPasswordField();
        pane.add(new JLabel("Confirm Password:"));
        pane.add(confirmPasswordField);

        JButton createAccountButton = new JButton("Create Your Account");
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleCreateAccount();
            }
        });
        pane.add(createAccountButton);
    }

   
    private void handleCreateAccount() {
    String firstName = firstNameField.getText();
    String lastName = lastNameField.getText();
    String email = emailField.getText();
    String phone = phoneField.getText();
    char[] password = passwordField.getPassword();

    if (signUpSuccessful(firstName, lastName, email, phone, password)) {
        openMenuPage(firstName, lastName);
    } else {
        JOptionPane.showMessageDialog(this, "Sign-up failed. Please check your information.");
    }
}

private boolean signUpSuccessful(String firstName, String lastName, String email, String phone, char[] password) {

    return !firstName.isEmpty() && !lastName.isEmpty() && !email.isEmpty() && !phone.isEmpty() && password.length > 0;
}

private void openMenuPage(String firstName, String lastName) {
    MenuPage menuPage = new MenuPage(firstName, lastName);
    menuPage.setVisible(true);
    dispose();
}
}
