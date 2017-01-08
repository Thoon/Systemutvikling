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
    public double percentage = 100.0;

    public MonitorResults(int sn, double mw, double cw, Timestamp ts, int cId, String cn, String ca) {
        this.serialnumber = sn;
        this.maxWeight = mw;
        this.currentWeight = cw;
        this.timestamp = ts;
        this.customerId = cId;
        this.customerName = cn;
        this.customerAddress = ca;
        this.percentage = currentWeight/maxWeight;
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
}
