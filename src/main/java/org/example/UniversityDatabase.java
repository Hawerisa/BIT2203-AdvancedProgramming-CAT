package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * UniversityDatabase.java
 * A complete JDBC program that:
 * 1. Connects to university_db database
 * 2. Creates a courses table
 * 3. Inserts sample courses
 * 4. Retrieves and displays courses from Computer Science department
 *
 * @author Nyevu Chea (Reg: SCT221-0595/2024)
 */
public class UniversityDatabase {

    // Database credentials with safe SSL & public key parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/university_db?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "secure123";

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         UNIVERSITY DATABASE MANAGEMENT           ");
        System.out.println("==================================================");
        System.out.println();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            System.out.println(" Connected to database successfully!");
            System.out.println();

            // 1. Create the courses table
            createTable(conn);

            // 2. Insert sample courses
            insertSampleCourses(conn);

            // 3. Retrieve and display Computer Science courses
            displayComputerScienceCourses(conn);

        } catch (SQLException e) {
            System.err.println(" Database Error: " + e.getMessage());
            System.err.println("Make sure MySQL is running and the database 'university_db' exists.");
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("           PROGRAM COMPLETED                      ");
        System.out.println("==================================================");
    }

    /**
     * Creates the courses table if it doesn't already exist.
     */
    public static void createTable(Connection conn) throws SQLException {
        System.out.println("--- CREATING TABLE ---");

        String sql = "CREATE TABLE IF NOT EXISTS courses (" +
                "course_id INT PRIMARY KEY, " +
                "course_name VARCHAR(100) NOT NULL, " +
                "credits INT NOT NULL, " +
                "department VARCHAR(50) NOT NULL" +
                ")";

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println(" Table 'courses' created successfully (or already exists).");
        }
        System.out.println();
    }

    /**
     * Inserts sample courses into the courses table safely.
     * Uses ON DUPLICATE KEY UPDATE so re-running the program won't trigger primary key errors.
     */
    public static void insertSampleCourses(Connection conn) throws SQLException {
        System.out.println("--- INSERTING SAMPLE COURSES ---");

        String sql = "INSERT INTO courses (course_id, course_name, credits, department) VALUES (?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE course_name = VALUES(course_name), credits = VALUES(credits), department = VALUES(department)";

        // Sample course data
        Object[][] courses = {
                {101, "Data Structures", 3, "Computer Science"},
                {102, "Database Systems", 4, "Computer Science"},
                {103, "Operating Systems", 3, "Computer Science"},
                {201, "Financial Accounting", 3, "Business"},
                {202, "Marketing Principles", 3, "Business"}
        };

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int count = 0;
            for (Object[] course : courses) {
                pstmt.setInt(1, (int) course[0]);
                pstmt.setString(2, (String) course[1]);
                pstmt.setInt(3, (int) course[2]);
                pstmt.setString(4, (String) course[3]);
                pstmt.executeUpdate();
                count++;
            }
            System.out.println(" Processed " + count + " sample courses into database.");
        }
        System.out.println();
    }

    /**
     * Retrieves and displays all courses from the Computer Science department.
     */
    public static void displayComputerScienceCourses(Connection conn) throws SQLException {
        System.out.println("--- COMPUTER SCIENCE COURSES ---");

        String sql = "SELECT * FROM courses WHERE department = ? ORDER BY course_id";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "Computer Science");

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("+-----------+----------------------------+---------+-------------------+");
                System.out.println("| Course ID | Course Name                | Credits | Department        |");
                System.out.println("+-----------+----------------------------+---------+-------------------+");

                boolean found = false;
                while (rs.next()) {
                    found = true;
                    int id = rs.getInt("course_id");
                    String name = rs.getString("course_name");
                    int credits = rs.getInt("credits");
                    String dept = rs.getString("department");

                    System.out.printf("| %-9d | %-26s | %-7d | %-17s |\n", id, name, credits, dept);
                }

                System.out.println("+-----------+----------------------------+---------+-------------------+");

                if (!found) {
                    System.out.println("| No Computer Science courses found.                          |");
                    System.out.println("+-----------+----------------------------+---------+-------------------+");
                }
            }
        }
        System.out.println();
    }
}