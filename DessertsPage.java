package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DessertsPage extends JFrame {

    public DessertsPage(String firstName, String lastName) {
        super("The Urban Slice Desserts");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addComponentsToPane(getContentPane(), firstName, lastName, "Desserts");
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName, String category) {
        pane.setLayout(new BorderLayout());
        pane.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Add the header
        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        // Content specific to Desserts page
        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(categoryLabel, BorderLayout.CENTER);

        // Add the dessert buttons
        JPanel dessertButtonsPanel = createDessertButtonsPanel(firstName, lastName);
        JScrollPane scrollPane = new JScrollPane(dessertButtonsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollPane, BorderLayout.CENTER);

        // Add the footer
        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createDessertButtonsPanel(String firstName, String lastName) {
        JPanel dessertButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        dessertButtonsPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        String[] dessertTypes = {"Brownie Tray", "Cookie Tray", "Pretzel Bites"};
        String[] dessertImages = {
                "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\Brownies_L_1080x1080.png",
                "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\cookieTrayLarge_PDP.png",
                "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\pretzel-bites-1.jpg"
        };

        for (int i = 0; i < dessertTypes.length; i++) {
            JPanel dessertPanel = new JPanel();
            dessertPanel.setLayout(new BoxLayout(dessertPanel, BoxLayout.Y_AXIS));
            dessertPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

            JButton dessertButton = createDessertButton(firstName, lastName, dessertTypes[i], dessertImages[i]);
            dessertPanel.add(dessertButton);

            JPanel orderPanel = createOrderPanel(dessertTypes[i]);
            dessertPanel.add(orderPanel);

            dessertButtonsPanel.add(dessertPanel);
        }

        return dessertButtonsPanel;
    }

    private JButton createDessertButton(String firstName, String lastName, String dessertType, String imagePath) {
        JButton dessertButton = new JButton("<html><center>" + dessertType + "</center></html>");

        dessertButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        dessertButton.setHorizontalTextPosition(SwingConstants.CENTER);

        // Add dessert image to the button
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH); // Adjusted size
        dessertButton.setIcon(new ImageIcon(img));

        // Set button size and border
        dessertButton.setPreferredSize(new Dimension(160, 160)); // Adjusted size
        dessertButton.setBorder(BorderFactory.createEmptyBorder());

        dessertButton.addActionListener(e -> {
            handleDessertButtonClick(firstName, lastName, dessertType);
        });

        return dessertButton;
    }
  private JPanel createOrderPanel(String dessertType) {
    JPanel orderPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 rows, 1 column
    orderPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

    JButton sizeButton = new JButton("Select Size");
    sizeButton.addActionListener(e -> handleSizeButtonClick(dessertType));
    sizeButton.setBackground(new Color(173, 216, 230)); // Set blue background color
    orderPanel.add(sizeButton);

    JButton orderButton = new JButton("Add to Order");
    orderButton.addActionListener(e -> handleOrderButtonClick(dessertType));
    orderPanel.add(orderButton);

    return orderPanel;
}

private void handleSizeButtonClick(String dessertType) {
    String[] sizes = {"Small", "Medium", "Large"};
    String selectedSize = (String) JOptionPane.showInputDialog(
            this,
            "Select Size for " + dessertType,
            "Size Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            sizes,
            sizes[0]
    );

    if (selectedSize != null) {
        String message = "Item successfully added to cart:\n"
                + "Dessert Type: " + dessertType + "\n"
                + "Size: " + selectedSize;
        JOptionPane.showMessageDialog(this, message);
    }
}

 
    private void handleOrderButtonClick(String dessertType) {
        // Check if a size has been selected
        if (!isSizeSelected(dessertType)) {
            JOptionPane.showMessageDialog(this, "Please select a size before adding to the order.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Continue with the order process
        String message = "Item successfully added to cart: " + dessertType;
        JOptionPane.showMessageDialog(this, message);
    }

    private boolean isSizeSelected(String dessertType) {
        // Check if a size has been selected for the specified dessert type
        // You may need to modify this based on how you store the selected sizes in your application
        // For now, assuming that a size is selected if it is not null or an empty string
        return !getSizeSelection(dessertType).isEmpty();
    }

    private String getSizeSelection(String dessertType) {
        // Retrieve and return the selected size for the specified dessert type
        // You may need to modify this based on how you store the selected sizes in your application
        // For now, using a simple placeholder, you may need to replace it with your actual implementation
        // Example: return selectedSizes.get(dessertType);
        return "";
    }


    private void handleDessertButtonClick(String firstName, String lastName, String dessertType) {
        // Customize the behavior when a dessert button is clicked
        String message = "Dessert button clicked: " + dessertType;
        JOptionPane.showMessageDialog(this, message);
    }

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

    // "Start Your Order" button
  

    // "MENU" button
    JButton menuButton = new JButton("MENU");
    menuButton.setFont(new Font("Arial", Font.BOLD, 12));
    menuButton.addActionListener(e -> {
        MenuPage menuPage = new MenuPage(firstName, lastName);
        menuPage.setVisible(true);
        DessertsPage.this.dispose(); // Close the current page
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
    cartButton.addActionListener(e -> JOptionPane.showMessageDialog(DessertsPage.this, "Cart button clicked"));
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
        SwingUtilities.invokeLater(() -> new DessertsPage("John", "Doe").setVisible(true));
    }
}
