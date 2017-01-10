/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

/**
 *Creates object of type SupplierPerson
 * @author ntnu
 */
public class SupplierPerson {
    private int supplierId;
    private String email;
    
    public SupplierPerson() {
        
    }
    
    public SupplierPerson(int supplierId, String email){
        this.supplierId = supplierId;
        this.email = email;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SupplierPerson{" + "supplier=" + supplierId + ", person=" + email + '}';
    }
    
}
