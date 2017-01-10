/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.SupplierChain;
import springmvc.domene.SupplierChainPerson;
import springmvc.respository.SupplierChainRepository;

/**
 *
 * @author ganon
 */
public class SupplierChainServiceImpl implements SupplierChainService {
    private SupplierChainRepository repo;

     @Autowired
     public void setRepository(SupplierChainRepository repo){
         this.repo = repo;
     }
   
    @Override
    public SupplierChain getSupplierChain(int scId){
        return repo.getSupplierChain(scId);
    }
     
    @Override
    public List<SupplierChain> getEveryone(){
        return repo.getEveryone();
    }
    
    @Override
    public boolean updateSupplierChains(List<SupplierChain> supplierChainList){
        if (supplierChainList == null || supplierChainList.size() == 0){
            return true;
        }
            
        boolean isUpdateOK = true;
        for (SupplierChain sc : supplierChainList){
            if (!repo.updateSupplierChain(sc)) isUpdateOK=false;
        }
        return isUpdateOK;
    }
    
    @Override
    public boolean registerSupplierChain(SupplierChain sc){
        return repo.registerSupplierChain(sc);
    }
    
    @Override
    public boolean deleteSupplierChains(List<SupplierChain> supplierChainList){
        if (supplierChainList == null || supplierChainList.isEmpty()) return true;
               
        boolean isDeleteOK = true;
        for (SupplierChain sc : supplierChainList){
            if (!repo.deleteSupplierChain(sc)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    
    @Override
    public boolean updateSupplierChain(SupplierChain sc){
        return repo.updateSupplierChain(sc);
    }
    
    @Override
    public boolean registerSupplierPerson(SupplierChainPerson c){
        return repo.registerSupplierChainPerson(c);
    }    
}