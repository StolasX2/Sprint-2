package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class DessertsPage extends JFrame {

    private final String[] dessertTypes = {"Brownie Tray", "Cookie Tray", "Pretzel Bites"};
    private final Map<String, String> dessertImages = new HashMap<>();
    private final double[] smallPrices = {5.99, 5.99, 5.99};
    private final double[] mediumPrices = {6.99, 6.99, 6.99};
    private final double[] largePrices = {7.99, 7.99, 7.99};
    private final double[] xlPrices = {8.99, 8.99, 8.99};
    private final Map<String, String> selectedSizes = new HashMap<>();

    public DessertsPage(String firstName, String lastName) {
        super("The Urban Slice Desserts");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        dessertImages.put("Brownie Tray", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\Brownies_L_1080x1080.png");
        dessertImages.put("Cookie Tray", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\cookieTrayLarge_PDP.png");
        dessertImages.put("Pretzel Bites", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\pretzel-bites-1.jpg");

        addComponentsToPane(getContentPane(), firstName, lastName, "Desserts");
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName, String category) {
        pane.setLayout(new BorderLayout());
        pane.setBackground(new Color(173, 216, 230));

        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(categoryLabel, BorderLayout.CENTER);

        JPanel dessertButtonsPanel = createDessertButtonsPanel(firstName, lastName);
        JScrollPane scrollPane = new JScrollPane(dessertButtonsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createDessertButtonsPanel(String firstName, String lastName) {
        JPanel dessertButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        dessertButtonsPanel.setBackground(new Color(173, 216, 230));

        for (int i = 0; i < dessertTypes.length; i++) {
            JPanel dessertPanel = new JPanel();
            dessertPanel.setLayout(new BoxLayout(dessertPanel, BoxLayout.Y_AXIS));
            dessertPanel.setBackground(new Color(173, 216, 230));

            JButton dessertButton = createDessertButton(firstName, lastName, dessertTypes[i], dessertImages.get(dessertTypes[i]));
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

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        dessertButton.setIcon(new ImageIcon(img));

        dessertButton.setPreferredSize(new Dimension(160, 160));
        dessertButton.setBorder(BorderFactory.createEmptyBorder());

        dessertButton.addActionListener(e -> {
            handleDessertButtonClick(firstName, lastName, dessertType);
        });

        return dessertButton;
    }

    private JPanel createOrderPanel(String dessertType) {
        JPanel orderPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        orderPanel.setBackground(new Color(173, 216, 230));

        JButton sizeButton = new JButton("Select Size");
        sizeButton.addActionListener(e -> handleSizeButtonClick(dessertType));
        sizeButton.setBackground(new Color(173, 216, 230));
        orderPanel.add(sizeButton);

        JButton orderButton = new JButton("Add to Order");
        orderButton.addActionListener(e -> handleOrderButtonClick(dessertType));
        orderPanel.add(orderButton);

        return orderPanel;
    }

    private void handleSizeButtonClick(String dessertType) {
        String[] sizes = {"Small", "Medium", "Large", "Extra Large"};
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
            selectedSizes.put(dessertType, selectedSize);

            double price;
            switch (selectedSize) {
                case "Small":
                    price = smallPrices[getIndex(dessertType)];
                    break;
                case "Medium":
                    price = mediumPrices[getIndex(dessertType)];
                    break;
                case "Large":
                    price = largePrices[getIndex(dessertType)];
                    break;
                case "Extra Large":
                    price = xlPrices[getIndex(dessertType)];
                    break;
                default:
                    price = 0.0;
                    break;
            }

            String message = "Item successfully added to cart:\n"
                    + "Dessert Type: " + dessertType + "\n"
                    + "Size: " + selectedSize + "\n"
                    + "Price: $" + price;
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void handleOrderButtonClick(String dessertType) {
        String message = "Item successfully added to cart: " + dessertType;
        JOptionPane.showMessageDialog(this, message);
    }

  private void handleDessertButtonClick(String firstName, String lastName, String dessertType) {
    int index = getIndex(dessertType);

    String message = "Dessert: " + dessertType + "\n"
            + "Prices:\n"
            + "Small: $" + smallPrices[index] + "\n"
            + "Medium: $" + mediumPrices[index] + "\n"
            + "Large: $" + largePrices[index] + "\n"
            + "Extra Large: $" + xlPrices[index];

    JOptionPane.showMessageDialog(this, message);
}

    private int getIndex(String dessertType) {
        for (int i = 0; i < dessertTypes.length; i++) {
            if (dessertTypes[i].equals(dessertType)) {
                return i;
            }
        }
        return -1; // Should not happen if the dessertType is in the array
    }

    private JPanel createHeaderPanel(String firstName, String lastName) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(255, 204, 102));

        ImageIcon logoIcon = new ImageIcon("C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\MicrosoftTeams-image.png");
        Image logoImage = logoIcon.getImage();
        int logoSize = 80;
        Image resizedLogo = logoImage.getScaledInstance(logoSize, logoSize, Image.SCALE_SMOOTH);
        ImageIcon resizedLogoIcon = new ImageIcon(resizedLogo);

        JLabel logoLabel = new JLabel(resizedLogoIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        headerPanel.add(logoLabel, BorderLayout.WEST);

        JLabel greetingLabel = new JLabel("Hi, " + firstName + " " + lastName + "!");
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        greetingLabel.setHorizontalAlignment(JLabel.CENTER);
        greetingLabel.setForeground(new Color(51, 51, 51));
        headerPanel.add(greetingLabel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setOpaque(false);

        JButton menuButton = new JButton("MENU");
        menuButton.setFont(new Font("Arial", Font.BOLD, 12));
        menuButton.addActionListener(e -> {
            MenuPage menuPage = new MenuPage(firstName, lastName);
            menuPage.setVisible(true);
            DessertsPage.this.dispose();
        });
        buttonsPanel.add(menuButton);

        JButton dealsButton = new JButton("DEALS");
        dealsButton.setFont(new Font("Arial", Font.BOLD, 12));
        dealsButton.addActionListener(e -> {
            DealsPage dealsPage = new DealsPage("John", "Doe");
            dealsPage.setVisible(true);
            DessertsPage.this.dispose();
        });
        buttonsPanel.add(dealsButton);

        JButton rewardsButton = new JButton("REWARDS");
        rewardsButton.setFont(new Font("Arial", Font.BOLD, 12));
        rewardsButton.addActionListener(e -> {
            RewardsPage rewardsPage = new RewardsPage("John", "Doe");
            rewardsPage.setVisible(true);
            DessertsPage.this.dispose();
        });
        buttonsPanel.add(rewardsButton);

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
