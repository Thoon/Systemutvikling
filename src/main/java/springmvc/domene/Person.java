/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domene;

//import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

//import javax.validation.constraints.Size;

/**
 *
 * @author Grethe
 */
public class Person {
    
    @Size(min = 3)
    private String personnr;
    @NotEmpty
    private String fornavn;
    @NotEmpty
    private String etternavn;

    public Person(String personnr, String fornavn, String etternavn) {
        this.personnr = personnr.trim().toUpperCase();
        this.fornavn = fornavn.trim().toUpperCase();
        this.etternavn = etternavn.trim().toUpperCase();
    }

    public Person() {
    }

    public String getPersonnr() {
        return personnr;
    }

    public void setPersonnr(String newValue) {
        personnr = newValue;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String newVaule) {
        fornavn = newVaule;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String newVaule) {
        etternavn = newVaule;
    }

    @Override
    public String toString() {
        return personnr + " " + fornavn + " " + etternavn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Person)) {
            return false;
        } else {
            Person p = (Person) obj;
            if (this.personnr.equals(p.getPersonnr())) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.personnr != null ? this.personnr.hashCode() : 0);
        return hash;
    }
}
