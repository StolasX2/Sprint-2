package user.user.gui;

import javax.swing.*;
import java.awt.*;

public class ClassicsPage extends JFrame {

    public ClassicsPage(String firstName, String lastName) {
        super("The Urban Slice Classics");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addComponentsToPane(getContentPane(), firstName, lastName, "Classics");
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName, String category) {
        pane.setLayout(new BorderLayout());
        pane.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Add the header
        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        // Content specific to Classics page
        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(categoryLabel, BorderLayout.CENTER);

        // Add the pizza buttons
        JPanel pizzaButtonsPanel = createPizzaButtonsPanel(firstName, lastName);
        JScrollPane scrollPane = new JScrollPane(pizzaButtonsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollPane, BorderLayout.CENTER);

        // Add the footer
        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

private JPanel createPizzaButtonsPanel(String firstName, String lastName) {
    JPanel pizzaButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
    pizzaButtonsPanel.setBackground(new Color(173, 216, 230));

    String[] pizzaTypes = {"Pepperoni", "Cheese", "Meat Lovers", "Veggie Lovers", "Supreme"};
    String[] pizzaImages = {"C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\pepperoni.png",
            "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\cheese.png",
            "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\images.jpg",
            "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\5ff33d7df72e71d8755784135a6e6edd.jpg",
            "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\DSC_0905-min.png"};

    double[] smallPrices = {13.99, 13.99, 13.99, 13.99, 13.99};
    double[] mediumPrices = {15.99, 15.99, 15.99, 15.99, 15.99};
    double[] largePrices = {17.99, 17.99, 17.99, 17.99, 17.99};
 double[] xlPrices = {21.99, 21.99, 21.99, 21.99, 21.99}; 
    for (int i = 0; i < pizzaTypes.length; i++) {
        JPanel pizzaPanel = new JPanel();
        pizzaPanel.setLayout(new BoxLayout(pizzaPanel, BoxLayout.Y_AXIS));
        pizzaPanel.setBackground(new Color(173, 216, 230));

        JButton pizzaButton = createPizzaButton(firstName, lastName, pizzaTypes[i], pizzaImages[i],
                smallPrices[i], mediumPrices[i], largePrices[i], xlPrices[i]);
        pizzaPanel.add(pizzaButton);

        JPanel sizeAndOrderPanel = createSizeAndOrderPanel(pizzaTypes[i], smallPrices[i], mediumPrices[i], largePrices[i], xlPrices[i]);
        pizzaPanel.add(sizeAndOrderPanel);

        pizzaButtonsPanel.add(pizzaPanel);
    }

    return pizzaButtonsPanel;
}

private JButton createPizzaButton(String firstName, String lastName, String pizzaType, String imagePath,
                                  double smallPrice, double mediumPrice, double largePrice, double xlPrice) {
    JButton pizzaButton = new JButton("<html><center>" + pizzaType + "</center></html>");

    pizzaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
    pizzaButton.setHorizontalTextPosition(SwingConstants.CENTER);

    ImageIcon icon = new ImageIcon(imagePath);
    Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    pizzaButton.setIcon(new ImageIcon(img));

    pizzaButton.setPreferredSize(new Dimension(160, 160));
    pizzaButton.setBorder(BorderFactory.createEmptyBorder());

    pizzaButton.addActionListener(e -> {
        handlePizzaButtonClick(firstName, lastName, pizzaType, smallPrice, mediumPrice, largePrice,xlPrice );
    });

    return pizzaButton;
}

private void handlePizzaButtonClick(String firstName, String lastName, String pizzaType, double smallPrice, double mediumPrice, double largePrice, double xlPrice) {
    String message = "Pizza: " + pizzaType + "\n"
            + "Prices:\n"
            + "Small: $" + smallPrice + "\n"
            + "Medium: $" + mediumPrice + "\n"
            + "Large: $" + largePrice + "\n"
            +"XLarge : $" +xlPrice;
    JOptionPane.showMessageDialog(this, message);
}



private JPanel createSizeAndOrderPanel(String pizzaType, double smallPrice, double mediumPrice, double largePrice, double xlPrice) {
    JPanel sizeAndOrderPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 rows, 1 column
    sizeAndOrderPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

    String[] sizes = {"Small", "Medium", "Large", "Extra Large"}; // Added "Extra Large"

    JButton sizeButton = new JButton("Select Size");
    sizeButton.addActionListener(e -> handleSizeButtonClick(pizzaType, sizes, smallPrice, mediumPrice, largePrice, xlPrice));
    sizeButton.setBackground(new Color(173, 216, 230)); // Set blue background color
    sizeAndOrderPanel.add(sizeButton);

    JButton orderButton = new JButton("Add to Order");
    orderButton.addActionListener(e -> handleOrderButtonClick(pizzaType));
    orderButton.setBackground(new Color(173, 216, 230)); // Set blue background color
    sizeAndOrderPanel.add(orderButton);

    return sizeAndOrderPanel;
}


private void handleSizeButtonClick(String pizzaType, String[] sizes, double smallPrice, double mediumPrice, double largePrice, double xlPrice) {
    String selectedSize = (String) JOptionPane.showInputDialog(
            this,
            "Select Size for " + pizzaType,
            "Size Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            sizes,
            sizes[0]
    );

    if (selectedSize != null) {
        double price;
        switch (selectedSize) {
            case "Small":
                price = smallPrice;
                break;
            case "Medium":
                price = mediumPrice;
                break;
            case "Large":
                price = largePrice;
                break;
            case "Extra Large":  // Change the case to match "Extra Large"
                price = xlPrice;
                break;
            default:
                price = 0.0;
                break;
        }

        String sizeLabel;
        switch (selectedSize) {
            case "Extra Large":
                sizeLabel = "XL";
                break;
            default:
                sizeLabel = selectedSize.substring(0, 1); // Use the first letter for normal sizes
                break;
        }

        String message = "Item successfully added to cart:\n"
                + "Pizza Type: " + pizzaType + "\n"
                + "Size: " + sizeLabel + "\n"
                + "Price: $" + price; // Display the price
        JOptionPane.showMessageDialog(this, message);
    }
}





     private void handleOrderButtonClick(String pizzaType) {
        // Check if a size has been selected
        if (!isSizeSelected(pizzaType)) {
            JOptionPane.showMessageDialog(this, "Please select a size before adding to the order.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Continue with the order process
        String message = "Item successfully added to cart: " + pizzaType;
        JOptionPane.showMessageDialog(this, message);
    }

    private boolean isSizeSelected(String pizzaType) {
        // Check if a size has been selected for the specified pizza type
        // You may need to modify this based on how you store the selected sizes in your application
        // For now, assuming that a size is selected if it is not null or an empty string
        return !getSizeSelection(pizzaType).isEmpty();
    }

    private String getSizeSelection(String pizzaType) {
        // Retrieve and return the selected size for the specified pizza type
        // You may need to modify this based on how you store the selected sizes in your application
        // For now, using a simple placeholder, you may need to replace it with your actual implementation
        // Example: return selectedSizes.get(pizzaType);
        return "";
    }


    private void handlePizzaButtonClick(String firstName, String lastName, String pizzaType) {
        // You can customize the behavior when a pizza button is clicked
        // For example, showing additional information about the pizza
        // For now, I'm just displaying a message
        String message = "Pizza button clicked: " + pizzaType;
        JOptionPane.showMessageDialog(this, message);
    }


    private JPanel createHeaderPanel(String firstName, String lastName) {
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
        JButton startOrderButton = new JButton("Start Your Order");
        startOrderButton.setFont(new Font("Arial", Font.BOLD, 14));
        startOrderButton.setForeground(Color.WHITE);
        startOrderButton.setBackground(new Color(204, 51, 51));
        startOrderButton.setBorderPainted(false);
        startOrderButton.setFocusPainted(false);
        startOrderButton.addActionListener(e -> JOptionPane.showMessageDialog(ClassicsPage.this, "Start Your Order button clicked"));
        buttonsPanel.add(startOrderButton);

        // "MENU" button
        JButton menuButton = new JButton("MENU");
        menuButton.setFont(new Font("Arial", Font.BOLD, 12));
        menuButton.addActionListener(e -> {
            MenuPage menuPage = new MenuPage(firstName, lastName);
            menuPage.setVisible(true);
            ClassicsPage.this.dispose(); // Close the current page
        });
        buttonsPanel.add(menuButton);

        // "DEALS" button
        JButton dealsButton = new JButton("DEALS");
        dealsButton.setFont(new Font("Arial", Font.BOLD, 12));
        dealsButton.addActionListener(e -> JOptionPane.showMessageDialog(ClassicsPage.this, "DEALS button clicked"));
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
        cartButton.addActionListener(e -> JOptionPane.showMessageDialog(ClassicsPage.this, "Cart button clicked"));
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
        SwingUtilities.invokeLater(() -> new ClassicsPage("John", "Doe").setVisible(true));
    }
}