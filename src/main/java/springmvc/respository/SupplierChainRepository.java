/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.util.List;
import springmvc.domene.SupplierChain;
import springmvc.domene.SupplierChainPerson;

/**
 *
 * @author ganon
 */
public interface SupplierChainRepository {
    
    public SupplierChain getSupplierChain(int scId);

    public List<SupplierChain> getEveryone() ;

    public boolean registerSupplierChain(SupplierChain sc) ;

    public boolean updateSupplierChain(SupplierChain sc) ;

    public boolean deleteSupplierChain(SupplierChain sc) ;
    
    public boolean registerSupplierChainPerson(SupplierChainPerson sp);
}
