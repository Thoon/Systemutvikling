/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.MonitorResults;
import springmvc.respository.MonitorResultsRepository;

/**
 *Implementation of MonitorResultService
 * @author ntnu
 */
public class MonitorResultsServiceImpl implements MonitorResultsService{
    
    private MonitorResultsRepository repo;
    /**
     * Configures repository for MonitorResultService
     * @param repo 
     */
    @Autowired
    public void setRepository(MonitorResultsRepository repo){
        this.repo = repo;
    }
    /**
     * Method for getting all MonitorResults returned
     * @param userLevel
     * @return List
     */
    @Override
    public List<MonitorResults> getAllMonitorResults(int userLevel){
        if(userLevel == 0){
            return repo.getAllMonitorResultsAdmin();
        }
        if(userLevel == 2){
            return repo.getAllMonitorResultsSupplier(userLevel);
        }
        if(userLevel == 3){
            return repo.getAllMonitorResultsCustomer(userLevel);
        }
        else return null;
    }
    
    /**
     * Method for getting results from the database sorted by its current gaslevel and urge for refill
     * @param gasMonitorList
     * @return resultList after calculations
     */
    @Override
    public List<MonitorResults> getCalculatedResults (int userLevel){
        return sortByPercentage(getAllMonitorResults(userLevel));
    }
    
    /**
     * Help method for getResults() method, which does calculations on the MonitorResults table's weightdata
     * @param gasMonitorList
     * @return A sorted list of GasMonitorobjects by gaslevel-percentage
     */
    private List<MonitorResults> sortByPercentage(List<MonitorResults> gasMonitorList){
        Collections.sort(gasMonitorList, new ComparatorImpl());
        for(int i = 0; i < gasMonitorList.size();i++){  
            double max = gasMonitorList.get(i).getMaxWeight();
            double current = gasMonitorList.get(i).getCurrentWeight();
            max /= 2;
            current -= max;
            double percent = (current/max)*100;
  
            percent = Math.round (percent * 100.0) / 100.0;
            gasMonitorList.get(i).setPercentage(percent);
            
            gasMonitorList.get(i).setStreng((gasMonitorList.get(i).toString()));
        }
        return gasMonitorList;
    }
    /**
     * Help method to sort MonitorResults
     */
    private static class ComparatorImpl implements Comparator<MonitorResults> {
    
        public ComparatorImpl() {}

        @Override
        public int compare(MonitorResults mr1, MonitorResults mr2) {
            return Double.compare(mr1.percentage, mr2.percentage);
        }
    }
}