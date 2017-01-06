/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import springmvc.domene.SupplierChain;

/**
 *
 * @author ganon
 */
public class SupplierChainMapper implements RowMapper<SupplierChain> {

    @Override
    public SupplierChain mapRow(ResultSet rs, int i) throws SQLException {
        SupplierChain sc = new SupplierChain();
        sc.setName(rs.getString("name"));
        return sc;
    }
}
