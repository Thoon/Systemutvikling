/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.util.List;
import springmvc.domene.MonitorResults;

/**
 *
 * @author ganon
 */
public interface MonitorResultsRepository {
    public List<MonitorResults> getAllMonitorResults();
}
