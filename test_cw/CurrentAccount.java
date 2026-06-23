// CurrentAccount.java
public class CurrentAccount extends Account {
    public CurrentAccount(String f, String l, String n, String e, String p, String d, String b, double dep) {
        super(f, l, n, e, p, d, b, dep);
    }
    @Override public double getMinimumDeposit() { return 200000; }
    @Override public String getAccountType() { return "Current"; }
}