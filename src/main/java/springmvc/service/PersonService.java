package springmvc.service;

import java.util.List;
import springmvc.domene.Person;

/**
 * Interface for PersonService
 * @author ntnu
 */

public interface PersonService {
        
    public List<Person> getEveryone();
    
    public Person getPerson(String personNr);
    
    public boolean updatePerson(Person p);
    
    public boolean updatePersons(List<Person> personList);
    
    public boolean registerPerson(Person p);
    
    public boolean deletePersons(List<Person> personList);
    
    public int checkLogin(Person p);
    
    public int checkNewUser(Person p);
    
    public boolean updatePassword(Person p);
    
    public void changePassword(String p, String email);
    
    public int getPermission(String email);
}
