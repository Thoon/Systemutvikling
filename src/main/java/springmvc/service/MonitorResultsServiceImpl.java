/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.MonitorResults;
import springmvc.respository.MonitorResultsRepository;

/**
 *
 * @author ganon
 */
public class MonitorResultsServiceImpl implements MonitorResultsService{
    
    private MonitorResultsRepository repo;
    
    @Autowired
    public void setRepository(MonitorResultsRepository repo){
        System.out.println("MonitorResultsServiceImpl.setDatabase   " + repo);
        this.repo = repo;
    }
    @Override
    public List<MonitorResults> getAllMonitorResults(){
        System.out.println("**** MonitorResultsServiceImpl.getAllMonitorResults()  *** ");
        return repo.getAllMonitorResults();
    }
    
    /**
     * Method for getting results from the database sorted by its current gaslevel and urge for refill
     * @param gasMonitorList
     * @return resultList after calculations
     */
    @Override
    public List<MonitorResults> getCalculatedResults (){
        System.out.println("**** MonitorResultsServiceImpl.getCalculatedResults ****");
        return sortByPercentage(getAllMonitorResults());
    }
    
    /**
     * Help method for getResults() method, which does calculations on the MonitorResults table's weightdata
     * @param gasMonitorList
     * @return A sorted list of GasMonitorobjects by gaslevel-percentage
     */
    private List<MonitorResults> sortByPercentage(List<MonitorResults> gasMonitorList){
        System.out.println("**** MonitorResultsServiceImpl.sortByPercentage ****");
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
    
    private static class ComparatorImpl implements Comparator<MonitorResults> {
    
        public ComparatorImpl() {}

        @Override
        public int compare(MonitorResults mr1, MonitorResults mr2) {
            System.out.println("**** comparator ****");
            return Double.compare(mr1.percentage, mr2.percentage);
        }
    }
}