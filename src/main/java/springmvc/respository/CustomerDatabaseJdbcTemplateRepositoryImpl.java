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

/**
 *
 * @author ganon
 */
public class CustomerDatabaseJdbcTemplateRepositoryImpl implements CustomerRepository{
    
    private Connection connection;
    private final String sqlDeleteCustomer = "DELETE FROM customer WHERE cust_id = ?";
    private final String sqlSelectCustomer = "SELECT * FROM customer WHERE cust_id = ?";
    private final String sqlSelectEveryone = "SELECT * FROM customer";
    private final String sqlInsertCustomer = "INSERT INTO person (firstName, lastname, password, email, phone, permissions, active) VALUES(?,?,?,?,?,?,?)";
    private final String sqlUpdateCustomer = "UPDATE person SET firstName=?, lastname = ?, address = ? WHERE email = ?";
    
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
            c.getFirstName(),
            c.getLastName(),
            c.getAddress(),
            c.getCustomerId()
        });
        return true;
    }
    
    @Override
    public boolean registerCustomer(Customer c){
        jdbcTemplateObject.update(sqlInsertCustomer, 
            new Object[]{
                c.getFirstName(), 
                c.getLastName(),
                c.getAddress(),
                c.getCustomerId()
        });
        return true;
    }
}
