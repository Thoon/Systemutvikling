
package springmvc.respository;

import springmvc.domene.GasMonitor;


public interface GasMonitorRepository {
    
    public boolean registerGasMonitor(GasMonitor g);
    
    public GasMonitor getGasMonitor(int id);
    
}
