package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Main.java
 * Application entry point demonstrating all project components:
 * 1. JDBC University Database Operations
 * 2. Spring IoC Container & Constructor Dependency Injection
 * 3. URL Parsing Utility
 *
 * @author Nyevu Chea (Reg: SCT221-0595/2024)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("  BIT2203 ADVANCED PROGRAMMING CAT EXECUTION      ");
        System.out.println("==================================================");
        System.out.println();

        // ----------------------------------------------------
        // 1. RUN SPRING DEPENDENCY INJECTION DEMO (Question 4)
        // ----------------------------------------------------
        System.out.println(">>> 1. DEMONSTRATING SPRING DEPENDENCY INJECTION <<<");
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(AppConfig.class)) {

            // Retrieve the UserService bean auto-wired with UserRepository via Constructor Injection
            UserService userService = context.getBean(UserService.class);
            String userDetail = userService.getUserDetails(101);

            System.out.println("✅ Spring IoC Container initialized successfully.");
            System.out.println("✅ Retrieved UserService bean.");
            System.out.println("  Result: " + userDetail);

        } catch (Exception e) {
            System.err.println("❌ Spring Initialization Error: " + e.getMessage());
        }

        System.out.println();

        // ----------------------------------------------------
        // 2. RUN JDBC UNIVERSITY DATABASE DEMO (Question 2)
        // ----------------------------------------------------
        System.out.println(">>> 2. DEMONSTRATING JDBC UNIVERSITY DATABASE <<<");
        try {
            UniversityDatabase.main(new String[]{});
        } catch (Exception e) {
            System.err.println("❌ Database Execution Error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("==================================================");
        System.out.println("             ALL DEMOS COMPLETED                  ");
        System.out.println("==================================================");
    }
}