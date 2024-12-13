package org.example.chapter_10.V_B_13.model;

public class TruckConnector {

    private Truck truck;
    private String fileName;

    public TruckConnector(String fileName) {
        this.fileName = fileName;
        this.truck = Truck.loadFromFile(fileName);
    }

    public Truck getTruck() {
        return truck;
    }

    public void saveTruck() {
        truck.saveToFile(fileName);
    }
}
