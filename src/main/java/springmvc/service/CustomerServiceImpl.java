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
 *
 * @author ganon
 */
public class CustomerServiceImpl implements CustomerService {
     
    private CustomerRepository repo;

     @Autowired
     public void setRepository(CustomerRepository repo){
         System.out.println("CustomerServiceImpl.setDatabase2   " + repo);
         this.repo = repo;
     }
   
    @Override
    public Customer getCustomer(int customerId){
        System.out.println("**** CustomerServiceImpl.getCustomer()  *** ");
        return repo.getCustomer(customerId);
    }
     
    @Override
    public List<Customer> getEveryone(){
        System.out.println("**** CustomerServiceImpl.getEveryone()  *** ");
        return repo.getEveryone();
    }
    
    @Override
    public boolean updateCustomers(List<Customer> customerList){
        System.out.println("**** CustomerServiceImpl.updateCustomers()  *** ");
        if (customerList == null || customerList.size() == 0){
            return true;
        }
            
        boolean isUpdateOK = true;
        for (Customer c : customerList){
            if (!repo.updateCustomer(c)) isUpdateOK=false;
        }
        return isUpdateOK;
    }
    
    @Override
    public boolean registerCustomer(Customer c){
        System.out.println("**** CustomerServiceImpl.registerCustomer()  *** ");
        return repo.registerCustomer(c);
    }
    
    @Override
    public boolean deleteCustomers(List<Customer> customerList){
        System.out.println("**** CustomerServiceImpl.deleteCustomers()  *** ");
        if (customerList == null || customerList.isEmpty()) return true;
               
        boolean isDeleteOK = true;
        for (Customer c : customerList){
            if (!repo.deleteCustomer(c)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    
    @Override
    public boolean updateCustomer(Customer c){
        System.out.println("**** CustomerServiceImpl.updateCustomer()  *** ");
        return repo.updateCustomer(c);
    }
    
    @Override
    public boolean registerCustomerPerson(CustomerPerson c){
        System.out.println("**** CustomerServiceImpl.registerCustomerPerson()  *** ");
        return repo.registerCustomerPerson(c);
    }
}
