package user.gui;

import user.order.*;


import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CartPage extends JFrame {

    public String firstName;
    public String lastName;
    public static LinkedList<Item> cartItems = new LinkedList<Item>();
    public CartPage(String firstName, String lastName) {
        super("The Urban Slice Cart");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.firstName = firstName;
        this.lastName = lastName;

        addComponentsToPane(getContentPane(), firstName, lastName);
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
            CartPage.this.dispose();
        });
        buttonsPanel.add(menuButton);

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
        pane.setBackground(new Color(173, 216, 230));

        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        JPanel contentPanel = createContentPanel();
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        pane.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

    public JPanel createCartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        cartPanel.setBackground(new Color(173, 216, 230));

        for (Item cartItem : cartItems) {
            JPanel itemPanel = createCartItemPanel(cartItem);
            cartPanel.add(itemPanel);
        }

        JPanel totalsPanel = createTotalsPanel();
        cartPanel.add(totalsPanel);

        return cartPanel;
    }
    private JPanel createTotalsPanel() {
        JPanel totalsPanel = new JPanel(new GridLayout(3, 1));
        totalsPanel.setBackground(new Color(173, 216, 230));

        double subtotal = calculateSubtotal();
        double tax = subtotal * 0.07;
        double orderTotal = subtotal + tax;

        JLabel subtotalLabel = new JLabel("Subtotal: $" + String.format("%.2f", subtotal));
        JLabel taxLabel = new JLabel("Tax (7%): $" + String.format("%.2f", tax));
        JLabel orderTotalLabel = new JLabel("Order Total: $" + String.format("%.2f", orderTotal));

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

        JLabel titleLabel = new JLabel("Your Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        contentPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel cartPanel = createCartPanel();
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        contentPanel.add(cartScrollPane, BorderLayout.CENTER);

        JButton continueToPaymentButton = new JButton("Continue to Payment");
        continueToPaymentButton.setFont(new Font("Arial", Font.BOLD, 12));
        continueToPaymentButton.addActionListener(e -> {
            PaymentPage paymentPage = new PaymentPage(firstName, lastName,  CartPage.this);
            paymentPage.setVisible(true);
            CartPage.this.dispose();
        });
        contentPanel.add(continueToPaymentButton, BorderLayout.SOUTH);
        return contentPanel;
    }
    private JPanel createCartItemPanel(Item item) {
        JPanel cartItemPanel = new JPanel();
        cartItemPanel.setLayout(new BorderLayout());
        cartItemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cartItemPanel.setBackground(Color.WHITE);

        ImageIcon itemIcon = new ImageIcon("Pizza-logo-design-template-Vector-PNG.png");
        Image itemImage = itemIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon resizedItemIcon = new ImageIcon(itemImage);

        JLabel imageLabel = new JLabel(resizedItemIcon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cartItemPanel.add(imageLabel, BorderLayout.WEST);

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel(Item.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        detailsPanel.add(nameLabel);

        JLabel quantityLabel = new JLabel("Quantity: 1");
        detailsPanel.add(quantityLabel);

        JLabel priceLabel = new JLabel("Price: $" + Item.getCost());
        detailsPanel.add(priceLabel);

        cartItemPanel.add(detailsPanel, BorderLayout.CENTER);

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> handleRemoveButtonClick((Item) item));
        cartItemPanel.add(removeButton, BorderLayout.EAST);

        return cartItemPanel;
    }

    private void handleRemoveButtonClick(Item removedItem) {
        cartItems.remove(removedItem);
        refreshCartPanel();
        JOptionPane.showMessageDialog(this, "Item removed: " + removedItem.getName());
    }

    private void refreshCartPanel() {
        JPanel cartPanel = createCartPanel();
        JScrollPane scrollPane = new JScrollPane(cartPanel);

        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                getContentPane().remove(component);
                break;
            }
        }

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

}