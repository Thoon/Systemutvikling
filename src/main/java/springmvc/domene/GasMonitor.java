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
    private int customerId;
    @NotNull
    private int supplierId;
    @NotNull
    private int gasTanks;    
    
    public GasMonitor(){}
    
    public GasMonitor(int id,int maxWeight, int currentWeight, int customerId, int supplierId, int gasTanks){
        this.id = id;
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.customerId = customerId;
        this.supplierId = supplierId;
        this.gasTanks = gasTanks;
    }
    
    public GasMonitor(int maxWeight, int currentWeight, int customerId, int supplierId, int gasTanks){
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.customerId = customerId;
        this.supplierId = supplierId;
        this.gasTanks = gasTanks;
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
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
    
    public int getGasTanks(){
        return gasTanks;
    }
    
    public void setGasTanks(int gasTanks){
        this.gasTanks = gasTanks;
    }

    @Override
    public String toString() {
        if(id == 0){
            return "GasMonitor{maxWeight=" + maxWeight + ", currentWeight=" + currentWeight + ", customerId=" + customerId + ", supplierId=" + supplierId + ", gastanks= " + gasTanks + '}';
        }else{
            return "GasMonitor{" + "id=" + id + ", maxWeight=" + maxWeight + ", currentWeight=" + currentWeight + ", customerId=" + customerId + ", supplierId=" + supplierId +", gastanks= " + gasTanks + '}';
        }
    }
}
