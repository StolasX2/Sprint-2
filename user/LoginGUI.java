package user.user;

import user.order.MenuPage;
import user.user.gui.LoginDialog;
import user.user.gui.SignUpDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI {
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI().initialize());
    }

    private void initialize() {
        frame = new JFrame("Welcome to The Urban Slice");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentsToPane(frame.getContentPane());

        frame.setVisible(true);
    }

    private void addComponentsToPane(Container pane) {
        pane.setLayout(new BorderLayout());

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to The Urban Slice!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(welcomeLabel, BorderLayout.NORTH);

        // Placeholder image (replace with your logo)
        ImageIcon pizzaIcon = new ImageIcon("C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\download.png");
        Image scaledImage = pizzaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledPizzaIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledPizzaIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(imageLabel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        // Add action listeners to the buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle login button click by opening the login dialog
                openLoginDialog();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle sign-up button click
                openSignUpDialog();
            }
        });

        // Add buttons to the panel
        buttonsPanel.add(loginButton);
        buttonsPanel.add(signUpButton);

        // Add buttons panel to the bottom of the frame
        pane.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void openLoginDialog() {
        // Open the login dialog
        LoginDialog loginDialog = new LoginDialog(frame);
        loginDialog.setVisible(true);
    }

    private void openSignUpDialog() {
        SignUpDialog signUpDialog = new SignUpDialog(frame);
        signUpDialog.setVisible(true);
    }

    private boolean loginSuccessful(String username, char[] password) {
        // Replace this with your actual login logic
        // For now, just return true if the username and password are non-empty
        return !username.isEmpty() && password.length > 0;
    }

    private void openMenuPage() {
        MenuPage menuPage = new MenuPage("John", "Doe"); // Replace with actual user information
        menuPage.setVisible(true);
        frame.dispose(); // Close the login page
    }
}
