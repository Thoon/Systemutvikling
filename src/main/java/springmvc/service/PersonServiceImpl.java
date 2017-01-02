package springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.Person;
import springmvc.respository.PersonRepository;

//@Service
public class PersonServiceImpl implements PersonService{
     
    private PersonRepository repo;

     @Autowired
     public void setRepository(PersonRepository repo){
         System.out.println("PersonServiceImpl.setDatabase2   " + repo);
         this.repo = repo;
     }
   
    @Override
    public Person getPerson(String personNr){
        System.out.println("**** PersonServiceImpl.getPerson()  *** ");
        return repo.getPerson(personNr);
    }
     
    @Override
    public List<Person> getEveryone(){
        System.out.println("**** PersonServiceImpl.getEveryone()  *** ");
        return repo.getEveryone();
    }
    
    @Override
    public boolean updatePersons(List<Person> personList){
        System.out.println("**** PersonServiceImpl.updatePersons()  *** ");
        if (personList == null || personList.size() == 0){
            return true;
        }
            
        boolean isUpdateOK = true;
        for (Person p : personList){
            if (!repo.updatePerson(p)) isUpdateOK=false;
        }
        return isUpdateOK;
    }
    
    @Override
    public boolean registerPerson(Person p){
        System.out.println("**** PersonServiceImpl.registerPerson()  *** ");
        return repo.registerPerson(p);
    }
    
    @Override
    public boolean deletePersons(List<Person> personList){
        System.out.println("**** PersonServiceImpl.deletePersons()  *** ");
        if (personList == null || personList.isEmpty()) return true;
               
        boolean isDeleteOK = true;
        for (Person p : personList){
            if (!repo.deletePerson(p)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    
    @Override
    public boolean updatePerson(Person p){
        System.out.println("**** PersonServiceImpl.updatePerson()  *** ");
        return repo.updatePerson(p);
    }
    @Override
    public int checkLogin(Person p) {
        // 0: begge er tomme felter
        // 1: Epost er tom
        // 2: passord er tom
        // 3: passord samsvarer ikke
        // 4: Epost er ikke registrert
        // 5: Godkjent

        if (p.getEmail() == "" && p.getPassword() == "") {
            return 0;
        } else if (p.getEmail() == "") {
            return 1;
        } else if (p.getPassword() == "") {
            return 2;
        }

        try {
            Person personToCheck = repo.getPerson(p.getEmail());
            if (!Passord.checkPassword(p.getPassord(), personToCheck.getPassord())) {
                return 3;
            }
            return 5;
        } catch (Exception e) {
            return 4;
        }

    }
}
