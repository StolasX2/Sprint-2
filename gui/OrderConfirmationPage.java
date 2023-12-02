package user.gui;

import user.order.Dessert;
import user.order.Item;
import user.order.Pizza;
import user.order.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class OrderConfirmationPage extends JFrame {

    private final List<Item> orderedItems;

    public OrderConfirmationPage(String firstName, String lastName, List<Item> orderedItems) {
        super("Order Confirmation");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        this.orderedItems = orderedItems;

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
            OrderConfirmationPage.this.dispose();
        });
        buttonsPanel.add(menuButton);


        headerPanel.add(buttonsPanel, BorderLayout.EAST);

        return headerPanel;
    }

private JPanel createContentPanel() {
    JPanel contentPanel = new JPanel(new BorderLayout());

    JPanel thankYouPanel = createThankYouPanel();
    contentPanel.add(thankYouPanel, BorderLayout.NORTH);

    JPanel orderDetailsPanel = createOrderDetailsPanel();
    contentPanel.add(orderDetailsPanel, BorderLayout.WEST);

    JPanel pizzaImagePanel = createPizzaImagePanel();
    contentPanel.add(pizzaImagePanel, BorderLayout.CENTER);

    JPanel waitTimePanel = createWaitTimePanel();
    contentPanel.add(waitTimePanel, BorderLayout.EAST);

    JButton backToMenuButton = new JButton("Back to Menu");
    backToMenuButton.setFont(new Font("Arial", Font.BOLD, 12));
    backToMenuButton.addActionListener(e -> {
        MenuPage menuPage = new MenuPage("John", "Doe");
        menuPage.setVisible(true);
        OrderConfirmationPage.this.dispose();
    });

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(backToMenuButton);
    contentPanel.add(buttonPanel, BorderLayout.SOUTH);

    return contentPanel;
}

    private JPanel createThankYouPanel() {
        JPanel thankYouPanel = new JPanel();
        thankYouPanel.setBackground(new Color(255, 204, 102));

        JLabel thankYouLabel = new JLabel("Thank you for choosing The Urban Slice!");
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 24));
        thankYouLabel.setHorizontalAlignment(JLabel.CENTER);

        thankYouPanel.add(thankYouLabel);

        return thankYouPanel;
    }

    private JPanel createOrderDetailsPanel() {
        JPanel orderDetailsPanel = new JPanel();
        orderDetailsPanel.setLayout(new GridLayout(orderedItems.size() + 1, 1));

        JLabel orderLabel = new JLabel("Order Details:");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        orderDetailsPanel.add(orderLabel);

        for (Item item : orderedItems) {
            JLabel itemLabel = new JLabel(item.toString());
            orderDetailsPanel.add(itemLabel);
        }

        return orderDetailsPanel;
    }

    private JPanel createPizzaImagePanel() {
        JPanel pizzaImagePanel = new JPanel();
        pizzaImagePanel.setBackground(Color.WHITE);

        ImageIcon pizzaIcon = new ImageIcon("C:\\Users\\ebend\\OneDrive\\Desktop\\VsCode Projects\\TheUrbanSlice\\gui\\images\\pepperoni.jpeg");
        Image pizzaImage = pizzaIcon.getImage();
        int pizzaSize = 200;
        Image resizedPizza = pizzaImage.getScaledInstance(pizzaSize, pizzaSize, Image.SCALE_SMOOTH);
        ImageIcon resizedPizzaIcon = new ImageIcon(resizedPizza);

        JLabel pizzaLabel = new JLabel(resizedPizzaIcon);
        pizzaImagePanel.add(pizzaLabel);

        return pizzaImagePanel;
    }

    private JPanel createWaitTimePanel() {
        JPanel waitTimePanel = new JPanel();
        waitTimePanel.setBackground(new Color(255, 204, 102)); // Light orange background color

        JLabel waitTimeLabel = new JLabel("Estimated Wait Time: 15 minutes");
        waitTimeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        waitTimeLabel.setHorizontalAlignment(JLabel.CENTER);

        waitTimePanel.add(waitTimeLabel);

        return waitTimePanel;
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
        pane.add(contentPanel, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }
    

    public static void main(String[] args) {

        List<Item> orderedItems = new java.util.ArrayList<>();
        Order.getItemList().add(new Pizza("Large", 12.99f,"Pizza"));
        Order.getItemList().add(new Dessert(5.99f, "Brownie" ));

        SwingUtilities.invokeLater(() -> {
            OrderConfirmationPage confirmationPage = new OrderConfirmationPage("John", "Doe", orderedItems);
            confirmationPage.setVisible(true);
        });
    }
}
