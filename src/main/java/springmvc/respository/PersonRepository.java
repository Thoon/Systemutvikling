/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.respository;

import java.util.Date;
import java.util.List;
import springmvc.domene.Person;

public interface PersonRepository {
     
    public Person getPerson(String personNr);

    public List<Person> getEveryone() ;

    public boolean registerPerson(Person p) ;

    public boolean updatePerson(Person p) ;

    public boolean deletePerson(Person p) ;
    
    public boolean updatePassword(Person p) ;
}
