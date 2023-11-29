package user.gui;

import user.order.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BeveragesPage extends JFrame {

    private final String[] drinkCategories = {"Sprite", "CocaCola", "Mountain Dew", "Water", "Fanta Orange"};
    private final Map<String, String> drinkImages = new HashMap<>();
    private final float[] smallPrices = {1.99f, 1.99f, 1.99f, 1.99f, 1.99f};
    private final float[] mediumPrices = {2.99f, 2.99f, 2.99f, 2.99f, 2.99f};
    private final float[] largePrices = {3.99f, 3.99f, 3.99f, 3.99f, 3.99f};
    private final float[] xlPrices = {4.99f, 4.99f, 4.99f, 4.99f, 4.99f};
    private Item newdrink = new Item();
    private static float price;
    private final Map<String, String> selectedSizes = new HashMap<>();

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

        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Arial", Font.BOLD, 18));
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        pane.add(categoryLabel, BorderLayout.CENTER);

        JPanel drinkButtonsPanel = createDrinkButtonsPanel(firstName, lastName);
        JScrollPane scrollPane = new JScrollPane(drinkButtonsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createDrinkButtonsPanel(String firstName, String lastName) {
        JPanel drinkButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        drinkButtonsPanel.setBackground(new Color(173, 216, 230));

        for (int i = 0; i < drinkCategories.length; i++) {
            JPanel drinkPanel = new JPanel();
            drinkPanel.setLayout(new BoxLayout(drinkPanel, BoxLayout.Y_AXIS));
            drinkPanel.setBackground(new Color(173, 216, 230));

            JButton drinkButton = createDrinkButton(firstName, lastName, drinkCategories[i], drinkImages.get(drinkCategories[i]), i);
            drinkPanel.add(drinkButton);

            JPanel sizeAndOrderPanel = createSizeAndOrderPanel(drinkCategories[i], i);
            drinkPanel.add(sizeAndOrderPanel);

            drinkButtonsPanel.add(drinkPanel);
        }

        return drinkButtonsPanel;
    }

    private JButton createDrinkButton(String firstName, String lastName, String drinkCategory, String imagePath, int index) {
        JButton drinkButton = new JButton("<html><center>" + drinkCategory + "</center></html>");

        drinkButton.setVerticalTextPosition(SwingConstants.BOTTOM);
        drinkButton.setHorizontalTextPosition(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        drinkButton.setIcon(new ImageIcon(img));

        drinkButton.setPreferredSize(new Dimension(160, 160));
        drinkButton.setBorder(BorderFactory.createEmptyBorder());

        drinkButton.addActionListener(e -> {
            handleDrinkButtonClick(firstName, lastName, drinkCategory, index);
        });

        return drinkButton;
    }

    private JPanel createSizeAndOrderPanel(String drinkCategory, int index) {
        JPanel sizeAndOrderPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        sizeAndOrderPanel.setBackground(new Color(173, 216, 230));

        JButton sizeButton = new JButton("Select Size");
        sizeButton.addActionListener(e -> handleSizeButtonClick(drinkCategory, index));
        sizeButton.setBackground(new Color(173, 216, 230));
        sizeAndOrderPanel.add(sizeButton);

        JButton orderButton = new JButton("Add to Order");
        orderButton.addActionListener(e -> handleOrderButtonClick(drinkCategory));
        orderButton.setBackground(new Color(173, 216, 230));
        sizeAndOrderPanel.add(orderButton);

        return sizeAndOrderPanel;
    }

    private void handleSizeButtonClick(String drinkCategory, int index) {
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
            selectedSizes.put(drinkCategory, selectedSize);

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
                    price = 0.0f;
                    break;
            }
            newdrink.setCost(price);
            String message = "Item successfully added to cart:\n"
                    + "Drink Category: " + drinkCategory + "\n"
                    + "Size: " + selectedSize + "\n"
                    + "Price: $" + price;
            JOptionPane.showMessageDialog(this, message);
        }
    }

    private void handleOrderButtonClick(String drinkCategory) {
        newdrink.setName(drinkCategory);
        newdrink.setCost(price);

        CartPage.cartItems.add(newdrink);
        String message = "Item successfully added to cart: " + drinkCategory;
        JOptionPane.showMessageDialog(this, message);
    }

    private void handleDrinkButtonClick(String firstName, String lastName, String drinkCategory, int index) {
        String message = "Drink: " + drinkCategory + "\n"
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
        headerPanel.setBackground(new Color(255, 204, 102));

        ImageIcon logoIcon = new ImageIcon("gui/images/Pizza-logo-design-template-Vector-PNG.png");
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
            BeveragesPage.this.dispose();
        });
        buttonsPanel.add(menuButton);

        JButton dealsButton = new JButton("DEALS");
        dealsButton.setFont(new Font("Arial", Font.BOLD, 12));
        dealsButton.addActionListener(e -> {
            DealsPage dealsPage = new DealsPage("John", "Doe");
            dealsPage.setVisible(true);
            BeveragesPage.this.dispose();
        });
        buttonsPanel.add(dealsButton);

        JButton rewardsButton = new JButton("REWARDS");
        rewardsButton.setFont(new Font("Arial", Font.BOLD, 12));
        rewardsButton.addActionListener(e -> {
            RewardsPage rewardsPage = new RewardsPage("John", "Doe");
            rewardsPage.setVisible(true);
            BeveragesPage.this.dispose();
        });
        buttonsPanel.add(rewardsButton);

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
