package springmvc.domene;

import org.hibernate.validator.constraints.NotEmpty;

public class Supplier {
    @NotEmpty 
    private Integer supplierId;
    @NotEmpty
    private String supplierName;
    @NotEmpty 
    private String address;

    public Supplier(Integer supplierId, String supplierName, String address) {
        this.supplierId = supplierId;
        this.supplierName = supplierName.trim().toUpperCase();
        this.address = address;
    }

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
}