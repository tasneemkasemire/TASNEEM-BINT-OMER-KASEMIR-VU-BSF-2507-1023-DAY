// JointAccount.java
public class JointAccount extends Account {
    private String secondNin;

    public JointAccount(String f, String l, String n, String e, String p, String d, String b, double dep, String secondNin) {
        super(f, l, n, e, p, d, b, dep);
        this.secondNin = secondNin;
    }
    @Override public double getMinimumDeposit() { return 100000; }
    public String getAccountType() { return "Joint"; }
    public String getSecondNin() { return secondNin; }
}