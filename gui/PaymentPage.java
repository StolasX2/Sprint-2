package user.gui;

import user.order.Dessert;
import user.order.Item;
import user.order.Order;
import user.order.Pizza;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static user.gui.CartPage.cartItems;

public class PaymentPage extends JFrame {
    order.Payment pay;

    private final CartPage cartPage;

       public PaymentPage(String firstName, String lastName, CartPage cartPage) {
        super("Payment Page");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.cartPage = cartPage;

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
            PaymentPage.this.dispose(); // Close the current page
        });
        buttonsPanel.add(menuButton);

        // "Cart" button
        JButton cartButton = new JButton("Cart");
        cartButton.setFont(new Font("Arial", Font.BOLD, 12));
        cartButton.addActionListener(e -> {
            cartPage.setVisible(true);
            PaymentPage.this.dispose(); // Close the current page
        });
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

        // Add the header
        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        // Content specific to Payment page
        JPanel contentPanel = createContentPanel();
        pane.add(contentPanel, BorderLayout.CENTER);

        // Add the footer
        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }
private JPanel createContentPanel() {
    JPanel contentPanel = new JPanel(new BorderLayout());

    // Checkout panel
    JPanel checkoutPanel = createCheckoutPanel();
    contentPanel.add(checkoutPanel, BorderLayout.WEST);

    // Payment panel
    JPanel paymentPanel = createPaymentPanel();
    contentPanel.add(paymentPanel, BorderLayout.CENTER);

    // Confirmation panel
    JPanel confirmationPanel = createConfirmationPanel();
    contentPanel.add(confirmationPanel, BorderLayout.EAST);

    return contentPanel;
}


    private JPanel createCheckoutPanel() {
        JPanel checkoutPanel = new JPanel();
        checkoutPanel.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Your Cart");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        checkoutPanel.add(titleLabel, BorderLayout.NORTH);

        // Cart items and total
        JPanel cartPanel = cartPage.createCartPanel();
        JScrollPane cartScrollPane = new JScrollPane(cartPanel);
        checkoutPanel.add(cartScrollPane, BorderLayout.CENTER);

        return checkoutPanel;
    }

    private JPanel createPaymentPanel() {
        JPanel paymentPanel = new JPanel();
        paymentPanel.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Payment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        paymentPanel.add(titleLabel, BorderLayout.NORTH);

        // Balance Due
        double orderTotal = cartPage.calculateSubtotal(); // Get order total from cart page
        JLabel balanceDueLabel = new JLabel("Balance Due: $" + orderTotal);
        balanceDueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceDueLabel.setHorizontalAlignment(JLabel.CENTER);
        paymentPanel.add(balanceDueLabel, BorderLayout.CENTER);

        // Payment options
        JPanel paymentOptionsPanel = createPaymentOptionsPanel();
        paymentPanel.add(paymentOptionsPanel, BorderLayout.SOUTH);

        return paymentPanel;
    }

    private JPanel createBalanceAndOptionsPanel() {
        JPanel balanceAndOptionsPanel = new JPanel();
        balanceAndOptionsPanel.setLayout(new BorderLayout());

        // Balance due
        double balanceDue = calculateBalanceDue();
        JLabel balanceLabel = new JLabel("Balance Due: $" + balanceDue);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        balanceAndOptionsPanel.add(balanceLabel, BorderLayout.NORTH);

        // Payment options
        JPanel paymentOptionsPanel = createPaymentOptionsPanel();
        balanceAndOptionsPanel.add(paymentOptionsPanel, BorderLayout.CENTER);

        return balanceAndOptionsPanel;
    }
        private JPanel createOrderDetailsPanel() {
        JPanel orderDetailsPanel = new JPanel();
        //orderDetailsPanel.setLayout(new GridLayout(Order.getItemList().size(), 1));

        for (Item item : Order.getItemList()) {
            JLabel itemLabel = new JLabel(item.toString());
            orderDetailsPanel.add(itemLabel);
        }

        JLabel totalLabel = new JLabel("Total: $" + cartPage.calculateSubtotal());
        orderDetailsPanel.add(totalLabel);

        return orderDetailsPanel;
    }

        private JPanel createConfirmationPanel() {
        JPanel confirmationPanel = new JPanel();
        confirmationPanel.setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Order Confirmation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        confirmationPanel.add(titleLabel, BorderLayout.NORTH);

        // Order details
        JPanel orderDetailsPanel = createOrderDetailsPanel();
        confirmationPanel.add(orderDetailsPanel, BorderLayout.CENTER);

        // Place Order button
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setFont(new Font("Arial", Font.BOLD, 16));
        placeOrderButton.addActionListener(e -> {
            handlePlaceOrderButtonClick();
        });
        confirmationPanel.add(placeOrderButton, BorderLayout.SOUTH);

        return confirmationPanel;
    }
       private void handlePlaceOrderButtonClick() {
    // Handle place order logic here
    JOptionPane.showMessageDialog(this, "Order Placed Successfully!");

    // Get first and last names
    String firstName = cartPage.firstName;
    String lastName = cartPage.lastName;

    // Create and show the OrderConfirmationPage with proper names
    List<Item> cartItems = Order.getItemList(); // Get cart items
    OrderConfirmationPage confirmationPage = new OrderConfirmationPage(firstName, lastName, cartItems);
    confirmationPage.setVisible(true);

    this.dispose(); // Close the current PaymentPage
}

     private void showCashPopup() {
        // Show a pop-up for cash payment instructions
        String message = "Please be sure to have cash in hand when you receive your order at the restaurant.";
        JOptionPane.showMessageDialog(this, message, "Cash Payment Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showCardPopup() {
        // Show a pop-up for entering card details
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Card Number:"));
        JTextField cardNumberField = new JTextField();
        panel.add(cardNumberField);
        panel.add(new JLabel("Expiration Date:"));
        JTextField expirationDateField = new JTextField();
        panel.add(expirationDateField);
        panel.add(new JLabel("Security Code:"));
        JTextField securityCodeField = new JTextField();
        panel.add(securityCodeField);
        panel.add(new JLabel("Billing Zip Code:"));
        JTextField billingZipCodeField = new JTextField();
        panel.add(billingZipCodeField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Enter Card Details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Process card details
            handlePaymentButtonClick(cartPage.calculateSubtotal(), true, false, false);
        }
    }

    private void showCheckPopup() {
        // Show a pop-up for entering check details
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Account Number:"));
        JTextField accountNumberField = new JTextField();
        panel.add(accountNumberField);
        panel.add(new JLabel("Routing Number:"));
        JTextField routingNumberField = new JTextField();
        panel.add(routingNumberField);
        panel.add(new JLabel("Check Number:"));
        JTextField checkNumberField = new JTextField();
        panel.add(checkNumberField);

        int result = JOptionPane.showConfirmDialog(this, panel,
                "Enter Check Details", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            // Process check details
            handlePaymentButtonClick(cartPage.calculateSubtotal(), false, false, true);
        }
    }

    private JPanel createPaymentOptionsPanel() {
        JPanel paymentOptionsPanel = new JPanel();
        paymentOptionsPanel.setLayout(new GridLayout(3, 1));

        // Payment option 1
        JRadioButton option1 = new JRadioButton("Pay Now With Cash");
        option1.setFont(new Font("Arial", Font.PLAIN, 14));
        option1.addActionListener(e -> showCashPopup());
        paymentOptionsPanel.add(option1);

        // Payment option 2
        JRadioButton option2 = new JRadioButton("Pay Now With Check");
        option2.setFont(new Font("Arial", Font.PLAIN, 14));
        option2.addActionListener(e -> showCheckPopup());
        paymentOptionsPanel.add(option2);

        // Payment option 3
        JRadioButton option3 = new JRadioButton("Pay Now With Card");
        option3.setFont(new Font("Arial", Font.PLAIN, 14));
        option3.addActionListener(e -> showCardPopup());
        paymentOptionsPanel.add(option3);

        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

        return paymentOptionsPanel;
    }

   private double calculateBalanceDue() {
    return cartPage.calculateSubtotal();
}
    private void handlePaymentButtonClick(double orderTotal, boolean creditCard, boolean payPal, boolean cashOnDelivery) {
        // Handle payment logic here based on selected options
        // For simplicity, just display a message
        JOptionPane.showMessageDialog(this, "Payment Successful!\nTotal Amount: $" + orderTotal);
    }

    public static void main(String[] args) {

           Order newOrder = new Order();
        // Sample cart items

        cartItems.add(new Pizza("Large", 12.99f,"Pizza"));
        cartItems.add(new Dessert(5.99f, "Brownie" ));

        SwingUtilities.invokeLater(() -> {
            CartPage cartPage = new CartPage("John", "doe",  cartItems);
            PaymentPage paymentPage = new PaymentPage("John", "Doe", cartPage);
            cartPage.setVisible(true);
            paymentPage.setVisible(true);
        });
    }
}
