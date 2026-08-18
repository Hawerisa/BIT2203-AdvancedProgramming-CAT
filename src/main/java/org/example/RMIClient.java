package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMIClient.java
 * Looks up the remote TemperatureConverter service and converts 25°C to Fahrenheit.
 */
public class RMIClient {

    public static void main(String[] args) {
        try {
            // Locate RMI Registry running on localhost port 1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup remote object by bound service name
            TemperatureConverter converter = (TemperatureConverter) registry.lookup("TemperatureConverter");

            // Convert 25°C to Fahrenheit
            double celsiusInput = 25.0;
            double resultFahrenheit = converter.celsiusToFahrenheit(celsiusInput);

            System.out.println("==================================================");
            System.out.println("                RMI CLIENT RESULT                 ");
            System.out.println("==================================================");
            System.out.println("Input Celsius:    " + celsiusInput + "°C");
            System.out.println("Converted Result: " + resultFahrenheit + "°F");
            System.out.println("==================================================");

        } catch (Exception e) {
            System.err.println("❌ RMI Client Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}