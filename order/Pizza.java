package user.order;

import java.util.List;

public class Pizza extends Item {
    private String size;
    private int toppingCount;
    private Topping[] toppingList;

    public Pizza(String s, float c, String n) {
        super(c, n);
        size = s;
        toppingCount = 0;
        toppingList = new Topping[4];
    }

    public Pizza(String customPizza) {
        name = "Custom Pizza";
        cost = 20.99f;

    }
    public void setSize(String s) {
        this.size = s;
    }

    public void addTopping(Topping top) {
        if (toppingCount < 4) {
            toppingList[toppingCount] = top;
            toppingCount++;
        } else {
            System.out.println("You can only have 4 toppings per pizza, please remove one and try again.");
        }
    }
}
