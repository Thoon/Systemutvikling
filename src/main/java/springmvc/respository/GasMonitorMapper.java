/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domene.GasMonitor;

/**
 *
 * @author ander
 */
public class GasMonitorMapper implements RowMapper<GasMonitor> {
    @Override
    public GasMonitor mapRow(ResultSet rs, int i) throws SQLException {
        GasMonitor g = new GasMonitor();
        g.setMaxWeight(rs.getInt("maxWeight"));
        g.setCurrentWeight(rs.getInt("currentWeight"));
        g.setBattery(rs.getInt("battery"));
        g.setCustomerId(rs.getInt("customerId"));
        g.setSupplierId(rs.getInt("supplierId"));
        return g;
    }
    
}
