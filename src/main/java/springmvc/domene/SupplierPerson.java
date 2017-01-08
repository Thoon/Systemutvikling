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
public class SupplierPerson {
    private Supplier supplier;
    private Person person;
    
    public SupplierPerson() {
        
    }
    
    public SupplierPerson(Supplier supplier, Person person){
        this.supplier = supplier;
        this.person = person;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "SupplierPerson{" + "supplier=" + supplier + ", person=" + person + '}';
    }
    
}
