/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.Customer;
import springmvc.domene.CustomerPerson;
import springmvc.respository.CustomerRepository;

/**
 *Implementation of CustomerService
 * @author ntnu
 */
public class CustomerServiceImpl implements CustomerService {
     
    private CustomerRepository repo;
/**
 * Sets which repository CustomerService will use
 * @param repo 
 */
     @Autowired
     public void setRepository(CustomerRepository repo){
         this.repo = repo;
     }
   /**
    * Send request to repository to get Customer with specified id
    * @param customerId
    * @return Customer
    */
    @Override
    public Customer getCustomer(int customerId){
        return repo.getCustomer(customerId);
    }
     /**
      * Send request to repository to get all Customers listed in the database
      * @return List
      */
    @Override
    public List<Customer> getEveryone(){
        return repo.getEveryone();
    }
    /**
     * Takes list of Customer objects and sends to repository for update
     * boolean tells us if update is ok
     * @param customerList
     * @return true, false 
     */
    @Override
    public boolean updateCustomers(List<Customer> customerList){
        if (customerList == null || customerList.size() == 0){
            return true;
        }
            
        boolean isUpdateOK = true;
        for (Customer c : customerList){
            if (!repo.updateCustomer(c)) isUpdateOK=false;
        }
        return isUpdateOK;
    }
    /**
     * Takes parameter of Customer and sends to repository to be inserted into the database
     * @param c
     * @return true, false
     */
    @Override
    public boolean registerCustomer(Customer c){
        return repo.registerCustomer(c);
    }
    /**
     * Takes a list of Customer and sends to repository for deletion
     * @param customerList
     * @return true,false
     */
    @Override
    public boolean deleteCustomers(List<Customer> customerList){
        if (customerList == null || customerList.isEmpty()) return true;
               
        boolean isDeleteOK = true;
        for (Customer c : customerList){
            if (!repo.deleteCustomer(c)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    /**
     * Takes a Customer object to get updated in the database
     * @param c
     * @return true, false
     */
    @Override
    public boolean updateCustomer(Customer c){
        return repo.updateCustomer(c);
    }
    /**
     * Takes a CustomerPerson object to get registered in the database
     * @param c
     * @return true, false
     */
    @Override
    public boolean registerCustomerPerson(CustomerPerson c){
        return repo.registerCustomerPerson(c);
    }
}
