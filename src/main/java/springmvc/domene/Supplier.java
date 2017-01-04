package springmvc.domene;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class Supplier {
    @NotNull
    private Integer supplierId;
    @NotEmpty
    private String supplierName;
    @NotEmpty 
    private String address;
    @NotNull
    private Integer supplierChainId;

    public Supplier(Integer supplierId, String supplierName, String address, Integer supplierChainId) {
        this.supplierId = supplierId;
        this.supplierName = supplierName.trim().toUpperCase();
        this.address = address;
        this.supplierChainId = supplierChainId;
    }
    
    public Supplier(){};

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer sId) {
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
    
    public Integer getSupplierChainId() {
        return supplierChainId;
    }

    public void setSupplierChainId(Integer supplierChainId) {
        this.supplierChainId = supplierChainId;
    }
}