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
import springmvc.domene.Supplier;

/**
 *
 * @author ganon
 */
public class SupplierDatabaseJdbcTemplateRepositoryImpl implements SupplierRepository {
    
    private Connection connection;
    private final String sqlDeleteSupplier = "DELETE FROM supplier WHERE supp_id = ?";
    private final String sqlSelectSupplier = "SELECT * FROM supplier WHERE supp_id = ?";
    private final String sqlSelectEveryone = "SELECT * FROM supplier";
    private final String sqlRegisterSupplier = "INSERT INTO supplier (supplierName, address, sc_id) VALUES(?,?,?)";
    private final String sqlUpdateSupplier = "UPDATE supplier SET supplierName=?, address = ?, sc_id = ? WHERE supp_id = ?";
    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public SupplierDatabaseJdbcTemplateRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Supplier getSupplier(int suppId){
        return (Supplier)jdbcTemplateObject.queryForObject(sqlSelectSupplier, new Object[]{suppId}, new SupplierMapper());
    }
    
    @Override
    public List<Supplier> getEveryone(){
        return jdbcTemplateObject.query(sqlSelectEveryone, new SupplierMapper());
    }

    @Override
    public boolean deleteSupplier(Supplier s) {
        jdbcTemplateObject.update(sqlDeleteSupplier, s.getSupplierId() );
        return true;
    }
    
    @Override
    public boolean updateSupplier(Supplier s){
        System.out.println("** Repository ** " + s);
        jdbcTemplateObject.update(sqlUpdateSupplier, new Object[]{
            s.getSupplierName(),
            s.getAddress(),
            s.getSupplierChainId(),
            s.getSupplierId()
        });
        return true;
    }
    
    @Override
    public boolean registerSupplier(Supplier s){
        jdbcTemplateObject.update(sqlRegisterSupplier, 
            new Object[]{
                s.getSupplierName(),
                s.getAddress(),
                s.getSupplierId()
        });
        return true;
    }
}