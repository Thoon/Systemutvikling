/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import springmvc.domene.Supplier;

/**
 *
 * @author ganon
 */
public interface SupplierService {
    
    public List<Supplier> getEveryone();
    
    public Supplier getSupplier(int supplierId);
    
    public boolean updateSupplier(Supplier s);
    
    public boolean updateSuppliers(List<Supplier> supplierList);
    
    public boolean registerSupplier(Supplier s);
    
    public boolean deleteSuppliers(List<Supplier> supplierList);
    
}
