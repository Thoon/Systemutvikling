/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author ganon
 */
public class SupplierChain {
    @NotNull
    private Integer scId;
    @NotEmpty
    private String name;

    public SupplierChain(Integer scId, String name) {
        this.scId = scId;
        this.name = name;
    }
    
    public SupplierChain(){};

    public Integer getScId() {
        return scId;
    }

    public void setScId(Integer scId) {
        this.scId = scId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
