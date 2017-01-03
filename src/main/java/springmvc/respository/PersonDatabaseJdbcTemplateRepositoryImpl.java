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
    private final String sqlDeletePerson = "Delete from person where email = ?";
    private final String sqlSelectPerson = "Select * from person where email = ?";
    private final String sqlSelectEveryone = "Select * from person";
    
    private final String sqlInsertPerson = "insert into person (firstName, lastname, password, email, phone, permissions, active) values(?,?,?,?,?,?,?)";
    private final String sqlUpdatePerson = "update person set firstName=?, lastname = ?, password = ?, phone = ?, permissions = ? where email = ?";

    private final String sqlUpdatePersonPassword = "update person set password=?, active=? where email = ?";
    private final String sqlInsertForgottenPassword = "insert into forgottenPassword (token, email, date) values(?,?,?)";

    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public PersonDatabaseJdbcTemplateRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    @Override
    public Person getPerson(String personnr ){
        return (Person)jdbcTemplateObject.queryForObject(sqlSelectPerson, new Object[]{personnr}, new PersonMapper());
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
}
