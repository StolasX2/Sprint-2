package user.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginDialog(Frame owner) {
        super(owner, "Login", true);
        setSize(300, 200);
        setLocationRelativeTo(owner);
        addComponentsToPane(getContentPane());
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new GridLayout(4, 2));

        usernameField = new JTextField();
        pane.add(new JLabel("Username:"));
        pane.add(usernameField);

        passwordField = new JPasswordField();
        pane.add(new JLabel("Password:"));
        pane.add(passwordField);

        JCheckBox staySignedInCheckBox = new JCheckBox("Stay Signed In");
        pane.add(staySignedInCheckBox);

        JButton signInButton = new JButton("Sign In");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignIn();
            }
        });
        pane.add(signInButton);
    }

    private void handleSignIn() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        if (loginSuccessful(username, password)) {
            openMenuPage();
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.");
        }
    }

    private boolean loginSuccessful(String username, char[] password) {
        return !username.isEmpty() && password.length > 0;
    }

    private void openMenuPage() {
        MenuPage menuPage = new MenuPage("John", "Doe");
        menuPage.setVisible(true);
        dispose();
    }
}
