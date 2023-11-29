package user.order;

import java.util.LinkedList;

public class Order {

    protected static int ordernum = 1;
    public static LinkedList<Item> orderlist;
    public static float checkouttotal;

    public Order() {
        orderlist = new LinkedList<>();
        checkouttotal = 0;
        ordernum = ordernum++;
    }

    public float getTotal() {
        return checkouttotal;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public static LinkedList<Item> getItemList() {
        return orderlist;
    }

    public void addItem(Item newitem) {
        if (orderlist.size() < 4) {
            orderlist.addLast(newitem);
        } else {
            return;
        }
    }

    public void removeItem(Item olditem) {
        orderlist.remove(olditem);
    }

    public static class Carryout extends Order {

    }

    public static class Delivery extends Order {
        private String address;

        public String getAddress() {
            return address;
        }
    }
}

