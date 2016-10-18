/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

import org.hibernate.validator.constraints.NotEmpty;

public class Customer {
    
    @NotEmpty
    private Integer customerId;
    @NotEmpty
    private String customerName;
    @NotEmpty
    private String address;

    public Customer(Integer customerId, String customerName, String address) {
        this.customerId = customerId;
        this.customerName = customerName.trim().toUpperCase();
        this.address = address;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer cId) {
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
}
