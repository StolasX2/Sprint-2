package user.gui;


import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SpecialtiesPage extends JFrame {

    private final String[] pizzaCategories = {"Margherita", "Chicken Alfredo", "BBQ Chicken", "Spinach Artichoke", "Hawaiian"};
    private final Map<String, String> pizzaImages = new HashMap<>();
    private final double[] smallPrices = {13.99, 13.99, 13.99, 13.99, 13.99};
    private final double[] mediumPrices = {15.99, 15.99, 15.99, 15.99, 15.99};
    private final double[] largePrices = {17.99, 17.99, 17.99, 17.99, 17.99};
    private final double[] xlPrices = {21.99, 21.99, 21.99, 21.99, 21.99};
    private final Map<String, String> selectedSizes = new HashMap<>();

    public SpecialtiesPage(String firstName, String lastName) {
        super("The Urban Slice Specialties");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pizzaImages.put("Margherita", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\download.jpg");
        pizzaImages.put("Chicken Alfredo", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\Chicken-Alfredo-Pizza-8.jpg");
        pizzaImages.put("BBQ Chicken", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\06mag-eat-image2-videoSixteenByNineJumbo1600.jpg");
        pizzaImages.put("Spinach Artichoke", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\wdy060120pizza-004-1590690616.jpg");
        pizzaImages.put("Hawaiian", "C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\download (1).jpg");

        addComponentsToPane(getContentPane(), firstName, lastName, "Specialties");
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName, String category) {
        pane.setLayout(new BorderLayout());
        pane.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Add the header
        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        // Content specific to Specialties page
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
        pizzaButtonsPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        for (int i = 0; i < pizzaCategories.length; i++) {
            JPanel pizzaPanel = new JPanel();
            pizzaPanel.setLayout(new BoxLayout(pizzaPanel, BoxLayout.Y_AXIS));
            pizzaPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

            JButton pizzaButton = createPizzaButton(firstName, lastName, pizzaCategories[i], pizzaImages.get(pizzaCategories[i]), i);
            pizzaPanel.add(pizzaButton);

            JPanel sizeAndOrderPanel = createSizeAndOrderPanel(pizzaCategories[i], i);
            pizzaPanel.add(sizeAndOrderPanel);

            pizzaButtonsPanel.add(pizzaPanel);
        }

        return pizzaButtonsPanel;
    }

    private JButton createPizzaButton(String firstName, String lastName, String pizzaCategory, String imagePath, int index) {
        JButton pizzaButton = new JButton("<html><center>" + pizzaCategory + "</center></html>");

        pizzaButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        pizzaButton.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        pizzaButton.setIcon(new ImageIcon(img));

        pizzaButton.setPreferredSize(new Dimension(160, 160));
        pizzaButton.setBorder(BorderFactory.createEmptyBorder());

        pizzaButton.addActionListener(e -> {
            handlePizzaButtonClick(firstName, lastName, pizzaCategory, index);
        });

        return pizzaButton;
    }

    private JPanel createSizeAndOrderPanel(String pizzaCategory, int index) {
        JPanel sizeAndOrderPanel = new JPanel(new GridLayout(2, 1, 5, 5)); // 2 rows, 1 column
        sizeAndOrderPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        JButton sizeButton = new JButton("Select Size");
        sizeButton.addActionListener(e -> handleSizeButtonClick(pizzaCategory, index));
        sizeButton.setBackground(new Color(173, 216, 230)); // Set blue background color
        sizeAndOrderPanel.add(sizeButton);

        JButton orderButton = new JButton("Add to Order");
        orderButton.addActionListener(e -> handleOrderButtonClick(pizzaCategory));
        orderButton.setBackground(new Color(173, 216, 230)); // Set blue background color
        sizeAndOrderPanel.add(orderButton);

        return sizeAndOrderPanel;
    }

    private void handleSizeButtonClick(String pizzaCategory, int index) {
        String[] sizes = {"Small", "Medium", "Large", "Extra Large"};
        String selectedSize = (String) JOptionPane.showInputDialog(
                this,
                "Select Size for " + pizzaCategory,
                "Size Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sizes,
                sizes[0]
        );

        if (selectedSize != null) {
            selectedSizes.put(pizzaCategory, selectedSize);

            double price;
            switch (selectedSize) {
                case "Small":
                    price = smallPrices[index];
                    break;
                case "Medium":
                    price = mediumPrices[index];
                    break;
                case "Large":
                    price = largePrices[index];
                    break;
                case "Extra Large":
                    price = xlPrices[index];
                    break;
                default:
                    price = 0.0;
                    break;
            }

            String message = "Item successfully added to cart:\n"
                    + "Pizza Category: " + pizzaCategory + "\n"
                    + "Size: " + selectedSize + "\n"
                    + "Price: $" + price;
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void handleOrderButtonClick(String pizzaCategory) {
        String message = "Item successfully added to cart: " + pizzaCategory;
        JOptionPane.showMessageDialog(this, message);
    }

    private void handlePizzaButtonClick(String firstName, String lastName, String pizzaCategory, int index) {
        String message = "Pizza: " + pizzaCategory + "\n"
                + "Prices:\n"
                + "Small: $" + smallPrices[index] + "\n"
                + "Medium: $" + mediumPrices[index] + "\n"
                + "Large: $" + largePrices[index] + "\n"
                + "XLarge : $" + xlPrices[index];
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

    // "MENU" button
    JButton menuButton = new JButton("MENU");
    menuButton.setFont(new Font("Arial", Font.BOLD, 12));
    menuButton.addActionListener(e -> {
        MenuPage menuPage = new MenuPage(firstName, lastName);
        menuPage.setVisible(true);
        SpecialtiesPage.this.dispose(); // Close the current page
    });
    buttonsPanel.add(menuButton);

    // "Cart" button
    JButton cartButton = new JButton("Cart");
    cartButton.setFont(new Font("Arial", Font.BOLD, 12));
    cartButton.addActionListener(e -> JOptionPane.showMessageDialog(SpecialtiesPage.this, "Cart button clicked"));
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
        SwingUtilities.invokeLater(() -> new SpecialtiesPage("John", "Doe").setVisible(true));
    }
}
