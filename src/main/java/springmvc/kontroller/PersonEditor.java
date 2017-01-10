/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.kontroller;

import java.beans.PropertyEditorSupport;
import springmvc.domene.Person;
import springmvc.service.PersonService;
/**
 *Converts string to object 
 * @author ntnu
 * 
 */
public class PersonEditor extends PropertyEditorSupport {
    private PersonService personService;
    
    public PersonEditor(PersonService personService){
        this.personService = personService;
    }
    
    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        String[] t = text.split(" ");
        Person p = personService.getPerson(t[0]);
        setValue(p);   
    }
    
    public String getAString(){
        Person p = (Person)getValue();
        return p.getEmail();
    }
}
