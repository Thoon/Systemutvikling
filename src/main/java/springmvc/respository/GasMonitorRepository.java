
package springmvc.respository;

import java.util.List;
import springmvc.domene.GasMonitor;


public interface GasMonitorRepository {
    
    public boolean registerGasMonitor(GasMonitor g);
    
    public GasMonitor getGasMonitor(int id);
    
    public boolean deleteGasMonitor(GasMonitor g);
    
    public List<GasMonitor> getAllGasMonitors();
    
}
