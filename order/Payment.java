package user.order;

public class Payment {
    private long confirmationNumber;
    private float amount;

    public Payment(long confirmationNumber, float amount) {
        this.confirmationNumber = confirmationNumber;
        this.amount = amount;
    }

    public void discount(int discountPercentage) {
        amount -= amount * (discountPercentage / 100.0f);
    }

    public boolean isPaid() {
        return amount <= 0;
    }

    public void pay() {
        System.out.println("Payment processed successfully.");
    }

    public String generateReceipt() {
        return "Receipt - Confirmation Number: " + confirmationNumber + "\nAmount Paid: $" + amount + "\n";
    }

    public boolean isDiscounted(String couponCode) {
        String[] validCouponCodes = {"DISCOUNT10", "SAVE20", "SPECIALOFFER"};
        for (String code : validCouponCodes) {
            if (code.equals(couponCode)) {
                return true;
            }
        }
        return false;
    }
}
