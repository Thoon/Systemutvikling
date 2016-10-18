package springmvc.respository;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domene.GasMonitor;

public class GasMonitorRepositoryImpl implements GasMonitorRepository{
    private final String sqlInsertGasMonitor = "insert into gasmonitor(maxweight, currentweight, battery, customerid, supplierid) values(?,?,?,?,?)";
    
    private DataSource dataSource;
    static JdbcTemplate jdbcTemplateObject;
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
    }
    
    public boolean registerGasMonitor(GasMonitor g){
        jdbcTemplateObject.update(sqlInsertGasMonitor,
                new Object []{
                    g.getMaxWeight(),
                    g.getCurrentWeight(),
                    g.getBattery(),
                    g.getCustomerId(),
                    g.getSupplierId()
                });
        return true;
    }
}
