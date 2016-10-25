/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

import javax.validation.constraints.NotNull;


public class GasMonitor {
    private int id;
    @NotNull
    private int maxWeight;
    @NotNull
    private int currentWeight;    
    @NotNull
    private double battery;
    @NotNull
    private int customerId;
    @NotNull
    private int supplierId;
    
    public GasMonitor(){
                System.out.println("--------------------------------hei1");

    }
    
    public GasMonitor(int id,int maxWeight, int currentWeight, double battery, int customerId, int supplierId){
                System.out.println("--------------------------------hei2");

        this.id = id;
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.battery = battery;
        this.customerId = customerId;
        this.supplierId = supplierId;
    }
    
    public GasMonitor(int maxWeight, int currentWeight, double battery, int customerId, int supplierId){
        System.out.println("--------------------------------hei3");
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.battery = battery;
        this.customerId = customerId;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
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
        if(id == 0){
            return "GasMonitor{maxWeight=" + maxWeight + ", currentWeight=" + currentWeight + ", battery=" + battery + ", customerId=" + customerId + ", supplierId=" + supplierId + '}';
        }else{
            return "GasMonitor{" + "id=" + id + ", maxWeight=" + maxWeight + ", currentWeight=" + currentWeight + ", battery=" + battery + ", customerId=" + customerId + ", supplierId=" + supplierId + '}';
        }
    }
}
