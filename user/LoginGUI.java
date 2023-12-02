package user.user;


import user.gui.LoginDialog;
import user.gui.MenuPage;
import user.gui.SignUpDialog;

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

        JLabel welcomeLabel = new JLabel("Welcome to The Urban Slice!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(welcomeLabel, BorderLayout.NORTH);

        ImageIcon pizzaIcon = new ImageIcon("C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\download.png");
        Image scaledImage = pizzaIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledPizzaIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledPizzaIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(imageLabel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginDialog();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpDialog();
            }
        });

        buttonsPanel.add(loginButton);
        buttonsPanel.add(signUpButton);

        pane.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void openLoginDialog() {
        LoginDialog loginDialog = new LoginDialog(frame);
        loginDialog.setVisible(true);
    }

    private void openSignUpDialog() {
        SignUpDialog signUpDialog = new SignUpDialog(frame);
        signUpDialog.setVisible(true);
    }

    private boolean loginSuccessful(String username, char[] password) {
        return !username.isEmpty() && password.length > 0;
    }

    private void openMenuPage() {
        MenuPage menuPage = new MenuPage(Account.getFirstName(),Account.getLastName());
        menuPage.setVisible(true);
        frame.dispose();
    }
}
