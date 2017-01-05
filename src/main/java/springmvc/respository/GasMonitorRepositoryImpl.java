package springmvc.respository;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domene.GasMonitor;

public class GasMonitorRepositoryImpl implements GasMonitorRepository{
    private final String sqlInsertGasMonitor = "insert into gas_monitor (serialnumber, max_weight, current_weight, supp_id, cust_id, number_gastanks) values(?,?,?,?,?,?,?);";
    private final String sqlSelectGasMonitor = "Select * from gas_monitor where gm_id = ?";
    private final String sqlDeleteGasMonitor = "Delete from gas_monitor where gm_id = ?";
    private final String sqlSelectAllGasMonitors = "Select * from gas_monitor";
    private final String sqlUpdateGasMonitor = "update gas_monitor set maxweight = ?, cust_id where gm_id = ?";
    
    private DataSource dataSource;
    static JdbcTemplate jdbcTemplateObject;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
    }
    
    @Override
    public boolean registerGasMonitor(GasMonitor g){
        System.out.println("*** GAS MONITOR REPOSITORY *** " + g);
        jdbcTemplateObject.update(sqlInsertGasMonitor,
                new Object []{
                    g.getId(),
                    g.getMaxWeight(),
                    g.getCurrentWeight(),
                    g.getCustomerId(),
                    g.getSupplierId(),
                    g.getGasTanks()
                });
        return true;
    }
    @Override
    public GasMonitor getGasMonitor(int id) {
        GasMonitor g = (GasMonitor) jdbcTemplateObject.queryForObject(sqlSelectGasMonitor, new Object[]{id}, new GasMonitorMapper());
        return g;
    }
    
    @Override
    public boolean deleteGasMonitor(GasMonitor gasMonitor){
        jdbcTemplateObject.update(sqlDeleteGasMonitor, gasMonitor.getId() );
        return true;
    }
    
    @Override
    public List<GasMonitor> getAllGasMonitors(){
        return jdbcTemplateObject.query(sqlSelectAllGasMonitors, new GasMonitorMapper());
    }
    
    @Override
    public boolean updateGasMonitor(GasMonitor gasMonitor){
        System.out.println("** Repository ** " + gasMonitor);
        jdbcTemplateObject.update(sqlUpdateGasMonitor, new Object[]{
            gasMonitor.getMaxWeight(),
            gasMonitor.getCurrentWeight(),
            gasMonitor.getCustomerId()
        });
        return true;
    }
}
