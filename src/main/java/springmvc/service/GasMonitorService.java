/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import springmvc.domene.GasMonitor;
import springmvc.domene.Person;

/**
 *
 * @author ander
 */
public interface GasMonitorService {
    
    public List<GasMonitor> getAllGasMonitors();
    
    public boolean registerGasMonitor(GasMonitor g);
   
    public GasMonitor getGasMonitor(int id);
   
     public boolean deleteGasMonitors(List<GasMonitor> gasMonitorList);
}
