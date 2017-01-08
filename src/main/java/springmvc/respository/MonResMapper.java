/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domene.MonitorResults;

/**
 *
 * @author ganon
 */
public class MonResMapper implements RowMapper<MonitorResults> {
    
    @Override
    public MonitorResults mapRow(ResultSet rs, int i) throws SQLException {
        System.out.println("**** MonitorResultsverdiene mappes ****");
        MonitorResults mr = new MonitorResults();
        mr.setSerialNumber(rs.getInt("serialnumber"));
        mr.setMaxWeight(rs.getDouble("maxWeight"));
        mr.setCurrentWeight(rs.getDouble("currentWeight"));
        mr.setTimestamp(rs.getTimestamp("timestamp"));
        mr.setCustomerId(rs.getInt("customerId"));
        mr.setCustomerName(rs.getString("customerName"));
        mr.setCustomerAddress(rs.getString("customerAddress"));
        return mr;
    }
}