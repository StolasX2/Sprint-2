 public abstract class Account
{
    protected static long accountID = 0;
    protected long phonenumber;
    protected string email;

    public Account(long phonenum, string em)
    {
        accountID = accountID++;
        phonenumber = phonenum;
        email = em;
    }

    public long getAccount()
    {
        return accountID;
    }
    public long getPhone()
    {
        return phonenumber;
    }

    public string getEmail()
    {
        return email;
    }
    public long setPhone(long p)
    {
        phonenumber = p;
        return phonenumber;
    }
    public string setEmail(string e)
    {
        email = e;
        return email;
    }
}

public class Customer : Account
{
    public Customer(long phonenum, string em) : base( phonenum, em) 
    {
        accountID = accountID++;
        phonenumber = phonenum;
        email = em;
    }
}
public class Employee : Account
{
    public Employee( long phonenum, string em) : base( phonenum, em)
    {
        accountID = accountID++;
        phonenumber = phonenum;
        email = em;
    }
}
