/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
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
        g.setId(rs.getInt("serialnumber"));
        g.setMaxWeight(rs.getDouble("max_weight"));
        g.setCustomerId(rs.getInt("cust_id"));
        g.setSupplierId(rs.getInt("supp_id"));
        g.setGasTanks(rs.getInt("number_gastanks"));
        
        return g;
    }
}
