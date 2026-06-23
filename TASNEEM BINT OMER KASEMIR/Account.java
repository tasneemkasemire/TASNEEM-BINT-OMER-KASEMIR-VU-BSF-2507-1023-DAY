// Account.java
public abstract class Account {
    private String firstName;
    private String lastName;
    private String nin;
    private String email;
    private String phoneNumber;
    private String dob;
    private String branch;
    protected double openingDeposit; 

    public Account(String firstName, String lastName, String nin, String email, 
                   String phoneNumber, String dob, String branch, double openingDeposit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nin = nin;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.branch = branch;
        this.openingDeposit = openingDeposit;
    }

    // Abstract method that every subclass MUST implement custom logic for
    public abstract double getMinimumDeposit();
    public abstract String getAccountType();

    // Polymorphic validation method easy to explain to the group
    public boolean hasValidDeposit() {
        return this.openingDeposit >= getMinimumDeposit();
    }

    // Getters for Database and Summary use
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNin() { return nin; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDob() { return dob; }
    public String getBranch() { return branch; }
    public double getOpeningDeposit() { return openingDeposit; }
}