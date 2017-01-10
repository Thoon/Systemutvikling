
package springmvc.service;

import java.util.List;
import springmvc.domene.Customer;
import springmvc.domene.CustomerPerson;

/**
 *Interface for CustomerService
 * @author ntnu
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
