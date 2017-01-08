/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.sql.Connection;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domene.Customer;
import springmvc.domene.CustomerPerson;

/**
 *
 * @author ganon
 */
public class CustomerDatabaseJdbcTemplateRepositoryImpl implements CustomerRepository{
    
    private Connection connection;
    private final String sqlDeleteCustomer = "DELETE FROM customer WHERE cust_id = ?";
    private final String sqlSelectCustomer = "SELECT * FROM customer WHERE cust_id = ?";
    private final String sqlSelectEveryone = "SELECT * FROM customer";
    private final String sqlRegisterCustomer = "INSERT INTO customer (customerName, address, supp_id) VALUES(?,?,?)";
    private final String sqlUpdateCustomer = "UPDATE customer SET customerName=?, address = ?, supp_id = ? WHERE cust_id = ?";
    private final String sqlRegisterCustomerPerson = "insert into cust_pers values (?,?)";
    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public CustomerDatabaseJdbcTemplateRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Customer getCustomer(int customerId){
        return (Customer)jdbcTemplateObject.queryForObject(sqlSelectCustomer, new Object[]{customerId}, new CustomerMapper());
    }
    
    @Override
    public List<Customer> getEveryone(){
        return jdbcTemplateObject.query(sqlSelectEveryone, new CustomerMapper());
    }

    @Override
    public boolean deleteCustomer(Customer c) {
        jdbcTemplateObject.update(sqlDeleteCustomer, c.getCustomerId() );
        return true;
    }
    
    @Override
    public boolean updateCustomer(Customer c){
        System.out.println("** Repository ** " + c);
        jdbcTemplateObject.update(sqlUpdateCustomer, new Object[]{
            c.getCustomerName(),
            c.getAddress(),
            c.getSupplierId(),
            c.getCustomerId()
        });
        return true;
    }
    
    @Override
    public boolean registerCustomer(Customer c){
        jdbcTemplateObject.update(sqlRegisterCustomer, 
            new Object[]{
                c.getCustomerName(),
                c.getAddress(),
                c.getSupplierId()
        });
        return true;
    }
    
    @Override
    public boolean registerCustomerPerson(CustomerPerson cp){
        jdbcTemplateObject.update(sqlRegisterCustomerPerson, 
            new Object[]{
                cp.getCustomer(),
                cp.getPerson()
            });
        return true;        
    }
}
