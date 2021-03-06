/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.ui;

import java.util.List;
import javax.validation.Valid;
import springmvc.domene.Supplier;
import springmvc.domene.SupplierPerson;

/**
 *
 * @author ganon
 */
public class SupplierFormBackingBean {
    @Valid
    private List<Supplier> everyone = null;
    private List<Supplier> selectedSuppliers = null;
    private List<SupplierPerson> suppPers = null;
    
    public List<Supplier> getSelectedSuppliers() {
        return selectedSuppliers;
    }

    public void setSelectedSuppliers(List<Supplier> selectedSuppliers) {
        this.selectedSuppliers = selectedSuppliers;
    }
    
    public List<Supplier> getEveryone(){
        return everyone;
    }
    
    public Supplier getSupplier(int suppId){
        for (Supplier s : selectedSuppliers){
            if(s.getSupplierId() == suppId) return s;
        }
        return null;
    }
    
    public void setEveryone(List<Supplier> everyone){
        this.everyone = everyone;
    }
    
    public List<SupplierPerson> getSupplierPerson(){
        return suppPers;
    }
    
    public void setCustPers(List<SupplierPerson> sp){
        this.suppPers = sp;
    }
}