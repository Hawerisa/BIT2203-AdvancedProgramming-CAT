package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMIServer.java
 * Initializes the RMI Registry, instantiates the implementation, and binds the service.
 */
public class RMIServer {

    public static void main(String[] args) {
        try {
            // Create RMI Registry on default port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Instantiate remote service implementation
            TemperatureConverter service = new TemperatureConverterImpl();

            // Bind remote object in registry with lookup name "TemperatureConverter"
            registry.rebind("TemperatureConverter", service);

            System.out.println("✅ RMI Server started successfully on port 1099.");
            System.out.println("✅ 'TemperatureConverter' service is bound and ready for clients.");

        } catch (Exception e) {
            System.err.println("❌ RMI Server Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}