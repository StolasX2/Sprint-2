package user.gui;

import user.order.*;


import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CartPage extends JFrame {

    Item removeditem;
   public String firstName;
     public String lastName;
    public static LinkedList<Item> cartItems = new LinkedList<Item>();
 public CartPage(String firstName, String lastName, LinkedList<Item> cartItems) {
        super("The Urban Slice Cart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        CartPage.cartItems = cartItems;
        this.firstName = firstName;
        this.lastName = lastName;

        addComponentsToPane(getContentPane(), firstName, lastName);
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

        // "MENU" button
        JButton menuButton = new JButton("MENU");
        menuButton.setFont(new Font("Arial", Font.BOLD, 12));
        menuButton.addActionListener(e -> {
            MenuPage menuPage = new MenuPage(firstName, lastName);
            menuPage.setVisible(true);
            CartPage.this.dispose(); // Close the current page
        });
        buttonsPanel.add(menuButton);

        // "Cart" button
        JButton cartButton = new JButton("Cart");
        cartButton.setFont(new Font("Arial", Font.BOLD, 12));
        cartButton.addActionListener(e -> JOptionPane.showMessageDialog(CartPage.this, "Cart button clicked"));
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

 private void addComponentsToPane(Container pane, String firstName, String lastName) {
    pane.setLayout(new BorderLayout());
    pane.setBackground(new Color(173, 216, 230)); // Set blue background color

    // Add the header
    JPanel headerPanel = createHeaderPanel(firstName, lastName);
    pane.add(headerPanel, BorderLayout.NORTH);

    // Content specific to Cart page
    JPanel contentPanel = createContentPanel();
    JScrollPane scrollPane = new JScrollPane(contentPanel);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    pane.add(scrollPane, BorderLayout.CENTER);

    // Add the footer
    JPanel footerPanel = createFooterPanel();
    pane.add(footerPanel, BorderLayout.SOUTH);
}

    public JPanel createCartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Iterate over cart items and add them to the cart panel
        for (Item cartItem : cartItems) {
            JPanel itemPanel = createCartItemPanel(cartItem); // Pass 'cartItem' here
            cartPanel.add(itemPanel);
        }

        // Add subtotal, tax, and order total
        JPanel totalsPanel = createTotalsPanel();
        cartPanel.add(totalsPanel);

        return cartPanel;
    }
    private JPanel createTotalsPanel() {
        JPanel totalsPanel = new JPanel(new GridLayout(3, 1));
        totalsPanel.setBackground(new Color(173, 216, 230)); // Set blue background color

        // Calculate subtotal, tax, and order total
        double subtotal = calculateSubtotal();
        double tax = subtotal * 0.07; // Assuming 7% tax, adjust as needed
        double orderTotal = subtotal + tax;

        // Add labels to the totals panel
        JLabel subtotalLabel = new JLabel("Subtotal: $" + String.format("%.2f", subtotal));
        JLabel taxLabel = new JLabel("Tax (7%): $" + String.format("%.2f", tax));
        JLabel orderTotalLabel = new JLabel("Order Total: $" + String.format("%.2f", orderTotal));

        // Add labels to the totals panel
        totalsPanel.add(subtotalLabel);
        totalsPanel.add(taxLabel);
        totalsPanel.add(orderTotalLabel);

        return totalsPanel;
    }

    public double calculateSubtotal() {
        double subtotal = 0.0;
        for (Item cartItem : cartItems) {
            subtotal += cartItem.getCost();
        }
        return subtotal;
    }

 private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Your Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        // Cart items panel
        JPanel cartPanel = createCartPanel();
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        contentPanel.add(cartScrollPane, BorderLayout.CENTER);

        // Continue to Payment button
        JButton continueToPaymentButton = new JButton("Continue to Payment");
        continueToPaymentButton.setFont(new Font("Arial", Font.BOLD, 12));
        continueToPaymentButton.addActionListener(e -> {
            PaymentPage paymentPage = new PaymentPage(firstName, lastName,  CartPage.this);
            paymentPage.setVisible(true);
            CartPage.this.dispose(); // Close the current page
        });
        contentPanel.add(continueToPaymentButton, BorderLayout.SOUTH);

        return contentPanel;
    }
    private JPanel createCartItemPanel(Item item) {
        JPanel cartItemPanel = new JPanel();
        cartItemPanel.setLayout(new BorderLayout());
        cartItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cartItemPanel.setBackground(Color.WHITE);

        // Image on the left
        ImageIcon itemIcon = new ImageIcon("Pizza-logo-design-template-Vector-PNG.png");
        Image itemImage = itemIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedItemIcon = new ImageIcon(itemImage);

        JLabel imageLabel = new JLabel(resizedItemIcon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cartItemPanel.add(imageLabel, BorderLayout.WEST);

        // Details in the center
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(Item.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanel.add(nameLabel);

        JLabel quantityLabel = new JLabel("Quantity: 1");
        detailsPanel.add(quantityLabel);

        JLabel priceLabel = new JLabel("Price: $" + Item.getCost()); // Corrected to getCost()
        detailsPanel.add(priceLabel);

        cartItemPanel.add(detailsPanel, BorderLayout.CENTER);

        // Remove button on the right
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> handleRemoveButtonClick((Item) item)); // Pass 'item' here
        cartItemPanel.add(removeButton, BorderLayout.EAST);

        return cartItemPanel;
    }

    private void handleRemoveButtonClick(Item removedItem) {
        cartItems.remove(removedItem);
        refreshCartPanel();
        JOptionPane.showMessageDialog(this, "Item removed: " + removedItem.getName());
    }

    private void refreshCartPanel() {
        // Refresh the cart panel after removing an item
        JPanel cartPanel = createCartPanel();
        JScrollPane scrollPane = new JScrollPane(cartPanel);

        // Remove the existing cart panel before adding the updated one
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                getContentPane().remove(component);
                break; // Assuming there's only one JScrollPane, exit the loop after removal
            }
        }

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

}