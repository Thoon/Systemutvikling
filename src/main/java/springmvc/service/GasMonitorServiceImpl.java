
package springmvc.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.GasMonitor;
import springmvc.respository.GasMonitorRepository;

/**
 *Implementation of GasMonitorService
 * @author ntnu
 */

public class GasMonitorServiceImpl implements GasMonitorService {
    private GasMonitorRepository repo;
    /**
     * Configures repository GasMonitorService will use
     * @param repo 
     */
    @Autowired
    public void setRepository(GasMonitorRepository repo){
        this.repo = repo;
    }
    /**
     * Sends a request to repository to get GasMonitor with specified id
     * @param id
     * @return GasMonitor
     */
    @Override
    public GasMonitor getGasMonitor(int id){
        return repo.getGasMonitor(id);
    }
    /**
     * Takes an object of GasMonitor to be inserted into the database
     * @param g
     * @return true, false
     */
    @Override
    public boolean registerGasMonitor(GasMonitor g){
        return repo.registerGasMonitor(g);
    }
    /**
     * Takes a List of GasMonitorObjects to be deleted
     * @param gasMonitorList
     * @return true, false
     */
    @Override
    public boolean deleteGasMonitors(List<GasMonitor> gasMonitorList){
        if (gasMonitorList == null || gasMonitorList.size() == 0) return true;
               
        boolean isDeleteOK = true;
        for (GasMonitor g : gasMonitorList){
            if (!repo.deleteGasMonitor(g)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    /**
     * Gets a List of all GasMonitors stored in the database
     * @return List
     */
    @Override
    public List<GasMonitor> getAllGasMonitors(){
        return repo.getAllGasMonitors();
    }
    /**
     * Takes a List of GasMonitorObjects to be updated in the database
     * @param gasMonitorList
     * @return true, false
     */
    @Override
    public boolean updateGasMonitors(List<GasMonitor> gasMonitorList){
        if (gasMonitorList == null || gasMonitorList.size() == 0) return true;
            
        boolean isUpdateOK = true;
        for (GasMonitor g : gasMonitorList){
            if (!repo.updateGasMonitor(g)) isUpdateOK=false;
        }
        return isUpdateOK;
    }
    /**
     * Takes a GasMonitor object to be updated in the database
     * @param g
     * @return true, false
     */
    @Override
    public boolean updateGasMonitor(GasMonitor g){
        return repo.updateGasMonitor(g);
    }
}