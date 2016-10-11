package springmvc.service;

import java.util.List;
import springmvc.domene.Person;

/**
 *
 * @author tomash
 */
public interface PersonService {
        
    public List<Person> getAllePersoner();
    public Person getPerson(String personNr);
    
    public boolean oppdaterPerson(Person p);
    public boolean oppdaterPersoner(List<Person> personListe);
    
    public boolean registrerPerson(Person p);
    
    public boolean slettPersoner(List<Person> personListe);
    
}
