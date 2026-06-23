// StudentAccount.java
public class StudentAccount extends Account {
    public StudentAccount(String f, String l, String n, String e, String p, String d, String b, double dep) {
        super(f, l, n, e, p, d, b, dep);
    }
    @Override public double getMinimumDeposit() { return 10000; }
    public String getAccountType() { return "Student"; }
}