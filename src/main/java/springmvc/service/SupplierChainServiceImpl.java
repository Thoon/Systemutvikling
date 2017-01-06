/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.SupplierChain;
import springmvc.respository.SupplierChainRepository;

/**
 *
 * @author ganon
 */
public class SupplierChainServiceImpl implements SupplierChainService {
    private SupplierChainRepository repo;

     @Autowired
     public void setRepository(SupplierChainRepository repo){
         System.out.println("SupplierChainServiceImpl.setDatabase2   " + repo);
         this.repo = repo;
     }
   
    @Override
    public SupplierChain getSupplierChain(int scId){
        System.out.println("**** SupplierChainServiceImpl.getSupplier()  *** ");
        return repo.getSupplierChain(scId);
    }
     
    @Override
    public List<SupplierChain> getEveryone(){
        System.out.println("**** SupplierChainServiceImpl.getEveryone()  *** ");
        return repo.getEveryone();
    }
    
    @Override
    public boolean updateSupplierChains(List<SupplierChain> supplierChainList){
        System.out.println("**** SupplierChainServiceImpl.updateSupplierChains()  *** ");
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
        System.out.println("**** SupplierChainServiceImpl.registerSupplierChain()  *** ");
        return repo.registerSupplierChain(sc);
    }
    
    @Override
    public boolean deleteSupplierChains(List<SupplierChain> supplierChainList){
        System.out.println("**** SupplierChainServiceImpl.deleteSupplierChains()  *** ");
        if (supplierChainList == null || supplierChainList.isEmpty()) return true;
               
        boolean isDeleteOK = true;
        for (SupplierChain sc : supplierChainList){
            if (!repo.deleteSupplierChain(sc)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    
    @Override
    public boolean updateSupplierChain(SupplierChain sc){
        System.out.println("**** SupplierChainServiceImpl.updateSupplierChain()  *** ");
        return repo.updateSupplierChain(sc);
    }
}