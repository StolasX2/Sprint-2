package user.order;

public class Beverage extends Item {
    private String size;

    public Beverage(String s, float c, String n) {
        super(c, n);
        size = s;
    }

    public void setSize(String s) {
        this.size = s;
    }
}
