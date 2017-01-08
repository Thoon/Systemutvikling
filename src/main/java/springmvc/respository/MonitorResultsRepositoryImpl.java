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
import springmvc.domene.MonitorResults;

/**
 *
 * @author ganon
 */
public class MonitorResultsRepositoryImpl implements MonitorResultsRepository{
    
    private Connection connection;
    private final String sqlSelectResults = "SELECT gas_monitor.cust_id, customer.customerName, customer.address, gas_monitor.max_weight, monitor_results. * FROM customer INNER JOIN gas_monitor ON gas_monitor.cust_id = customer.cust_id INNER JOIN monitor_results ON monitor_results.serialnumber = gas_monitor.serialnumber";
    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public MonitorResultsRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<MonitorResults> getAllMonitorResults(){
        System.out.println("**** MonitorResultsRepositoryImpl.getAllMonitorResults ****");
        return jdbcTemplateObject.query(sqlSelectResults, new MonResMapper());
    }
}
