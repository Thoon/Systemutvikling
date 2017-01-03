package springmvc.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domene.Person;
import springmvc.domene.Password;
import static springmvc.domene.Password.*;
import springmvc.domene.Email;
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
    public boolean updatePassword(Person p) {
        System.out.println(p);
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
            if (Password.checkPassword(p.getPassword(), personToCheck.getPassword())) {
                System.out.println(p.getPassword()+ " " + personToCheck.getPassword());
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
                return 2;
            } else {

                Email email = new Email();
                String newPassword = getNewPassword();
                email.createAndSendEmail(personToCheck.getEmail(), "Aktivering av bruker hos StudyEasy", 
                        "Gratulerer med aktivert bruker hos StudyEasy.\n\nDin epost: " + personToCheck.getEmail() + 
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
    public int sendForgotPasswordInstructions(Person p) {
        // 0: ingen email skrevet
        // 1: Email eksisterer ikke
        // 2: Godkjent
        if (p.getEmail() == "") {
            return 0;
        }
        try {
            repo.getPerson(p.getEmail());
            Email email = new Email();
            String token = getNewToken();
            Date avsluttDato = new Date();
            repo.forgotPassword(token, p.getEmail(), avsluttDato);
            email.createAndSendEmail(p.getEmail(), "Glemt passord hos SmartCylinders", "Følg disse instruksjonene for å endre ditt passord hos SmartCylinders.<br><br>Følg denne linken: www.testtesttest.com/" + token + "<br>Skriv inn det nye passordet.");
            return 2;
        } catch (Exception e) {
            return 1;
        }
    }
    
}
