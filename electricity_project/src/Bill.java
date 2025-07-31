// BillCalculator.java
public class Bill {
    public static double calculateBill(int units) {
        int costPerUnit = 9;
        int meterRent = 47;
        int serviceCharge = 22;
        int serviceTax = 57;
        int swachhCess = 6;
        int fixedTax = 18;

        return (units * costPerUnit) + meterRent + serviceCharge + serviceTax + swachhCess + fixedTax;
    }
}
