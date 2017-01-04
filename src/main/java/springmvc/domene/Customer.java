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
    @NotEmpty
    private int supplierId;
    

    public Customer(Integer customerId, String cn, String address, int supplierId) {
        this.customerId = customerId;
        this.customerName = cn;
        this.address = address;
        this.supplierId = supplierId;
    }
    
    public Customer(){};
    
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
    
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
