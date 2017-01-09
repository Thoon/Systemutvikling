/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.ui;
import java.util.List;
import javax.validation.Valid;
import springmvc.domene.GasMonitor;

public class GasMonitorFormBackingBean {
    @Valid
    private List<GasMonitor> allGasMonitors = null;
    private List<GasMonitor> foundGasMonitors = null;
    private List<GasMonitor> selectedGasMonitors = null;
    
    public List<GasMonitor> getAllGasMonitors(){
        return allGasMonitors;
    }
    public void setAllGasMonitors(List<GasMonitor> allGasMonitors){
        System.out.println(" GasMonitorFormBackingBean.setEveryone()  "  + allGasMonitors);
        this.allGasMonitors = allGasMonitors;
    }
    public List<GasMonitor> getFoundGasMonitors(){
        return foundGasMonitors;
    }
    public void setFoundGasMonitors(List<GasMonitor> foundGasMonitors){
        this.foundGasMonitors = foundGasMonitors;
    }
    public List<GasMonitor> getSelectedGasMonitors(){
        return selectedGasMonitors;
    }
    public void setSelectedGasMonitors(){
        this.selectedGasMonitors = selectedGasMonitors;
    }
    public GasMonitor getGasMonitor(int id){
        for(GasMonitor g : selectedGasMonitors){
            if(g.getId() == id) return g;
        }
        return null;
    }
}
