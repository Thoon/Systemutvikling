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
import springmvc.domene.SupplierChain;

/**
 *
 * @author ganon
 */
public class SupplierChainDatabaseJdbcTemplateRepositoryImpl implements SupplierChainRepository{
        
    private Connection connection;
    private final String sqlDeleteSupplierChain = "DELETE FROM supplier_chain WHERE sc_id = ?";
    private final String sqlSelectSupplierChain = "SELECT * FROM supplier_chain WHERE sc_id = ?";
    private final String sqlSelectEveryone = "SELECT * FROM supplier_chain";
    private final String sqlRegisterSupplierChain = "INSERT INTO supplier_chain (name) VALUES(?)";
    private final String sqlUpdateSupplierChain = "UPDATE supplier_chain SET name=?, WHERE sc_id = ?";
    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public SupplierChainDatabaseJdbcTemplateRepositoryImpl(){}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public SupplierChain getSupplierChain(int scId){
        return (SupplierChain)jdbcTemplateObject.queryForObject(sqlSelectSupplierChain, new Object[]{scId}, new SupplierChainMapper());
    }
    
    @Override
    public List<SupplierChain> getEveryone(){
        return jdbcTemplateObject.query(sqlSelectEveryone, new SupplierChainMapper());
    }

    @Override
    public boolean deleteSupplierChain(SupplierChain sc) {
        jdbcTemplateObject.update(sqlDeleteSupplierChain, sc.getScId() );
        return true;
    }
    
    @Override
    public boolean updateSupplierChain(SupplierChain sc){
        System.out.println("** Repository ** " + sc);
        jdbcTemplateObject.update(sqlUpdateSupplierChain, new Object[]{
            sc.getName(),
        });
        return true;
    }
    
    @Override
    public boolean registerSupplierChain(SupplierChain sc){
        jdbcTemplateObject.update(sqlRegisterSupplierChain, 
            new Object[]{
                sc.getName(),
                sc.getScId()
        });
        return true;
    }
}