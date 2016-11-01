package springmvc.respository;

import java.sql.Connection;
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
    
    private final String sqlInsertPerson = "insert into person (firstName, lastname, password, email, phone, permissions) values(?,?,?,?,?,?)";
    private final String sqlUpdatePerson = "update person set firstName=?, lastname = ?, password = ?, phoneNumber = ?, permission = ? where email = ?";

    
    private DataSource dataSource;
    JdbcTemplate jdbcTemplateObject;
    
    public PersonDatabaseJdbcTemplateRepositoryImpl() {}
    
    @Autowired
    public void setDataSource(DataSource dataSource){
        System.out.println(" Database.setDataSource " + dataSource);
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }
    
    public Person getPerson(String personnr ){
        return (Person)jdbcTemplateObject.queryForObject(sqlSelectPerson, new Object[]{personnr}, new PersonMapper());
    }
    
    public List<Person> getEveryone(){
        return jdbcTemplateObject.query(sqlSelectEveryone, new PersonMapper());
    }

    public boolean deletePerson(Person person) {
        jdbcTemplateObject.update(sqlDeletePerson, person.getEmail() );
        return true;
    }
    
    public boolean updatePerson(Person person){
        jdbcTemplateObject.update(sqlUpdatePerson, new Object[]{
            person.getFirstName(),
            person.getSurname(),
            person.getEmail()
        });
        return true;
    }
    
    public boolean registerPerson(Person person){
        jdbcTemplateObject.update(sqlInsertPerson, 
            new Object[]{
                person.getFirstName(), 
                person.getSurname(),
                person.getPassword(),
                person.getEmail(),
                person.getPhoneNumber(),
                person.getPermission()
        });
        return true;
    }
}
