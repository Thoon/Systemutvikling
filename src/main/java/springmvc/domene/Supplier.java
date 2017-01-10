package springmvc.domene;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Creates object of type Supplier
 * @author ntnu
 */

public class Supplier {
    @NotNull
    private int supplierId;
    @NotEmpty
    private String supplierName;
    @NotEmpty 
    private String address;
    @NotNull
    private int supplierChainId;

    public Supplier(int supplierId, String supplierName, String address, int supplierChainId) {
        this.supplierId = supplierId;
        this.supplierName = supplierName.trim().toUpperCase();
        this.address = address;
        this.supplierChainId = supplierChainId;
    }
    
    public Supplier(){};

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int sId) {
        this.supplierId = sId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String sn) {
        this.supplierName = sn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String add) {
        this.address = add;
    }
    
    public int getSupplierChainId() {
        return supplierChainId;
    }

    public void setSupplierChainId(int supplierChainId) {
        this.supplierChainId = supplierChainId;
    }
    
    @Override
    public String toString() {
        return "Supplier{" + "supplierId=" + supplierId + ", supplierName=" + supplierName + ", address=" + address + ", supplierChainId=" + supplierChainId + '}';
    }

}