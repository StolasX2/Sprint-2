package user.order;

public class Item {
    protected static float cost;
    public static String name;

    public Item(float c, String n) {
        cost = c;
        name = n;
    }
    public Item(){
        cost = 0;
        name = "null";
    }

    public static float getCost() {
        return cost;
    }

    public static String getName() {
        return name;
    }

    public String setName(String n) {
        name = n;
        return name;
    }

    public float setCost(float c) {
        cost = c;
        return cost;
    }
}

class Topping extends Item {
    public Topping(float c, String n) {
        super(c, n);
    }
}

