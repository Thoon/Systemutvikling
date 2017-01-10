/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.Supplier;
import springmvc.domene.SupplierPerson;
import springmvc.respository.SupplierRepository;

/**
 *
 * @author ganon
 */
public class SupplierServiceImpl implements SupplierService {
     
    private SupplierRepository repo;

     @Autowired
     public void setRepository(SupplierRepository repo){
         this.repo = repo;
     }
   
    @Override
    public Supplier getSupplier(int supplierId){
        return repo.getSupplier(supplierId);
    }
     
    @Override
    public List<Supplier> getEveryone(){
        return repo.getEveryone();
    }
    
    @Override
    public boolean updateSuppliers(List<Supplier> supplierList){
        if (supplierList == null || supplierList.size() == 0){
            return true;
        }
            
        boolean isUpdateOK = true;
        for (Supplier s : supplierList){
            if (!repo.updateSupplier(s)) isUpdateOK=false;
        }
        return isUpdateOK;
    }
    
    @Override
    public boolean registerSupplier(Supplier s){
        return repo.registerSupplier(s);
    }
    
    @Override
    public boolean deleteSuppliers(List<Supplier> supplierList){
        if (supplierList == null || supplierList.isEmpty()) return true;
               
        boolean isDeleteOK = true;
        for (Supplier s : supplierList){
            if (!repo.deleteSupplier(s)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    
    @Override
    public boolean updateSupplier(Supplier s){
       return repo.updateSupplier(s);
    }
    @Override
    public boolean registerSupplierPerson(SupplierPerson c){
        return repo.registerSupplierPerson(c);
    }
}
