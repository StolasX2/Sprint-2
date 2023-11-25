using System;

public class Payment
{
    private long confirmationNumber;
    private float amount;

    public Payment(long confirmationNumber, float amount)
    {
        this.confirmationNumber = confirmationNumber;
        this.amount = amount;
    }

    public void Discount(int discountPercentage)
    {
        amount -= amount * (discountPercentage / 100.0f);
    }

    public bool IsPaid()
    {
        return amount <= 0;
    }

    public void Pay()
    {
        Console.WriteLine($"Payment of ${amount} processed successfully.");
    }

    public string GenerateReceipt()
    {
        string receipt = $"Receipt - Confirmation Number: {confirmationNumber}\n";
        receipt += $"Amount Paid: ${amount}\n";
        return receipt;
    }

    public bool IsDiscounted(string couponCode)
    {
        string[] validCouponCodes = { "DISCOUNT10", "SAVE20", "SPECIALOFFER" };
        return Array.Exists(validCouponCodes, code => code == couponCode);
    }

    public static void Main()
    {
        Payment payment1 = new Payment(123456, 100.0f);
        payment1.Discount(10);
        Console.WriteLine(payment1.IsPaid());
        payment1.Pay();
        Console.WriteLine(payment1.IsPaid());
        string receipt = payment1.GenerateReceipt();
        Console.WriteLine(receipt);

        string couponCode = "SAVE20";
        Console.WriteLine(payment1.IsDiscounted(couponCode));
    }
}
