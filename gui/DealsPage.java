package gui;

import javax.swing.*;
import java.awt.*;

public class DealsPage extends JFrame {

    public DealsPage(String firstName, String lastName) {
        super("The Urban Slice Deals");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addComponentsToPane(getContentPane(), firstName, lastName, "Deals");
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName, String category) {
        pane.setLayout(new BorderLayout());
        pane.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Add the header
        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        // Content specific to Deals page
        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(categoryLabel, BorderLayout.CENTER);

        // Add the deals area
        JPanel dealsPanel = createDealsPanel();
        pane.add(dealsPanel, BorderLayout.CENTER);

        // Add the footer
        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

 private JPanel createDealsPanel() {
    JPanel dealsPanel = new JPanel(new BorderLayout());
    dealsPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

    // Title
    JLabel titleLabel = new JLabel("Current Deals");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
    titleLabel.setHorizontalAlignment(JLabel.CENTER);
    dealsPanel.add(titleLabel, BorderLayout.NORTH);

    // Deals Content
    JTextArea dealsText = new JTextArea();
    dealsText.setFont(new Font("Arial", Font.PLAIN, 16));
    dealsText.setLineWrap(true);
    dealsText.setWrapStyleWord(true);
    dealsText.setEditable(false);

    // Check if there are deals
    if (!hasDeals()) {
        dealsText.setText("No current promotions available, but our pizzas are always made with the freshest ingredients and served with a smile! "
                + "Check back soon for exciting offers and new deals.");
    } else {
        // Customize the deals content here
        dealsText.setText("1. Two for Tuesday: Buy one pizza, get one free!\n" +
                "2. Family Feast: Large pizza, garlic knots, and a 2-liter soda for $25.\n" +
                "3. Lunch Special: Any personal pizza and a drink for $10.\n" +
                "4. Dessert Delight: Free dessert with any order of $30 or more.");
    }

    JScrollPane scrollPane = new JScrollPane(dealsText);
    dealsPanel.add(scrollPane, BorderLayout.CENTER);

    return dealsPanel;
}
private boolean hasDeals() {
    // Implement your logic to check if there are current deals
    // For simplicity, I'm assuming there are no deals at the moment
    return false;
}

    private JPanel createHeaderPanel(String firstName, String lastName) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(255, 204, 102)); // Light orange background color

        // Logo on the left
        ImageIcon logoIcon = new ImageIcon("gui/images/MicrosoftTeams-image.png");
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
            DealsPage.this.dispose(); // Close the current page
        });
        buttonsPanel.add(menuButton);

        // "DEALS" button (Active page, can be styled differently)
        JButton dealsButton = new JButton("DEALS");
        dealsButton.setFont(new Font("Arial", Font.BOLD, 12));
        dealsButton.setEnabled(false); // Deals page is active, so disable the button
        buttonsPanel.add(dealsButton);

        // "REWARDS" button
       JButton rewardsButton = new JButton("REWARDS");
    rewardsButton.setFont(new Font("Arial", Font.BOLD, 12));
    rewardsButton.addActionListener(e -> {
        RewardsPage rewardsPage = new RewardsPage("John", "Doe");
        rewardsPage.setVisible(true);
        this.dispose(); // Close the current page
    });
    buttonsPanel.add(rewardsButton);



        // "Cart" button
        JButton cartButton = new JButton("Cart");
        cartButton.setFont(new Font("Arial", Font.BOLD, 12));
        cartButton.addActionListener(e -> JOptionPane.showMessageDialog(DealsPage.this, "Cart button clicked"));
        buttonsPanel.add(cartButton);

        headerPanel.add(buttonsPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createFooterPanel() {
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
        SwingUtilities.invokeLater(() -> new DealsPage("John", "Doe").setVisible(true));
    }
}
