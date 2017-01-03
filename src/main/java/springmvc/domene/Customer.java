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
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String address;

    public Customer(Integer customerId, String firstName, String lastName, String address) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
    
    public Customer(){};
    
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer cId) {
        this.customerId = cId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public void setLastName(String ln){
        this.lastName = ln;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String add) {
        this.address = add;
    }   
}
