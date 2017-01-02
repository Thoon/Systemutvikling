
package springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.GasMonitor;
import springmvc.domene.Person;
import springmvc.respository.GasMonitorRepository;


public class GasMonitorServiceImpl implements GasMonitorService {
    private GasMonitorRepository repo;
    
    @Autowired
    public void setRepository(GasMonitorRepository repo){
        System.out.println("GasMonitorServiceImpl.setDatabase2   " + repo);
        this.repo = repo;
    }
    @Override
    public GasMonitor getGasMonitor(int id){
        System.out.println("**** PersonServiceImpl.getPerson()  *** ");
        return repo.getGasMonitor(id);
    }
    @Override
    public boolean registerGasMonitor(GasMonitor g){
        System.out.println("******* GasMonitorServiceImpl.registerGasMonitor() ***** " + g);
        return repo.registerGasMonitor(g);
    }
    
    @Override
    public boolean deleteGasMonitors(List<GasMonitor> gasMonitorList){
        System.out.println("**** GasMonitorServiceImpl.deleteGasMonitor()  *** ");
        if (gasMonitorList == null || gasMonitorList.size() == 0) return true;
               
        boolean isDeleteOK = true;
        for (GasMonitor g : gasMonitorList){
            if (!repo.deleteGasMonitor(g)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    
    @Override
    public List<GasMonitor> getAllGasMonitors(){
        System.out.println("**** GasMonitorServiceImpl.getAllGasMonitors()  *** ");
        return repo.getAllGasMonitors();
    }
    
    
    public boolean updateGasMonitors(List<GasMonitor> gasMonitorList){
        System.out.println("**** GasMonitorServiceImpl.updateGasMonitors()  *** ");
        if (gasMonitorList == null || gasMonitorList.size() == 0) return true;
            
        boolean isUpdateOK = true;
        for (GasMonitor g : gasMonitorList){
            System.out.println("test");
            if (!repo.updateGasMonitor(g)) isUpdateOK=false;
        }
        return isUpdateOK;
    }
    
    public boolean updateGasMonitor(GasMonitor g){
        System.out.println("**** GasMonitorServiceImpl.updateGasMonitor()  *** ");
        return repo.updateGasMonitor(g);
    }
}
