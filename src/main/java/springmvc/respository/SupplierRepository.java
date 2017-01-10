/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.util.List;
import springmvc.domene.Supplier;
import springmvc.domene.SupplierPerson;

/**
 *
 * @author ganon
 */
public interface SupplierRepository {
    public Supplier getSupplier(int supplierId);

    public List<Supplier> getEveryone() ;

    public boolean registerSupplier(Supplier s) ;

    public boolean updateSupplier(Supplier s) ;

    public boolean deleteSupplier(Supplier s) ;
    
    public boolean registerSupplierPerson(SupplierPerson sp);
}
