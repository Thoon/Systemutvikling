/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.ui;

import java.util.List;
import javax.validation.Valid;
import springmvc.domene.MonitorResults;

/**
 *
 * @author ganon
 */
public class MonitorResultsBackingBean {
    @Valid
    private List<MonitorResults> allResults = null;
    
    public void setAllResults(List<MonitorResults> allresults){
        this.allResults = allresults;
    }
    
    public List<MonitorResults> getAllResults(){
        return allResults;
    }
}