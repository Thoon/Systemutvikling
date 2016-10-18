package springmvc.ui;

import java.util.List;
import javax.validation.Valid;
import springmvc.domene.Person;

/**
 *
 * @author tomash
 */
public class PersonFormBackingBean {
    
    @Valid
    private List<Person> allePersoner = null;
    private List<Person> valgtePersoner = null;
    
    public List<Person> getValgtePersoner() {
        return valgtePersoner;
    }

    public void setValgtePersoner(List<Person> valgtePersoner) {
        this.valgtePersoner = valgtePersoner;
    }
    
    public List<Person> getAllePersoner(){
        return allePersoner;
    }
    
    public Person getPerson(int fnr){
        for (Person p : valgtePersoner){
            if(p.getPersonId() == fnr) return p;
        }
        return null;
    }
    
    public void setAllePersoner(List<Person> allePersoner){
        System.out.println(" PersonFormBackingBean.setAllerPersoner()  "  + allePersoner);
        this.allePersoner = allePersoner;
    }
}
