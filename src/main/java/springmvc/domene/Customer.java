/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * Class Customer
 * Creates object of type customer
 * @author ntnu
 */
public class Customer {
    
    @NotNull
    private int customerId;
    @NotEmpty
    private String customerName;
    @NotEmpty
    private String address;
    @NotNull
    private int supplierId;
    

    public Customer(int customerId, String cn, String address, int supplierId) {
        this.customerId = customerId;
        this.customerName = cn;
        this.address = address;
        this.supplierId = supplierId;
    }
    
    public Customer(){};
    
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int cId) {
        this.customerId = cId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String cn) {
        this.customerName = cn;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String add) {
        this.address = add;
    }
    
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", customerName=" + customerName + ", address=" + address + ", supplierId=" + supplierId + '}';
    }
}
