package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BeveragesPage extends JFrame {

    private final String[] drinkCategories = {"Sprite", "CocaCola", "Mountain Dew", "Water", "Fanta Orange"};
    private final Map<String, String> drinkImages = new HashMap<>();

    public BeveragesPage(String firstName, String lastName) {
        super("The Urban Slice Beverages");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        drinkImages.put("Sprite", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\41MD13Rg7JL.jpg");
        drinkImages.put("CocaCola", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\GUEST_7819ee30-1f78-46ee-a21c-d2096f99ba42.jpg");
        drinkImages.put("Mountain Dew", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\000026103-1.jpg");
        drinkImages.put("Water", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\aquafina-water-1l.jpg");
        drinkImages.put("Fanta Orange", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\051272e9441cd832ed94e6439b12dd3a_large.png");

        addComponentsToPane(getContentPane(), firstName, lastName, "Beverages");
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName, String category) {
        pane.setLayout(new BorderLayout());

        // Add the header
        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        // Content specific to Beverages page
        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(categoryLabel, BorderLayout.CENTER);

        // Add the drink buttons
        JPanel drinkButtonsPanel = createDrinkButtonsPanel(firstName, lastName);
        JScrollPane scrollPane = new JScrollPane(drinkButtonsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollPane, BorderLayout.CENTER);

        // Add the footer
        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createDrinkButtonsPanel(String firstName, String lastName) {
        JPanel drinkButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        drinkButtonsPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        for (String drinkCategory : drinkCategories) {
            JPanel drinkPanel = new JPanel();
            drinkPanel.setLayout(new BoxLayout(drinkPanel, BoxLayout.Y_AXIS));
            drinkPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

            JButton drinkButton = createDrinkButton(firstName, lastName, drinkCategory);
            drinkPanel.add(drinkButton);

            JPanel sizeAndOrderPanel = createSizeAndOrderPanel(drinkCategory);
            drinkPanel.add(sizeAndOrderPanel);

            drinkButtonsPanel.add(drinkPanel);
        }

        return drinkButtonsPanel;
    }

    private JButton createDrinkButton(String firstName, String lastName, String drinkCategory) {
        JButton drinkButton = new JButton("<html><center>" + drinkCategory + "</center></html>");

        drinkButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        drinkButton.setHorizontalTextPosition(SwingConstants.CENTER);

        // Add drink image to the button
        ImageIcon icon = new ImageIcon(drinkImages.get(drinkCategory));
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        drinkButton.setIcon(new ImageIcon(img));

        // Set button size and border
        drinkButton.setPreferredSize(new Dimension(160, 160));
        drinkButton.setBorder(BorderFactory.createEmptyBorder());

        drinkButton.addActionListener(e -> {
            handleDrinkButtonClick(firstName, lastName, drinkCategory);
        });

        return drinkButton;
    }

    private JPanel createSizeAndOrderPanel(String drinkCategory) {
        JPanel sizeAndOrderPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 rows, 1 column
        sizeAndOrderPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        JButton sizeButton = new JButton("Select Size");
        sizeButton.addActionListener(e -> handleSizeButtonClick(drinkCategory));
        sizeButton.setBackground(new Color(173, 216, 230)); // Set blue background color
        sizeAndOrderPanel.add(sizeButton);

        JButton orderButton = new JButton("Add to Order");
        orderButton.addActionListener(e -> handleOrderButtonClick(drinkCategory));
        orderButton.setBackground(new Color(173, 216, 230)); // Set blue background color
        sizeAndOrderPanel.add(orderButton);

        return sizeAndOrderPanel;
    }

 private void handleSizeButtonClick(String drinkCategory) {
    String[] sizes = {"Small", "Medium", "Large", "Extra Large"};
    String selectedSize = (String) JOptionPane.showInputDialog(
            this,
            "Select Size for " + drinkCategory,
            "Size Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            sizes,
            sizes[0]
    );

    if (selectedSize != null) {
        String message = "Item successfully added to cart:\n"
                + "Drink Category: " + drinkCategory + "\n"
                + "Size: " + selectedSize;
        JOptionPane.showMessageDialog(this, message);
    }
}
private void handleOrderButtonClick(String drinkCategory) {
    // Check if a size has been selected
    if (!isSizeSelected(drinkCategory)) {
        JOptionPane.showMessageDialog(this, "Please select a size before adding to the order.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Continue with the order process
    String message = "Item successfully added to cart: " + drinkCategory;
    JOptionPane.showMessageDialog(this, message);
}
private boolean isSizeSelected(String drinkCategory) {
    // Check if a size has been selected for the specified drink category
    // You may need to modify this based on how you store the selected sizes in your application
    // For now, assuming that a size is selected if it is not null or an empty string
    return !getSizeSelection(drinkCategory).isEmpty();
}

private String getSizeSelection(String drinkCategory) {
    // Retrieve and return the selected size for the specified drink category
    // You may need to modify this based on how you store the selected sizes in your application
    // For now, using a simple placeholder, you may need to replace it with your actual implementation
    // Example: return selectedSizes.get(drinkCategory);
    return "";
}

    private void handleDrinkButtonClick(String firstName, String lastName, String drinkCategory) {
        // You can customize the behavior when a drink button is clicked
        // For example, showing additional information about the drink
        // For now, I'm just displaying a message
        String message = "Drink button clicked: " + drinkCategory;
        JOptionPane.showMessageDialog(this, message);
    }

    private JPanel createHeaderPanel(String firstName, String lastName) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(255, 204, 102)); // Light orange background color

        // Logo on the left
        ImageIcon logoIcon = new ImageIcon("gui/images/Pizza-logo-design-template-Vector-PNG.png");
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

        // "Start Your Order" button
        JButton startOrderButton = new JButton("Start Your Order");
        startOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        startOrderButton.setForeground(Color.WHITE);
        startOrderButton.setBackground(new Color(204, 51, 51));
        startOrderButton.setBorderPainted(false);
        startOrderButton.setFocusPainted(false);
        startOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(BeveragesPage.this, "Start Your Order button clicked"));
        buttonsPanel.add(startOrderButton);

        // "MENU" button
        JButton menuButton = new JButton("MENU");
        menuButton.setFont(new Font("Arial", Font.BOLD, 12));
        menuButton.addActionListener(e -> {
            MenuPage menuPage = new MenuPage(firstName, lastName);
            menuPage.setVisible(true);
            BeveragesPage.this.dispose(); // Close the current page
        });
        buttonsPanel.add(menuButton);

        // "DEALS" button
         JButton dealsButton = new JButton("DEALS");
    dealsButton.setFont(new Font("Arial", Font.BOLD, 12));
    dealsButton.addActionListener(e -> {
        DealsPage dealsPage = new DealsPage("John", "Doe");
        dealsPage.setVisible(true);
        this.dispose(); // Close the current page
    });
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
        cartButton.addActionListener(e -> JOptionPane.showMessageDialog(BeveragesPage.this, "Cart button clicked"));
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
        SwingUtilities.invokeLater(() -> new BeveragesPage("John", "Doe").setVisible(true));
    }
}
