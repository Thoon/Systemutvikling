/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

import org.hibernate.validator.constraints.NotEmpty;


public class GasMonitor {
    private int id;
    @NotEmpty
    private int maxWeight;
    private int currentWeight;    
    private double battery;
    @NotEmpty
    private int customerId;
    @NotEmpty
    private int supplierId;
    
    public GasMonitor(){
                System.out.println("--------------------------------hei");

    }
    
    public GasMonitor(int id,int maxWeight, int currentWeight, double battery, int customerId, int supplierId){
                System.out.println("--------------------------------hei");

        this.id = id;
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.battery = battery;
        this.customerId = customerId;
        this.supplierId = supplierId;
    }
    
    public GasMonitor(int maxWeight, int currentWeight, double battery, int customerId, int supplierId){
        System.out.println("--------------------------------hei");
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.battery = battery;
        this.customerId = customerId;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(int currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "GasMonitor{" + "id=" + id + ", maxWeight=" + maxWeight + ", currentWeight=" + currentWeight + ", battery=" + battery + ", customerId=" + customerId + ", supplierId=" + supplierId + '}';
    }
}
