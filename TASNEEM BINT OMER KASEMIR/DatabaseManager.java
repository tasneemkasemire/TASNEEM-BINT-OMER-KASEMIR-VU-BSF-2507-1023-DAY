// DatabaseManager.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseManager {
    // Database URL pointing to your local MS Access .accdb file
    private static final String DB_URL = "jdbc:ucanaccess://FirstBankDB.accdb";

    public static boolean saveAccount(Account acc, String accountNumber) {
        String query = "INSERT INTO Accounts (AccountNumber, FirstName, LastName, NIN, Email, PhoneNumber, DOB, Branch, AccountType, Deposit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        // Ensure the UCanAccess driver is loaded
        try { Class.forName("net.ucanaccess.jdbc.UcanaccessDriver"); } catch (Exception e) {}

        try (Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, accountNumber);
            pstmt.setString(2, acc.getLastName());
            pstmt.setString(3, acc.getLastName());
            pstmt.setString(4, acc.getNin());
            pstmt.setString(5, acc.getEmail());
            pstmt.setString(6, acc.getPhoneNumber());
            pstmt.setString(7, acc.getDob());
            pstmt.setString(8, acc.getBranch());
            pstmt.setString(9, acc.getAccountType());
            pstmt.setDouble(10, acc.getDeposit());
            
            pstmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
