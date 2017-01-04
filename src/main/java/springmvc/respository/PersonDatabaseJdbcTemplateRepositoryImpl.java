package springmvc.respository;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import springmvc.domene.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tomash
 * Klasser for å hente/lagre/slette personer fra DB.
 */

public class PersonDatabaseJdbcTemplateRepositoryImpl implements PersonRepository{
    
    private Connection connection;
    private final String sqlDeletePerson = "DELETE FROM person WHERE email = ?";
    private final String sqlSelectPerson = "SELECT * FROM person WHERE email = ?";
    private final String sqlSelectEveryone = "SELECT * FROM person";
    
    private final String sqlInsertPerson = "INSERT INTO person (firstName, lastname, password, email, phone, permissions, active) VALUES(?,?,?,?,?,?,?)";
    private final String sqlUpdatePerson = "UPDATE person SET firstName=?, lastname = ?, password = ?, phone = ?, permissions = ? WHERE email = ?";

    private final String sqlUpdatePersonPassword = "UPDATE person SET password=?, active=? WHERE email = ?";
    private final String sqlInsertForgottenPassword = "INSERT INTO forgottenPassword (token, email, date) VALUES(?,?,?)";
    private final String sqlSelectForgotPassword = "SELECT epost FROM forgottenPassword WHERE token = ?";
    private final String sqlDeleteForgotPassword = "DELETE FROM forgottenPassword WHERE token = ?";

    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public PersonDatabaseJdbcTemplateRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    

    public Person getPerson(String email ){
        return (Person)jdbcTemplateObject.queryForObject(sqlSelectPerson, new Object[]{email}, new PersonMapper());

    }
    
    @Override
    public List<Person> getEveryone(){
        return jdbcTemplateObject.query(sqlSelectEveryone, new PersonMapper());
    }

    @Override
    public boolean deletePerson(Person person) {
        jdbcTemplateObject.update(sqlDeletePerson, person.getEmail() );
        return true;
    }
    
    @Override
    public boolean updatePerson(Person person){
        System.out.println("** Repository ** " + person);
        jdbcTemplateObject.update(sqlUpdatePerson, new Object[]{
            person.getFirstName(),
            person.getLastName(),
            person.getPassword(),
            person.getPhoneNumber(),
            person.getPermission(),
            person.getEmail()
        });
        return true;
    }
    
    @Override
    public boolean registerPerson(Person person){
        jdbcTemplateObject.update(sqlInsertPerson, 
            new Object[]{
                person.getFirstName(), 
                person.getLastName(),
                person.getPassword(),
                person.getEmail(),
                person.getPhoneNumber(),
                person.getPermission(),
                person.isIsActive()
        });
        return true;
    }
    
    @Override
    public boolean updatePassword(Person person){
        jdbcTemplateObject.update(sqlUpdatePersonPassword, new Object[]{
            person.getPassword(),
            person.isIsActive(),
            person.getEmail()
        });
        return true;
    }
    
    @Override
     public boolean forgotPassword(String token, String email, Date stopdate){
        jdbcTemplateObject.update(sqlInsertForgottenPassword, 
            new Object[]{
                token,
                email, 
                stopdate
        });
        return true;
    }
     
    @Override
    public boolean checkForgotPassword(String token) {
       try{
           jdbcTemplateObject.queryForObject(sqlSelectForgotPassword, new Object[]{token}, String.class);
           return true;
       }catch(Exception e){
           return false;
       }
    }
    
    @Override
    public String tokenForgotPasswordEmail(String token){
        try{
            return jdbcTemplateObject.queryForObject(sqlSelectForgotPassword, new Object[]{token}, String.class);
        }catch(Exception e){
            return "";
        }
    }
    
    @Override
    public void deleteForgotPassword(String token){
          jdbcTemplateObject.update(sqlDeleteForgotPassword, token );
    }
}
