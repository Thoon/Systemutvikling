/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.ui;

import java.util.List;
import javax.validation.Valid;
import springmvc.domene.SupplierChain;

/**
 *
 * @author ganon
 */
public class SupplierChainFormBackingBean {
    @Valid
    private List<SupplierChain> everyone = null;
    private List<SupplierChain> selectedSupplierChains = null;
    
    public List<SupplierChain> getSelectedSupplierChains() {
        return selectedSupplierChains;
    }

    public void setSelectedSupplierChains(List<SupplierChain> selectedSupplierChains) {
        this.selectedSupplierChains = selectedSupplierChains;
    }
    
    public List<SupplierChain> getEveryone(){
        return everyone;
    }
    
    public SupplierChain getSupplierChain(int scId){
        for (SupplierChain sc : selectedSupplierChains){
            if(sc.getScId() == scId) return sc;
        }
        return null;
    }
    
    public void setEveryone(List<SupplierChain> everyone){
        this.everyone = everyone;
    }
}