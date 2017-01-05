/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.List;
import springmvc.domene.SupplierChain;

/**
 *
 * @author ganon
 */
public interface SupplierChainService {
    public List<SupplierChain> getEveryone();
    
    public SupplierChain getSupplierChain(int scId);
    
    public boolean updateSupplierChain(SupplierChain sc);
    
    public boolean updateSupplierChains(List<SupplierChain> supplierChainList);
    
    public boolean registerSupplierChain(SupplierChain sc);
    
    public boolean deleteSupplierChains(List<SupplierChain> supplierChainList);   
}