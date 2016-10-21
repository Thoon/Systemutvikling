
package springmvc.kontroller;

import java.beans.PropertyEditorSupport;
import springmvc.domene.GasMonitor;
import springmvc.service.GasMonitorService;


public class GasMonitorEditor extends PropertyEditorSupport{
    private GasMonitorService gasMonitorService;
    
    public GasMonitorEditor (GasMonitorService gasMonitorService){
        this.gasMonitorService = gasMonitorService;
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        String[] t = text.split(" ");
        GasMonitor g = gasMonitorService.getGasMonitor(Integer.parseInt(text));
        setValue(g);   
    }
        public int getAString(){
        GasMonitor g = (GasMonitor)getValue();
        return g.getId();
    }
}
