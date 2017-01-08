/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import springmvc.domene.Customer;
import springmvc.domene.CustomerPerson;

/**
 *
 * @author ganon
 */
public interface CustomerService {
    
    public Customer getCustomer(int custId);
    
    public List<Customer> getEveryone();
    
    public boolean updateCustomer(Customer c);
    
    public boolean updateCustomers(List<Customer> customerList);
    
    public boolean registerCustomer(Customer c);
    
    public boolean deleteCustomers(List<Customer> customerList);
    
    public boolean registerCustomerPerson(CustomerPerson c);
    
}
