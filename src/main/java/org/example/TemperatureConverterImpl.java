package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * TemperatureConverterImpl.java
 * Concrete implementation of the TemperatureConverter remote interface.
 */
public class TemperatureConverterImpl extends UnicastRemoteObject implements TemperatureConverter {

    public TemperatureConverterImpl() throws RemoteException {
        super();
    }

    @Override
    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        return (celsius * 9 / 5) + 32;
    }

    @Override
    public double fahrenheitToCelsius(double fahrenheit) throws RemoteException {
        return (fahrenheit - 32) * 5 / 9;
    }
}