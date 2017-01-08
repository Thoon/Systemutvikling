/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.util.List;
import springmvc.domene.Customer;
import springmvc.domene.CustomerPerson;

/**
 *
 * @author ganon
 */
public interface CustomerRepository {
    
    public Customer getCustomer(int customerId);

    public List<Customer> getEveryone() ;

    public boolean registerCustomer(Customer c) ;

    public boolean updateCustomer(Customer c) ;

    public boolean deleteCustomer(Customer c) ;
    
    public boolean registerCustomerPerson(CustomerPerson cp);
}
