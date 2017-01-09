/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domene.Supplier;

/**
 *
 * @author ganon
 */
public class SupplierMapper implements RowMapper<Supplier> {

    @Override
    public Supplier mapRow(ResultSet rs, int i) throws SQLException {
        Supplier s = new Supplier();
        s.setSupplierName(rs.getString("supplierName"));
        s.setAddress(rs.getString("address"));
        s.setSupplierChainId(rs.getInt("sc_id"));
        return s;
    }
}
