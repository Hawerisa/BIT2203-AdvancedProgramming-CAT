import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentAuthentication {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/university_db";
    private static final String DB_USER = "admin";
    private static final String DB_PASS = "secure123";

    /**
     * Authenticates student credentials using a secure PreparedStatement.
     */
    public static boolean authenticateStudent(String regNo, String password) {
        // Parameterized SQL query (prevents SQL Injection)
        String sql = "SELECT * FROM students WHERE reg_no = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Binding parameters to placeholders (?)
            pstmt.setString(1, regNo);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Returns true if record exists
            }

        } catch (SQLException e) {
            System.err.println("Authentication Error / Database Connection Notice: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         STUDENT AUTHENTICATION DEMO              ");
        System.out.println("==================================================");

        String sampleReg = "BIT/0001/2026";
        String samplePass = "password123";

        System.out.println("Attempting authentication for:");
        System.out.println("  Registration No : " + sampleReg);
        System.out.println("  Password        : " + samplePass);
        System.out.println("--------------------------------------------------");

        // Explaining the security behind PreparedStatement
        System.out.println("\n--- SECURITY ADVANTAGE ---");
        System.out.println("1. Parameterized Query: User inputs are bound as literal values, not executable code.");
        System.out.println("2. SQL Injection Prevention: Inputs like \"' OR '1'='1\" are escaped automatically.");
        System.out.println("3. Efficiency: Pre-compiled by the DB engine for repeated execution.\n");

        System.out.println("Executing JDBC PreparedStatement authentication...");
        boolean success = authenticateStudent(sampleReg, samplePass);
        System.out.println("Authentication Result: " + (success ? "SUCCESS" : "FAILED / NO DB CONNECTION"));
        System.out.println("==================================================");
    }
}