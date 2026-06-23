// BankFormGUI.java

import javax.swing.*;
import java.awt.*;    
import java.awt.event.*;
import java.time.LocalDate;
import java.time.Period;

public class BankFormGUI extends JFrame {

    // Simple color palette for the bank theme
    private static final Color HEADER_COLOR = Color.decode("#1B5E20");      // Dark green header
    private static final Color PRIMARY_BACKGROUND = Color.decode("#E8F5E9"); // Light green background
    private static final Color FIELD_BACKGROUND = Color.WHITE;               // White input boxes
    private static final Color ACCENT_COLOR = Color.decode("#43A047");     // Green submit button
    private static final Color RESET_COLOR = Color.decode("#757575");        // Grey reset button
    private static final Color TEXT_COLOR = Color.decode("#212121");
    private static final Color LABEL_COLOR = Color.decode("#1B5E20");
    private static final Color SUMMARY_BACKGROUND = Color.decode("#C8E6C9");
    // UI Input Elements
    private JTextField txtFirst, txtLast, txtNIN, txtEmail, txtConfirmEmail, txtPhone, txtDeposit;
    private JPasswordField txtPIN, txtConfirmPIN;
    private JComboBox<Integer> cmbYear, cmbDay;
    private JComboBox<String> cmbMonth, cmbType, cmbBranch;
    private JTextArea txtSummary;
    
    // Virtual mock counter for sequential account generation
    private static int sequentialCounter = 142; 

    private final String[] MONTHS = {"January", "February", "March", "April", "May", "June", 
                                     "July", "August", "September", "October", "November", "December"};

    public BankFormGUI() {
        setTitle("First Bank Uganda - New Account Application Form");
        setSize(850, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(PRIMARY_BACKGROUND);

        // Colored header banner at the top
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(HEADER_COLOR);
        pnlHeader.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        JLabel lblTitle = new JLabel("First Bank Uganda - New Account Application");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        pnlHeader.add(lblTitle);
        add(pnlHeader, BorderLayout.NORTH);

        // Form Fields Grid Panel
        JPanel pnlFields = new JPanel(new GridLayout(13, 2, 10, 10));
        pnlFields.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                "Customer Details"));
        pnlFields.setBackground(PRIMARY_BACKGROUND);

        JLabel lblFirst = new JLabel("First Name:"); lblFirst.setForeground(LABEL_COLOR); pnlFields.add(lblFirst); txtFirst = new JTextField(); txtFirst.setBackground(FIELD_BACKGROUND); pnlFields.add(txtFirst);
        JLabel lblLast = new JLabel("Last Name:"); lblLast.setForeground(LABEL_COLOR); pnlFields.add(lblLast); txtLast = new JTextField(); txtLast.setBackground(FIELD_BACKGROUND); pnlFields.add(txtLast);
        JLabel lblNIN = new JLabel("National ID (NIN):"); lblNIN.setForeground(LABEL_COLOR); pnlFields.add(lblNIN); txtNIN = new JTextField(); txtNIN.setBackground(FIELD_BACKGROUND); pnlFields.add(txtNIN);
        JLabel lblEmail = new JLabel("Email:"); lblEmail.setForeground(LABEL_COLOR); pnlFields.add(lblEmail); txtEmail = new JTextField(); txtEmail.setBackground(FIELD_BACKGROUND); pnlFields.add(txtEmail);
        JLabel lblConfirmEmail = new JLabel("Confirm Email:"); lblConfirmEmail.setForeground(LABEL_COLOR); pnlFields.add(lblConfirmEmail); txtConfirmEmail = new JTextField(); txtConfirmEmail.setBackground(FIELD_BACKGROUND); pnlFields.add(txtConfirmEmail);
        JLabel lblPhone = new JLabel("Phone (+256...):"); lblPhone.setForeground(LABEL_COLOR); pnlFields.add(lblPhone); txtPhone = new JTextField(); txtPhone.setBackground(FIELD_BACKGROUND); pnlFields.add(txtPhone);
        JLabel lblPIN = new JLabel("PIN (4-6 Digits):"); lblPIN.setForeground(LABEL_COLOR); pnlFields.add(lblPIN); txtPIN = new JPasswordField(); txtPIN.setBackground(FIELD_BACKGROUND); pnlFields.add(txtPIN);
        JLabel lblConfirmPIN = new JLabel("Confirm PIN:"); lblConfirmPIN.setForeground(LABEL_COLOR); pnlFields.add(lblConfirmPIN); txtConfirmPIN = new JPasswordField(); txtConfirmPIN.setBackground(FIELD_BACKGROUND); pnlFields.add(txtConfirmPIN);

        // Date of Birth Dropdowns Panel
        JLabel lblDOB = new JLabel("Date of Birth:"); lblDOB.setForeground(LABEL_COLOR); pnlFields.add(lblDOB);
        JPanel pnlDOB = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlDOB.setBackground(PRIMARY_BACKGROUND);
        cmbYear = new JComboBox<>();
        cmbYear.setBackground(FIELD_BACKGROUND);
        int currentYear = LocalDate.now().getYear();
        for (int y = currentYear; y >= 1930; y--) cmbYear.addItem(y);
        cmbMonth = new JComboBox<>(MONTHS);
        cmbMonth.setBackground(FIELD_BACKGROUND);
        cmbDay = new JComboBox<>();
        cmbDay.setBackground(FIELD_BACKGROUND);
        pnlDOB.add(new JLabel("YY:")); pnlDOB.add(cmbYear);
        pnlDOB.add(new JLabel("MM:")); pnlDOB.add(cmbMonth);
        pnlDOB.add(new JLabel("DD:")); pnlDOB.add(cmbDay);
        pnlFields.add(pnlDOB);

        // Selection Combo Boxes
        JLabel lblType = new JLabel("Account Type:"); lblType.setForeground(LABEL_COLOR); pnlFields.add(lblType);
        cmbType = new JComboBox<>(new String[]{"Savings", "Current", "Fixed Deposit", "Student", "Joint"});
        cmbType.setBackground(FIELD_BACKGROUND);
        pnlFields.add(cmbType);

        JLabel lblBranch = new JLabel("Branch Location:"); lblBranch.setForeground(LABEL_COLOR); pnlFields.add(lblBranch);
        cmbBranch = new JComboBox<>(new String[]{"Kampala", "Gulu", "Mbarara", "Jinja", "Mbale"});
        cmbBranch.setBackground(FIELD_BACKGROUND);
        pnlFields.add(cmbBranch);

        JLabel lblDeposit = new JLabel("Opening Deposit (UGX):"); lblDeposit.setForeground(LABEL_COLOR); pnlFields.add(lblDeposit);
        txtDeposit = new JTextField(); txtDeposit.setBackground(FIELD_BACKGROUND); pnlFields.add(txtDeposit);

        // Action Buttons
        JButton btnSubmit = new JButton("Submit Application");
        JButton btnReset = new JButton("Reset Form");
        btnSubmit.setBackground(ACCENT_COLOR);
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setOpaque(true);
        btnSubmit.setFocusPainted(false);
        btnReset.setBackground(RESET_COLOR);
        btnReset.setForeground(Color.WHITE);
        btnReset.setOpaque(true);
        btnReset.setFocusPainted(false);
        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlButtons.setBackground(PRIMARY_BACKGROUND);
        pnlButtons.add(btnSubmit); pnlButtons.add(btnReset);

        // Bottom Display Summary Section
        JPanel pnlBottom = new JPanel(new BorderLayout(5, 5));
        pnlBottom.setBackground(PRIMARY_BACKGROUND);
        pnlBottom.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 15, 15, 15),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                        "Account Summary")));
        txtSummary = new JTextArea(5, 60);
        txtSummary.setEditable(false);
        txtSummary.setForeground(TEXT_COLOR);
        txtSummary.setBackground(SUMMARY_BACKGROUND);
        txtSummary.setFont(new Font("Monospaced", Font.PLAIN, 12));
        pnlBottom.add(new JScrollPane(txtSummary), BorderLayout.CENTER);

        // Put form and buttons in the middle, summary at the bottom
        JPanel mainContent = new JPanel(new BorderLayout(10, 10));
        mainContent.setBackground(PRIMARY_BACKGROUND);
        mainContent.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        mainContent.add(pnlFields, BorderLayout.CENTER);
        mainContent.add(pnlButtons, BorderLayout.SOUTH);
        add(mainContent, BorderLayout.CENTER);
        add(pnlBottom, BorderLayout.SOUTH);

        // Setup Leap Year Listener Triggers
        ActionListener dateValidator = e -> updateDayComboBox();
        cmbYear.addActionListener(dateValidator);
        cmbMonth.addActionListener(dateValidator);
        updateDayComboBox(); // Baseline initial load execution

        // Submit Button Event Trigger Action
        btnSubmit.addActionListener(e -> processFormSubmission());

        // Reset Button Event Action
        btnReset.addActionListener(e -> resetAllFields());
    }

    private void updateDayComboBox() {
        if (cmbYear.getSelectedItem() == null || cmbMonth.getSelectedItem() == null) return;
        
        int year = (int) cmbYear.getSelectedItem();
        int monthIndex = cmbMonth.getSelectedIndex() + 1;

        // Use built-in Java LocalDate framework to extract lengths instantly
        LocalDate tempDate = LocalDate.of(year, monthIndex, 1);
        int daysInMonth = tempDate.lengthOfMonth(); 

        // Smoothly adjust values without clearing selections unexpectedly
        int currentSelection = (cmbDay.getSelectedItem() != null) ? (int) cmbDay.getSelectedItem() : 1;
        cmbDay.removeAllItems();
        for (int d = 1; d <= daysInMonth; d++) {
            cmbDay.addItem(d);
        }
        if (currentSelection <= daysInMonth) {
            cmbDay.setSelectedItem(currentSelection);
        }
    }

    private void processFormSubmission() {
        StringBuilder errors = new StringBuilder();

        // 1. Inputs Sanitation Extraction
        String fName = txtFirst.getText().trim(); 
        String lName = txtLast.getText().trim();
        String nin = txtNIN.getText().trim().toUpperCase(); 
        String email = txtEmail.getText().trim(); 
        String confirmEmail = txtConfirmEmail.getText().trim(); 
        String phone = txtPhone.getText().trim(); 
        String pin = new String(txtPIN.getPassword()).trim();
        String confirmPin = new String(txtConfirmPIN.getPassword()).trim(); 
        String accType = (String) cmbType.getSelectedItem();
        String branch = (String) cmbBranch.getSelectedItem();
        String depositRaw = txtDeposit.getText().trim(); 

        // 2. Comprehensive System Input Validation Checks
        if (!fName.matches("[A-Za-z]{2,30}")) errors.append("- First Name must be letters only (2-30 chars).\n");
        if (!lName.matches("[A-Za-z]{2,30}")) errors.append("- Last Name must be letters only (2-30 chars).\n");
        if (!nin.matches("[A-Z0-9]{14}")) errors.append("- NIN must be exactly 14 alphanumeric uppercase characters.\n"); 
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$") || !email.equals(confirmEmail)) errors.append("- Emails must be valid and match.\n");
        if (!phone.matches("\\+256\\d{9}")) errors.append("- Phone must follow Ugandan format (+256XXXXXXXXX).\n"); 
        if (!pin.matches("\\d{4,6}") || pin.equals("0000") || !pin.equals(confirmPin)) errors.append("- PIN must be 4-6 unmatched digits and match confirmation.\n"); 

        // Derive Age & Validate Boundary Constraint Rules
        int birthYear = (int) cmbYear.getSelectedItem();
        int birthMonth = cmbMonth.getSelectedIndex() + 1;
        int birthDay = (int) cmbDay.getSelectedItem();
        LocalDate dob = LocalDate.of(birthYear, birthMonth, birthDay);
        int age = Period.between(dob, LocalDate.now()).getYears();

        if (age < 18 || age > 75) {
            errors.append("- Account holder age must be between 18 and 75.\n");
        }
        if (accType.equals("Student") && (age < 18 || age > 25)) {
            errors.append("- Student account access requires an age bracket of 18-25.\n"); 
        }

        // Parse and check currency amount via account polymorphism bounds
        double depositValue = 0;
        try {
            depositValue = Double.parseDouble(depositRaw);
        } catch (NumberFormatException ex) {
            errors.append("- Opening Deposit must be a valid numeric amount.\n"); 
        }

        // If validation errors are present, show error dialog and halt
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, errors.toString(), "Input Validation Errors Found", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        // 3. Polymorphic Object Instantiation Matching Subtype Form Selections
        Account userAccount;
        switch (accType) {
            case "Savings": userAccount = new SavingsAccount(fName, lName, nin, email, phone, dob.toString(), branch, depositValue); break; 
            case "Current": userAccount = new CurrentAccount(fName, lName, nin, email, phone, dob.toString(), branch, depositValue); break; 
            case "Fixed Deposit": userAccount = new FixedDepositAccount(fName, lName, nin, email, phone, dob.toString(), branch, depositValue); break; 
            case "Student": userAccount = new StudentAccount(fName, lName, nin, email, phone, dob.toString(), branch, depositValue); break; 
            default: userAccount = new JointAccount(fName, lName, nin, email, phone, dob.toString(), branch, depositValue, ""); break; 
        }

        // Polymorphic check executing subclass overrides cleanly
        if (!userAccount.hasValidDeposit()) {
            JOptionPane.showMessageDialog(this, "Rejected! Minimum deposit required for " + accType + " is " + userAccount.getMinimumDeposit() + " UGX.", "Deposit Violation", JOptionPane.ERROR_MESSAGE); 
            return;
        }

        // 4. Generate Unique Sequential Account Number Sequence String Layout
        String branchCode = "KLA";
        if (branch.equals("Gulu")) branchCode = "GUL";
        else if (branch.equals("Mbarara")) branchCode = "MBR";
        else if (branch.equals("Jinja")) branchCode = "JNJ";
        else if (branch.equals("Mbale")) branchCode = "MBL";

        String finalGeneratedAccNum = branchCode + "-" + LocalDate.now().getYear() + "-" + String.format("%06d", sequentialCounter++);

        // 5. Append To Local Storage and Render UI Reports Text String Summary
        boolean saveSuccessful = DatabaseManager.saveAccount(userAccount, finalGeneratedAccNum); 

        if (saveSuccessful) {
            String outputReport = String.format("ACC: %s | %s %s | %s | %s | DOB %s | %s | Deposit %,.0f | %s",
                    finalGeneratedAccNum, fName, lName, accType, branch, dob.toString(), phone, depositValue, email); 
            
            txtSummary.setText(outputReport); 
            JOptionPane.showMessageDialog(this, "Account Successfully Created and Saved to MS Access Database!", "Transaction Approved", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save account to local database.", "Database Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetAllFields() {
        txtFirst.setText(""); txtLast.setText(""); txtNIN.setText("");
        txtEmail.setText(""); txtConfirmEmail.setText(""); txtPhone.setText("");
        txtPIN.setText(""); txtConfirmPIN.setText(""); txtDeposit.setText("");
        cmbYear.setSelectedIndex(0); cmbMonth.setSelectedIndex(0);
        cmbType.setSelectedIndex(0); cmbBranch.setSelectedIndex(0);
        txtSummary.setText("");
    }

    public static void main(String[] args) {
        // Makes button colors show properly on all computers
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) { }

        SwingUtilities.invokeLater(() -> new BankFormGUI().setVisible(true));
    }
}

// Local stub classes to allow standalone compilation
class Account {
    protected String firstName, lastName, nin, email, phone, dob, branch;
    protected double balance;

    public Account(String firstName, String lastName, String nin, String email, String phone, String dob, String branch, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nin = nin;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.branch = branch;
        this.balance = balance;
    }

    public boolean hasValidDeposit() {
        return balance >= getMinimumDeposit();
    }

    public double getMinimumDeposit() {
        return 0;
    }

    public String getDob() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDob'");
    }

    public String getAccountType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountType'");
    }

    public double getDeposit() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDeposit'");
    }

    public String getBranch() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBranch'");
    }

    public String getPhoneNumber() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPhoneNumber'");
    }

    public String getLastName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastName'");
    }

    public String getEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }

    public String getNin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNin'");
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(String firstName, String lastName, String nin, String email, String phone, String dob, String branch, double balance) {
        super(firstName, lastName, nin, email, phone, dob, branch, balance);
    }

    @Override
    public double getMinimumDeposit() {
        return 50000;
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(String firstName, String lastName, String nin, String email, String phone, String dob, String branch, double balance) {
        super(firstName, lastName, nin, email, phone, dob, branch, balance);
    }

    @Override
    public double getMinimumDeposit() {
        return 100000;
    }
}

class FixedDepositAccount extends Account {
    public FixedDepositAccount(String firstName, String lastName, String nin, String email, String phone, String dob, String branch, double balance) {
        super(firstName, lastName, nin, email, phone, dob, branch, balance);
    }

    @Override
    public double getMinimumDeposit() {
        return 200000;
    }
}

class StudentAccount extends Account {
    public StudentAccount(String firstName, String lastName, String nin, String email, String phone, String dob, String branch, double balance) {
        super(firstName, lastName, nin, email, phone, dob, branch, balance);
    }

    @Override
    public double getMinimumDeposit() {
        return 20000;
    }
}

class JointAccount extends Account {
    @SuppressWarnings("unused")
    private String partnerName;

    public JointAccount(String firstName, String lastName, String nin, String email, String phone, String dob, String branch, double balance, String partnerName) {
        super(firstName, lastName, nin, email, phone, dob, branch, balance);
        this.partnerName = partnerName;
    }

    @Override
    public double getMinimumDeposit() {
        return 100000;
    }
}

class DatabaseManager {
    public static boolean saveAccount(Account account, String accountNumber) {
        return true;
    }
}
