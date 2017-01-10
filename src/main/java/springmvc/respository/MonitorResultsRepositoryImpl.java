/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.sql.Connection;
import java.util.List;
import javax.servlet.http.HttpSession;
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
    
    // Query to return wanted attributes from the tables Customer, Gas_monitor and Monitor_results.
    // Returns only the latest registered timestamp for each gas monitor 
    private final String sqlSelectResultsAdmin = ""
            + "SELECT gm.cust_id, c.customerName, c.address, gm.max_weight, mr. * FROM customer c "
            + "INNER JOIN gas_monitor gm ON gm.cust_id = c.cust_id "
            + "INNER JOIN monitor_results mr ON mr.serialnumber = gm.serialnumber "
            + "WHERE mr.timestamp = (SELECT MAX( mr2.timestamp ) FROM monitor_results mr2 WHERE mr2.serialnumber = mr.serialnumber)";
    private final String sqlSelectResultsSupplier = ""
            + "SELECT gm.cust_id, c.customerName, c.address, gm.max_weight, mr. * FROM customer c "
            + "INNER JOIN gas_monitor gm ON gm.cust_id = c.cust_id "
            + "INNER JOIN monitor_results mr ON mr.serialnumber = gm.serialnumber "
            + "WHERE mr.timestamp = (SELECT MAX( mr2.timestamp ) FROM monitor_results mr2 WHERE mr2.serialnumber = mr.serialnumber ) "
            + "AND mr.supp_id = ?";
    private final String sqlSelectResultsCustomer = ""
            + "SELECT gm.cust_id, c.customerName, c.address, gm.max_weight, mr. * FROM customer c "
            + "INNER JOIN gas_monitor gm ON gm.cust_id = c.cust_id "
            + "INNER JOIN monitor_results mr ON mr.serialnumber = gm.serialnumber "
            + "WHERE mr.timestamp = (SELECT MAX( mr2.timestamp ) FROM monitor_results mr2 WHERE mr2.serialnumber = mr.serialnumber ) "
            + "AND mr.cust_id = ?";
    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public MonitorResultsRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<MonitorResults> getAllMonitorResultsAdmin(){
        return jdbcTemplateObject.query(sqlSelectResultsAdmin, new MonResMapper());
    }
    
    @Override
    public List<MonitorResults> getAllMonitorResultsSupplier(int userLevel){
        return jdbcTemplateObject.query(sqlSelectResultsSupplier, new Object[] {userLevel}, new MonResMapper());
    }
    
    @Override
    public List<MonitorResults> getAllMonitorResultsCustomer(int userLevel){
        return jdbcTemplateObject.query(sqlSelectResultsCustomer, new Object[] {userLevel}, new MonResMapper());
    }
    
}
