package user.user;

public class Account {
    protected static long accountID = 0;
    protected static long phoneNumber;
    protected static String email;
    protected static String name;

    public Account(long phoneNumber, String email, String name) {
        this.accountID++;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
    }
    public static String getName(){return name;}
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
