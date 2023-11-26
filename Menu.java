package menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> pizzas;
    private List<MenuItem> toppings;
    private List<MenuItem> crusts;
    private List<MenuItem> beverages;

    public Menu() {
        pizzas = new ArrayList<>();
        toppings = new ArrayList<>();
        crusts = new ArrayList<>();
        beverages = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        // Add menu items for pizzas, toppings, crusts, and beverages
        pizzas.add(new MenuItem("Small Pizza", 8.99f));
        pizzas.add(new MenuItem("Medium Pizza", 11.99f));
        pizzas.add(new MenuItem("Large Pizza", 14.99f));
        pizzas.add(new MenuItem("Extra Large Pizza", 17.99f));

        // Add toppings
        addTopping("Pepperoni", 1.50f);
        addTopping("Mushrooms", 1.00f);
        addTopping("Green Peppers", 0.75f);
        addTopping("Olives", 1.25f);

        // Add crusts
        addCrust("Thin Crust", 0.00f);
        addCrust("Regular Crust", 0.00f);
        addCrust("Thick Crust", 1.50f);

        // Add beverages
        addBeverage("Cola", 2.00f);
        addBeverage("Sprite", 2.00f);
        addBeverage("Root Beer", 2.50f);
    }

    private void addTopping(String name, float price) {
        toppings.add(new MenuItem(name, price));
    }

    private void addCrust(String name, float price) {
        crusts.add(new MenuItem(name, price));
    }

    private void addBeverage(String name, float price) {
        beverages.add(new MenuItem(name, price));
    }

    public List<MenuItem> getPizzas() {
        return pizzas;
    }

    public List<MenuItem> getToppings() {
        return toppings;
    }

    public List<MenuItem> getCrusts() {
        return crusts;
    }

    public List<MenuItem> getBeverages() {
        return beverages;
    }
}
