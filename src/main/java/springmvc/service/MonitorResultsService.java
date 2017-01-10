/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import springmvc.domene.MonitorResults;

/**
 *Interface for MonitorResultsService
 * @author ntnu
 */
public interface MonitorResultsService {
    public List<MonitorResults> getAllMonitorResults(int userLevel);
    public List<MonitorResults> getCalculatedResults(int userLevel);
}
