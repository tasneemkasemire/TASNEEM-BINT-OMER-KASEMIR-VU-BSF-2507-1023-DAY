// SavingsAccount.java
public class SavingsAccount extends Account {
    public SavingsAccount(String f, String l, String n, String e, String p, String d, String b, double dep) {
        super(f, l, n, e, p, d, b, dep);
    }
    @Override public double getMinimumDeposit() { return 50000; }
    public String getAccountType() { return "Savings"; }
}