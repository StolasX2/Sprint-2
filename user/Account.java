package user.user;

public class Account {
    protected static long accountID = 0;
    protected static long phoneNumber;
    protected static String email;
    protected static String firstname;
    protected static String lastname;

    public Account(long phoneNumber, String email, String firstname, String lastname) {
        this.accountID++;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public static String getFirstName(){return firstname;}
    public static String getLastName(){return lastname;}

    public long getAccount() {
        return accountID;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
