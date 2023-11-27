package user;

public class Account {
    protected static long accountID = 0;
    protected long phoneNumber;
    protected String email;

    public Account(long phoneNumber, String email) {
        this.accountID++;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

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
