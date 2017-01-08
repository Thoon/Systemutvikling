/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

/**
 *
 * @author ander
 */
public class SupplierChainPerson {
    
    private SupplierChain supplierChain;
    private Person person;
    
    public SupplierChainPerson(){
        
    }
    
    public SupplierChainPerson(SupplierChain supplierChain, Person person){
        this.supplierChain = supplierChain;
        this.person = person;
    }

    public SupplierChain getSupplierChain() {
        return supplierChain;
    }

    public void setSupplierChain(SupplierChain supplierChain) {
        this.supplierChain = supplierChain;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    
    @Override
    public String toString() {
        return "SupplierChainPerson{" + "supplierChain=" + supplierChain + ", person=" + person + '}';
    }
    
}
