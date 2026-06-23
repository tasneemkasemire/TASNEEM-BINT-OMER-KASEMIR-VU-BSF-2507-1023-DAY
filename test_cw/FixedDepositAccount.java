// FixedDepositAccount.java
public class FixedDepositAccount extends Account {
    public FixedDepositAccount(String f, String l, String n, String e, String p, String d, String b, double dep) {
        super(f, l, n, e, p, d, b, dep);
    }
    @Override public double getMinimumDeposit() { return 1000000; }
    public String getAccountType() { return "Fixed Deposit"; }
}