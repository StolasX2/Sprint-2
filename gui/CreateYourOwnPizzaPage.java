package user.gui;

import user.order.Item;
import user.order.Pizza;
import user.order.Topping;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

import static user.gui.CartPage.cartItems;

public class CreateYourOwnPizzaPage extends JFrame {
    private Map<String, Map<String, List<String>>> pizzaOptions;
    private Iterator<Map.Entry<String, Map<String, List<String>>>> sectionIterator;
    private String currentSection;
    private int currentSectionIndex;

    public CreateYourOwnPizzaPage(String firstName, String lastName) {
        super("Create Your Own Pizza");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        pizzaOptions = new LinkedHashMap<>();
        initializePizzaOptions();

        sectionIterator = pizzaOptions.entrySet().iterator();
        currentSection = sectionIterator.next().getKey();

        addComponentsToPane(getContentPane(), firstName, lastName);
    }

    private void initializePizzaOptions() {
        pizzaOptions = new LinkedHashMap<>();
        pizzaOptions.put("Base", createBaseOptions());
        pizzaOptions.put("Cheese", createCheeseOptions());
        pizzaOptions.put("Meat", createMeatOptions());
        pizzaOptions.put("Veggies", createVeggiesOptions());
        currentSectionIndex = 0;
    }

    private Map<String, List<String>> createBaseOptions() {
        Map<String, List<String>> baseOptions = new LinkedHashMap<>();
        baseOptions.put("Size", List.of("Small", "Medium", "Large", "Extra Large"));
        baseOptions.put("Crust", List.of("Thin Crust", "Original Crust", "Pepperoni Crust", "Stuffed Crust"));
        baseOptions.put("Cut", List.of("Normal Cut", "Square Cut", "Clean Cut", "No Cut"));
        baseOptions.put("Sauce", List.of("None", "BBQ", "Ranch", "Buffalo"));
        baseOptions.put("Bake", List.of("Normal Bake", "Well Done"));
        return baseOptions;
    }

    private Map<String, List<String>> createCheeseOptions() {
        Map<String, List<String>> cheeseOptions = new LinkedHashMap<>();
        cheeseOptions.put("How much?", List.of("Light Cheese", "Normal Cheese", "Extra Cheese", "No Cheese"));
        return cheeseOptions;
    }

    private Map<String, List<String>> createMeatOptions() {
        Map<String, List<String>> meatOptions = new LinkedHashMap<>();
        meatOptions.put("Meats", List.of("Pepperoni", "Sausage", "Pineapple", "Bacon"));
        return meatOptions;
    }

    private Map<String, List<String>> createVeggiesOptions() {
        Map<String, List<String>> veggieOptions = new LinkedHashMap<>();
        veggieOptions.put("Veggies", List.of("Onions", "Green Pepper", "Spinach", "None"));
        return veggieOptions;
    }

    private void addComponentsToPane(Container pane, String firstName, String lastName) {
        pane.setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel(firstName, lastName);
        pane.add(headerPanel, BorderLayout.NORTH);

        JPanel bodyPanel = createBodyPanel();
        pane.add(bodyPanel, BorderLayout.CENTER);

        JPanel footerPanel = createFooterPanel();
        pane.add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createBodyPanel() {
        JPanel bodyPanel = new JPanel(new BorderLayout());
        bodyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20),
                "Create your Own!",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                Color.RED
        ));

        JPanel sectionPanel = createSectionPanel(currentSection, pizzaOptions.get(currentSection));
        bodyPanel.add(sectionPanel, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> handleNextButton());
        bodyPanel.add(nextButton, BorderLayout.SOUTH);

        return bodyPanel;
    }

    private JPanel createSectionPanel(String sectionName, Map<String, List<String>> options) {
        JPanel sectionPanel = new JPanel(new GridLayout(options.size(), 1));

        // Always set a titled border
        sectionPanel.setBorder(BorderFactory.createTitledBorder(sectionName));

        for (Map.Entry<String, List<String>> entry : options.entrySet()) {
            String subSectionName = entry.getKey();
            List<String> subOptions = entry.getValue();

            JPanel subSectionPanel = createSubSectionPanel(subSectionName, subOptions);
            sectionPanel.add(subSectionPanel);
        }

        return sectionPanel;
    }


    private JPanel createSubSectionPanel(String subSectionName, List<String> options) {
        JPanel subSectionPanel = new JPanel(new GridLayout(options.size(), 1));
        subSectionPanel.setBorder(BorderFactory.createTitledBorder(subSectionName));

        ButtonGroup buttonGroup = new ButtonGroup();
        for (String option : options) {
            JRadioButton radioButton = new JRadioButton(option);
            buttonGroup.add(radioButton);
            subSectionPanel.add(radioButton);
        }

        return subSectionPanel;
    }

    private void handleNextButton() {
        // Save the user's selection for the current section
        saveUserSelection(currentSection, pizzaOptions.get(currentSection));

        // Move to the next section
        currentSectionIndex++;

        if (currentSectionIndex < pizzaOptions.size()) {
            currentSection = getPizzaOptionKey(currentSectionIndex);
            System.out.println("Next Section: " + currentSection);
            refreshBodyPanel();
        } else {
            // All sections completed, finish the pizza creation
            createPizzaAndAddToCart();
            JOptionPane.showMessageDialog(this, "Pizza creation complete!");
            // Optionally close the window
            // this.dispose();
        }
    }

    private void createPizzaAndAddToCart() {
        // Create a Pizza object with selected toppings
        Pizza pizza = new Pizza("Custom Pizza: ");
        List<Item> selectedToppings = new ArrayList<>();
        for (Item topping : selectedToppings) {
           pizza.setName(pizza.name + ", "+topping);
        }

        // Create a Pizza object with the selected toppings


        // Add the Pizza to the cart
        CartPage.cartItems.add(pizza);
    }



    private void refreshBodyPanel() {
        Container contentPane = getContentPane();
        Component[] components = contentPane.getComponents();
        if (components.length > 1) {
            contentPane.remove(1);
        }
        JPanel bodyPanel = createBodyPanel();
        contentPane.add(bodyPanel, BorderLayout.CENTER);
        validate();
        repaint();
    }
    private String getPizzaOptionKey(int index) {
        return pizzaOptions.keySet().toArray(new String[0])[index];
    }
    private void saveUserSelection(String sectionName, Map<String, List<String>> options) {
        Container contentPane = getContentPane();
        Component[] components = contentPane.getComponents();

        for (Component component : components) {
            if (component instanceof JPanel) {
                Border border = ((JPanel) component).getBorder();

                if (border instanceof TitledBorder) {
                    TitledBorder titledBorder = (TitledBorder) border;
                    String currentSectionName = titledBorder.getTitle();
                    if (currentSectionName.equals(sectionName)) {
                        Map<String, List<String>> currentOptions = options;

                        for (Component innerComponent : ((JPanel) component).getComponents()) {
                            if (innerComponent instanceof JPanel) {
                                Border subBorder = ((JPanel) innerComponent).getBorder();

                                if (subBorder instanceof TitledBorder) {
                                    TitledBorder subTitledBorder = (TitledBorder) subBorder;
                                    String subSectionName = subTitledBorder.getTitle();
                                    List<String> subOptions = currentOptions.get(subSectionName);

                                    for (Component subInnerComponent : ((JPanel) innerComponent).getComponents()) {
                                        if (subInnerComponent instanceof JRadioButton) {
                                            JRadioButton radioButton = (JRadioButton) subInnerComponent;
                                            if (radioButton.isSelected()) {
                                                Topping newtop = new Topping(1.99f, "topping");

                                                JOptionPane.showMessageDialog(this, "Selected " + sectionName + " - " + subSectionName + ": " + radioButton.getText());
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
            CreateYourOwnPizzaPage.this.dispose();
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
        SwingUtilities.invokeLater(() -> new CreateYourOwnPizzaPage("John", "Doe").setVisible(true));
    }
}
