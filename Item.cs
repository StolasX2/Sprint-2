abstract class Item
{
    protected float cost;
    protected string name;

    public Item(float c, string n)
    {
        cost = c;
        name = n;
    }

    public float getCost(float c)
    {
        return cost;
    }
    public string getName()
    {
        return name;
    }
    public string setName(string n) 
    {
        name = n;
        return name; 
    }

    public float setCost(float c)
    {
        cost = c;
        return cost;
    }
}

class Beverage : Item
{
    string size;
    public Beverage(string s, float c, string n) : base(c, n)
    {
        cost = c;
        name = n;
        size = s;
    }

    public void setSize(string s)
    {
        this.size = s;
    }
    class Topping : Item
    {

        public Topping(float c, string n) : base(c, n)
        {
            cost = c;
            name = n;
        }
    }
    class Dessert : Item
    {

        public Dessert(float c, string n) : base(c, n)
        {
            cost = c;
            name = n;
        }
    }
    class Pizza : Item
    {
        string size;
        int topping_count;
        Topping[] topping_list = new Topping[4];
        public Pizza(string s, float c, string n) : base(c, n)
        {
            cost = c;
            name = n;
            size = s;
            topping_count = 0;
        }
        public void setSize(string s)
        {
            this.size = s;
        }

        public void addTopping(Topping top)
        {
            if (topping_count < 4)
            {
                topping_list[0] = top;
                topping_count++;
            }
            else
            {
                Console.WriteLine("You can only have 4 toppings per pizza, please remove one and try again.");
                return;
            }
        }
    }
}