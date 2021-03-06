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
    
    public Person getPerson(String email){
        for (Person p : selectedPersons){
            if(p.getEmail().equals(email)) return p;
        }
        return null;
    }
    
    public void setEveryone(List<Person> everyone){
        this.everyone = everyone;
    }
}
