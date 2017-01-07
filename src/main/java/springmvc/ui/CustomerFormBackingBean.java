/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.ui;

import java.util.List;
import javax.validation.Valid;
import springmvc.domene.Customer;

/**
 *
 * @author ganon
 */
public class CustomerFormBackingBean {
    @Valid
    private List<Customer> everyone = null;
    private List<Customer> selectedCustomers = null;
    
    public List<Customer> getSelectedCustomers() {
        return selectedCustomers;
    }

    public void setSelectedCustomers(List<Customer> selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
    }
    
    public List<Customer> getEveryone(){
        return everyone;
    }
    
    public Customer getCustomer(int custId){
        for (Customer c : selectedCustomers){
            if(c.getCustomerId() == custId) return c;
        }
        return null;
    }
    
    public void setEveryone(List<Customer> everyone){
        System.out.println(" CustomerFormBackingBean.setEveryone()  "  + everyone);
        this.everyone = everyone;
    }
}