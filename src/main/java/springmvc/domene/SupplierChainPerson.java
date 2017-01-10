/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

/**
 *Creates object of type SupplierChainPerson
 * @author ntnu
 */
public class SupplierChainPerson {
    
    private int scId;
    private String email;
    
    public SupplierChainPerson(){
        
    }
    
    public SupplierChainPerson(int scId, String email){
        this.scId = scId;
        this.email = email;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "SupplierChainPerson{" + "supplierChain=" + scId + ", person=" + email + '}';
    }
    
}
