package user.menu;


import user.order.Item;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Item> pizzas;
    private List<Item> toppings;
    private List<Item> crusts;
    private List<Item> beverages;

    public Menu() {
        pizzas = new ArrayList<>();
        toppings = new ArrayList<>();
        crusts = new ArrayList<>();
        beverages = new ArrayList<>();
        initializeMenu();
    }

    private void initializeMenu() {
        // Add menu items for pizzas, toppings, crusts, and beverages
        pizzas.add(new Item(8.99f,"Small Pizza"));
        pizzas.add(new Item( 11.99f,"Medium Pizza"));
        pizzas.add(new Item( 14.99f,"Large Pizza"));
        pizzas.add(new Item(17.99f,"Extra Large Pizza"));

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
        toppings.add(new Item(price,name));
    }

    private void addCrust(String name, float price) {
        crusts.add(new Item(price,name));
    }

    private void addBeverage(String name, float price) {
        beverages.add(new Item(price,name));
    }

    public List<Item> getPizzas() {
        return pizzas;
    }

    public List<Item> getToppings() {
        return toppings;
    }

    public List<Item> getCrusts() {
        return crusts;
    }

    public List<Item> getBeverages() {
        return beverages;
    }
}
