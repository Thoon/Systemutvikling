/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import springmvc.domene.MonitorResults;

/**
 *
 * @author ganon
 */
public interface MonitorResultsService {
    public List<MonitorResults> getAllMonitorResults();
    public List<MonitorResults> getCalculatedResults();
}
