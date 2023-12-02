package user.gui;


import javax.swing.*;
import java.awt.*;

import static user.gui.CartPage.cartItems;

public class MenuPage extends JFrame {
    private JLabel welcomeLabel;

    public MenuPage(String firstName, String lastName) {
        super("The Urban Slice Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addComponentsToPane(getContentPane(), firstName, lastName);
    }


private void addComponentsToPane(Container pane, String firstName, String lastName) {
    pane.setLayout(new BorderLayout());

    JPanel headerPanel = createHeaderPanel(firstName, lastName);
    pane.add(headerPanel, BorderLayout.NORTH);


    JPanel categoriesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    categoriesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    categoriesPanel.setBackground(new Color(173, 216, 230));

    JButton classicsButton = createCategoryButton("Classics", "classics_image.png");
    categoriesPanel.add(classicsButton);

    JButton beveragesButton = createCategoryButton("Beverages", "beverages_image.png");
    beveragesButton.setFont(new Font("Arial", Font.BOLD, 12));
    beveragesButton.addActionListener(e -> handleCategorySelection("Beverages"));
    categoriesPanel.add(beveragesButton);



    JButton createYourOwnButton = new JButton("Create Your Own Pizza!");
    createYourOwnButton.setFont(new Font("Arial", Font.BOLD, 14));
    createYourOwnButton.addActionListener(e -> handleCreateYourOwn());
    categoriesPanel.add(Box.createRigidArea(new Dimension(10, 0)));
    categoriesPanel.add(createYourOwnButton);

    pane.add(categoriesPanel, BorderLayout.CENTER);

    JPanel footerPanel = createFooterPanel();
    pane.add(footerPanel, BorderLayout.SOUTH);
}




    private JButton createCategoryButton(String categoryName, String imagePath) {
        JButton button = new JButton(categoryName);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(img));
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setPreferredSize(new Dimension(100, 100));
        button.addActionListener(e -> handleCategorySelection(categoryName));
        return button;
    }

   private void handleCategorySelection(String categoryName) {
    if ("Beverages".equals(categoryName)) {
        BeveragesPage beveragesPage = new BeveragesPage("John", "Doe");
        beveragesPage.setVisible(true);
        this.dispose();
    } else {

        switch (categoryName) {
            case "Classics":
                ClassicsPage classicsPage = new ClassicsPage("John", "Doe");
                classicsPage.setVisible(true);
                this.dispose();
                break;


            default:
                JOptionPane.showMessageDialog(this, "Unknown category: " + categoryName);
        }
    }
}


private void handleCreateYourOwn() {
    CreateYourOwnPizzaPage createYourOwnPage = new CreateYourOwnPizzaPage("John", "Doe");
    createYourOwnPage.setVisible(true);
    this.dispose();
}
protected JPanel createHeaderPanel(String firstName, String lastName) {
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
        MenuPage menuPage = new MenuPage("John", "Doe");
        menuPage.setVisible(true);
        this.dispose();
    });
    buttonsPanel.add(menuButton);



    JButton cartButton = new JButton("Cart");
    cartButton.setFont(new Font("Arial", Font.BOLD, 12));
    cartButton.addActionListener(e -> {
        CartPage cartpage = new CartPage("Jon", "Doe");
        cartpage.setVisible(true);
        this.dispose();
    });
    buttonsPanel.add(cartButton);

    headerPanel.add(buttonsPanel, BorderLayout.EAST);

    return headerPanel;
}


    protected JPanel createFooterPanel() {
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
        SwingUtilities.invokeLater(() -> new MenuPage("John", "Doe").setVisible(true));
    }
}
