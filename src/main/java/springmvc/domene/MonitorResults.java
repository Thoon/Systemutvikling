/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

import java.sql.Timestamp;

/**
 * Class to take care of the results sent to the database from the Arduinounit. Attributes set to accomodate the desired information in myMonitors.jsp
 * @author NTNU
 */
public class MonitorResults {
    private int serialnumber;
    private double maxWeight;
    private double currentWeight;
    private Timestamp timestamp;
    private int customerId;
    private String customerName;
    private String customerAddress;
    public double percentage;
    private String streng;

    public MonitorResults(int serialnumber, double maxWeight, double currentWeight, Timestamp timestamp, int supplierId, int customerId, String customerName, String customerAddress, double percentage, String streng) {
        this.serialnumber = serialnumber;
        this.maxWeight = maxWeight;
        this.currentWeight = currentWeight;
        this.timestamp = timestamp;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.percentage = percentage;
        this.streng = streng;
    }
    
    public MonitorResults(){};

    public int getSerialNumber() {
        return serialnumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialnumber = serialNumber;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }
    
    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    public double getPercentage(){
        return percentage;
    }
    
    public void setPercentage(double p){
        this.percentage = p;
    }
    
    public String getStreng(){
        return streng;
    }
    
    public void setStreng(String streng){
        this.streng = streng;
    }
    

    @Override
    public String toString() {
        return "ID: " + serialnumber + ",  Navn: " + customerName + ",  Addresse: " + customerAddress + ",  Prosent igjen: " + percentage + "%,  Tidspunkt: " + timestamp;
    }
}
