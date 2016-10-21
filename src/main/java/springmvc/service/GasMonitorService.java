/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import springmvc.domene.GasMonitor;

/**
 *
 * @author ander
 */
public interface GasMonitorService {
    
   public boolean registerGasMonitor(GasMonitor g);
   
   public GasMonitor getGasMonitor(int id);
}
