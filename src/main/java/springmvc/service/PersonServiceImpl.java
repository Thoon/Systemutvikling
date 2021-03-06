package springmvc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.Person;
import springmvc.domene.Password;
import static springmvc.domene.Password.*;
import springmvc.domene.Email;
import springmvc.respository.PersonRepository;

/**
 * Implementation of PersonService
 * @author ntnu
 */
public class PersonServiceImpl implements PersonService{
     
    private PersonRepository repo;

     @Autowired
     public void setRepository(PersonRepository repo){
         this.repo = repo;
     }
   
    @Override
    public Person getPerson(String email){
        return repo.getPerson(email);
    }
     
    @Override
    public List<Person> getEveryone(){
        return repo.getEveryone();
    }
    
    @Override
    public boolean updatePersons(List<Person> personList){
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
        return repo.registerPerson(p);
    }
    
    @Override
    public boolean deletePersons(List<Person> personList){
        if (personList == null || personList.isEmpty()) return true;
               
        boolean isDeleteOK = true;
        for (Person p : personList){
            if (!repo.deletePerson(p)) isDeleteOK = false;
        }
        return isDeleteOK;
    }
    
    @Override
    public boolean updatePerson(Person p){
        return repo.updatePerson(p);
    }
    
    @Override
    public boolean updatePassword(Person p) {
        return repo.updatePassword(p);
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
            if (!Password.checkPassword(p.getPassword(), personToCheck.getPassword())) {
                return 3;
            }
            return 5;
        } catch (Exception e) {
            return 4;
        }

    }
    
    @Override
    public int checkNewUser(Person p) {
        // 0: ingen email skrevet
        // 1: Email eksisterer ikke
        // 2: Personen er allerede aktiv
        // 3: Godkjent
        if (p.getEmail() == "") {
            return 0;
        }
        try {
            Person personToCheck = repo.getPerson(p.getEmail());
            if (personToCheck.isIsActive()) {
                Email email = new Email();
                String newPassword = getNewPassword();
                email.createAndSendEmail(personToCheck.getEmail(), "Nytt passord til SmartCylinders", "Ditt nye passord er: " + newPassword + "\n\n "
                        + "Du vil få muligheten til å bytte passord på innstillinger");
                personToCheck.setPassword(Password.hashPassword(newPassword));
                updatePassword(personToCheck);
                return 2;
            } else {

                Email email = new Email();
                String newPassword = getNewPassword();
                email.createAndSendEmail(personToCheck.getEmail(), "Aktivering av bruker hos SmartCylinders", 
                        "Gratulerer med aktivert bruker hos SmartCylinders.\n\nDin epost: " + personToCheck.getEmail() + 
                        "\nDitt passord: " + newPassword + "\n\n* Du vil få muligheten til å bytte passord på innstillinger.");
                personToCheck.setIsActive(true);
                personToCheck.setPassword(Password.hashPassword(newPassword));
                updatePassword(personToCheck);
                return 3;
            }
        } catch (Exception e) {
            return 1;
        }
    }
    
    @Override
    public void changePassword(String pass, String email) {
        Person pers = repo.getPerson(email);
        pers.setPassword(Password.hashPassword(pass));
        repo.updatePassword(pers);
    }
    
    @Override
    public int getPermission(String email) {
        return repo.getPermission(email);
    }
}
