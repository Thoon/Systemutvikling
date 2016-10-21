package springmvc.ui;

import java.util.List;
import javax.validation.Valid;
import springmvc.domene.Person;

public class PersonFormBackingBean {
    
    @Valid
    private List<Person> everyone = null;
    private List<Person> selectedPersons = null;
    
    public List<Person> getSelectedPersons() {
        return selectedPersons;
    }

    public void setSelectedPersons(List<Person> selectedPersons) {
        this.selectedPersons = selectedPersons;
    }
    
    public List<Person> getEveryone(){
        return everyone;
    }
    
    public Person getPerson(int fnr){
        for (Person p : selectedPersons){
            if(p.getEmail().equals(fnr)) return p;
        }
        return null;
    }
    
    public void setEveryone(List<Person> everyone){
        System.out.println(" PersonFormBackingBean.setEveryone()  "  + everyone);
        this.everyone = everyone;
    }
}
