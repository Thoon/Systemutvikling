package springmvc.kontroller;

import java.beans.PropertyEditorSupport;
import springmvc.domene.SupplierChain;
import springmvc.service.SupplierChainService;

/**
 * Converts string to Object
 * @author ntnu
 */
public class SupplierChainEditor extends PropertyEditorSupport{
    private SupplierChainService supplierChainService;
    
    public SupplierChainEditor (SupplierChainService supplierChainService){
        this.supplierChainService = supplierChainService;
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        String[] t = text.split(" ");
        SupplierChain sc = supplierChainService.getSupplierChain(Integer.parseInt(text));
        setValue(sc);   
    }
        public int getAString(){
        SupplierChain sc = (SupplierChain)getValue();
        return sc.getScId();
    }
}