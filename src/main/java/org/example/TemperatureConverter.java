package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * TemperatureConverter.java
 * Remote Interface defining temperature conversion services.
 */
public interface TemperatureConverter extends Remote {

    double celsiusToFahrenheit(double celsius) throws RemoteException;

    double fahrenheitToCelsius(double fahrenheit) throws RemoteException;
}
