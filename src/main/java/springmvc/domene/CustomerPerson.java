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
    private Customer customer;
    private Person person;
    
    public CustomerPerson(){
        
    }
    
    public CustomerPerson(Customer customer, Person person){
        this.customer = customer;
        this.person = person;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "CustomerPerson{" + "customer= " + customer + ", person= " + person + '}';
    }
    
}
