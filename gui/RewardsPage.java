package user.gui;

import javax.swing.*;
import java.awt.*;

public class RewardsPage extends JFrame {

    public RewardsPage(String firstName, String lastName) {
        super("The Urban Slice Rewards");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addComponentsToPane(getContentPane(), firstName, lastName, "Rewards");
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName, String category) {
        pane.setLayout(new BorderLayout());
        pane.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Add the header
        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        // Content specific to Rewards page
        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(categoryLabel, BorderLayout.CENTER);

        // Add the rewards content area
        JPanel rewardsPanel = createRewardsPanel();
        pane.add(rewardsPanel, BorderLayout.CENTER);

        // Add the footer
        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createRewardsPanel() {
        JPanel rewardsPanel = new JPanel(new BorderLayout());
        rewardsPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Rewards Content
        JTextArea rewardsText = new JTextArea();
        rewardsText.setFont(new Font("Arial", Font.PLAIN, 16));
        rewardsText.setLineWrap(true);
        rewardsText.setWrapStyleWord(true);
        rewardsText.setEditable(false);

        // Customize the rewards content here
        rewardsText.setText("Join our rewards program and earn points with every purchase!\n" +
                "Redeem points for discounts and special offers.");

        JScrollPane scrollPane = new JScrollPane(rewardsText);
        rewardsPanel.add(scrollPane, BorderLayout.CENTER);

        return rewardsPanel;
    }

    // Header and Footer methods similar to other classes

    protected JPanel createHeaderPanel(String firstName, String lastName) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(255, 204, 102)); // Light orange background color

        // Logo on the left
        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\MicrosoftTeams-image.png");
        Image logoImage = logoIcon.getImage();
        int logoSize = 80;
        Image resizedLogo = logoImage.getScaledInstance(logoSize, logoSize, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogo);

        JLabel logoLabel = new JLabel(resizedLogoIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Greeting label in the center
        JLabel greetingLabel = new JLabel("Hi, " + firstName + " " + lastName + "!");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        greetingLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingLabel.setForeground(new Color(51, 51, 51));
        headerPanel.add(greetingLabel, BorderLayout.CENTER);

        // Navigation buttons on the right
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setOpaque(false); // Make the buttonsPanel transparent

        // "MENU" button
        JButton menuButton = new JButton("MENU");
        menuButton.setFont(new Font("Arial", Font.BOLD, 12));
        menuButton.addActionListener(e -> {
            MenuPage menuPage = new MenuPage(firstName, lastName);
            menuPage.setVisible(true);
            RewardsPage.this.dispose(); // Close the current page
        });
        buttonsPanel.add(menuButton);

        // "DEALS" button
        JButton dealsButton = new JButton("DEALS");
        dealsButton.setFont(new Font("Arial", Font.BOLD, 12));
        dealsButton.addActionListener(e -> {
            DealsPage dealsPage = new DealsPage(firstName, lastName);
            dealsPage.setVisible(true);
            RewardsPage.this.dispose(); // Close the current page
        });
        buttonsPanel.add(dealsButton);

        // "REWARDS" button (Active page, can be styled differently)
        JButton rewardsButton = new JButton("REWARDS");
        rewardsButton.setFont(new Font("Arial", Font.BOLD, 12));
        rewardsButton.setEnabled(false); // Rewards page is active, so disable the button
        buttonsPanel.add(rewardsButton);

        // "Cart" button
        JButton cartButton = new JButton("Cart");
        cartButton.setFont(new Font("Arial", Font.BOLD, 12));
        cartButton.addActionListener(e -> JOptionPane.showMessageDialog(RewardsPage.this, "Cart button clicked"));
        buttonsPanel.add(cartButton);

        headerPanel.add(buttonsPanel, BorderLayout.EAST);

        return headerPanel;
    }

    protected JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(51, 51, 51));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel footerLabel = new JLabel("Questions? Call us at  478-123-6521 or email us at support@theurbanslice.com");
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        footerLabel.setForeground(Color.WHITE);

        footerPanel.add(footerLabel);

        return footerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RewardsPage("John", "Doe").setVisible(true));
    }
}
