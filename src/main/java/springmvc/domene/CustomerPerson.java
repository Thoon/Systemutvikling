/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

import java.util.List;

/**
 *
 * @author ander
 */
public class CustomerPerson {
    private String email;
    private int customerId;
    
    public CustomerPerson(){
        
    }
    
    public CustomerPerson(int customerId, String email){
        this.customerId = customerId;
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerName(int customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CustomerPerson{" + "email=" + email + ", customerName=" + customerId + '}';
    }
}
