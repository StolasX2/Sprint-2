using System;
using System.Xml.Serialization;
using System.Collections;

 abstract class Order {
    
    protected static int ordernum;
    protected LinkedList<Item> orderlist;
    protected float checkouttotal;

    public Order()
    {
        orderlist = new LinkedList<Item>();
        checkouttotal = 0;
        ordernum = ordernum++;
    }

    public float getTotal()
    {
        return checkouttotal;
    }
    public int getOrdernum()
    {
        return ordernum;
    }
    public LinkedList<Item> getItemList()
    {
        return orderlist;
    }
    public void addItem(Item newitem)
    {
        if(orderlist.Count < 4)
        {
            orderlist.AddLast(newitem);
        }
        else
        {
            return;
        }
    }

    public void removeItem(Item olditem) {
        orderlist.Remove(olditem);
    }




    public class Carryout : Order
    {

    }

    public class Delivery : Order
    {
        private string address;

        public string getAddress()
        {
            return address;
        }
    }
}








