package Inheritance;

public class HomeWork {

    public static void main(String[] args) {
        
        Vehicle car = new Car("Toyota", 2002);
        Vehicle electr = new ElectricCar("Io", 2025);

        car.start();
        electr.start();
    }
}


public abstract class Vehicle {
    protected String model;
    protected int year;

    public Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    public abstract void start();
    public abstract void stop();
}

public class Car extends Vehicle {
    protected double fuelLevel;

    public Car(String model, int year) {
        super(model, year);
        this.fuelLevel = 0;
    }

    @Override
    public void start() {
        if (fuelLevel > 0) {
            System.out.println("Car started");
        } else {
            System.out.println("No fuel");
        }
    }

    @Override
    public void stop() {
        System.out.println("Car stopped");
    }

    public void refuel(double amount) {
        this.fuelLevel += amount;
    }
}

public class ElectricCar extends Car {
    private double batteryLevel;

    public ElectricCar(String model, int year) {
        super(model, year);
        this.batteryLevel = 0;
    }

    @Override
    public void start() {
        if (batteryLevel > 0) {
            System.out.println("Electric car started silently");
        } else {
            System.out.println("Battery empty");
        }
    }

    public void charge(double amount) {
        this.batteryLevel += amount;
        if (batteryLevel > 100) batteryLevel = 100;
    }
}

