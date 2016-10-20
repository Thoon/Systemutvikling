
package springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.GasMonitor;
import springmvc.respository.GasMonitorRepository;


public class GasMonitorServiceImpl implements GasMonitorService {
    private GasMonitorRepository repo;
    
    @Autowired
    public void setRepository(GasMonitorRepository repo){
        System.out.println("GasMonitorServiceImpl.setDatabase2   " + repo);
        this.repo = repo;
    }
    public GasMonitor getGasMonitor(int id){
        System.out.println("**** PersonServiceImpl.getPerson()  *** ");
        return repo.getGasMonitor(id);
    }
    
    public boolean registerGasMonitor(GasMonitor g){
        System.out.println("******* GasMonitorServiceImpl.registerGasMonitor() ***** ");
        return repo.registerGasMonitor(g);
    }
}
